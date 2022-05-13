package com.artyom.services;

import com.artyom.entities.Question;
import com.artyom.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question getRandomQuestion(){
        Long rand = (long) (Math.random() * repository.count());
        return repository.getById(rand);
    }

    public Question getQuestionById(Long id){
        return repository.findQuestionById(id);
    }
}
