package ants;

import Audio.AudioPlayer;
import core.Ant;
import core.AntColony;
import core.Bee;

public class ThrowerAnt extends Ant {
	public ThrowerAnt () {
		super(1,4);
		setDamage(1);
	}

	public ThrowerAnt(int armor, int foodCost) { //Surcharge pour la scuba. Et la Long thrower
		super(armor, foodCost);
	}

	public Bee getTarget () {
		return place.getClosestBee(0, 3);
	}


	@Override
	public void action (AntColony colony) {

		Bee target = getTarget();
		if (target != null) {
			//On met un son de jet de feuille
			AudioPlayer bgMusic = new AudioPlayer("/res/swing.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
			bgMusic.play();
			target.reduceArmor(this.getDamage());
		}
	}

}
