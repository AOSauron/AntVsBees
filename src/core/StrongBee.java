package core;

public class StrongBee extends Bee {
	// Trouver un gif le mettre en 66x66 d'une abeille
	
	
	public StrongBee(int armor) {
		super(armor);  // 2x plus d'armure que l'abeille présente de base
	}
	
	public void moveTo (Place place) {
		this.place.removeInsectSB(this);
		place.addInsectSB(this);
	}

	@Override
	public void leavePlace () {
		place.removeInsectSB(this);
	}
	
}
