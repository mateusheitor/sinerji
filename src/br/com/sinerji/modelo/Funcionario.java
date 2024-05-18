package br.com.sinerji.modelo;

public class Funcionario {
	
	private String nome;
	private Cargo cargo;
	private String contratacao;
	
	public Funcionario() {
		super();
	}

	public Funcionario(String nome, Cargo cargo, String contratacao) {
		super();
		this.nome = nome;
		this.cargo = cargo;
		this.contratacao = contratacao;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Cargo getCargo() {
		return cargo;
	}
	
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public String getContratacao() {
		return contratacao;
	}
	
	public void setContratacao(String contratacao) {
		this.contratacao = contratacao;
	}

}