package ants;

import core.Ant;
import core.AntColony;
import core.NonRemovableAnt;

//Fourmis muraille enracin�e (donc non supprimable) qui r�colte de la nourriture �galement

public class RootedAnt extends Ant implements NonRemovableAnt {

	public RootedAnt() {
		super(5, 6);
	}

	@Override
	public void action(AntColony colony) {
		colony.increaseFood(1);
	}
	
}
