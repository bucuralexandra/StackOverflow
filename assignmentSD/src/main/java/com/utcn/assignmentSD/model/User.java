package com.utcn.assignmentSD.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="users")
@JsonIgnoreProperties({"questions", "answers"})
public class User {

    @Id
    @Column(name ="user_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;


    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    @OneToMany (mappedBy = "userr")
    private Set<Answer> answers = new HashSet<>();

    @OneToMany (mappedBy = "userQ", cascade = CascadeType.ALL)
    private Set<VoteQuestion> votesQ = new HashSet<>();

    @OneToMany (mappedBy = "userA", cascade = CascadeType.ALL)
    private Set<VoteAnswer> votesA= new HashSet<>();


    public User( int id, String name, String username, String password)
    {
        this.id = id;
        this.name=name;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //@JsonBackReference
    public Set<Question> getQuestions() {
        return questions;
    }

    @JsonBackReference
    public Set<Answer> getAnswers() {
        return answers;
    }
    public void addQuestion(Question q)
    {
        this.questions.add(q);
    }

    public void deleteQuestion(Question q) {
        this.questions.remove(q);
    }

    public void deleteAnswer(Answer answer) {
        this.answers.remove(answer);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }


    public void addVoteQ(VoteQuestion voteQuestion) {
        this.votesQ.add(voteQuestion);
    }

    public void addVoteA(VoteAnswer voteAnswer) {
        this.votesA.add(voteAnswer);
    }
}
