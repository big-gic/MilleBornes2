package cartes;

public abstract class Probleme extends Carte {
	
	private Type type;
	
	protected Probleme(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Probleme) {
			Probleme probleme = (Probleme)obj;
			if (obj.getClass() == this.getClass() && probleme.type == this.type) {
				return true;
			}
		}
		return false;
	}
	
	


}
