package com.example.divulgador.controller;

import com.example.divulgador.ConexaoBanco.ConexaoMySQL;
import com.example.divulgador.DAO.IPessoa;
import com.example.divulgador.model.Pessoa;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
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

    // Não será necessário deletar alguma pessoa pelo site, o delete por ser feito diretamente no banco
    @DeleteMapping("/deletar/{cpf}")
    @Transactional
    public Optional<Pessoa> excluirPessoa (@PathVariable String cpf){ // Método para excluir uma pessoa
        Optional<Pessoa> pessoa = dao.findByCpf(cpf);
        dao.deleteByCpf(cpf);
        return pessoa;
    }

    @PostMapping("/login") // Método para realizar o login
    public String login (@RequestBody Pessoa pessoa) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = null;
        Connection conexao = ConexaoMySQL.getConexaoMySQL();

        // Comando SQL para busca de cpf e senha compatíveis
        sql = "SELECT * FROM pessoa WHERE email = ? and senha = md5(?)";
        try {
            // Prepara a consulta ao banco em função do que foi recebido. O "?" é substituído pelo conteúdo das variáveis
            pst = conexao.prepareStatement(sql);
            pst.setString(1, pessoa.getEmail());
            pst.setString(2, pessoa.getSenha());
            rs = pst.executeQuery(); // Executa Query

            if(rs.next()) { // Retorna um boolean. Se for verdadeiro, significa que há uma linha no banco com cpf e senha compatíveis.
                return "Logado com sucesso!";
            } else {
                return "Login e/ou senha incorreto(s)";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
