package com.utcn.assignmentSD.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="votes_q")
@JsonIgnoreProperties({"questionV"})
public class VoteQuestion {

    @Id
    @Column(name="voteq_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="vote")
    private Integer vote;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User userQ;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question questionV;

    public VoteQuestion() {
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    public User getUserQ() {
        return userQ;
    }

    public void setUserQ(User userQ) {
        this.userQ = userQ;
    }

    public Question getQuestion() {
        return questionV;
    }

    public void setQuestion(Question question) {
        this.questionV = question;
    }

    public VoteQuestion(Integer vote) {
        this.vote = vote;
    }
}
