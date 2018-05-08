package repositories;

import org.springframework.data.repository.CrudRepository;
import model.Hello;

public interface HelloRepository
extends CrudRepository<Hello, Integer> {}
