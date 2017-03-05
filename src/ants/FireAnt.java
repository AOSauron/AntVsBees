/**
package ants;

import Audio.AudioPlayer;
import core.Ant;
import core.AntColony;
import core.Bee;

public class FireAnt extends Ant {
	 
	public FireAnt() {
		super(1,4);
		setDamage(3);
			}
	
	public Bee getTarget() {
		return place.getClosestBee(0,0); // Explication HungryAnt.java
	}
	
	public void action(AntColony colony) {
		Bee target = getTarget();
		if(target != null) {
			target.reduceArmor(this.getDamage());
			target=getTarget();
			while(target != null){
				target.reduceArmor(this.getDamage()); //r�duit l'armure de damage
				target = getTarget();
			}
			AudioPlayer bgMusic = new AudioPlayer("fire.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
			bgMusic.play();
			place.getAnt().reduceArmor(1);
			
		}
		
	}
	
}
*/



// Meilleur comportement(colle à la consigne) :
package ants;

import Audio.AudioPlayer;
import core.Ant;
import core.AntColony;
import core.Bee;

public class FireAnt extends Ant {
	
	public FireAnt() {
		super(1,4);
		setDamage(3);
	}
	
	public void reduceArmor(int amount) {
			Bee[] targets = this.place.getBees();
			super.reduceArmor(amount);
			if (this.getArmor() <=0) {
				if (targets.length>0 && this.getArmor()==0) {
					AudioPlayer bgMusic = new AudioPlayer("fire.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
					bgMusic.play();
					for (Bee b : targets) {
						b.reduceArmor(this.getDamage());
					}
				}
			}
	}
	
	public void action(AntColony colony) {
		//Nada
	}
}

