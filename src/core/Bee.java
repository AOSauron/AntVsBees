package core;


public class Bee extends Insect {

	private static final int DAMAGE = 1;



	public Bee (int armor) {
		super(armor);
		watersafe=true;  //Ajout. Par défaut vrai
	}

	public void sting (Ant ant) {
		ant.reduceArmor(DAMAGE);
	}

	
	public void moveTo (Place place) {
		this.place.removeInsect(this);
		place.addInsect(this);
	}

	@Override
	public void leavePlace () {
		place.removeInsect(this);
	}

	
	public boolean isBlocked () {
		if (place == null) { //Sinon ne fonctionne pas avec plusieurs abeilles sur la FireAnt
			return true;
		}
		if(place.getAnt() != null && place.getAnt().getBlockingAnt()==true) { 
			return false;
		}else{
			return place.getAnt() != null;
		}
	}

	
	@Override
	public void action (AntColony colony) {
		//On prend en compte les parametres des Thrower Stun et Slow qui "handicapent l'abeille pdt un nombre de tours
		if(StunThrowerCompteur==0){
			if(SlowThrowerCompteur==0){
				if (isBlocked() && place != null) {
					sting(place.getAnt());
				}
				else if (armor > 0) {
					moveTo(place.getExit());
				}
				else {
					if (isBlocked()) {

					}
					else if (armor > 0) {
						SlowThrowerCompteur--;
						moveTo(place.getExit());
					}
				}
			}
			else
				StunThrowerCompteur--;
		}
	}
}

