package net.uun.java.planner.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RegisterData {
    @NotNull
    @NotEmpty
    public String username;
    @NotNull
    @NotEmpty
    public String password;

    public static RegisterData of(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password) {
        var data = new RegisterData();
        data.username = username;
        data.password = password;
        return data;
    }
}
