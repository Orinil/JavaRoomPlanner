package net.uun.java.planner.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginData {
    @NotNull @NotEmpty
    public String username;
    @NotNull @NotEmpty
    public String password;
}

