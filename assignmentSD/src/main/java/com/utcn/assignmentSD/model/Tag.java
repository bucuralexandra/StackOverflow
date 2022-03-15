package com.utcn.assignmentSD.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Question> questions = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public void addQuestion(Question question)
    {
        this.questions.add(question);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
