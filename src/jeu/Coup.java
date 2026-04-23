package jeu;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return 31*(carte.hashCode() + joueurCible.hashCode() + joueurCourant.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup) {
			Coup coup = (Coup) obj;
			return this.carte.equals(coup.carte)
					&& this.joueurCourant.equals(coup.joueurCourant)
					&& this.joueurCible.equals(coup.joueurCible);
		}
		return false;
	}
	
	@Override
	public String toString() {
		if (joueurCible != null) {
			return joueurCourant.toString() +" defausse la carte "+ carte.toString();
		}
		return joueurCourant.toString() +" depose la carte "+ carte.toString() +" dans la zone de jeu de "
		+ joueurCible.toString();
	}
	
	
	
		
	

}
