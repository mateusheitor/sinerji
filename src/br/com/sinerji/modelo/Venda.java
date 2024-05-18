package br.com.sinerji.modelo;

public class Venda {
	
	private Funcionario vendedor;
	private String mes;
	private double valor;
	
	public Venda() {
		super();
	}

	public Venda(Funcionario vendedor, String mes, double valor) {
		super();
		this.vendedor = vendedor;
		this.mes = mes;
		this.valor = valor;
	}

	public Funcionario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Funcionario vendedor) {
		this.vendedor = vendedor;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}