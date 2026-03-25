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


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DebutLimite) {
			DebutLimite lim = (DebutLimite)obj;
			if (lim.limite == this.limite) {
				return true;
			}
		}
		return false;
	}
	

}
