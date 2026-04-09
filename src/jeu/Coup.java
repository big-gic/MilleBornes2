package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	
	Joueur joueurCourant;
	Carte carte;
	Joueur joueurCible;
	
	public Coup(Joueur joueurCourant, Carte carte, Joueur joueurCible) {
		this.joueurCourant = joueurCourant;
		this.carte = carte;
		this.joueurCible = joueurCible;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Carte getCarte() {
		return carte;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
		if (carte instanceof Attaque || carte instanceof DebutLimite) {
			return joueurCible != null && !joueurCible.equals(joueurCourant)
					&& joueurCible.getZoneDeJeu().estDepotAutorise(carte);
		}else {
			return joueurCible.equals(joueurCourant) && joueurCible.getZoneDeJeu().estDepotAutorise(carte);
		}
	}
		
	

}
