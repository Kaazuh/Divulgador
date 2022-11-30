package com.example.divulgador.model;

import javax.persistence.*;

@Entity
@Table(name = "evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "palestrante", length = 255, nullable = false)
    private String palestrante;

    @Column(name = "descricao", length = 400, nullable = false)
    private String descricao;

    @Column(name = "local_evento", length = 255, nullable = false)
    private String local_evento;

    @Column(name = "carga_horaria", nullable = false)
    private int carga_horaria;

    @Column(name = "data", length = 10, nullable = false)
    private String data;

    @Column(name = "hora", length = 5, nullable = false)
    private String hora;

    @Column(name = "codigo_checkin", length = 20, nullable = false)
    private String codigo_checkin;

    @Column(name = "capacidade_maxima", nullable = false)
    private int capacidade_maxima;

    @Column(name = "curso_fk", nullable = false)
    private int curso_fk;

    @Column(name = "organizador", length = 255, nullable = false)
    private String organizador;

    @Column(name = "file_name", length = 200, nullable = true)
    private String file_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(String palestrante) {
        this.palestrante = palestrante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal_evento() {
        return local_evento;
    }

    public void setLocal_evento(String local_evento) {
        this.local_evento = local_evento;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public String getCodigo_checkin() {
        return codigo_checkin;
    }

    public void setCodigo_checkin(String codigo_checkin) {
        this.codigo_checkin = codigo_checkin;
    }

    public int getCapacidade_maxima() {
        return capacidade_maxima;
    }

    public void setCapacidade_maxima(int capacidade_maxima) {
        this.capacidade_maxima = capacidade_maxima;
    }

    public int getCurso_fk() {
        return curso_fk;
    }

    public void setCurso_fk(int curso_fk) {
        this.curso_fk = curso_fk;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

}