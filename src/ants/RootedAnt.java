package ants;

import core.Ant;
import core.AntColony;
import core.NonRemovableAnt;

//Fourmis muraille enracinée (donc non supprimable) qui récolte de la nourriture également

public class RootedAnt extends Ant implements NonRemovableAnt {

	public RootedAnt() {
		super(5, 6);
	}

	@Override
	public void action(AntColony colony) {
		colony.increaseFood(1);
	}
	
}
