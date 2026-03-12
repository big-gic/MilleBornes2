package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte>{
	
	private int nbCartes = 106;
	private Carte[] cartes;
	private int nbOperationsReference = 0;
	
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
	}
	
	public Boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes == 106) {
			throw new IllegalStateException();
		}
		cartes[nbCartes] = carte;
		nbCartes++;
		nbOperationsReference++;
	}
	
	public Carte piocher() {
		Iterator<Carte> it = iterator();
		Carte carte = it.next();
		it.remove();
		return carte;
	}
	
	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private Boolean nextEffectue = false;
		private int nbOperations = nbOperationsReference;
		
		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		@Override
		public Carte next() {
			verificationConcurrence();
			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if (!nextEffectue || nbCartes < 1) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur-1; i < nbCartes-1; i++) {
				cartes[i] = cartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nbOperations++;
			nbOperationsReference++;
		}
		
		public void verificationConcurrence() {
			if (nbOperations != nbOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}
	}

}
