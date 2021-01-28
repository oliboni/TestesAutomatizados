package br.com.caelum.leilao.matematica;

public class ContaMaluca {
    public int contaMaluca(int num){
        if (num > 30)
            return num * 4;
        else if (num > 10)
            return num * 3;
        else
            return num * 2;
    }
}
