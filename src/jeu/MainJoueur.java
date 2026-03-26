package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
	
	List<Carte> main = new ArrayList<>();
	
	public MainJoueur() {
		
	}
	
	public void prendre(Carte carte) {
		main.add(carte);
	}
	
	public void jouer(Carte carte) {
		assert (main.contains(carte));
		main.remove(carte);
	}
	
	@Override
	public String toString() {
		return main.toString();
	}
	
}
