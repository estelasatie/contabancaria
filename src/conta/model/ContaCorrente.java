package conta.model;

//Conta corrente herda de Conta (classe mae)
public class ContaCorrente extends Conta{
	
	private float limite;

	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		super(numero, agencia, tipo, titular, saldo);
		// TODO Auto-generated constructor stub
		
		this.limite = limite;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	//metodos especificos
	@override
	
	public boolean sacar (float valor) {
		if (this.getSaldo() + this.getLimite() < valor) {
			System.out.println("saldo insuficiente!");
			return false;
			{
				this.setSaldo(this.getSaldo() - valor);
				return true;
			}
		}
	}
	public void visualizar () {
		super.visualizar();
		System.out.println("limite de credito: " + this.limite);
	}

}
