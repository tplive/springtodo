package no.qvidahl.springtodo.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrukerRepository extends MongoRepository<Bruker, String> {


    public Bruker findByUsername(String username);

}
