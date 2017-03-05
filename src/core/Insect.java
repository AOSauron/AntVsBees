package core;

/**
 * Represents an insect (e.g., an Ant or a Bee) in the game
 *
 * @author Joel
 * @version Fall 2014
 */
public abstract class Insect {

	protected int armor; // insect's current armor
	protected Place place; // insect's current location
	public boolean watersafe;  //Ajout d'un boolean "waterproof" pour empecher de mettre une fourmi non aquatique dans l'eau
	protected int StunThrowerCompteur=0;
	protected int SlowThrowerCompteur=0;

	public int getStunThrowerCompteur() {
		return StunThrowerCompteur;
	}

	public void setStunThrowerCompteur(int stunThrowerCompteur) {
		StunThrowerCompteur = stunThrowerCompteur;
	}

	public int getSlowThrowerCompteur() {
		return SlowThrowerCompteur;
	}

	public void setSlowThrowerCompteur(int slowThrowerCompteur) {
		SlowThrowerCompteur = slowThrowerCompteur;
	}

	/**
	 * Creates a new Insect with the given armor in the given location
	 *
	 * @param armor
	 *            The insect's armor
	 * @param place
	 *            The insect's location
	 */
	
	public Insect (int armor, Place place, boolean watersafe) { 
		if (armor <= 0) {
			throw new IllegalArgumentException("Cannot create an insect with armor of 0");
		}
		this.armor = armor;
		this.place = place;
		this.watersafe=false; // L'attribut est faux par défaut.

	}

	public boolean getWatersafe() {
		return watersafe;
	}

	public void setWatersafe(boolean watersafe) {
		this.watersafe = watersafe;
	}

	/**
	 * Creates an Insect with the given armor. The insect's location is null
	 *
	 * @param armor
	 *            The insect's armor
	 */
	public Insect (int armor) {
		this(armor, null, false);
	}

	/**
	 * Set's the insect's current location
	 *
	 * @param place
	 *            The insect's current location
	 */
	public void setPlace (Place place) {
		this.place = place;
	}

	/**
	 * Return's the insect's current location
	 *
	 * @return the insect's current location
	 */
	public Place getPlace () {
		return place;
	}

	/**
	 * Returns the insect's current armor
	 *
	 * @return the insect's current armor
	 */
	public int getArmor () {
		return armor;
	}

	/**
	 * Reduces the insect's current armor (e.g., through damage)
	 *
	 * @param amount
	 *            The amount to decrease the armor by
	 */
	public void reduceArmor (int amount) {
		armor -= amount;
		if (armor <= 0) {

			System.out.println(this + " ran out of armor and expired");
			leavePlace();
		}
	}

	/**
	 * Has the insect move out of its current location. Abstract in case the insect takes action when it leaves
	 */
	public abstract void leavePlace ();

	/**
	 * The insect takes an action on its turn
	 *
	 * @param colony
	 *            The colony in which this action takes place (to support wide-spread effects)
	 */
	public abstract void action (AntColony colony);

	@Override
	public String toString () {
		return this.getClass().getName() + "[" + armor + ", " + place + "]"; // supports inheritance!
	}
}
