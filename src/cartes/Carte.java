package cartes;

public abstract class Carte {
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return this.getClass() == obj.getClass();
	}
	
	@Override
	public int hashCode() {
		return 31*getClass().hashCode();
	}
}
