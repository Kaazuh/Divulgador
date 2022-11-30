package com.example.divulgador.controller;

import com.example.divulgador.ConexaoBanco.ConexaoMySQL;
import com.example.divulgador.DAO.IPessoa;
import com.example.divulgador.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {
    @Autowired // Injeção automática de todos os métodos. Desse modo a interface não precisa ser implementada
    private IPessoa dao;

    @GetMapping("/lista")
    public List<Pessoa> listaPessoas(){ // Listagem de todas as pessoas cadastradas
        return (List<Pessoa>) dao.findAll();
    }

    @PostMapping("/criar")
    public Pessoa criarPessoa (@RequestBody Pessoa pessoa) { // Método para a criação de uma pessoa
        return dao.save(pessoa);
    }

    @GetMapping(value = "/buscar/{cpf}")
    public Optional<Pessoa> buscarPessoa(@PathVariable String cpf){ // Método para buscar 1 única pessoa
        if(dao.existsByCpf(cpf)){
            Optional<Pessoa> obj = dao.findByCpf(cpf);
            return ResponseEntity.ok().body(obj).getBody();
        } else{
            return null;
        }

    }

    @PutMapping("/editar")
    public Pessoa editarPessoa (@RequestBody Pessoa pessoa) { // Método para a edição de uma pessoa
        return dao.save(pessoa);
    }

//    @DeleteMapping("/deletar/{cpf}")
//    public Optional<Pessoa> excluirPessoa (@PathVariable String cpf){ // Método para excluir uma pessoa
//        Optional<Pessoa> pessoa = dao.findById(cpf);
//        dao.deleteById(cpf);
//        return pessoa;
//    }

    @GetMapping("/login")
    public String login (@RequestBody Pessoa pessoa) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        Connection conexao = ConexaoMySQL.getConexaoMySQL(); // Conexão com o banco de dados
        // Comando SQL para busca de email e senha compatíveis
        sql = "select * from pessoa where email = ? and senha = md5(?)";


        try {
            pst = conexao.prepareStatement(sql); // Prepara o comando SQL
            rs = pst.executeQuery(); // Executa a query
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e); // Tratamento de exceções
        }
    }
}