package testsFonctionnels;

import java.util.Set;

import jeu.Jeu;
import jeu.Joueur;
import jeu.ZoneDeJeu;

public class TestJeu {

	public static void main(String[] args) {
		
		Jeu jeu = new Jeu();
		Joueur joueur1 = new Joueur("Jack", new ZoneDeJeu());
		Joueur joueur2 = new Joueur("Bill", new ZoneDeJeu());
		Joueur joueur3 = new Joueur("Luffy", new ZoneDeJeu());
		jeu.inscrire(joueur1, joueur2, joueur3);
		jeu.distribuerCartes();
		Set<Joueur> joueurs = jeu.getJoueurs();
		for (Joueur joueur : joueurs) {
			joueur.getMainJoueur().toString();
		}
		for (Joueur joueur : joueurs) {
			jeu.jouerTour(joueur);
		}
	}
}
