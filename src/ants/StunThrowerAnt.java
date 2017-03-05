package ants;

import Audio.AudioPlayer;
import core.AntColony;
import core.Bee;


//Applique un eet d'étourdissement pour 1 tour. Une abeille étourdie passe sn tour

public class StunThrowerAnt extends ThrowerAnt {
	
	public  StunThrowerAnt () {
		super(1,6);
		
	}
	
	
	public void action (AntColony colony) {
		Bee target = getTarget();
		
		if (target != null) {
			AudioPlayer bgMusic = new AudioPlayer("swing.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
			bgMusic.play();

			target.setStunThrowerCompteur(1);
		}
	}

}
