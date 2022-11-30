package com.example.divulgador.controller;

import com.example.divulgador.ConexaoBanco.ConexaoMySQL;
import com.example.divulgador.DAO.IEvento;
import com.example.divulgador.model.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping(value = "/eventos")
public class EventoController {

    @Autowired // Injeção automática de todos os métodos. Desse modo a interface não precisa ser implementada
    private IEvento dao;

    @GetMapping("/lista")
    public List<Evento> listaEventos (){ // Listagem de todos os eventos
        return (List<Evento>) dao.findAll();
    }

    @PostMapping("/criar")
    public Evento criarEvento (@RequestBody Evento evento){ // Método para a criação de um evento
        return dao.save(evento);
    }

    @GetMapping("/buscar/{id}")
    public Optional<Evento> listaEventoUnico(@PathVariable (value = "id") Integer id){ // Método para buscar 1 único evento
        return dao.findById(id);
    }

    @PutMapping("/editar")
    public Evento editarEvento (@RequestBody Evento evento) { // Método para a edição de um evento
        return dao.save(evento);
    }

    @DeleteMapping("/deletar/{id}")
    public Optional<Evento> excluirEvento (@PathVariable Integer id){ // Método para excluir um evento
        Optional<Evento> evento = dao.findById(id);
        dao.deleteById(id);
        return evento;
    }

    @GetMapping("/buscarCurso/{id}") // Método para buscar o nome do curso e apresentar em evento
    public String buscarCurso (@PathVariable (value = "id") Integer id){
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        Connection conexao = ConexaoMySQL.getConexaoMySQL(); // Conexão com o banco de dados

        sql = "SELECT descricao FROM curso WHERE id = " + id;

        try {
            pst = conexao.prepareStatement(sql); // Prepara o comando SQL
            rs = pst.executeQuery(); // Executa a query
            if(rs.next()) { // Se o comando SQL funcionar, retorna verdadeiro
                return rs.getString(1); // Seleciona o conteúdo da primeira (e única) coluna
            } else {
                return "Curso não encontrado";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // Tratamento de exceções
        }
    }

    // Fazer upload de fotos para pasta local
//    public ResponseEntity<Evento> uploadFotos(@RequestParam("foto") MultipartFile foto) {
//        try {
//
//            return null;
//        } catch (Exception e) {
//            return null;
//        }
//    }
}
