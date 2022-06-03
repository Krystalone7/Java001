package com.artyom.repositories;


import com.artyom.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional
    void deleteCategoryById(Long id);
}
