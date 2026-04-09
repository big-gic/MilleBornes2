package cartes;

public class Botte extends Probleme {

	public Botte(Type type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return getType().getBotte();
	}

	@Override
	public int hashCode() {
		return 31*this.getType().hashCode();
	}
	
	

}
