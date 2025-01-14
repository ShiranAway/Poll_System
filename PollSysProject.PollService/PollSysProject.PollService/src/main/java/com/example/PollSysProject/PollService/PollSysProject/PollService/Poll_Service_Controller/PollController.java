package com.example.PollSysProject.PollService.PollSysProject.PollService.Poll_Service_Controller;


import com.example.PollSysProject.PollService.PollSysProject.PollService.Poll_Service_Entity.Poll;
import com.example.PollSysProject.PollService.PollSysProject.PollService.Poll_Service_LogicService.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping
    public ResponseEntity<String> createPoll(@RequestBody Poll poll) {
        try {
            if (poll.getQuestion() == null || poll.getOptions() == null || poll.getOptions().size() != 4) {
                return ResponseEntity.badRequest().body("Error: must have at least 4 options.");

            }
            return ResponseEntity.ok(pollService.createPoll(poll));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error while creating Poll" + e.getMessage());

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable Long id) {
        Poll poll = pollService.getPollById(id);
        if (poll == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(poll);
    }

    @GetMapping
    public ResponseEntity<List<Poll>> getAllPolls() {
        return ResponseEntity.ok(pollService.getAllPolls());
    }
    @PostMapping("/{pollId}/responses")
    public ResponseEntity<String> recordResponse(
            @PathVariable Long pollId,
            @RequestParam Long userId,
            @RequestParam String choice) {
        try {
            return ResponseEntity.ok(pollService.recordResponse(pollId, userId, choice));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error recording response: " + e.getMessage());
        }
    }

    // Get statistics for a poll
    @GetMapping("/{pollId}/stats")
    public ResponseEntity<java.util.Map<String, Long>> getPollStatistics(@PathVariable Long pollId) {
        Map<String, Long> stats = pollService.getPollStatistics(pollId);
        if (stats == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stats);
    }

    // Delete a poll
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePoll(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pollService.deletePoll(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting poll: " + e.getMessage());
        }
    }


    public void setPollService(PollService pollService) {
        this.pollService = pollService;
    }
}
