package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if (lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	private boolean podeDarLance(Usuario usuario){
		return !ultimoLanceDado().getUsuario().equals(usuario) && qtdDeLances(usuario) <5;
	}

	private int qtdDeLances(Usuario usuario) {
		int total = 0;

		for (Lance l:lances){
			if (l.getUsuario().equals(usuario)) {
				total++;
			}
		}
		return total;
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size() - 1);
	}


	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}


	public void dobraLance(Usuario usuario) {
		Double ultimoLance = valorUltimoLance(usuario);

		if (ultimoLance > 0.0){
			propoe(new Lance(usuario,(ultimoLance*2)));
		}
	}

	private Double valorUltimoLance(Usuario usuario) {
		Double ultimoLance = 0.0;

		for (Lance l: lances) {
			if (l.getUsuario().equals(usuario)){
				ultimoLance = l.getValor();
			}
		}
		return ultimoLance;
	}
}
