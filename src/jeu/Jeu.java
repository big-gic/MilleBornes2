package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	
	private Sabot sabot;
	private Set<Joueur> joueurs = new LinkedHashSet<>();
	private static final int NBCARTES = 6;

	public Jeu() {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		Carte[] cartes = jeuDeCartes.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		GestionCartes.melanger(listeCartes);
		sabot = new Sabot(listeCartes.toArray(new Carte[0]));
	}
	
	
	public Sabot getSabot() {
		return sabot;
	}

	public Set<Joueur> getJoueurs() {
		return joueurs;
	}

	public void inscrire(Joueur...participants) {
		Collections.addAll(joueurs, participants);
	}
	
	public void distribuerCartes() {
		for (int i = 0; i < NBCARTES; i++) {
			for (Joueur joueur : joueurs) {
				joueur.donner(sabot.piocher());
			}
		}
	}
	
	public String jouerTour(Joueur joueur) {
		StringBuilder str = new StringBuilder();
		Carte carte = joueur.prendreCarte(sabot);
		str.append("Le joueur a pioche "+ carte.toString() +"\n");
		Coup coup = joueur.choisirCoup(joueurs);
		str.append(coup.toString());
		carte = coup.getCarte();
		joueur.getMainJoueur().jouer(carte);
		if (coup.getJoueurCible() == null) {
			sabot.ajouterCarte(carte);
		}
		else {
			coup.getJoueurCible().getZoneDeJeu().deposer(carte);;
		}
		return str.toString();
	}
	
	
	
	
	
}
