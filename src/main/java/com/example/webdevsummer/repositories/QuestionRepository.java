package com.example.webdevsummer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
