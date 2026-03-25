package cartes;

import java.util.ArrayList;
import java.util.List;

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
		for (Configuration configuration : typesDeCartes) {
			str.append("\n"+configuration.toString());
		}
		return str.toString();
	}
	
	private boolean checkCountConfiguration(Configuration conf, Carte[] cartes) {
		int nbEx = conf.getNbExemplaires();
		Carte carte = conf.getCarte();
		int countEx = 0;
		for (Carte elt : cartes) {
			if (elt.equals(carte)) {
				countEx++;
				if (countEx == nbEx) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkCount() {
		if (donnerCartes().length != 106) {
			return false;
		}
		Carte[] cartes = donnerCartes();
		for (Configuration conf : typesDeCartes) {
			checkCountConfiguration(conf, cartes);
		}
		return true;
	}
	
	public Carte[] donnerCartes() {
		List<Carte> cartes = new ArrayList<>();
		for (Configuration configuration : typesDeCartes) {
			for (int i = 0; i < configuration.getNbExemplaires(); i++) {
				cartes.add(configuration.getCarte());
			}
		}
		return cartes.toArray(new Carte[0]);
	}
	
	private class Configuration {
		private int nbExemplaires;
		private Carte carte;

		private Configuration(Carte carte, int nbExemplaires) {
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
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
