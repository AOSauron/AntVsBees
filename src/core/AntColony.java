package core;

import java.util.ArrayList;
import ants.ContainingAnt;
import ants.QueenAnt;

/**
 * An entire colony of ants and their tunnels.
 *
 * @author Joel
 * @version Fall 2014
 */

public class AntColony {

	public static final String QUEEN_NAME = "AntQueen"; // name of the Queen's place
	public static final int MAX_TUNNEL_LENGTH = 12;
	
	public static int TUNNELENGTH;  //On ajoute �a + l.40 pour y acc�der dans la LongThrowerAnt

	private int food; // amount of food available
	private Place queenPlace; // where the queen is
	private ArrayList<Place> places; // the places in the colony
	private ArrayList<Place> beeEntrances; // places which bees can enter (the starts of the tunnels)

	
	/**
	 * Creates a new ant colony with the given layout.
	 *
	 * @param numTunnels
	 *            The number of tunnels (paths)
	 * @param tunnelLength
	 *            The length of each tunnel
	 * @param moatFrequency
	 *            The frequency of which moats (water areas) appear. 0 means that there are no moats
	 * @param startingFood
	 *            The starting food for this colony.
	 */
	public AntColony (int numTunnels, int tunnelLength, int moatFrequency, int startingFood) {
		// simulation values
		food = startingFood;
		TUNNELENGTH = tunnelLength;

		// init variables
		places = new ArrayList<Place>();
		beeEntrances = new ArrayList<Place>();
		queenPlace = new Place(QUEEN_NAME); // magic variable namexw

		tunnelLength = Math.min(tunnelLength, MAX_TUNNEL_LENGTH); // don't go off the screen!
		// set up tunnels, as a kind of linked-list
		Place curr, prev; // reference to current exit of the tunnel
		for (int tunnel = 0; tunnel < numTunnels; tunnel++) {
			curr = queenPlace; // start the tunnel's at the queen
			for (int step = 0; step < tunnelLength; step++) {
				prev = curr; // keep track of the previous guy (who we will exit to)
				
				//Honey plac�e au milieu du tunnel
				/**if (tunnelLength/2==step) {
					curr = new Honey("tunnel[" + tunnel + "-" + step + "]", prev);
					prev.setEntrance(curr);
					places.add(curr);
				}*/
				
				if (moatFrequency==0) {
				curr = new Place("tunnel[" + tunnel + "-" + step + "]", prev); // create new place with an exit that is the previous spot
				prev.setEntrance(curr); // the previous person's entrance is the new spot
				places.add(curr); // add new place to the list
				}
				if (moatFrequency!=0) {//Ne pas oublier sinon erreur division par 0!
					if (moatFrequency==1) {
						curr = new Water("tunnel[" + tunnel + "-" + step + "]", prev); // create new place with an exit that is the previous spot
						prev.setEntrance(curr); // the previous person's entrance is the new spot
						places.add(curr); // add new place to the list
					}
					else {
						if (step%moatFrequency==0) {
							curr = new Water("tunnel[" + tunnel + "-" + step + "]", prev); // create new place with an exit that is the previous spot
							prev.setEntrance(curr); // the previous person's entrance is the new spot
							places.add(curr); // add new place to the list
						} else {
							curr = new Place("tunnel[" + tunnel + "-" + step + "]", prev); // create new place with an exit that is the previous spot
							prev.setEntrance(curr); // the previous person's entrance is the new spot
							places.add(curr); // add new place to the list
						}
					}
				}// Modifs de 50�74
			}
			beeEntrances.add(curr); // current place is last item in the tunnel, so mark that it is a bee entrance
		} // loop to next tunnel

	}

	/**
	 * Returns an array of Places in this colony. Places are ordered by tunnel, with each tunnel's places listed start to end.
	 *
	 * @return The tunnels in this colony
	 */
	public Place[] getPlaces () {
		return places.toArray(new Place[0]);
	}

	/**
	 * Returns an array of places that the bees can enter into the colony
	 *
	 * @return Places the bees can enter
	 */
	
	
	public Place[] getBeeEntrances () {
		return beeEntrances.toArray(new Place[0]);
	}

	/**
	 * Returns the queen's location
	 *
	 * @return The queen's location
	 */
	
	public Place getQueenPlace () {
		return queenPlace;
	}

	public void setQueenPlace(Place p) { // On rajoute le setter pour la queen (voir QueenPlace)
		queenPlace=p;       
	}
	
	/**
	 * Returns the amount of available food
	 *
	 * @return the amount of available food
	 */
	
	public int getFood () {
		return food;
	}

	/**
	 * Increases the amount of available food
	 *
	 * @param amount
	 *            The amount to increase by
	 */
	public void increaseFood (int amount) {
		food += amount;
	}
	

	/**
	 * Returns if there are any bees in the queen's location (and so the game should be lost)
	 *
	 * @return if there are any bees in the queen's location
	 */
	
	public boolean queenHasBees () {
		return queenPlace.getBees().length > 0;
	}

	// place an ant if there is enough food available
	/**
	 * Places the given ant in the given tunnel IF there is enough available food. Otherwise has no effect
	 *
	 * @param place
	 *            Where to place the ant
	 * @param ant
	 *            The ant to place
	 */
	
	public void deployAnt (Place place, Ant ant) {		
		/**if (food >= ant.getFoodCost() && ant instanceof QueenAnt) {
			food -= ant.getFoodCost();
				if (QueenAnt.getInstance() != null) {
					place.addInsect(QueenAnt.getInstance());
				}
		}
		else*/ if ((food >= ant.getFoodCost() && place.getAnt() == null) || (food >= ant.getFoodCost() && place.getAnt() instanceof ContainingAnt && !(ant instanceof ContainingAnt)) || (food >= ant.getFoodCost() && !(place.getAnt() instanceof ContainingAnt) && ant instanceof ContainingAnt)) {
			if (place instanceof Water && ant.getWatersafe()==true) {
				food -= ant.getFoodCost();
				place.addInsect(ant);
			} else if (place instanceof Water && (ant.getWatersafe()==false)) {
				System.out.println("This ant can't swim!! " + ant); // Si la fourmi n'est pas watersafe
				
			} else {
			food -= ant.getFoodCost();
			place.addInsect(ant);
			}
		}
		else {
			System.out.println("Not enough food remains to place " + ant + " or already an ant in there");
			if (ant instanceof QueenAnt) {
				((QueenAnt) ant).decrementnbQueen(); //d�cr�menter nbQueen puisque elle a �t� instanci�e mais pas plac�e dans le jeu (n'existe donc pas r�ellement)
			}
		}
	}

	
	/**
	 * Removes the ant inhabiting the given Place
	 *
	 * @param place
	 *            Where to remove the ant from
	 */
	public void removeAnt (Place place) {
		if (place.getAnt() != null && !(place.getAnt() instanceof NonRemovableAnt)) { //Removable manuellement seulement si n'est pas une NonRemovableAnt
			place.removeInsect(place.getAnt());
		} else {
			System.out.println("Can't place " + place.getAnt() + " !");
		}
	}

	/**
	 * Returns a list of all the ants currently in the colony
	 *
	 * @return a list of all the ants currently in the colony
	 */
	public ArrayList<Ant> getAllAnts () {
		ArrayList<Ant> ants = new ArrayList<Ant>();
		for (Place p : places) {
			if (p.getAnt() != null) {
				if (p.getAnt() instanceof ContainingAnt) { //Si la fourmi sur la place est une containing
				//	ants.add(p.getAnt()); //Ajoute � la liste la fourmi contenante. (Voir l.197, d�ja l�)
					if (((ContainingAnt) p.getAnt()).ObtainInsect() != null){
						ants.add(((ContainingAnt) p.getAnt()).ObtainInsect());//ET SON CONTENU
					} 
				}
				ants.add(p.getAnt());
			}
		}
		return ants;
	}

	/**
	 * Returns a list of all the bees currently in the colony
	 *
	 * @return a list of all the bees currently in the colony
	 */
	public ArrayList<Bee> getAllBees () {
		ArrayList<Bee> bees = new ArrayList<Bee>();
		for (Place p : places) {
			for (Bee b : p.getBees()) {
				bees.add(b);
			}
		}
		return bees;
	}
	
	public ArrayList<StrongBee> getAllBeesSB () {
		ArrayList<StrongBee> bees = new ArrayList<StrongBee>();
		for (Place p : places) {
			for (StrongBee b : p.getBeesSB()) {
				bees.add(b);
			}
		}
		return bees;
	}

	@Override
	public String toString () {
		return "Food: " + food + "; " + getAllBees() + "; " + getAllAnts();
	}

	public void setQueenPlace(QueenPlace q) {
		queenPlace = q;
		
	}
}
