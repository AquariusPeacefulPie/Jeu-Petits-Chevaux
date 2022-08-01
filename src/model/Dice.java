package model;


public class Dice {
	/**
	 * Tire un nombre entre 1 et 6
	 * @return	le nombre tiré au hasard
	 */
	public static int draw() {
		return (int)((Math.random()*6)+1);
	}
}
