package br.com.caelum.leilao.dominio;

import java.util.Objects;

public class Lance {

	private Usuario usuario;
	private double valor;
	
	public Lance(Usuario usuario, double valor) {
		validaValor(valor);
		this.usuario = usuario;
		this.valor = valor;
	}

	private void validaValor(double valor) {
		if (valor <=0) {
			throw new IllegalArgumentException("O valor do lance precisa ser maior que zero!");
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public double getValor() {
		return valor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Lance)) return false;
		Lance lance = (Lance) o;
		return Double.compare(lance.getValor(), getValor()) == 0 && getUsuario().equals(lance.getUsuario());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUsuario(), getValor());
	}
}
