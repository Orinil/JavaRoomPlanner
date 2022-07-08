package net.uun.java.planner.requests;

import net.uun.java.planner.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRequest {

    @Size(max = User.MAX_NAME_LENGTH)
    @NotEmpty
    public String name;
}
