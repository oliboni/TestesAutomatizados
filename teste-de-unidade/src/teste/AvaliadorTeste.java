package teste;

import br.com.caelum.leilao.avaliacao.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.jupiter.api.*;
import teste.build.CriadorDeLeilao;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AvaliadorTeste {


    private Avaliador leiloeiro;
    private Usuario rodrigo;
    private Usuario joao;
    private Usuario maria;

//  Passo 1 - Cenario
    @BeforeEach
    private void criaAvaliador(){
        leiloeiro = new Avaliador();
        rodrigo = new Usuario("Rodrigo");
        joao = new Usuario("João");
        maria = new Usuario("Maria");
    }
    @AfterAll
    public static void finaliza(){
        System.out.println("Fim");
    }

    @Test
    public void naoDeveAvaliarLeiloesSemLance(){
//        try {
//            Leilao leilao = new CriadorDeLeilao().para("Play 5").constroi();
//            leiloeiro.avalia(leilao);
//            fail();
//        } catch (RuntimeException e){
//            //Deu boa
//        }

        Leilao leilao = new CriadorDeLeilao().para("Play 5").constroi();
        assertThrows(RuntimeException.class, ()-> leiloeiro.avalia(leilao));
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente(){

        Leilao leilao = new CriadorDeLeilao().para("Play 5 usado")
                .lance(rodrigo,1000.0)
                .lance(joao,1500.0)
                .lance(maria,2000.0)
                .lance(rodrigo,3500.0)
                .constroi();
//        Passo 2 - Ação
        leiloeiro.avalia(leilao);
//        Passo 3 - Validar
        Double menorEsperado = 1000.0;
        Double maiorEsperado = 3500.0;


        assertEquals(menorEsperado, leiloeiro.getMenorValor(), 0.00001);
//        assertEquals(maiorEsperado, leiloeiro.getMaiorValor(), 0.00001);
        assertThat(leiloeiro.getMaiorValor(), equalTo(maiorEsperado));

    }
    @Test
    public void deveEntenderLancesEmOrdemDecrescente(){

        Leilao leilao = new Leilao("Play 5 usado");

        leilao.propoe(new Lance(rodrigo,3500.0));
        leilao.propoe(new Lance(joao,2500.0));
        leilao.propoe(new Lance(maria,2000.0));
        leilao.propoe(new Lance(joao,1500.0));
        leilao.propoe(new Lance(rodrigo,1000.0));

//        Passo 2 - Ação
        leiloeiro.avalia(leilao);
//        Passo 3 - Validar
        Double menorEsperado = 1000.0;
        Double maiorEsperado = 3500.0;


        assertEquals(menorEsperado, leiloeiro.getMenorValor(), 0.00001);
        assertEquals(maiorEsperado, leiloeiro.getMaiorValor(), 0.00001);
    }

    @Test
    public void deveCalcularAMedia(){
        Leilao leilao = new Leilao("Play 5 usado");

        leilao.propoe(new Lance(rodrigo,1000.0));
        leilao.propoe(new Lance(joao,1500.0));
        leilao.propoe(new Lance(maria,2000.0));
        leilao.propoe(new Lance(rodrigo,3500.0));
        leilao.propoe(new Lance(joao,2500.0));
//        Passo 2 - Ação
        leiloeiro.avalia(leilao);
//        Passo 3 - Validar
        Double valorMedio = 2100.0;

        assertEquals(valorMedio, leiloeiro.getValorLanceMedio(), 0.0001);
    }
    @Test
    public void deveEnterderUmLeilaoComApenasUmlance(){

        Leilao leilao = new Leilao("Play 5 usado");

        leilao.propoe(new Lance(rodrigo,1000.0));
//          Passo 2 - Ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);
//        Passo 3 - Validar

        Double maiorEsperado = 1000.0;
        Double menorEsperado = 1000.0;


        assertEquals(menorEsperado, leiloeiro.getMenorValor(), 0.00001);
        assertEquals(maiorEsperado, leiloeiro.getMaiorValor(), 0.00001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances(){
        Leilao leilao = new CriadorDeLeilao()
                            .para("Play 5 usado")
                            .lance(rodrigo,1000.0)
                            .lance(joao,1500.0)
                            .lance(maria,2000.0)
                            .lance(rodrigo,3500.0)
                            .lance(joao,2500.0)
                            .constroi();
//        Passo 2 - Ação
        leiloeiro.avalia(leilao);
//        Passo 3 - Validar
        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3,maiores.size());
//        assertEquals(3500.0,maiores.get(0).getValor(),0.0001);
//        assertEquals(2500.0, maiores.get(1).getValor(),0.0001);
//        assertEquals(2000.0, maiores.get(2).getValor(),0.0001);
        assertThat(maiores,hasItems(
                new Lance(rodrigo, 3500),
                new Lance(joao,2500),
                new Lance(maria,2000)
        ));
    }
}
