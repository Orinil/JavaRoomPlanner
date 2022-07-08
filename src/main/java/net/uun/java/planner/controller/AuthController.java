package net.uun.java.planner.controller;

import net.uun.java.planner.auth.*;
import net.uun.java.planner.controller.utils.NotAuthorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    //API for registering:
    @Transactional
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String register(@Valid @RequestBody RegisterData registerData){
        return "Successfully registered";
    }

    //API for logging out:
    @Transactional
    @RequestMapping(method = RequestMethod.DELETE)
    public String logout(){
       return "Successfully logged out";
    }

    //API for logging in with AUTH:
    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public UUID login(@RequestBody @NotNull LoginData loginData) throws NotAuthorized {
        var token = authService.login(loginData);

        if(token.isEmpty())
          throw new NotAuthorized();

        return token.get();
    }
}
