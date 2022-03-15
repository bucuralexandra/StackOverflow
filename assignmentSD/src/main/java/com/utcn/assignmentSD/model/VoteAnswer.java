package com.utcn.assignmentSD.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
@Entity
@Table(name="votes_a")
@JsonIgnoreProperties({"answerV"})
public class VoteAnswer {
    @Id
    @Column(name = "votesa_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vote")
    private Integer vote;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userA;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answerV;

    public VoteAnswer() {
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public User getUserA() {
        return userA;
    }

    public void setUserA(User userA) {
        this.userA = userA;
    }

    public VoteAnswer(Integer vote) {
        this.vote = vote;
    }

    public Answer getAnswerV() {
        return answerV;
    }

    public void setAnswerV(Answer answerV) {
        this.answerV = answerV;
    }
}
