package net.uun.java.planner.service;

import com.speedment.jpastreamer.application.JPAStreamer;
import net.uun.java.planner.entity.AuthToken;
import net.uun.java.planner.entity.AuthToken$;
import net.uun.java.planner.entity.User;
import net.uun.java.planner.repository.IAuthTokenRepository;
import net.uun.java.planner.repository.IUserRepository;
import net.uun.java.planner.requests.UserRequest;
import net.uun.java.planner.utils.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    public JPAStreamer jpaStreamer;

    @Autowired
    public IUserRepository userRepository;
    @Autowired
    public IAuthTokenRepository authRepository;

    public Optional<User> getById(int id) {
        System.out.println(id);
        return userRepository.findById(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> editById(int id, UserRequest model) {
        var user = getById(id);
        user.ifPresent(u -> setDataFromModelAndSave(u, model));
        return user;
    }

    private void setDataFromModelAndSave(User u, UserRequest model) {
        u.setName(model.name);
        userRepository.save(u);
    }

    public User create(UserRequest model) {
        var entity = new User();
        setDataFromModelAndSave(entity, model);
        return entity;
    }

    public void deleteById(int id) throws EntityNotFound {
        if (!userRepository.existsById(id))
            throw new EntityNotFound();

        var tokenIds = jpaStreamer.stream(AuthToken.class)
                .filter(AuthToken$.userId.equal(id))
                .map(AuthToken::getToken)
                .toList();
        authRepository.deleteAllById(tokenIds);

        userRepository.deleteById(id);

    }
}
