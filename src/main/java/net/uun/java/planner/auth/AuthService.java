package net.uun.java.planner.auth;

import com.speedment.jpastreamer.application.JPAStreamer;
import net.uun.java.planner.controller.utils.NotAuthenticated;
import net.uun.java.planner.entity.AuthToken;
import net.uun.java.planner.entity.AuthToken$;
import net.uun.java.planner.entity.User;
import net.uun.java.planner.entity.User$;
import net.uun.java.planner.repository.IAuthTokenRepository;
import net.uun.java.planner.repository.IRoomRepository;
import net.uun.java.planner.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

  private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
  private final Map<UUID, AuthenticationData> ids = new HashMap<>();
  private final Map<String, String> users = new HashMap<>();

  @Autowired
  private JPAStreamer jpaStreamer;

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IAuthTokenRepository authTokenRepository;

  @Autowired
  private IRoomRepository roomRepository;

  @Autowired
  private AuthUtilsService authUtilsService;

  public AuthService() throws UsernameTaken {
    this.register(RegisterData.of("a@b.cz", "Bábovka1234"));
    this.register(RegisterData.of("b@c.cz", "Bábovka12345"));
  }

  public void register(@NotNull RegisterData data) throws UsernameTaken {
    if (users.containsKey(data.username))
      throw new UsernameTaken();

    users.put(data.username, bCrypt.encode(data.password));
  }

  public Optional<UUID> login(@NotNull LoginData data) {

    var user = jpaStreamer.stream(User.class)
      .filter(User$.name.equal(data.username)).findFirst();

    if (user.isEmpty())
      return Optional.empty();

    if (!bCrypt.matches(data.password, user.get().getPassword()))
      return Optional.empty();

    var authToken = jpaStreamer.stream(AuthToken.class)
      .filter(AuthToken$.userId.equal(user.get().getId())).findFirst();

    // create new token
    if(authToken.isEmpty())
    {
      var newAuthToken = new AuthToken();
      newAuthToken.setUserId(user.get().getId());
      newAuthToken.setValidTo(LocalDateTime.now().plusDays(2));
      authTokenRepository.save(newAuthToken);

      return Optional.of(newAuthToken.getToken());
    }

    // refresh token
    authToken.get().setValidTo(LocalDateTime.now().plusDays(2));
    authTokenRepository.save(authToken.get());

    return Optional.of(authToken.get().getToken());

  }

  public Optional<Integer> getAuthorizeUserId() {

    var token = authUtilsService.get();

    var authToken = jpaStreamer.stream(AuthToken.class)
      .filter(AuthToken$.token.equal(token.get())).findFirst();

    if (authToken.isEmpty())
      return Optional.empty();

    return Optional.of(authToken.get().getUserId());
  }

  public boolean CanReserveRoom(int roomId) {

    try {
      var userId = this.getAuthorizeUserId().get();
      var rolesIds = userRepository.getRoleIds(userId);
      var roomRoleIds = roomRepository.getRoleIds(roomId);

      if(roomRoleIds.isEmpty())
        return true;

      for (var roleId : rolesIds) {
        if (roomRoleIds.contains(roomId)) {
          return true;
        }
      }
    }catch(Exception e){
      return false;
    }

    return false;
  }

  public Optional<AuthToken> getDataByToken(UUID token) {

    var authToken = jpaStreamer.stream(AuthToken.class)
      .filter(AuthToken$.token.equal(token)).filter(AuthToken$.validTo.greaterThan(LocalDateTime.now()))
      .findFirst();

    return authToken;
  }

  public boolean remove(UUID uuid) {
    return this.ids.remove(uuid) != null;
  }
}
