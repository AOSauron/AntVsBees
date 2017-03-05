package ants;

import Audio.AudioPlayer;
import core.Ant;
import core.AntColony;
import core.Bee;
import core.Honey;


//Comparable à une HarvestAnt mais plus chère et amasse deux fois plus de nourriture sur une place de type Honey. Lors de sa Mort, elle pique (1 de dégat) au hasard 1 abeille sur elle.
public class HoneyAnt extends Ant {

	//private static final int pique=1;
	
	public HoneyAnt() {
		super(1, 3);
	}
	
	public void reduceArmor(int amount) {
		Bee target = this.place.getClosestBee(0,0);
		super.reduceArmor(amount);
		if (this.getArmor() <=0) {
			if (target != null && this.getArmor()==0) {
				AudioPlayer bgMusic = new AudioPlayer("bzzz.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
				bgMusic.play();
					target.reduceArmor(1);
			}
		}
}

	@Override
	public void action(AntColony colony) {
		if (this.place instanceof Honey) {
			colony.increaseFood(2);
		}
		else if (!(this.place instanceof Honey)) {
			colony.increaseFood(1);
		}
	}
	
	//Si on prévoit des fourmis spéciales miel
	public HoneyAnt(int armor, int foodCost) {
		super(armor, foodCost);
	}

}
