package com.example.webdevsummer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer.model.Hello;

public interface HelloRepository
extends CrudRepository<Hello, Integer> {}
