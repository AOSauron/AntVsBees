package ants;

import core.Ant;
import core.AntColony;
import core.Bee;
import core.Water;


//Une ant amphibi qui ne r�colte de la nourriture que sur de l'eau et qui tape au corps � corps uniquement (1 point de d�gat). Troupe faible mais l�g�rement offensive et lucrative sur l'eau.
public class AmphibiousAnt extends Ant {

	public AmphibiousAnt() {
		super(1, 3);
		watersafe=true;
		setDamage(1);
	}

	public Bee getTarget() {
		return place.getClosestBee(0,0);
	}
	
	@Override
	public void action(AntColony colony) {
		//Ne r�colte que sur l'eau
		if (this.place instanceof Water) {
			colony.increaseFood(1);
		}
		//Action simple au corps � corps sur une abeille random
		Bee target = getTarget();
		if(target != null) {
			target.reduceArmor(this.getDamage());
			}
		}

}

