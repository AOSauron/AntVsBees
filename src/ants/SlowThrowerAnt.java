package ants;

import Audio.AudioPlayer;
import core.AntColony;
import core.Bee;

//
//Applique un efft de ralentissement de 3 tours. Une abeille ralentie effctue une action apres en avoir passée 2.

public class SlowThrowerAnt extends ThrowerAnt {
	
	public  SlowThrowerAnt () {
		super(1,4);
		setDamage(1);
	}
	

	
	public void action (AntColony colony) {
		Bee target = getTarget();
		
		if (target != null) {
			AudioPlayer bgMusic = new AudioPlayer("swing.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
			bgMusic.play();

			target.setSlowThrowerCompteur(1);;
		}
	}

}
