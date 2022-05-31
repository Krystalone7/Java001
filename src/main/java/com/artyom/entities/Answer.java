package com.artyom.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers", schema = "quiz")
public class Answer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "question_number")
    private Integer questionNumber;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    public Answer(Long gameId, Integer questionNumber, Long questionId, Boolean isCorrect) {
        this.gameId = gameId;
        this.questionNumber = questionNumber;
        this.questionId = questionId;
        this.isCorrect = isCorrect;
    }
}
