// src/main/java/no/hvl/PollApp/VoteController.java
package no.hvl.PollApp.controllers;

import no.hvl.PollApp.domain.*;
import no.hvl.PollApp.component.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/votes")
public class VoteController {
    private final PollManager mgr;
    public VoteController(PollManager mgr) { this.mgr = mgr; }

    @GetMapping
    public Collection<Vote> list() { return mgr.listVotes(); }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> get(@PathVariable long id) {
        return mgr.getVote(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vote cast(@RequestParam long userId, @RequestParam long optionId, @RequestBody Vote v) {
        return mgr.castVote(userId, optionId, v);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vote> change(@PathVariable long id, @RequestParam long newOptionId) {
        return mgr.updateVote(id, newOptionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return mgr.deleteVote(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
