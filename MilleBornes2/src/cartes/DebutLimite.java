package cartes;

public class DebutLimite extends Limite {
	
	private int limite;

	public DebutLimite(int limite) {
		this.limite = limite;
	}
	
	@Override
	public String toString() {
		return "Limite "+this.limite;
	}

}
