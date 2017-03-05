package core;

/**
 * A driver for the Ants vs. Some-Bees game
 */
//N 'EST PLUS LA CLASS MAIN !! C'EST MENU.JAVA
public class AntsVsSomeBees {
	
	public static int tunnel=1;
	public static int food=30;
	public static int moatfrequency=2;
	public static int honeyfrequency=4;
	public static int tunnellength=9;
	
	public static int level=3;

	public static void play() { //ce n'est plus le main puisque c'est le menu le main!
		//AntColony colony = new AntColony(tunnel, tunnellength, moatfrequency, honeyfrequency, food); // specify the colony ]tunnels, length, moats, food]
		Hive hive=Hive.makeTestHive(); // specify the attackers (the hive)
		
		if (level==0) {			//Niveau 0 que des bees
			moatfrequency=5;
			AntColony colony = new AntColony(tunnel, tunnellength, moatfrequency, food);
			hive = Hive.makeTestHive();
			new AntGame(colony, hive);
		} else if (level==1) {		//Niveau 1 quelques bees et quelques strongbees	
			AntColony colony = new AntColony(tunnel, tunnellength, moatfrequency, food);
			hive = Hive.makeTestHiveSB();
			new AntGame(colony, hive);
		}
		else if (level==2) {    //Ca se corse
			AntColony colony = new AntColony(tunnel, tunnellength, moatfrequency, food);
			hive = Hive.makeFullHive();
			new AntGame(colony, hive);
		} else if (level==3) {		//DUR
			AntColony colony = new AntColony(tunnel, tunnellength, moatfrequency, food);
			hive = Hive.makeInsaneHive();
			new AntGame(colony, hive);
		}
		//new AntGame(colony, hive); // launch the game
	}
}
