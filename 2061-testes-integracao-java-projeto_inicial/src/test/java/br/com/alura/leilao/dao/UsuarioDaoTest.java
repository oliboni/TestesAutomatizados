package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.dao.util.JpaUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    private void criaUsuDao(){
        this.em = JpaUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach(){
        em.getTransaction().rollback();
    }

    private Usuario criarUsuario(){
        Usuario usuario = new Usuario("fulano","fulano@teste.com.br", "123456");
        em.persist(usuario);

        return usuario;
    }

    @Test
    public void naoDeveriaEncontrarUsuarioCadastrado(){
        criarUsuario();
        Assertions.assertThrows(NoResultException.class, ()->this.dao.buscarPorUsername("beltrano")) ;
    }

    @Test
    public void deveriaEncontrarUsuarioCadastrado(){
        Usuario usuario = criarUsuario();

        Usuario usuarioEncontrado = this.dao.buscarPorUsername("fulano");
        Assertions.assertNotNull(usuario);
    }

    @Test
    public void deveriaDeletarUmUsuario(){
        Usuario usuario = criarUsuario();
        dao.deletar(usuario);

        Assertions.assertThrows(NoResultException.class, ()-> this.dao.buscarPorUsername(usuario.getNome()));
    }

}