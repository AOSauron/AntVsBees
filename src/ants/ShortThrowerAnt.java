package ants;

import Audio.AudioPlayer;
import core.AntColony;
import core.Bee;


public class ShortThrowerAnt extends ThrowerAnt {
	
	public ShortThrowerAnt() {
		super (1,3);
		setDamage(1);
	}
	public Bee getTarget () {
		return place.getClosestBee(0,2); // lance feuilles aux abeilles à au plus 2 places.
		
	}
	
	public void action (AntColony colony) {
		Bee target = getTarget();
		if (target != null) {
			//On met un son de jet de feuille
			AudioPlayer bgMusic = new AudioPlayer("swing.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
			bgMusic.play();
			target.reduceArmor(this.getDamage());
		}
	}
}
