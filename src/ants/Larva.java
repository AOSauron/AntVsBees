package ants;

import java.util.Random;
import Audio.AudioPlayer;
import core.Ant;
import core.AntColony;
import core.Place;

//Pour g�n�rer un nombre al�atoire :http://www.journaldunet.com/developpeur/pratique/developpement/12315/comment-generer-un-nombre-aleatoire-random-en-java-compris-entre-deux-chiffres.html

//Larve ne faisant rien pendant 4 tours jusqu'� faire qqchose ;) on ne peut pas la prot�ger par une body.
//11% de chance d'obtenir des Ant classiques (9 possibles) offensives/d�fensives, et 1% de chance d'obtenir une NukeAnt!

public class Larva extends Ant {

	private int comp=0;
	public Place pl;
	public Ant a;
	public int min=1;
	public int max=100;
	
	public Larva() {
		super(1, 1);
	}

	@Override
	public void action(AntColony colony) {
		if (comp==4) {
			
			Random rand = new Random();
			int nombreAleatoire = rand.nextInt(max - min + 1) + min;
			
			if (nombreAleatoire <=11) {
				a=new NinjaAnt();
			}
			else if (nombreAleatoire <= 22) {
				a=new WallAnt();
			}
			else if (nombreAleatoire <= 33) {
				a=new FireAnt();
			}
			else if (nombreAleatoire <= 44) {
				a=new ThrowerAnt();
			}
			else if (nombreAleatoire <= 55) {
				a=new RootedAnt();
			}
			else if (nombreAleatoire <= 66) {
				a=new HungryAnt();
			}
			else if (nombreAleatoire <= 77) {
				a=new StunThrowerAnt();
			}
			else if (nombreAleatoire <= 88) {
				a=new ScubaThrowerAnt();
			}
			else if (nombreAleatoire <= 99) {
				a=new ShortThrowerAnt();
			}
			else if (nombreAleatoire == 100) {
				a=new NukeAnt();
			}
			
			pl=place;
			place.removeInsect(this);
			
			AudioPlayer bgMusic = new AudioPlayer("/res/larvevo.wav"); //Mettre le fichier dans ANTSvsBEES\bin\Audio
			bgMusic.play();
			
			pl.addInsect(a);

		}
		else {
			comp++;
		}
	}

}
