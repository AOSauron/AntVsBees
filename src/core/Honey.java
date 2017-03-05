package core;

//Nouvelle place qui galvanise l'abeille (dommages x2). La honey ant ramasse 2 fois plus de nourriture dessus.
public class Honey extends Place {

	public Honey(String name, Place exit) {
		super(name, exit);
		//Galvanise:
		if (this.getAnt() != null) {
			this.getAnt().setDamage((this.getAnt().getDamage())*2);
		}
	}

}
