package com.example.webdevsummer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer.model.Module;

public interface ModuleRepository
extends CrudRepository<Module, Integer>{}
