package net.uun.java.planner.controller.utils;

import javax.servlet.ServletException;

public class NotAuthenticated extends ServletException {
    public NotAuthenticated() {
        super("Not Authenticated");
    }
}
