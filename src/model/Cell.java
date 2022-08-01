package model;

public abstract class Cell {
	protected char symbol;
	protected int coordX;
	protected int coordY;
	
	public char getSymbol() {
		return symbol;
	}
	
	public int getCoordX() {
		return coordX;
	}
	
	public int getCoordY() {
		return coordY;
	}
}
