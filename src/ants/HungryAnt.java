package ants;

import core.Ant;
import core.AntColony;
import core.Bee;

public class HungryAnt extends Ant {
 
	private int tour;
	private boolean digere;
	
	public HungryAnt() {
		super(1,4);
		tour=0;
	}
	
	public Bee getTarget() {
		return place.getClosestBee(0,0);
			}
	
	public int getTour() {
		return this.tour;
	}
	
	public void setTour(int d) {
		this.tour=d;
	}
	
	public void action (AntColony colony) {
		Bee target = getTarget();
		if (target != null && getTour()%3==0) {
			target.reduceArmor(target.getArmor());
			setTour(getTour()+1);
			digere=true;
		}
		else if (digere==true) {
			setTour(getTour()+1);
		}
	}
}

