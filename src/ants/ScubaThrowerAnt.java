package ants;


public class ScubaThrowerAnt extends ThrowerAnt {
	
	public ScubaThrowerAnt() {
		super(1,5);
		watersafe=true;
		setDamage(1);
		}
	
	public ScubaThrowerAnt(int armor, int foodCost) {
		super(armor, foodCost);
	}
	
}
