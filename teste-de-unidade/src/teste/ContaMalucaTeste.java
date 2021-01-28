package teste;

import br.com.caelum.leilao.matematica.ContaMaluca;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaMalucaTeste {
    @Test
    public void deveriaRetornarVezesQuatro(){
        ContaMaluca contaMaluca = new ContaMaluca();

        int result = 31 * 4;

        assertEquals(result,contaMaluca.contaMaluca(31));
    }

    @Test
    public void deveriaRetornarVezesTres(){
        ContaMaluca contaMaluca = new ContaMaluca();

        int result = 11 * 3;

        assertEquals(result,contaMaluca.contaMaluca(11));
    }

    @Test
    public void deveriaRetornarVezesDois(){
        ContaMaluca contaMaluca = new ContaMaluca();

        int result = 9 * 2;

        assertEquals(result,contaMaluca.contaMaluca(9));
    }
}
