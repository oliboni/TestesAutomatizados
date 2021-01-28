package teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.jupiter.api.Test;
import teste.build.CriadorDeLeilao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static br.com.caelum.leilao.matcher.LeilaoMatcher.temUmLance;

public class LeilaoTeste {

    @Test
    public void deveReceberUmLance(){
        Leilao leilao = new Leilao("Mackbook air");
        assertEquals(0, leilao.getLances().size());

        leilao.propoe(new Lance(new Usuario("Steve Jobs"),2000.0));
        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(),0.00001);
    }

    @Test
    public void deveReceberVariosLances(){
        Leilao leilao = new Leilao("Macbook air");
        leilao.propoe(new Lance(new Usuario("Steve Jobs"),2000));
        leilao.propoe(new Lance(new Usuario("Steve Wozniak"), 3000));

        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.0,leilao.getLances().get(0).getValor(),0.0001);
        assertEquals(3000.0,leilao.getLances().get(1).getValor(),0.0001);
    }

    @Test
    public void naoDeveAceitarDoisLancesDeUmUsuario() {
        Leilao leilao = new Leilao("Macbook air");
        Usuario steve = new Usuario("Steve Jobs");

        leilao.propoe(new Lance(steve,2000));
        leilao.propoe(new Lance(steve,3000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.0, leilao.getLances().get(0).getValor(),0.0001);
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario(){
        Leilao leilao = new Leilao("Macbook air");
        Usuario steve = new Usuario("Steve Jobs");
        Usuario bil = new Usuario("Bil Gates");

        leilao.propoe(new Lance(steve, 2000.0));
        leilao.propoe(new Lance(bil, 3000.0));

        leilao.propoe(new Lance(steve, 4000.0));
        leilao.propoe(new Lance(bil, 5000.0));

        leilao.propoe(new Lance(steve, 6000.0));
        leilao.propoe(new Lance(bil, 7000.0));

        leilao.propoe(new Lance(steve, 8000.0));
        leilao.propoe(new Lance(bil, 9000.0));

        leilao.propoe(new Lance(steve, 10000.0));
        leilao.propoe(new Lance(bil, 11000.0));

        assertEquals(10, leilao.getLances().size());
        assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(),0.0001);
    }

    @Test
    public void deveDobrarOLance(){
        Leilao leilao = new Leilao("Macbook air");
        Usuario steve = new Usuario("Steve Jobs");
        Usuario bil = new Usuario("Bil Gates");

        leilao.propoe(new Lance(steve, 2000.0));
        leilao.propoe(new Lance(bil, 3000.0));
        leilao.dobraLance(steve);

        assertEquals(3, leilao.getLances().size());
        assertEquals(4000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(),0.0001);
    }

    @Test
    public void naoDeveDobrarLanceSemUsuarioTerDadoLance(){
        Leilao leilao = new Leilao("Macbook air");
        Usuario steve = new Usuario("Steve Jobs");

        leilao.dobraLance(steve);

        assertEquals(0, leilao.getLances().size());
    }
    @Test
    public void naoPermiteAdicionarValorMenorIgualAZero(){
        Usuario steve = new Usuario("Steve jobs");

        assertThrows(IllegalArgumentException.class, ()-> new CriadorDeLeilao().para("Macbook air").lance(steve,-1).constroi());
    }

    @Test
    public void deveReceberUmLance2(){
        Leilao leilao = new CriadorDeLeilao().para("Macbook air").constroi();

        assertEquals(0, leilao.getLances().size());
        Lance lance = new Lance(new Usuario("Steve Jobs"),5222);

        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(lance));
    }
}
