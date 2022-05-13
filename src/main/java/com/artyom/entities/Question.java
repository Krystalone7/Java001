package com.artyom.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(schema = "quiz", name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "question_text")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "answer")
    private String answer;

    public Question() {
    }

    public Question(String questionText, Integer difficulty, String answer) {
        this.questionText = questionText;
        this.difficulty = difficulty;
        this.answer = answer;
    }
}
