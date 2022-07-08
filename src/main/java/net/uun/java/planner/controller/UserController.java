package net.uun.java.planner.controller;

import net.uun.java.planner.auth.Authenticated;
import net.uun.java.planner.entity.User;
import net.uun.java.planner.requests.UserRequest;
import net.uun.java.planner.service.UserService;
import net.uun.java.planner.utils.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
@Authenticated
public class UserController {

    @Autowired
    UserService service;

    @Transactional
    @Authenticated
    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @Transactional
    @GetMapping()
    public List<User> getAll() {
        return service.getAll();
    }

    @Transactional
    @PostMapping()
    public User create(@Valid @RequestBody UserRequest model) {
        return service.create(model);
    }

    @Transactional
    @PutMapping("/{id}")
    public Optional<User> editById(@PathVariable int id, @Valid @RequestBody UserRequest model) {
        return service.editById(id, model);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) throws EntityNotFound {
        service.deleteById(id);
    }
}
