package cartes;

public class JeuDeCartes {

	private Configuration[] typesDeCartes;
	
	public JeuDeCartes() {
		this.typesDeCartes = new Configuration[] {
				new Configuration(new Borne(10), 10),
				new Configuration(new Borne(50), 10),
				new Configuration(new Borne(75), 10),
				new Configuration(new Borne(100), 12),
				new Configuration(new Borne(200), 4),
				new Configuration(new Parade(Type.FEU), 14),
				new Configuration(new FinLimite(), 6),
				new Configuration(new Parade(Type.ESSENCE), 6),
				new Configuration(new Parade(Type.CREVAISON), 6),
				new Configuration(new Parade(Type.ACCIDENT), 6),
				new Configuration(new Attaque(Type.FEU), 5),
				new Configuration(new DebutLimite(50), 4),
				new Configuration(new Attaque(Type.ESSENCE), 3),
				new Configuration(new Attaque(Type.CREVAISON), 3),
				new Configuration(new Attaque(Type.ACCIDENT), 3),
				new Configuration(new Botte(Type.FEU), 1),
				new Configuration(new Botte(Type.ESSENCE), 1),
				new Configuration(new Botte(Type.CREVAISON), 1),
				new Configuration(new Botte(Type.ACCIDENT), 1)
		};
	}
	
	public String affichageJeuDeCartes() {
		StringBuilder str = new StringBuilder();
		str.append("JEU");
		for (Configuration configuration : typesDeCartes) {
			str.append("\n"+configuration.toString());
		}
		return str.toString();
	}
	
	public Carte[] donnerCartes() {
		
		for (Configuration configuration : typesDeCartes) {
			for (int i = 0; i < configuration.getNbExemplaires(); i++) {
				
			}
		}
	}
	
	private class Configuration {
		private int nbExemplaires;
		private Carte carte;

		private Configuration(Carte carte, int nbExemplaires) {
			this.nbExemplaires = nbExemplaires;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}

		public Carte getCarte() {
			return carte;
		}
		
		@Override
		public String toString() {
			return nbExemplaires+" "+carte.toString();
		}
		
	}

}
