package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Cell;
import model.CellFree;
import model.CellHedge;
import model.CellHole;
import model.CellRiver;
import model.CellSide;
import model.CellStable;
import model.CellStart;
import model.CellWhite;

public class GraphicalCell extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	private JLabel label;
	
	
	/**
	 * Construit une case spécifique
	 * @param type	Type de la case
	 */
	public GraphicalCell(Cell cell, int length_board){
		//Case départ
		if(cell instanceof CellStart) {
			color = Color.pink;
		}
		//Case écurie
		else if(cell instanceof CellStable) {
			color = Color.green;
		}
		//Case libre
		else if(cell instanceof CellFree) {
			color = Color.lightGray;
		}
		//Case 
		else if(cell instanceof CellSide) {
			color = Color.green;
		}
		else if(cell instanceof CellWhite) {
			color = Color.white;
		}
		else if(cell instanceof CellRiver) {
			color = Color.blue;
		}
		else if(cell instanceof CellHedge) {
			color = Color.darkGray;
		}
		else if(cell instanceof CellHole) {
			color = Color.yellow;
		}
		else {
			color = Color.cyan;
		}
		
		this.setBackground(color);
		label = new JLabel();
		if(cell.getSymbol()=='R'||cell.getSymbol()=='B') {
			String symb = cell.getSymbol()=='R'? "R" : "B";
			label.setText(symb);
		}
		//Affichage du mot "Dé" sur une case
		if((cell.getCoordX()==(length_board-1)/2)&&cell.getCoordY()==3) {
			label.setText("Dé");
		}
		this.add(label);
	}
	
	/**********	GETTERS ET SETTERS **********/
	public void setColor(Color color) {
		this.color = color; 
	}
	
	public Color getColor() {
		return color;
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public void setLabel(int nb) {
		label.setText(Integer.toString(nb));
	}
}
