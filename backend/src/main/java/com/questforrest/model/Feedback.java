package com.questforrest.model;

import javax.persistence.*;

/**
 * Created by Ira Zyabkina on 08.10.2016.
 */
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @Column(name = "feedback_rating")
    private double rating;
    @Column(name = "feedback_comment")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
