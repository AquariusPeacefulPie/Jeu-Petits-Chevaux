package model;

import java.util.ArrayList;

public class Game {
	private Board board;
	private boolean rider_turn;
	private int draw;
	
	public Game(int l) {
		//Création de deux cavaliers
		Rider r1 = new Rider('R');
		Rider r2 = new Rider('B');
		
		r1.initCourse('R', l);
		r2.initCourse('B', l);
		board = new Board(l,r1,r2);
	}
	

	/**
	 * Joue le tour d'un cavalier
	 * @param r		cavalier concerné
	 * @param step	nombre de case à déplacer le cavalier (0 si tirage aléatoire)
	 */
	public void playTurn(Rider r, int step) {
		
		boolean play = true;
		
		//Vérification si le cavalier n'est pas retourné à la case départ
		if(r.getReset()&&r.getInStable()==false) {
			//Remise à la case départ du cavalier
			r.setCurrInstruction(0);
			ArrayList<Cell> tmpList = board.getBoard().get(r.getCourse().get(0)[0]);
			Occupable tmpCell = (Occupable) tmpList.get(r.getCourse().get(0)[1]);
			tmpCell.addRider(r);
			r.setAction('O', false);
		}
		
		//Vérification que le cavalier n'est pas bloqué dans un trou
		if(r.getNbTurnStucked()!=0) {
			r.setNbTurnStucked(r.getNbTurnStucked()-1);
			return;
		}
		
				
		//Mise a jour du jeu
		this.update();
		
		
		String color = (r.getSymbol()=='R')? "ROUGE" : "BLEU";
		//Annonce du cavalier jouant
		System.out.println("\nLe cavalier de couleur "+color+" joue\n");
		
		
		//Tirage du dé
		
		draw = (step==0) ? Dice.draw() : step;
			
		System.out.println("Valeur du dé : "+draw+"\n");
		
		//Vérification que le tirage n'excède pas le nombre d'instruction maximale
		if(draw+r.getCurrInstruction()>r.getCourse().size()-1) {
			draw = (r.getCourse().size()-1)-r.getCurrInstruction();
		}
		//Vérification que le cavalier n'est pas à l'écurie
		if(r.getInStable()) {
			if(draw==6) {
				r.setAction('I', false);
				//Instruction chargée avant
				r.setAction('O', true);
				board.moveRider(0, r);
				return;
			}
			else {
				return;
			}
		}
	
		//Vérification si le cavalier est bloqué dans la rivière ou la haie
		if(r.getStuckedOdd()||r.getStuckedEven()) {
			if(r.getStuckedEven()&&draw%2==0) {
				r.setAction('R', false);
				board.moveRider(draw, r);
				play = false;
			}
			else if(r.getStuckedOdd()&&draw%2==1){
				r.setAction('H', false);
				board.moveRider(draw, r);
				play = false;
			}
			else {
				//Mise a jour du jeu
				this.update();
				return;
			}
		}
		if(play) {
			//Déplacement du cavalier du nombre de cases tirées
			board.moveRider(draw, r);
		}
		
		//Mise à jour de l'index de l'instruction courante du cavalier
		if(r.getCurrInstruction()+draw>r.getCourse().size()-1) {
			r.setCurrInstruction(r.getCourse().size()-1);
		}
		else {
			r.setCurrInstruction(r.getCurrInstruction()+draw);
		}
		
		
	}
	
	/**
	 * Affiche le status des cavaliers
	 * @return	une chaîne contenant les informations des positions des cavaliers
	 */
	public String printSummary() {
		String res = "";
		for(int i = 0; i<2; ++i) {
			char symbolRider = board.getRiderList().get(i).getSymbol();
			Rider r = board.getRiderList().get(i);
			//Affichage du cavalier concerné
			if(symbolRider=='R') {
				res+="R ROUGE ";
			}else {
				res+="B BLEU ";
			}
			//Affichage de la situation du cavalier courant
			if(r.getInStable()) {
				res+="dans l'écurie	";
			}
			else if(r.getStuckedEven()) {
				res+="dans la rivière	";
			}
			else if(r.getStuckedOdd()) {
				res+="dans la haie	";
			}
			else if(r.getNbTurnStucked()!=0){
				res+="dans le trou	";
			}
			else if(r.getReset()) {
				res+="case départ	";
			}
			else {
				res+="case neutre	";
			}
		}
		return res;
	}
	
	/**
	 * Réalise l'affichage du plateau ainsi que des différents commentaires résumant la partie d'un tour en cours
	 */
	public void update() {
		//Affichage du plateau
		board.displayBoard();
		//Affichage du résumé de la partie
		System.out.println(this.printSummary());	
	}
	
	/**
	 * Teste si un cavalier a atteint l'arrivée
	 * @param r		cavalier considéré
	 * @return		renvoie vrai si le cavalier a atteint la ligne d'arrivé, faux sinon
	 */
	public boolean testWin(Rider r) {
		return (r.getCoordX()==r.getCourse().get(r.getCourse().size()-1)[0])&&(r.getCoordY()==r.getCourse().get(r.getCourse().size()-1)[1]);
	}
	
	/**
	 * Affiche le gagnant de la partie
	 * @param r		cavalier gagnant
	 * @return		renvoie une chaîne affichant le gagnant de la partie
	 */
	public String printWinner(Rider r) {
		String winner = (r.getSymbol()=='R')? "ROUGE" : "BLEU";
		String res = "Le cavalier "+winner+" à remporté la partie\n";
		board.displayBoard();
		return res;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public boolean getRiderTurn() {
		return rider_turn;
	}
	
	public int getDraw() {
		return draw;
	}
	
	public void setRiderTurn(boolean status) {
		rider_turn = status;
	}
	
}