package br.com.alura.leilao.dao.util.builder;

import br.com.alura.leilao.model.Usuario;

import javax.persistence.EntityManager;
import java.time.LocalDate;

public class UsuarioBuilder {
    private String nome;
    private String email;
    private String senha;

    public UsuarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }


    public UsuarioBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public Usuario cria(EntityManager em) {
        Usuario usuario = new Usuario(nome,email,senha);
        em.persist(usuario);
        return usuario;
    }
}
