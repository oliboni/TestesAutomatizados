package br.com.caelum.leilao.avaliacao;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {

    private Double maiorValor = Double.NEGATIVE_INFINITY;
    private Double menorValor = Double.POSITIVE_INFINITY;
    private Double valorTotal = 0.0;
    private Integer totalLances = 0;
    private Double valorLanceMedio;
    private List<Lance> maiores;

    public void avalia(Leilao leilao){
        if (leilao.getLances().size() == 0){
            throw new RuntimeException("Não é possível avaliar um leilão sem lances!");
        }

        for(Lance lance : leilao.getLances()){
            if (lance.getValor() > maiorValor ) maiorValor = lance.getValor();
            if (lance.getValor() < menorValor) menorValor = lance.getValor();

            valorTotal += lance.getValor();
            totalLances += 1;
        }

        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            @Override
            public int compare(Lance o1, Lance o2) {
                if(o1.getValor() < o2.getValor()) return 1;
                if(o1.getValor() > o2.getValor()) return -1;
                return 0;
            }
        });
        maiores = maiores.subList(0,maiores.size() > 3 ? 3: maiores.size());

        valorLanceMedio = valorMedio();
    }

    public Double valorMedio(){
        return valorTotal/totalLances;

    }

    public Double getValorLanceMedio() {
        return valorLanceMedio;
    }

    public List<Lance> getTresMaiores() {
        return maiores;
    }

    public Double getMaiorValor() {
        return maiorValor;
    }

    public Double getMenorValor() {
        return menorValor;
    }
}
