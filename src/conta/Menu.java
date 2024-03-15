package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {

		int opcao, numero, agencia, tipo, aniversario = 0, numeroDestino;
		String titular;
		float saldo, limite, valor;

		ContaController conta = new ContaController();

		ContaCorrente cc1 = new ContaCorrente(1234567, 123, 2, "joao silva", 2500f, 1000f);
		cc1.visualizar();
		cc1.sacar(12000.0f);
		cc1.depositar(5000.0f);
		cc1.visualizar();

		ContaPoupanca cp1 = new ContaPoupanca(3, 123, 2, "Victor", 100000.0f, 15);
		cp1.visualizar();
		cp1.sacar(1000.0f);
		cp1.depositar(5000.0f);
		cp1.visualizar();

		Scanner ler = new Scanner(System.in);

		try {
			opcao = ler.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("\nDigite valores inteiros!");
			ler.nextLine();
			opcao = 0;
		}

		while (true) {

			System.out.println(
					Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND + "**********************************************");
			System.out.println("                                              ");
			System.out.println("           BANCO DO BRAZIL COM Z              ");
			System.out.println("                                              ");
			System.out.println("**********************************************");
			System.out.println("                                              ");
			System.out.println("           1 - Criar conta                    ");
			System.out.println("           2 - lista todas as contas          ");
			System.out.println("           3 - Buscar conta por número        ");
			System.out.println("           4 - Atualizar dados da conta       ");
			System.out.println("           5 - Apagar conta                   ");
			System.out.println("           6 - Sacar                          ");
			System.out.println("           7 - Depositar                      ");
			System.out.println("           8 - Transferir valores entre contas");
			System.out.println("           9 - Sair                           ");
			System.out.println("**********************************************");
			System.out.println("Entre com a opção desejada:                   ");
			System.out.println("                                              " + Cores.TEXT_RESET);

			opcao = ler.nextInt();

			try {
				opcao = ler.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				ler.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - Seu futuro começa aqui!");
				sobre();
				ler.close();
				System.exit(0);
			}

			switch (opcao) {

			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Criar conta\n\n");
				System.out.println("\nDigite o número da agência: ");
				agencia = ler.nextInt();
				System.out.println("\nDigite o nome do titular: ");
				ler.skip("\\R?");
				titular = ler.nextLine();

				do {
					System.out.println("Digite o tipo de conta (1- Conta Corrente  2- Conta Poupança)");
					tipo = ler.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println("Digite o saldo da conta: ");
				saldo = ler.nextFloat();

				switch (tipo) {

				case 1 -> {
					System.out.println("Digite o limite de crédito: ");
					limite = ler.nextFloat();
					conta.cadastrar(new ContaCorrente(conta.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}

				case 2 -> {
					System.out.println("Digite o dia do aniversario da conta: ");
					aniversario = ler.nextInt();
					conta.cadastrar(new ContaPoupanca(conta.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				break;

			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as contas\n\n");
				conta.listarTodas();

				break;

			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Listar dados da conta por número\n\n");
				System.out.println("Digite o número da conta:");
				numero = ler.nextInt();

				conta.procurarPorNumero(numero);

				break;

			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da conta\n\n");
				System.out.println("Digite o número da conta: ");
				numero = ler.nextInt();

				var buscaConta = conta.buscarNaCollection(numero);
				if (buscaConta != null) {
					tipo = buscaConta.getNumero();

					System.out.println("Digite o número da agência: ");
					agencia = ler.nextInt();

					System.out.println("Digite o nome do titular: ");
					ler.skip("\\R?");
					titular = ler.nextLine();

					System.out.println("Dgite o saldo da conta: ");
					saldo = ler.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o limite de crédito: ");
						limite = ler.nextFloat();
						conta.Atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}

					case 2 -> {
						System.out.println("Digite o aniversário da conta: ");
						aniversario = ler.nextInt();

						conta.Atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));

					}
					default -> {
						System.out.println("tipo de conta inválido!");
					}
					}
				} else {
					System.out.println("A conta não foi encontrada!");
				}

				break;

			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a conta\n\n");
				
				System.out.println("Digite o número da conta:");
				numero = ler.nextInt();
				conta.deletar(numero);

				break;

			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a conta\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = ler.nextInt();
				
				do {
					System.out.println("Digite o valor do saque (R$):");
					valor = ler.nextFloat();
					
				}while(valor<=0);
				
				conta.Sacar(numero, valor);
					

				break;

			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");
				
				System.out.println("Digite o número da conta:");
				numero = ler.nextInt();
				
				do {
					System.out.println("Digite o valor do depósito:");
					valor = ler.nextFloat();
				} while(valor<=0);
					
					conta.depositar(numero, valor);

				break;

			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre contas\n\n");
				
				System.out.println("Digite o número da conta de origem:");
				numero = ler.nextInt();
				System.out.println("Digite o número da conta destino: ");
				numeroDestino = ler.nextInt();
				
				do {
					System.out.println("Digite o valor da transferência: ");
					valor = ler.nextFloat();
				}while(valor<=0);
				
				conta.transferir(numero, numeroDestino, valor);

				break;

			default:

				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção inválida!\n");
			}
		}
	}

	public static void sobre() {
		System.out.println("\n***********************************");
		System.out.println("Projeto Desenvolvido por: Estela Satie Kitagami");
		System.out.println("Estela Satie Kitagami- email: estela.tatie@hotmail.com");
		System.out.println("https://github.com/estelasatie");
		System.out.println("\n***********************************");

	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}

	}
}
