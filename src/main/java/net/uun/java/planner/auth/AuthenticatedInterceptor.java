package net.uun.java.planner.auth;

import net.uun.java.planner.controller.utils.NotAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;
import java.util.Optional;
import java.util.UUID;

@Component
public class AuthenticatedInterceptor implements HandlerInterceptor {

    @Autowired
    AuthService service;

    @Autowired
    AuthUtilsService utils;

    public AuthenticatedInterceptor() {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(Authenticated.class)) {
            Optional<UUID> token = utils.get();
            if (token.isEmpty() || service.getDataByToken(token.get()).isEmpty())
                throw new NotAuthenticated();
        }
        return true;
    }
}
