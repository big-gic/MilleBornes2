package jeu;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cartes.Bataille;
import cartes.Botte;
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
		mainJoueur.jouer(carte);
	}
	
	private Coup choisirCoupAleatoire(Set<Coup> coups) {
		Random generateur = new Random();
		int ind = generateur.nextInt(coups.size());
		int i = 0;
		for (Coup coup : coups) {
			if (i == ind) {
				return coup;
			}
			i++;
		}
		throw new IllegalStateException();
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupsPos = coupsPossibles(participants);
		if (coupsPos.isEmpty()) {
			return choisirCoupAleatoire(coupsDefausse());
		}
		else {
			return choisirCoupAleatoire(coupsPos);
		}
	}
	
	public String afficherEtatJoueur() {
		StringBuilder str = new StringBuilder();
		Set<Botte> bottes = zoneDeJeu.getCollectionBottes();
		str.append("[");
		for (Botte botte : bottes) {
			str.append(botte.toString()+", ");
		}
		str.append("]\n");
		str.append(Boolean.toString(zoneDeJeu.donnerLimitationVitesse() == 50)+"\n");
		List<Bataille> pileBataille = zoneDeJeu.getPileBataille();
		if (pileBataille.isEmpty()) {
			str.append("null\n");
		}
		else {
			str.append(pileBataille.get(pileBataille.size()-1).toString()+"\n");
		}
		str.append(mainJoueur.toString());
		
		return str.toString();
	}
	
	
	
	
	
	
	
}
