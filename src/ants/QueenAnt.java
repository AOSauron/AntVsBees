package ants;

import java.util.ArrayList;

import Audio.AudioPlayer;
import core.Ant;
import core.AntColony;
import core.Bee;
import core.NonRemovableAnt;
import core.Place;

//Inspir� de https://fr.wikipedia.org/wiki/Singleton_(patron_de_conception)

public final class QueenAnt extends ScubaThrowerAnt implements Damaging, NonRemovableAnt {
	
	private static volatile QueenAnt instance = null;
	
	public Place p;
	private static int nbQueen=-1;
	private ArrayList<Ant> boost;
	
	public QueenAnt() {
			super(1,6);
			setDamage(1);
			watersafe=true;
			boost = new ArrayList<Ant>();
			nbQueen++;
	}
	
	public int getnbQueen() {
		return nbQueen;
	}
	
	public void decrementnbQueen() {
		nbQueen--;
	}
	
	/**
     * M�thode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */

    public final static QueenAnt getInstance() {
        if (QueenAnt.instance == null) {
           synchronized(QueenAnt.class) {
             if (QueenAnt.instance == null) {
               QueenAnt.instance = new QueenAnt();
             }
           }
        }
        return QueenAnt.instance;
    }

	
	public void action (AntColony colony) {
		if (nbQueen<=1) {
			//Attaque:
			Bee target = getTarget();
			if (target != null) {
				AudioPlayer bgMusic = new AudioPlayer("/res/swing.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
				bgMusic.play();
				target.reduceArmor(this.getDamage());
			}
			
			//Game over si Queen atteinte:
			colony.setQueenPlace(this.place);
			
			//Galvanise:
			if (place.getExit() != null && place.getExit().getAnt() != null) {
				Ant targetG = place.getExit().getAnt();
				if (boost.contains(targetG)==false && targetG !=null) {
					targetG.setDamage(targetG.getDamage()*2);
					boost.add(targetG);
				}
			}
			if (place.getEntrance() != null && place.getEntrance().getAnt() != null) {
				Ant targetD = place.getEntrance().getAnt();
				if (boost.contains(targetD)==false && targetD != null) {
					targetD.setDamage(targetD.getDamage()*2);
					boost.add(targetD);
				}		
			}
			System.out.println(nbQueen);
		}
		else { //Si il y a plus d'une reine, on la d�truit et on d�cr�mente la variable nbQueen. L'imposteur ne peut effectuer les actions de la vraire reine.
			reduceArmor(1);
			nbQueen--;
		}
	}
}

