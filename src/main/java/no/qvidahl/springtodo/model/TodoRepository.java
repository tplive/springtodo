package no.qvidahl.springtodo.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo, String> {

    public List<Todo> findAllByOrderBySortIdxAsc();

    //public List<Todo> findAllByOrderByStartAsc();
    //public List<Todo> findAllByOrOrderByStartDesc();
}
