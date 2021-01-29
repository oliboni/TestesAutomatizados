package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.dao.util.JpaUtil;
import br.com.alura.leilao.dao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.dao.util.builder.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LeilaoDaoTest {
    private LeilaoDao dao;
    private EntityManager em;

    @BeforeEach
    public void beforeEach(){
        this.em = JpaUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach(){
        em.getTransaction().rollback();
    }

    @Test
    public void deveriaCadastrarUmLeilao(){

        Leilao leilao = criaLeilao();

        Leilao salvo = dao.buscarPorId(leilao.getId());
        assertNotNull(salvo);
    }

    @Test
    public void deveriaAtualizarUmLeilao(){

        Leilao leilao = criaLeilao();

        leilao.setNome("Celular");
        leilao.setValorInicial(new BigDecimal("400"));

        dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());
        assertEquals("Celular", salvo.getNome());
        assertEquals(new BigDecimal( "400"), salvo.getValorInicial());
    }

    private Usuario criaUsuario(){
        return new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("123456")
                .cria(em);
    }

    private Leilao criaLeilao(){
        return new LeilaoBuilder()
                .comNome("Teste")
                .comValorInicial("500")
                .comData(LocalDate.now())
                .comUsuario(criaUsuario())
                .criar(em);
    }


}