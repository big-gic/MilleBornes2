package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
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
	private Set<Botte> collectionBottes = new HashSet<>();
	
	
	public List<Limite> getPileLimite() {
		return pileLimite;
	}

	public List<Bataille> getPileBataille() {
		return pileBataille;
	}

	public Set<Borne> getCollectionBornes() {
		return collectionBornes;
	}

	public Set<Botte> getCollectionBottes() {
		return collectionBottes;
	}

	protected boolean estPrioritaire() {
		return collectionBottes.contains(new Botte(Type.FEU));
	}
	
	public int donnerLimitationVitesse() {
		if (estPrioritaire() || pileLimite.isEmpty() || pileLimite.get(pileLimite.size()-1) instanceof FinLimite) {
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
		else if (c instanceof Limite) {
			pileLimite.add((Limite) c);
		}
		else if (c instanceof Bataille) {
			pileBataille.add((Bataille) c);
		}
		else {
			collectionBottes.add((Botte) c);
		}
	}
	
	public boolean peutAvancer() {
		if (estPrioritaire()) {
			if (pileBataille.isEmpty()) {
				return true;
			}else {
				Bataille sommet = pileBataille.get(pileBataille.size()-1);
				if (sommet instanceof Parade)
					return true;
				else {
					if (sommet.getType().equals(Type.FEU) || collectionBottes.contains(new Botte(sommet.getType()))){
						return true;
					}
				}
			}
		}
		if (!pileBataille.isEmpty() && pileBataille.get(pileBataille.size()-1).equals(new Parade(Type.FEU))) {
			return true;
		}
		return false;
	}
	
	protected boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) {
			return false;
		}
		if (pileBataille.isEmpty()) {
			return true;
		}
		else {
			Bataille sommet = pileBataille.get(pileBataille.size()-1);
			if (sommet instanceof Attaque &&
					(sommet.getType().equals(Type.FEU) || collectionBottes.contains(new Botte(sommet.getType())))) {
				return true;
			}
			else {
				if (!sommet.getType().equals(Type.FEU)) {
					return true;
				}
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
		if (estPrioritaire()) {
			return false;
		}
		if (limite instanceof DebutLimite) {
			return pileLimite.isEmpty() || pileLimite.get(pileLimite.size()-1) instanceof FinLimite;
		}
		else {
			return !pileLimite.isEmpty() && pileLimite.get(pileLimite.size()-1) instanceof DebutLimite;
		}
	}
	
	protected boolean estDepotBatailleAutorise(Bataille bataille) {
		if (collectionBottes.contains(new Botte(bataille.getType()))) {
			return false;
		}
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
		else if (carte instanceof Borne) {
			return estDepotBorneAutorise((Borne) carte);
		}
		else if (carte instanceof Limite) {
			return estDepotLimiteAutorise((Limite) carte);
		}
		else if (carte instanceof Bataille) {
			return estDepotBatailleAutorise((Bataille) carte);
		}
		else {
			return true;
		}
	}
	
	
	
}
