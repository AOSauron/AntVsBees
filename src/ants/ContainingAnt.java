package ants;

import core.Ant;
import core.AntColony;

public abstract class ContainingAnt extends Ant implements Containing{
	
	private Ant antCont=null; //On d�finit l'attribut ici pas dans l'interface
							// encapsulation propre variable priv��e
	public ContainingAnt() {
		super(2,5);
		}
	
public void action (AntColony colony) {
		
	}

@Override
public boolean AddAnInsect(Ant one) {
	if(antCont == null) {  // Si le "sac � dos" de la contenante est null on ADD la one dedans et on retourne vrai.
		one.setInAContaining(true);
		antCont = one;
		return true;
	}
	return false;
}

@Override
public boolean DelInsect(Ant one) {
	if(antCont !=null && antCont==one) {   //Si le sac � dos est vide, on renvoi false puisque on peut rien y retirer
		antCont=null; //Sinon, on met le contenu � null et on renvoi vrai
		return true;
	}
		return false;
}

@Override
public Ant ObtainInsect() {
	return antCont;
}

public ContainingAnt (int armor, int foodCost) { //Surcharge
	super(armor, foodCost);
}



}