package controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import model.Board;
import model.Cell;
import model.Game;
import view.GraphicalCell;
import view.Window;


public class Controller implements ActionListener{
	private Window w;
	private GraphicalCell[][] board;
	private Game game;

	
	public Controller(Window w) {
		this.w = w;
		board = new GraphicalCell[w.getLengthBoard()][7];
		game = new Game(w.getLengthBoard());
		updateBoard(game.getBoard());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==w.getDraw()||e.getSource()==w.getManualDraw()) {			
			if(!(game.testWin(game.getBoard().getRiderList().get(0)))&&!(game.testWin(game.getBoard().getRiderList().get(1)))) {
				//Détermination du cavalier jouant le tour
				int draw = (e.getSource()==w.getManualDraw())? Integer.parseInt(w.getNumber().getText()) : 0;
				if(game.getRiderTurn()) {
					w.setRiderTurn("Le cavalier de couleur ROUGE joue");
					game.playTurn(game.getBoard().getRiderList().get(0),draw);
					game.setRiderTurn(false);
				}
				else {
					w.setRiderTurn("Le cavalier de couleur BLEU joue");
					game.playTurn(game.getBoard().getRiderList().get(1),draw);
					game.setRiderTurn(true);
				}	
			}
			else {
				String symb = !(game.getRiderTurn()) ? "ROUGE" : "BLEU"; 
				w.setRiderTurn("Le cavalier "+symb+" a gagné");
				w.getDraw().setEnabled(false);
				w.getManualDraw().setEnabled(false);
				w.getNumber().setEnabled(false);
			}	
			//Mise à jour du plateau graphique
			updateBoard(game.getBoard());
			w.getGridLayout().removeAll();
			w.setBoard(w.getLengthBoard(),w.getGridLayout(),this);
			w.setRider1Status(game.getBoard().getRiderStatus());
			w.setRider2Status(game.printSummary());
			//Affichage du nombre tiré
			board[w.getLengthBoard()/2][3].setLabel(game.getDraw());
			//Affichage du plateau
			w.setVisible(true);
		}
	}
	
	/**
	 * 	Initialise un tableau de cellules graphique à partir d'un plateau de cases
	 * @param board		plateau de cases à initialiser
	 */
	public void updateBoard(Board board) {
		int i=0;
		int j=0;
		for(ArrayList<Cell> cell : board.getBoard()) {
			j=0;
			for(Cell tmp : cell) {
				if(i!=0&&i!=board.getLength()-1&&j!=0&&j!=6) {
					GraphicalCell cellTmp = new GraphicalCell(tmp,board.getLength());
					cellTmp.setBorder(BorderFactory.createLineBorder(Color.black));
					this.board[i][j] = cellTmp;
				}
				else {
					this.board[i][j] = new GraphicalCell(tmp,board.getLength());
				}			
				j++;
			}
			i++;
		}
	}
		
	/***********	GETTERS		**********/
	
	public GraphicalCell[][] getBoard(){
		return board;
	}

	
}
