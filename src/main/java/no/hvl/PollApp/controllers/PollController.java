package no.hvl.PollApp.controllers;

import no.hvl.PollApp.messaging.VotePublisher;
import no.hvl.PollApp.event.VoteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class PollController {
    @Autowired
    private VotePublisher votePublisher;

    @PostMapping("/vote")
    public String publishTest(@RequestParam String pollId,
                              @RequestParam String optionId,
                              @RequestParam(required = false) String voterId) {
        votePublisher.publishVote(pollId, optionId, voterId);
        return "published";
    }
}
