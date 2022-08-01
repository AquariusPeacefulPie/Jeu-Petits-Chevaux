package model;
import java.util.ArrayList;

public class Board{
	/***************	ATTRIBUTS		***************/
	
	private ArrayList<ArrayList<Cell>> board;
	private int length;
	private ArrayList<Rider> riders;
	private String rider_status;
	
	
	/**
	 * Constructeur du plateau
	 * @param length	longueur du plateau
	 * @param rider1	cavalier 1
	 * @param rider2	cavalier 2
	 */
	public Board(int length, Rider rider1, Rider rider2) {
		this.length = length;
		riders = new ArrayList<Rider>();
		riders.add(rider1);
		riders.add(rider2);
		rider_status = "";
		
		board = new ArrayList<ArrayList<Cell>>();
		for(int i = 0; i<length; ++i) {
			board.add(new ArrayList<Cell>());
		}
		
		
		
		for(int i = 0; i<length; ++i) {
			ArrayList<Cell> tmp = board.get(i);
			for(int j = 0; j<7; ++j) {	
				//Placement des cases cotés
				if(i==0||i==length-1||j==0||j==6) {
					if(j==0&&i==1) {
						tmp.add(j, new CellStable(i,j,'R',rider1));
					}
					else if(j==6&&i==length-2) {
						tmp.add(j, new CellStable(i,j,'B',rider2));
					}
					else {
						tmp.add(j, new CellSide(i,j));
					}
				}
				//Placement des cases départs
				else if(i==1&&j==1||i==length-2&&j==5) {
					tmp.add(j, new CellStart(i,j));
				}
				//Placement des cases arrivés
				else if((i==6&&j==3)||(i==length-7&&j==3)) {
					tmp.add(j, new CellFinish(i,j));
				}		
				//Placement des cases blanches
				else if(j==2&&(i>1&&i<length-2)||j==4&&(i>1&&i<length-2)||(j==3&&i>6)&&(j==3&&i<length-7)) {
					tmp.add(j, new CellWhite(i,j));
				}
				//Placement des pièges
				else if(j==1&&i==(length-1)/4) {
					tmp.add(j, new CellHole(i,j));
				}
				else if(j==5&&i==(length-1)/3) {
					tmp.add(j, new CellHedge(i,j));
				}
				else if(j==5&&i==((length-1)/4)*3) {
					tmp.add(j, new CellRiver(i,j));
				}
				//Placement des cases libres
				else {
					tmp.add(j, new CellFree(i,j));
				}
				
				

			}
		}

	}
	
	/***************	METHODES	***************/
	
	/**
	 * Affiche le plateau
	 */
	public void displayBoard() {
		for(int i = 0; i<7; ++i) {
			for(int j = 0; j<length; ++j) {
				System.out.print(board.get(j).get(i).getSymbol()+" ");
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Déplace un cavalier d'un nombre de case spécifié
	 * @param nb	nombre de cases que l'on souhaite déplacer le cavalier
	 * @param r		cavalier considéré
	 */
	public void moveRider(int nb, Rider r) {
		//Suppresion du cavalier de la case courante
		ArrayList<Cell> tmpListCurr = board.get(r.getCoordX());
		Occupable tmpCellCurr = (Occupable) tmpListCurr.get(r.getCoordY());
		tmpCellCurr.removeRider();
		
		//Récupération des coordonnées de l'instruction suivante
		int instX = r.getCourse().get(r.getCurrInstruction()+nb)[0];
		int instY = r.getCourse().get(r.getCurrInstruction()+nb)[1];
		
		//Récupération d'un référence correspondant à l'insctruction cible
		ArrayList<Cell> tmpListNext = board.get(instX);
		Occupable tmpCellNext = (Occupable) tmpListNext.get(instY);
		
		//Déclenchement de la méthode process
		String process = tmpCellNext.process(r);
		System.out.println(process);
		rider_status = process;
		
		//Ajout du cavalier sur sa nouvelle case
		tmpCellNext.addRider(r);
	}
	
	
	/**********		GETTERS		**********/
	
	public ArrayList<Rider> getRiderList(){
		return riders;
	}
	
	public ArrayList<ArrayList<Cell>> getBoard(){
		return board;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getRiderStatus() {
		return rider_status;
	}
}