package br.com.alura.leilao.dao;

import br.com.alura.leilao.dao.util.JpaUtil;
import br.com.alura.leilao.dao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.dao.util.builder.UsuarioBuilder;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LanceDaoTest {

    private EntityManager em;
    private LanceDao dao;

    @BeforeEach
    public void beforeEach(){
        this.em = JpaUtil.getEntityManager();
        this.dao = new LanceDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach(){
        em.getTransaction().rollback();
    }

    @Test
    void buscarMaiorLanceDoLeilao() {
        Usuario usuario = new UsuarioBuilder()
                            .comNome("fulano")
                            .comEmail("fulano@gmail.com")
                            .comSenha("12345")
                            .cria(em);

        Leilao leilao = new LeilaoBuilder()
                            .comNome("Teste")
                            .comValorInicial("500")
                            .comData(LocalDate.now())
                            .comUsuario(usuario)
                            .criar(em);

        Lance lance = new Lance(usuario,new BigDecimal("600"),leilao);
        em.persist(lance);

        assertEquals(new BigDecimal("600"),dao.buscarMaiorLanceDoLeilao(leilao).getValor());

    }
}