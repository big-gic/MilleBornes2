package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;

public class GestionCartes {

	public static <T> T extraire(List<T> liste) {
		Random generateur = new Random();
		int ind = generateur.nextInt(liste.size());
		return liste.remove(ind);
	}
	
	public static <T> T extraire2(List<T> liste) {
		Random generateur = new Random();
		int ind = generateur.nextInt(liste.size());
		ListIterator<T> iterator = liste.listIterator()
		while (iterator.hasNext() && iterator.nextIndex() <= ind) {
			iterator.next();
		}
		T t = iterator.next();
		iterator.remove();
		return t;
	}
	
	public static <T> List<T> melanger(List<T> liste) {
		List<T> liste_melangee = new ArrayList<>();
		while (!liste.isEmpty()) {
			liste_melangee.add(extraire(liste));
		}
		return liste_melangee;
	}
	
	public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2) {
		Set<T> elts = new HashSet<>();
		elts.addAll(liste1);
		elts.addAll(liste2);
		for (T t : elts) {
			 if (Collections.frequency(liste1, t) != Collections.frequency(liste2, t)) {
				 return false;
			 }
		}
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> liste) {
		Set<T> uniques = new HashSet<>();
		uniques.addAll(liste);
		List<T> resultat = new ArrayList<>();
		for (T t : uniques) {
			int nbOcc = Collections.frequency(liste, t);
			for (int i = 0; i < nbOcc; i++) {
				resultat.add(t);
			}
		}
		return resultat;
	}
	
	public static <T> List<T> verifierRassemblement(List<T> liste) {
		ListIterator<T> it1 = liste.listIterator();
		while (it1.hasNext()) {
			T previous = it1.next();
			i
			T current = it1.next();
			while (previous.equals(current)) {
				previous =  current;
				current = it1.next();
				it1.remove();
			}
			for (ListIterator<T> it2 = liste.listIterator(); it2.hasNext(); it2.next()) {
				
			}
		}
	}
	
	
}
