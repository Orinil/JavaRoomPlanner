package net.uun.java.planner.auth;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthUtilsService {
    private static final String HEADER_AUTH = "Authorization";

    public Optional<UUID> get() {
      var token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(HEADER_AUTH);
      return tryParseUUID(token);
    }

    public static Optional<UUID> tryParseUUID(@NotNull String value) {
        try {
            return Optional.of(UUID.fromString(value));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
