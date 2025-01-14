package com.example.PollSysProject.PollService.PollSysProject.PollService.Poll_Service_Entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Map;

@Entity
public class Poll {
    @Id
    private Long id;
    private String question;

    @ElementCollection
    private List<String> options;

    @ElementCollection
    private Map<Long, String> responses;


    public Poll() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Map<Long, String> getResponses() {
        return responses;
    }

    public void setResponses(Map<Long, String> responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        return "PollService{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", options=" + options +
                ", responses=" + responses +
                '}';
    }
}


