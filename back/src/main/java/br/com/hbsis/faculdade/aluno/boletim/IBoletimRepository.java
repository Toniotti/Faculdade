package br.com.hbsis.faculdade.aluno.boletim;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoletimRepository extends MongoRepository<Boletim, Long> {

}
