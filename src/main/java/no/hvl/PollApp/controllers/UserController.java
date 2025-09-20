/*
package no.hvl.PollApp.controllers;

import no.hvl.PollApp.entities.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private final PollManager mgr;
    public UserController(PollManager mgr) { this.mgr = mgr; }

    @GetMapping
    public Collection<User> list() { return mgr.listUsers(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable long id) {
        return mgr.getUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User create(@RequestBody User u) { return mgr.createUser(u); }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @RequestBody User u) {
        return mgr.updateUser(id, u).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return mgr.deleteUser(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
*/
