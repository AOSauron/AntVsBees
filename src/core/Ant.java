package core;


/**
 * A class representing a basic Ant
 *
 * @author YOUR NAME HERE
 */
public abstract class Ant extends Insect {
	public boolean blockingAnt;
	protected int foodCost; // the amount of food needed to make this ant
	private boolean isInAContaining;
	private int damage;
	
	public Ant (int armor, int foodCost) {			// On rajoute int foodCost
		super(armor, null, false);
		blockingAnt=false; // Par défaut à false
		isInAContaining = false; //Par défaut
		this.foodCost = foodCost;		//  
	}
	
	public boolean getBlockingAnt() {  //Ajout getters setters de BlockingAnt
		return blockingAnt;
	}

	public void setBlockingAnt(boolean blockingAnt) {
		this.blockingAnt = blockingAnt;
	}

	public int getFoodCost () {
		return foodCost;
	}

	@Override
	public void leavePlace () {
			place.removeInsect(this);
	}

	public boolean isInAContaining() {
		return isInAContaining;
	}

	public void setInAContaining(boolean isInAContaining) {
		this.isInAContaining = isInAContaining;
	}

	public int getDamage() {
		return damage;
	}
	
	public void setDamage (int d) {
		damage=d;
	}

}
