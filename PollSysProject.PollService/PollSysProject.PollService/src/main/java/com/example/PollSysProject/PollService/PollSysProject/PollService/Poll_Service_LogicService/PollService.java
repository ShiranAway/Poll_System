package com.example.PollSysProject.PollService.PollSysProject.PollService.Poll_Service_LogicService;

import com.example.PollSysProject.PollService.PollSysProject.PollService.Poll_Service_Entity.Poll;
import com.example.PollSysProject.PollService.PollSysProject.PollService.Poll_Service_Repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;


    private Long nextId = 1L;


    private synchronized Long copiedResponseById() {
        return nextId++;

    }


    public String createPoll(Poll poll) {
        try {

            if (poll.getQuestion() == null || poll.getOptions() == null || poll.getOptions().size() != 4) {
                return "Error: Poll must have a question and exactly 4 options";
            }


            poll.setId(copiedResponseById());
            pollRepository.save(poll);
            return "Poll created successfully with ID: " + poll.getId();
        } catch (Exception e) {
            return "Error while creating poll: " + e.getMessage();
        }
    }


    public Poll getPollById(Long id) {
        try {
            return pollRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }


    public List<Poll> getAllPolls() {
        try {
            return pollRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }


    public String recordResponse(Long pollId, Long userId, String choice) {
        try {
            Poll poll = pollRepository.findById(pollId).orElse(null);
            if (poll == null) {
                return "Poll not found";
            }
            if (!poll.getOptions().contains(choice)) {
                return "Invalid choice";
            }
            poll.getResponses().put(userId, choice);
            pollRepository.save(poll);
            return "Response recorded successfully";
        } catch (Exception e) {
            return "Error while recording response: " + e.getMessage();
        }
    }


    public Map<String, Long> getPollStatistics(Long pollId) {
        try {
            Poll poll = pollRepository.findById(pollId).orElse(null);
            if (poll == null) {
                return null;
            }
            return poll.getResponses().values().stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        } catch (Exception e) {
            return null;
        }
    }

    public String deletePoll(Long id) {
        try {
            if (!pollRepository.existsById(id)) {
                return "Poll not found";
            }
            pollRepository.deleteById(id);
            return "Poll deleted successfully";
        } catch (Exception e) {
            return "Error while deleting poll: " + e.getMessage();
        }
    }

    public void setPollRepository(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }
}