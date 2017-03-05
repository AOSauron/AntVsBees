package core;


public class QueenPlace extends Place {


	public QueenPlace(){
		super("QueenPlace");
			}
	
	public QueenPlace(String name) {
		super(name);
		
	}
	
	public QueenPlace(String name, Place exit) {
		super(name,exit);
	}
}
