package com.example.divulgador.DAO;

import com.example.divulgador.model.Evento;
import org.springframework.data.repository.CrudRepository;

//Interface criada para puxar todos os métodos contidos no CrudRepository
public interface IEvento extends CrudRepository<Evento, Integer> {


}
