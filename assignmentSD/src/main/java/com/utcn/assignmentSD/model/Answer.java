package com.utcn.assignmentSD.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="answers")
@JsonIgnoreProperties({"questionn" , "votes"})
public class Answer {

    @Id
    @Column(name="answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name="creation_date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userr;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question questionn;

    @OneToMany (mappedBy = "answerV")
    private Set<VoteAnswer> votes = new HashSet<>();


    public Answer() {
    }

    public Answer(String text, Date creationDate) {
        this.text = text;
        this.creationDate = creationDate;
    }

    public Answer(String text, Date creationDate, Question questionn) {
        this.text = text;
        this.creationDate = creationDate;
        this.questionn = questionn;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setUserr(User userr) {
        this.userr = userr;
    }

    //@JsonBackReference
    public Question getQuestionn() {
        return questionn;
    }

    public void setQuestionn(Question questionn) {
        this.questionn = questionn;
    }

    //@JsonBackReference
    public User getUserr() {
        return userr;
    }

    public void addVote(VoteAnswer voteAnswer) {
        this.votes.add(voteAnswer);
    }

    public Set<VoteAnswer> getVotes() {
        return votes;
    }

    public  int computeVotes()
    {
        int sum = 0;
        for(VoteAnswer voteAnswer: votes)
        {
            sum += voteAnswer.getVote();
        }
        return sum;
    }
    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", creationDate=" + creationDate +
                "votes: " + computeVotes() +
                '}';
    }

    public int compareTo(Answer a2) {
        if(this.computeVotes() > a2.computeVotes())
            return 0;
        return -1;
    }
}
