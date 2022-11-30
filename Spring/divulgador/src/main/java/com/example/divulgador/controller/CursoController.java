package com.example.divulgador.controller;

import com.example.divulgador.DAO.ICurso;
import com.example.divulgador.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

    @Autowired // Injeção automática de todos os métodos. Desse modo a interface não precisa ser implementada
    private ICurso dao;

    @GetMapping("/lista")
    public List<Curso> listaCursos(){ // Listagem de todos os cursos
        return (List<Curso>) dao.findAll();
    }

    @PostMapping("/criar")
    public Curso criarCurso (@RequestBody Curso curso) { // Método para a criação de um curso
        return dao.save(curso);
    }

    @GetMapping("/buscar/{id}")
    public Optional<Curso> listaCursoUnico(@PathVariable (value = "id") Integer id){ // Método para buscar 1 único curso
        return dao.findById(id);
    }

    @PutMapping("/editar")
    public Curso editarCurso (@RequestBody Curso curso) { // Método para a edição de um curso
        return dao.save(curso);
    }

    @DeleteMapping("/deletar/{id}")
    public Optional<Curso> excluirCurso (@PathVariable Integer id){ // Método para excluir um curso
        Optional<Curso> curso = dao.findById(id);
        dao.deleteById(id);
        return curso;
    }
}
