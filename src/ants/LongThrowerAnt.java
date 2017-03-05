package ants;


import Audio.AudioPlayer;
import core.AntColony;
import core.Bee;

public class LongThrowerAnt extends ThrowerAnt {

	public LongThrowerAnt() {
		super (1,3);
		setDamage(1);
	}

	public Bee getTarget () {
		return place.getClosestBee(4, AntColony.TUNNELENGTH); // lance feuilles aux abeilles � au moins 4 places jusqu'� la taille du tunnel

	}

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

