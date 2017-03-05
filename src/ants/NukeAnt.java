package ants;

import java.util.ArrayList;

import Audio.AudioPlayer;
import core.Ant;
import core.AntColony;
import core.Bee;
import core.NonRemovableAnt;

//Une ant ayant mangé trop de minerai d'uranium ! C'est une super arme, son coup en nourriture est aussi grand que sa puissance. On ne peut la retirer une fois placée!

public class NukeAnt extends Ant implements NonRemovableAnt {

	public NukeAnt() {
		super(1, 4);
	}
	
	@Override
	public void action(AntColony colony) {
		ArrayList<Bee> targets = colony.getAllBees();
		System.out.println(targets);
		if (targets.size()>0) {
			AudioPlayer bgMusic = new AudioPlayer("nuke.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
			bgMusic.play();
			for (Bee b : targets) {
				b.reduceArmor(b.getArmor());
			}
			this.reduceArmor(this.getArmor());
		}
	}
	
}


