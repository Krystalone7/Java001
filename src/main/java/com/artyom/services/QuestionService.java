package com.artyom.services;

import com.artyom.dto.CategoryDTO;
import com.artyom.dto.QuestionDTO;
import com.artyom.entities.Category;
import com.artyom.entities.Question;
import com.artyom.repositories.CategoryRepository;
import com.artyom.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository repository;
    private final CategoryRepository categoryRepository;

    public QuestionService(QuestionRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public Question getRandomQuestion(){
        long sk = (long) (Math.random() * repository.count());
        System.out.println(sk);
        Optional<Long> id = repository.findAll().stream()
                .map(Question::getId)
                .skip(sk)
                .findAny();
        System.out.println(repository.count());
        return id.map(repository::getById).orElse(null);
    }

    public Question getQuestionById(Long id){
        return repository.findQuestionById(id);
    }

    public Question saveQuestion(LinkedHashMap<String, Object> map){
        LinkedHashMap<String, Object> categoryMap = (LinkedHashMap)map.get("category");

        Category category = categoryRepository.saveAndFlush(new Category((String)categoryMap.get("title")));

        Question question = new Question(
                Long.valueOf((Integer)map.get("id")),
                (String)map.get("question"),
                category,
                (Integer) map.get("value"),
                (String) map.get("answer"));

        return repository.saveAndFlush(question);
    }
}
