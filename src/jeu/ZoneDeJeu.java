package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	
	private List<Limite> pileLimite = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private Set<Borne> collectionBornes = new HashSet<>();
	
	public int donnerLimitationVitesse() {
		if (pileLimite.isEmpty() || pileLimite.get(pileLimite.size()-1) instanceof FinLimite) {
			return 200;
		} else {
			return 50;
		}
	}
	
	public int donnerKmParcourus() {
		int km = 0;
		for(Borne borne : collectionBornes) {
			km += borne.getKm();
		}
		return km;
	}
	
	public void deposer(Carte c) {
		if (c instanceof Borne) {
			collectionBornes.add((Borne) c);
		}
		if (c instanceof Limite) {
			pileLimite.add((Limite) c);
		}
		if (c instanceof Bataille) {
			pileBataille.add((Bataille) c);
		}
	}
	
	public boolean peutAvancer() {
		if (!pileBataille.isEmpty() && pileBataille.get(pileBataille.size()-1).equals(new Parade(Type.FEU))) {
			return true;
		}
		return false;
	}
	
	protected boolean estDepotFeuVertAutorise() {
		if (pileBataille.isEmpty()) {
			return true;
		}
		Bataille sommet = pileBataille.get(pileBataille.size()-1);
		if (sommet.equals(new Attaque(Type.FEU))) {
			return true;
		}
		if (sommet instanceof Parade) {
			if (!sommet.getType().equals(Type.FEU)) {
				return true;
			}
		}
		return false;
	}
	
	protected boolean estDepotBorneAutorise(Borne borne) {
		if (!peutAvancer()) {
			return false;
		}
		if (!pileLimite.isEmpty()
				&& pileLimite.get(pileLimite.size()-1) instanceof DebutLimite
				&& borne.getKm() > 50) {
			return false;
		}
		if (donnerKmParcourus() + borne.getKm() > 1000) {
			return false;
		}
		return true;
	}
	
	protected boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			return pileLimite.isEmpty() || pileLimite.get(pileLimite.size()-1) instanceof FinLimite;
		}
		else {
			return !pileLimite.isEmpty() && pileLimite.get(pileLimite.size()-1) instanceof DebutLimite;
		}
	}
	
	protected boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			return peutAvancer();
		}
		else {
			Bataille sommet = pileBataille.get(pileBataille.size()-1);
			if (bataille.getType().equals(Type.FEU)) {
				return pileBataille.isEmpty() || sommet.equals(new Attaque(Type.FEU))
						|| (sommet instanceof Parade && !sommet.getType().equals(Type.FEU));
			}
			else {
				return !pileBataille.isEmpty()
						&& (sommet instanceof Attaque && sommet.getType().equals(bataille.getType()));
			}
		}
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte.equals(new Parade(Type.FEU))) {
			return estDepotFeuVertAutorise();
		}
		if (carte instanceof Borne) {
			return estDepotBorneAutorise((Borne) carte);
		}
		if (carte instanceof Limite) {
			return estDepotLimiteAutorise((Limite) carte);
		}
		else {
			return estDepotBatailleAutorise((Bataille) carte);
		}
	}
	
	
	
}
