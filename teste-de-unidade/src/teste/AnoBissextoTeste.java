package teste;

import br.com.caelum.leilao.matematica.AnoBissexto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnoBissextoTeste {
    @Test
    public void deveRetornarAnoBissexto() {
        AnoBissexto anoBissexto = new AnoBissexto();

        assertEquals(true, anoBissexto.ehBissexto(2016));
        assertEquals(true, anoBissexto.ehBissexto(2012));
    }

    @Test
    public void naoDeveRetornarAnoBissexto() {
        AnoBissexto anoBissexto = new AnoBissexto();

        assertEquals(false, anoBissexto.ehBissexto(2015));
        assertEquals(false, anoBissexto.ehBissexto(2011));
    }
}
