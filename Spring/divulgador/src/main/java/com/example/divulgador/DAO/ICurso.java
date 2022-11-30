package com.example.divulgador.DAO;

import com.example.divulgador.model.Curso;
import org.springframework.data.repository.CrudRepository;


//Interface criada para puxar todos os métodos contidos no CrudRepository
public interface ICurso extends CrudRepository<Curso, Integer> {


}