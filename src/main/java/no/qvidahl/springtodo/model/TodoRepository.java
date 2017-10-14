package no.qvidahl.springtodo.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {

    public Todo findTodoByText(String text);
    public List<Todo> findAllByText(String text);
}
