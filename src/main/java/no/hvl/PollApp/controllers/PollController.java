/*
// src/main/java/no/hvl/PollApp/PollController.java
package no.hvl.PollApp.controllers;

import no.hvl.PollApp.entities.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/polls")
public class PollController {
    private final PollManager mgr;
    public PollController(PollManager mgr) { this.mgr = mgr; }

    @GetMapping
    public Collection<Poll> list() { return mgr.listPolls(); }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> get(@PathVariable long id) {
        return mgr.getPoll(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Poll create(@RequestParam long creatorId, @RequestBody Poll p) {
        return mgr.createPoll(p, creatorId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poll> update(@PathVariable long id, @RequestBody Poll p) {
        return mgr.updatePoll(id, p).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return mgr.deletePoll(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // nested resource: options
    @PostMapping("/{pollId}/options")
    public VoteOption addOption(@PathVariable long pollId, @RequestBody VoteOption o) {
        return mgr.addOption(pollId, o);
    }
}
*/
