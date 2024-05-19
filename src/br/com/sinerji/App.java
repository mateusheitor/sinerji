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
	
	static final String MES_ANO = "01/2022";
	
	public static void main(String[] args) {
		popularCargos();
		popularFuncionarios();
		popularVendas();
		
		double salarioAnuidadeBeneficio = getSalarioAnuidadeBeneficio(funcionarios,MES_ANO);
		System.out.println("Salario + Anuidade + Beneficio: " + salarioAnuidadeBeneficio);
		
		double salario = getSalario(funcionarios,MES_ANO);
		System.out.println("Salario: " + salario);
		
		double beneficio = getBeneficio(funcionarios,MES_ANO);
		System.out.println("Beneficio: " + beneficio);
		
		Funcionario funcionarioComSalarioMaisAlto = getSalarioMaisAltoNoMes(funcionarios,MES_ANO); 
		System.out.println("Funcionario com o salario mais alto: " + funcionarioComSalarioMaisAlto.getNome());
		
		Funcionario funcionarioComBeneficioMaisAlto = getBeneficioMaisAlto(funcionarios,MES_ANO); 
		System.out.println("Funcionario com o beneficio mais alto: " + funcionarioComBeneficioMaisAlto.getNome());
		
		Funcionario vendedor = getVendedorQueMaisVendeu(funcionarios,MES_ANO); 
		System.out.println("Funcionario que mais vendeu no mes: " + vendedor.getNome());
	}
	
	static void popularCargos(){
		cargos = new ArrayList<Cargo>();
		cargos.add(new Cargo("Secretário",7000,1000,20));
		cargos.add(new Cargo("Vendedor",12000,1800,30));
		cargos.add(new Cargo("Gerente",20000,3000,0));
	}
	
	static void popularFuncionarios(){
		funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(new Funcionario("Jorge Carvalho",findByCargoEqualNome("Secretário"),"01/2018"));
		funcionarios.add(new Funcionario("Maria Souza",findByCargoEqualNome("Secretário"),"12/2015"));
		funcionarios.add(new Funcionario("Ana Silva",findByCargoEqualNome("Vendedor"),"12/2021"));
		funcionarios.add(new Funcionario("João Mendes",findByCargoEqualNome("Vendedor"),"12/2021"));
		funcionarios.add(new Funcionario("Juliana Alves",findByCargoEqualNome("Gerente"),"07/2017"));
		funcionarios.add(new Funcionario("Bento Albino",findByCargoEqualNome("Gerente"),"03/2014"));
	}
	
	static void popularVendas(){
		vendas = new ArrayList<Venda>();
		
		vendas.add(new Venda(findByFuncionarioEqualNome("Ana Silva"),"12/2021",5200));
		vendas.add(new Venda(findByFuncionarioEqualNome("Ana Silva"),"01/2022",4000));
		vendas.add(new Venda(findByFuncionarioEqualNome("Ana Silva"),"02/2022",4200));
		vendas.add(new Venda(findByFuncionarioEqualNome("Ana Silva"),"03/2022",5850));
		vendas.add(new Venda(findByFuncionarioEqualNome("Ana Silva"),"04/2022",7000));
		
		vendas.add(new Venda(findByFuncionarioEqualNome("João Mendes"),"12/2021",3400));
		vendas.add(new Venda(findByFuncionarioEqualNome("João Mendes"),"01/2022",7700));
		vendas.add(new Venda(findByFuncionarioEqualNome("João Mendes"),"02/2022",5000));
		vendas.add(new Venda(findByFuncionarioEqualNome("João Mendes"),"03/2022",5900));
		vendas.add(new Venda(findByFuncionarioEqualNome("João Mendes"),"04/2022",6500));
	}
	
	static Cargo findByCargoEqualNome(String nome) {
		Cargo retorno = null;
		for(Cargo cargo : cargos) {
			if(cargo.getNome().equals(nome)) {
				retorno = cargo;
				break;
			}
		}
		return retorno;
	}
	
	static Funcionario findByFuncionarioEqualNome(String nome) {
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
	static double getValorTotalVendas(Funcionario funcionario, String mesAno) {
		double retorno = 0;
		for( Venda venda : vendas) {
			if(venda.getVendedor().equals(funcionario)&&venda.getMes().equals(mesAno)) {
				retorno = venda.getValor();
				break;
			}
		}
		return retorno;
	}
	
	static double getSalarioAnuidadeBeneficio(List<Funcionario> funcionarios, String mesAno) {
		double total = 0;
		double salario = 0;
		double anuidade = 0;
		double beneficio = 0;
		for(Funcionario funcionario : funcionarios) {
			//salario
			salario = funcionario.getCargo().getSalario();
			//anuidade
			anuidade = 0;
			if( (mesAno!=funcionario.getContratacao())&&  
				(mesAno.substring(0,2).equals(funcionario.getContratacao().substring(0,2))) ) {
				anuidade = funcionario.getCargo().getAnuidade();
			}
			//beneficio
			beneficio = 0;
			if(funcionario.getCargo().getNome().equals("Secretário")) {
				beneficio = funcionario.getCargo().getSalario() * (funcionario.getCargo().getBeneficio() / 100);
			}else if(funcionario.getCargo().getNome().equals("Vendedor")) {
				beneficio = getValorTotalVendas(funcionario,mesAno) * (funcionario.getCargo().getBeneficio() / 100);
			}
			total += salario + anuidade + beneficio;
		}
		return total;
	}
	
	/*
	Um método que receba uma lista de funcionários, mês e ano e retorne o total pago
    somente em salários no mês
	*/
	static double getSalario(List<Funcionario> funcionarios, String mesAno) {
		double total = 0;
		double salario = 0;
		double anuidade = 0;
		for(Funcionario funcionario : funcionarios) {
			//salario
			salario = funcionario.getCargo().getSalario();
			//anuidade
			anuidade = 0;
			if( (mesAno!=funcionario.getContratacao())&&  
				(mesAno.substring(0,2).equals(funcionario.getContratacao().substring(0,2))) ) {
				anuidade = funcionario.getCargo().getAnuidade();
			}
			total += salario + anuidade;
		}
		return total;
	}
	
	/*
	Um método que receba uma lista somente com os funcionários que recebem
    benefícios, mês e ano e retorne o total pago em benefícios no mês.
	*/
	static List<Funcionario> findAllByFuncionarioBeneficioGReaterThanZero(List<Funcionario> funcionarios){
		List<Funcionario> beneficiarios = new ArrayList<Funcionario>();
		for(Funcionario funcionario : funcionarios) {
			if(funcionario.getCargo().getBeneficio()>0) {
				beneficiarios.add(funcionario);
			}
		}
		return beneficiarios;
	}
	
	static double getBeneficio(List<Funcionario> funcionarios, String mesAno) {
		double total = 0;
		double beneficio = 0;
		for(Funcionario funcionario : funcionarios) {
			//beneficio
			beneficio = 0;
			if(funcionario.getCargo().getNome().equals("Secretário")) {
				beneficio = funcionario.getCargo().getSalario() * (funcionario.getCargo().getBeneficio() / 100);
			}else if(funcionario.getCargo().getNome().equals("Vendedor")) {
				beneficio = getValorTotalVendas(funcionario,mesAno) * (funcionario.getCargo().getBeneficio() / 100);
			}
			total += beneficio;
		}
		return total;
	}
	
	
	/*
	Um método que receba uma lista de funcionários, mês e ano e retorne o que
    recebeu o valor mais alto no mês.
	*/
	static Funcionario getSalarioMaisAltoNoMes(List<Funcionario> funcionarios, String mesAno) {
		Funcionario funcionarioComSalarioMaisAlto = null;
		double salarioMaisAlto = 0;
		double salario = 0;
		double anuidade = 0;
		for(Funcionario funcionario : funcionarios) {
			//salario
			salario = funcionario.getCargo().getSalario();
			//anuidade
			anuidade = 0;
			if( (mesAno!=funcionario.getContratacao())&&  
				(mesAno.substring(0,2).equals(funcionario.getContratacao().substring(0,2))) ) {
				anuidade = funcionario.getCargo().getAnuidade();
			}
			if((salario+anuidade)>salarioMaisAlto) {
				salarioMaisAlto = salario + anuidade;
				funcionarioComSalarioMaisAlto = funcionario;
			}
		}
		return funcionarioComSalarioMaisAlto;
	}
	
	/*
	Um método que receba uma lista somente com os funcionários que recebem
    benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais
    alto em benefícios no mês.
	*/
	static Funcionario getBeneficioMaisAlto(List<Funcionario> funcionarios, String mesAno) {
		Funcionario funcionarioComBeneficioMaisAlto = null;
		double beneficioMaisAlto = 0;
		double beneficio = 0;
		for(Funcionario funcionario : funcionarios) {
			//beneficio
			beneficio = 0;
			if(funcionario.getCargo().getNome().equals("Secretário")) {
				beneficio = funcionario.getCargo().getSalario() * (funcionario.getCargo().getBeneficio() / 100);
			}else if(funcionario.getCargo().getNome().equals("Vendedor")) {
				beneficio = getValorTotalVendas(funcionario,mesAno) * (funcionario.getCargo().getBeneficio() / 100);
			}
			if(beneficio>beneficioMaisAlto) {
				beneficioMaisAlto = beneficio;
				funcionarioComBeneficioMaisAlto = funcionario;
			}
		}
		return funcionarioComBeneficioMaisAlto;
	}
	
	/*
	Um método que receba uma lista de vendedores, mês e ano e retorne o que mais
    vendeu no mês.
	*/
	static List<Funcionario> findAllByFuncionarioCargoVendedor(List<Funcionario> funcionarios){
		List<Funcionario> vendedores = new ArrayList<Funcionario>();
		for(Funcionario funcionario : funcionarios) {
			if(funcionario.getCargo().getNome().equals("Vendedor")) {
				vendedores.add(funcionario);
			}
		}
		return vendedores;
	}
	static Funcionario getVendedorQueMaisVendeu(List<Funcionario> funcionarios, String mesAno) {
		Funcionario vendedor = null;
		double valorVendaMaisAlto = 0;
		double valorVenda = 0;
		for(Funcionario funcionario : funcionarios) {
			valorVenda = getValorTotalVendas(funcionario,mesAno);
			if(valorVenda>valorVendaMaisAlto) {
				valorVendaMaisAlto = valorVenda;
				vendedor = funcionario;
			}
		}
		return vendedor;
	}
	
}