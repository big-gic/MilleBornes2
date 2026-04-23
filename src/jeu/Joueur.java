package jeu;

import java.util.HashSet;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur mainJoueur = new MainJoueur();
	
	public Joueur(String nom, ZoneDeJeu zoneDeJeu) {
		this.nom = nom;
		this.zoneDeJeu = zoneDeJeu;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur) {
			Joueur joueur = (Joueur) obj;
			if (joueur.nom.equals(this.nom)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return 31*nom.hashCode();
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	public MainJoueur getMainJoueur() {
		return mainJoueur;
	}
	
	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}
	
	public void donner(Carte carte) {
		mainJoueur.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (!sabot.estVide()) {
			Carte carte = sabot.piocher();
			donner(carte);
			return carte;
		}
		return null;
	}
	
	public int donnerKmParcours() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> coups = new HashSet<>();
		for (Joueur participant : participants) {
			for (Carte carte : mainJoueur.getMain()) {
				Coup coup = new Coup(this, carte, participant);
				if (coup.estValide()) {
					coups.add(coup);
				}
			}
		}
		return coups;
	}
	
	public Set<Coup> coupsDefausse() {
		Set<Coup> coups = new HashSet<>();
		for (Carte carte : mainJoueur.getMain()) {
			coups.add(new Coup(this, carte, null));
		}
		return coups;
	}
	
	public void retirerDeLaMain(Carte carte) {
		mainJoueur.getMain().remove(carte);
	}
	
}
