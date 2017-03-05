package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a hive--which contains the bees that will attack!
 *
 * @author Joel
 * @version Fall 2014
 */
public class Hive extends Place {

	public static final String NAME = "Hive";

	private int beeArmor; // armor for all the bees
	private Map<Integer, Bee[]> waves; // a mapping from attack times to the list of bees that will charge in
	private Map<Integer, StrongBee[]> strongwaves; //a mapping from attack times to the list of SRONGbees that will charge in
	
	/**
	 * Creates a new hive, in which Bees have the given armor
	 *
	 * @param beeArmor
	 *            The armor of the bees
	 */
	public Hive (int beeArmor) {
		super(NAME, null);
		this.beeArmor = beeArmor;
		waves = new HashMap<Integer, Bee[]>();
		strongwaves = new HashMap<Integer, StrongBee[]>(); //New hashmap for StrongBees
	}

	/**
	 * Moves in the invaders who are attacking the colony at the given time.
	 *
	 * @param colony
	 *            The colony to attack
	 * @param currentTime
	 *            The current time
	 * @return An array of the bees who invaded (for animation/processing)
	 */
	public Bee[] invade (AntColony colony, int currentTime) {
		Place[] exits = colony.getBeeEntrances();

		Bee[] wave = waves.get(currentTime);
		if (wave == null) {
			return new Bee[0]; // return empty set if no bees attacking now
		}

		for (Bee b : wave) // move all the bees in
		{
			int randExit = (int) (Math.random() * exits.length);
			b.moveTo(exits[randExit]); // move b to a random exit from the hive (entrance to the colony)
		}
		return wave; // return who invaded
	}
	
	public StrongBee[] invadeSB (AntColony colony, int currentTime) {  //On créé une fonction pour les Strongbees
		Place[] exits = colony.getBeeEntrances();

		StrongBee[] wave = strongwaves.get(currentTime);
		if (wave == null) {
			return new StrongBee[0]; // return empty set if no bees attacking now
		}

		for (StrongBee b : wave) // move all the bees in
		{
			int randExit = (int) (Math.random() * exits.length);
			b.moveTo(exits[randExit]); // move b to a random exit from the hive (entrance to the colony)
		}
		return wave; // return who invaded
	}

	/**
	 * Adds a wave of attacking bees to this hive
	 *
	 * @param attackTime
	 *            When the bees will attack
	 * @param numBees
	 *            The number of bees to attack
	 */
	public void addWave (int attackTime, int numBees) {
		Bee[] bees = new Bee[numBees];
		for (int i = 0; i < bees.length; i++) {
			bees[i] = new Bee(beeArmor);
			this.addInsect(bees[i]); // put the bee in Place
		}
		waves.put(attackTime, bees);
	}
	
	public void addWaveSB (int attackTime, int numBees) { //On crée un addwave pour les strongbees calqué sur le premier
		StrongBee[] bees = new StrongBee[numBees];
		for (int i = 0; i < bees.length; i++) {
			bees[i] = new StrongBee(beeArmor*2);
			this.addInsectSB(bees[i]); // put the bee in Place
		}
		strongwaves.put(attackTime, bees);
	}
	


	public Bee[] getAllBees () {
		ArrayList<Bee> bees = new ArrayList<Bee>(); // easy temp work
		for (Bee[] wave : waves.values()) {
			for (int i = 0; i < wave.length; i++) {
				bees.add(wave[i]);
			}
		}
		return bees.toArray(new Bee[0]);
	}
	
	public StrongBee[] getAllBeesSB () {
		ArrayList<StrongBee> bees = new ArrayList<StrongBee>(); // easy temp work
		for (StrongBee[] wave : strongwaves.values()) {
			for (int i = 0; i < wave.length; i++) {
				bees.add(wave[i]);
			}
		}
		return bees.toArray(new StrongBee[0]);
	}

	public static Hive makeTestHive () {   //LEVEL 0
		Hive hive = new Hive(2);
		hive.addWave(1, 2);
		hive.addWave(2, 4);
		return hive;
	}

	public static Hive makeTestHiveSB () {   //A lil bit harder. Some Strongbees //LEVEL 1
		Hive hive = new Hive(2);
		hive.addWave(1, 3);
		hive.addWaveSB(2, 1);
		hive.addWave(1, 3);
		return hive;
	}


	public static Hive makeFullHive () { //LEVEL2
		Hive hive = new Hive(3);
		hive.addWave(2, 1);
		 
		for (int i = 3; i < 15; i += 2) {
			hive.addWave(i, 1);
			hive.addWaveSB(1,1);
		}
		hive.addWave(15, 8);
		return hive;
	}
	
	
	public static Hive makeInsaneHive () { //LEVEL 3
		Hive hive = new Hive(4);

		
		for (int i = 3; i < 15; i++) {
			hive.addWave(i, 1);
			hive.addWaveSB(1, 1);
		}
		hive.addWave(15, 20);
		hive.addWaveSB(1, 2); // On ne met que Une ou deux strongbees sinon le jeu devient trop difficile.
//		hive.addWaveSB(1,3);
		return hive;
	}
}
