package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {

	private ArrayList<Conta> listaContas = new ArrayList<Conta>();
	int numero = 0;

	@Override
	public void procurarPorNumero(int numero) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\nA conta " + conta.getNumero() + " foi criada com sucesso!");

	}

	@Override
	public void atualizar(Conta conta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(int numero) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sacar(int numero, float valor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void depositar(int numero, float valor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		// TODO Auto-generated method stub

	}

	public int gerarNumero() {
		return ++numero;
	}

	public Conta buscarNaCollection(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta;
			}
			return null;
		}
		return null;
		

	}

	public void ProcurarPorNumero(int numero) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			conta.visualizar();
		}
		else {
			System.out.println("A conta número: " + numero + " não foi encontrada!");
		}
		
		
				
			}
	
	public void Atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());
		
		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("\nA conta número: " + conta.getNumero() + " foi atualizada com sucesso!");
		}else {
			System.out.println("A conta número: " + numero + " não foi encontrada!");
		}
	}

	public void Deletar (int numero) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			if(listaContas.remove(conta) == true);
			System.out.println("A conta " + numero + " foi deletada com sucesso!");
			
		}
		else {
			System.out.println("A conta " + numero + " não foi encontrada!");
			
		}
	}
	
	public void Sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if (conta != null) {
			if (conta.sacar(valor) == true) {
				System.out.println("o saque da conta numero: " + numero + " foi realizado com sucesso!");
			}else {
				System.out.println("A conta numero " + numero + " não foi encontrada!");
			}
		}
	}
	
	public void Depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);
		
		if (conta!= null) {
			System.out.println("O deposito da conta numero " + numero + " foi realizado com sucesso!");
		} else {
			System.out.println("A conta numero " + numero + " não foi encontrada ou não é uma conta corrente!");
			
		}
	}
	
	public void Transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);
		
		if (contaOrigem != null && contaDestino != null) {
			if (contaOrigem.sacar(valor)==true) {
				contaDestino.depositar(valor);
				System.out.println("A transferência foi efetuada com sucesso!");
				
			}else {
				System.out.println("A conta de origem e / ou destino não foram encontradas!");
			}
		}

	}
}
