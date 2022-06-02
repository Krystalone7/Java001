package com.artyom.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "questions")
    private List<Game> games;
}