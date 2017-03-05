package ants;

import core.Ant;
import core.AntColony;

public class HarvesterAnt extends Ant {

	public HarvesterAnt () {
		super(1,2);		
	}

	@Override
	public void action (AntColony colony) {
		colony.increaseFood(1);
	}
}
