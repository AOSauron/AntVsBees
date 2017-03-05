package ants;

import core.AntColony;

public class BodyguardAnt extends ContainingAnt implements Containing {

	public BodyguardAnt () {
		super(2,5);			/// Super fait r�ference au constructeur de la classe m�re de celle-ci. 
		watersafe=true;   // La bodyguard peut d�fendre les Scuba 
	}

	@Override
	public void action (AntColony colony) {
//		super.ObtainInsect().action(colony);  // Cela enclanche le 'action' de la fourmi contenue (fire ou thrower)
									//pcq sinon le jeu comprend qu'il doit executer le action de la bodyguard. 
									//Donc le super reference le action de la fourmie contenue (dernier point de l'�nonc� de la 
									//bODYGUARD.
		
								//Non c'est redondant en fait pcq tout va dans colony !
			}
}
