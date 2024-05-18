package br.com.sinerji.modelo;

public class Cargo {
	
	private String nome;
	private double salario;
	private double anuidade;
	private double beneficio;
	
	public Cargo() {
		super();
	}
	
	public Cargo(String nome, double salario, double anuidade, double beneficio) {
		super();
		this.nome = nome;
		this.salario = salario;
		this.anuidade = anuidade;
		this.beneficio = beneficio;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public double getAnuidade() {
		return anuidade;
	}

	public void setAnuidade(double anuidade) {
		this.anuidade = anuidade;
	}

	public double getBeneficio() {
		return beneficio;
	}
	
	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

}