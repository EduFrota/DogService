package br.com.pettinder.domain;

import java.io.Serializable;

public class Dog implements Serializable {
	private static final Long SerialVersionUID = 1L;
	private Long id;
	private int TesteCommmit;
	private String nome;
	private String idade;
	private String raca;
	private String temperamento;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIdade() {
		return idade;
	}
	public void setIdade(String string) {
		this.idade = string;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public String getTemperamento() {
		return temperamento;
	}
	public void setTemperamento(String temperamento) {
		this.temperamento = temperamento;
	}
	@Override
	public String toString() {
		return "Dog [id=" + id + ", nome=" + nome + ", idade=" + idade + ", raca=" + raca + ", temperamento="
				+ temperamento + "]";
	}

}
