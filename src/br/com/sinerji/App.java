package br.com.sinerji;

import java.util.ArrayList;
import java.util.List;

import br.com.sinerji.modelo.Cargo;
import br.com.sinerji.modelo.Funcionario;
import br.com.sinerji.modelo.Venda;

public class App {

	private static List<Cargo> cargos;
	private static List<Funcionario> funcionarios;
	private static List<Venda> vendas;
	
	public static void main(String[] args) {
		popularCargos();
		popularFuncionarios();
		popularVendas();
		
		double salarioeBeneficio = getSalarioBeneficio(funcionarios,"12","2024");
		System.out.println("Salario + Beneficio: " + salarioeBeneficio);
	}
	
	private static void popularCargos(){
		cargos = new ArrayList<Cargo>();
		cargos.add(new Cargo("Secretário",7000,1000,20));
		cargos.add(new Cargo("Vendedor",12000,1800,30));
		cargos.add(new Cargo("Gerente",20000,3000,0));
	}
	
	private static void popularFuncionarios(){
		funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(new Funcionario("Jorge Carvalho",getCargo("Secretário"),"01/2018"));
		funcionarios.add(new Funcionario("Maria Souza",getCargo("Secretário"),"12/2015"));
		funcionarios.add(new Funcionario("Ana Silva",getCargo("Vendedor"),"12/2021"));
		funcionarios.add(new Funcionario("João Mendes",getCargo("Vendedor"),"12/2021"));
		funcionarios.add(new Funcionario("Juliana Alves",getCargo("Gerente"),"07/2017"));
		funcionarios.add(new Funcionario("Bento Albino",getCargo("Gerente"),"03/2014"));
	}
	
	private static void popularVendas(){
		vendas = new ArrayList<Venda>();
		
		vendas.add(new Venda(getFuncionario("Ana Silva"),"12/2021",5200));
		vendas.add(new Venda(getFuncionario("Ana Silva"),"01/2022",4000));
		vendas.add(new Venda(getFuncionario("Ana Silva"),"02/2022",4200));
		vendas.add(new Venda(getFuncionario("Ana Silva"),"03/2022",5850));
		vendas.add(new Venda(getFuncionario("Ana Silva"),"04/2022",7000));
		
		vendas.add(new Venda(getFuncionario("João Mendes"),"12/2021",3400));
		vendas.add(new Venda(getFuncionario("João Mendes"),"01/2022",7700));
		vendas.add(new Venda(getFuncionario("João Mendes"),"02/2022",5000));
		vendas.add(new Venda(getFuncionario("João Mendes"),"03/2022",5900));
		vendas.add(new Venda(getFuncionario("João Mendes"),"04/2022",6500));
	}
	
	private static Cargo getCargo(String nome) {
		Cargo retorno = null;
		for(Cargo cargo : cargos) {
			if(cargo.getNome().equals(nome)) {
				retorno = cargo;
				break;
			}
		}
		return retorno;
	}
	
	private static Funcionario getFuncionario(String nome) {
		Funcionario retorno = null;
		for(Funcionario funcionario : funcionarios) {
			if(funcionario.getNome().equals(nome)) {
				retorno = funcionario;
				break;
			}
		}
		return retorno;
	}
	
	/*
	Um método que receba uma lista de funcionários, mês e ano e retorne o valor total
	pago (salário e benefício) a esses funcionários no mês.
	*/
	private static double getSalarioBeneficio(List<Funcionario> funcionarios, String mes, String ano) {
		double salario = 0;
		double beneficio = 0;
		String mesContratacao = null;
		for(Funcionario funcionario : funcionarios) {
			salario += funcionario.getCargo().getSalario();
			mesContratacao = funcionario.getContratacao().substring(0,1);
			if(mes.equals(mesContratacao)) {
				beneficio += funcionario.getCargo().getSalario() * (funcionario.getCargo().getBeneficio()/100);
			}
		}
		return salario + beneficio;
	}

}