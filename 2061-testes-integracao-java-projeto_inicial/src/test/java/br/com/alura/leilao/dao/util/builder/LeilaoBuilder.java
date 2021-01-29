package br.com.alura.leilao.dao.util.builder;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoBuilder {


    private String nome;
    private BigDecimal valor;
    private Usuario usuario;
    private LocalDate data;

    public LeilaoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LeilaoBuilder comValorInicial(String valor) {
        this.valor = new BigDecimal(valor);
        return this;
    }

    public LeilaoBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public LeilaoBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Leilao criar(EntityManager em) {
        Leilao leilao = new Leilao(nome,valor,data,usuario);
        em.persist(leilao);
        return leilao;
    }
}
