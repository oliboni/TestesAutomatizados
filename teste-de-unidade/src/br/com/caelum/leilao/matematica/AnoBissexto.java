package br.com.caelum.leilao.matematica;

public class AnoBissexto {

    public boolean ehBissexto(int ano){
        if ((ano%400 == 0) || (ano%4 == 0 && ano%100 != 0)){
            return true;
        } else {
            return false;
        }
    }
}
