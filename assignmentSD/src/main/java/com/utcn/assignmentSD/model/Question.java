package com.utcn.assignmentSD.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
@JsonIgnoreProperties({"votes"})
public class Question {

    @Id
    @Column (name = "question_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (name = "title")
    private String title;
    @Column (name = "body")
    private String body;
    @Column (name = "creation_date")
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "tag_link",
            joinColumns = {
                    @JoinColumn(name = "question_id", referencedColumnName = "question_id")},
            //prima ii numele din tabelu de legatura, a doua e din tabelu initial => aici questions
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private Set<Tag> tags = new HashSet<>();


    @OneToMany (mappedBy = "questionn")
    private Set<Answer> answers = new HashSet<>();

    @OneToMany (mappedBy = "questionV")
    private Set<VoteQuestion> votes = new HashSet<>();

    public Question() {
    }

    public Question(String title, String body, Date creationDate, User user) {
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
        this.user = user;
    }

    public Question(String title, String body) {
        this.title = title;
        this.body = body;
        this.creationDate = Calendar.getInstance().getTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public int computeVote()
    {
        int sum = 0;
        for( VoteQuestion voteQuestion: votes)
        {
            sum += voteQuestion.getVote();
        }
        return sum;
    }
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag)
    {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", creationDate=" + creationDate +
                ", user=" + user.getName() +
                '}' + "\n";
    }

    public void deleteAnswer(Answer answer) {
        this.answers.remove(answer);
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void addVote(VoteQuestion voteQuestion) {
        this.votes.add(voteQuestion);
    }

    public Set<VoteQuestion> getVotes() {
        return votes;
    }
}
