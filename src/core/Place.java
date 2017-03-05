package core;

import java.util.ArrayList;

import ants.Containing;
import ants.ContainingAnt;
import ants.Larva;


/**
 * Represents a location in the game
 *
 * @author Joel
 * @version Fall 2014
 */
public class Place {

	private String name; // a name we can use for debugging
	private Place exit; // where you leave this place to
	private Place entrance; // where you enter this place from
	private ArrayList<Bee> bees; // bees currently in the place
	private ArrayList<StrongBee> strongbees;
	private Ant ant; // ant (singular) currently in the place
	/**
	 * Creates a new place with the given name and exit
	 *
	 * @param name
	 *            The place's name
	 * @param exit
	 *            The place's exit
	 */
	public Place (String name, Place exit) {
		this.name = name;
		this.exit = exit;
		entrance = null;
		bees = new ArrayList<Bee>();
		strongbees = new ArrayList<StrongBee>();
		ant = null;
	}

	/**
	 * Creates a new place with the given name
	 *
	 * @param name
	 *            The place's name
	 */
	public Place (String name) {
		this(name, null);
	}

	/**
	 * Returns the place's ant
	 *
	 * @return the place's ant
	 */
	public Ant getAnt () {
		return ant;
	}

	/**
	 * Returns an array of the place's bees
	 *
	 * @return an array of the place's bees
	 */
	
	public Bee[] getBees () {
		return bees.toArray(new Bee[0]);
	}
	
	public StrongBee[] getBeesSB () {
		return strongbees.toArray(new StrongBee[0]);
	}

	/**
	 * Returns a nearby bee, up to the maxDistance ahead. If multiple bees are the same distance, a random bee is chosen
	 *
	 * @param minDistance
	 *            The minimum distance away (in Places) a bee can be. A value of -1 means no min distance
	 * @param maxDistance
	 *            The maximum distance away (in Places) a Bee can be. A value of -1 means no max distance
	 * @return A random nearby bee.
	 */
	
	
	public Bee getClosestBee (int minDistance, int maxDistance) {
		Place p = this; 
		for (int dist = 0; p != null && dist <= maxDistance; dist++) {
			if (dist >= minDistance && p.bees.size() > 0) { 
				return p.bees.get((int) (Math.random() * p.bees.size())); // pick a random bee 
			} 
			if (dist >= minDistance && p.strongbees.size() > 0) { 
				return p.strongbees.get((int) (Math.random() * p.strongbees.size())); // pick a random strongbee 
			} 
			p = p.entrance;
		} 
		return null; 
	}

	
	/**
	 * Returns the name of the place
	 *
	 * @return the name of the place
	 */
	public String getName () {
		return name;
	}

	/**
	 * Returns the exit of the place
	 *
	 * @return the exit of the place
	 */
	public Place getExit () {
		return exit;
	}

	/**
	 * Sets the entrance to the place
	 *
	 * @param entrance
	 *            The entrance to the place
	 */
	public void setEntrance (Place entrance) {
		this.entrance = entrance;
	}

	/**
	 * Returns the entrance to the place
	 *
	 * @return the entrance to the place
	 */
	
	public Place getEntrance () {
		return entrance;
	}

	
	/**
	 * Adds an ant to the place. If there is already an ant, this method has no effect
	 *
	 * @param ant
	 *            The ant to add to the place.
	 */

	public void addInsect (Ant ant) {
		if (this.ant instanceof Larva && ant instanceof Containing || this.ant instanceof Containing && ant instanceof Larva) {
			System.out.println("You can't protect a larva !");
		}
		else if (this.ant == null) {
			this.ant = ant;
			ant.setPlace(this);
		}
		else if (this.ant instanceof Containing && !(ant instanceof Containing)) {
			if (((Containing) this.ant).AddAnInsect(ant)) {
				ant.setPlace(this);
			}
			else {
				System.out.println("Already an ant in " + this);
			}
		}
		else if (this.ant != null && !(this.ant instanceof Containing) && ant instanceof Containing) {
			if (((Containing) ant).AddAnInsect(this.ant)) {
				this.ant=ant;
				ant.setPlace(this);
			}
			else {
				System.out.println("Already an ant in " + this);
			}
		}
		else {
			System.out.println("Already an ant in " + this); // report error
		}
	}

	/**
	 * Adds a bee to the place
	 *
	 * @param bee
	 *            The bee to add to the place.
	 */
	public void addInsect (Bee bee) {
		bees.add(bee);
		bee.setPlace(this);
	}
	
	public void addInsectSB (StrongBee bee) {
		strongbees.add(bee);
		bee.setPlace(this);
	}

	/**
	 * Removes the ant from the place. If the given ant is not in this place, this method has no effect
	 *
	 * @param ant
	 *            The ant to remove from the place
	 */
	public void removeInsect (Ant ant) {
		if (this.ant == ant) {
			if (this.ant instanceof ContainingAnt) { // Si la fourmi en place est une containing
				this.ant = ((ContainingAnt) ant).ObtainInsect(); //On la vire et on met � sa place la fourmi qu'elle contenait
				ant.setPlace(this); //
			}
			else {
				this.ant = null;
				ant.setPlace(null);
			}
		}
		else {
			System.out.println(ant + " is not in " + this);
		}
	}
/**
	public void removeInsect (Ant ant) {
		if (this.ant == ant && !(this.ant instanceof Containing)) {
			this.ant = null;
			ant.setPlace(null);
		}
		else if (this.ant == ant && this.ant instanceof Containing && ((Containing) this.ant).ObtainInsect()==null) {
			this.ant=null;
			ant.setPlace(null);
		}
		else if (this.ant != ant && this.ant instanceof Containing && ((Containing) this.ant).ObtainInsect()==ant) {
			((Containing) this.ant).DelInsect(ant);
		}
		else if (this.ant == ant && this.ant instanceof Containing && ((Containing) this.ant).ObtainInsect() != null) {
			//supprime la containing et la remplace par son contenant
			//this.ant=((Containing) this.ant).ObtainInsect();
			this.ant = ((ContainingAnt) ant).ObtainInsect();
			System.out.println(this.ant);
		}
		else {
			System.out.println(ant + " is not in " + this);
		}
	}
	*/
	
	/**
	 * Removes the bee from the place. If the given bee is not in this place, this method has no effect
	 *
	 * @param bee
	 *            The bee to remove from the place.
	 */
	public void removeInsect (Bee bee) {
		if (bees.contains(bee)) {
			bees.remove(bee);
			bee.setPlace(null);
		}
		else {
			System.out.println(bee + " is not in " + this);
		}
	}
	
	public void removeInsectSB (StrongBee bee) {
		if (strongbees.contains(bee)) {
			strongbees.remove(bee);
			bee.setPlace(null);
		}
		else {
			System.out.println(bee + " is not in " + this);
		}
	}

	@Override
	public String toString () {
		return name;
	}
}
