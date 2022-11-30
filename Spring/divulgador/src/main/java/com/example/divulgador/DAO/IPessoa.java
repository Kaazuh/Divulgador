package com.example.divulgador.DAO;

import com.example.divulgador.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


//Interface criada para puxar todos os métodos contidos no CrudRepository
public interface IPessoa extends CrudRepository<Pessoa, Integer> {

    Optional<Pessoa> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}