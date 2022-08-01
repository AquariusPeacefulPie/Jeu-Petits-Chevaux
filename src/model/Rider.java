package model;
import java.util.ArrayList;

public class Rider {
	/**************		ATTRIBUTS DE LA CLASSE ET CONSTRUCTEUR(S)		**************/
	
	private int currInstruction;
	private int x;
	private int y;
	private char symbol; 
	private int nb_turn_stucked;
	private boolean stucked_even;
	private boolean stucked_odd;
	private boolean reset;
	private boolean inStable;
	private ArrayList<int[]> course;
	
	
	/**
	 * Constructeur de la classe Rider
	 * @param p		symbol du cavalier ('R' ou 'B')
	 */
	public Rider(char p) {
		symbol = p;
		x = y = nb_turn_stucked = currInstruction = 0;
		stucked_even = stucked_odd = reset = false;
		inStable = true;
		course = new ArrayList<int[]>();
	}
	
	
	/**************		METHODES SPECIFIQUES A LA CLASSE		**************/
	
	
	/**
	 * Crée un couple de coordonnées x,y d'entiers
	 * @param x		coordonnée en x
	 * @param y		coordonnée en y
	 * @return		renvoie un tableau de 2 entiers représentant les coordonnées (x en position 0, y en position 1)
	 */
	public static int[] createCoordinates(int x, int y) {
		int[] coord = new int[2];
		coord[0] = x;
		coord[1] = y;
		return coord;
	}
	
	/**
	 * Initialise un parcours pour un cavalier
	 * @param symb		symbole du cavalier ('R' ou 'B')
	 * @param length	longueur du plateau
	 */
	public void initCourse(char symb, int length) {
		if(symb=='R') {
			course.add(createCoordinates(1,1));
			for(int i = 2; i<length-1 ; ++i) {
				course.add(createCoordinates(i,1));
			}
			for(int i = 2; i<5; ++i) {
				course.add(createCoordinates(length-2,i));
			}
			for(int i = length-2; i>0 ; --i) {
				course.add(createCoordinates(i,5));
			}
			for(int i = 4; i>2; --i) {
				course.add(createCoordinates(1,i));
			}
			for(int i = 2; i<7; ++i) {
				course.add(createCoordinates(i,3));
			}
		}
		else {
			course.add(createCoordinates(length-2,5));
			for(int i = length-3; i>0 ; --i) {
				course.add(createCoordinates(i,5));
			}
			for(int i = 4; i>0; --i) {
				course.add(createCoordinates(1,i));
			}
			for(int i = 2; i<length-1 ; ++i) {
				course.add(createCoordinates(i,1));
			}
			for(int i = 2; i<4; ++i) {
				course.add(createCoordinates(length-2,i));
			}
			for(int i = length-3; i>length-8; --i) {
				course.add(createCoordinates(i,3));
			}
		}
	}
	
	
	
	/**************		GETTERS ET SETTERS 		**************/
	
	
	/**
	 * Active ou non une action spécifique pour le cavalier lorsque celui-ci arrive sur une case
	 * @param action	action considéré (O : renvoyer au départ, F : tomber dans un trou, H : bloqué dans une haie, R : tombé dans la rivière)
	 */
	public void setAction(char action, boolean status) {
		switch(action) {
		case 'O':
			reset = status;
		break;
		case 'F':
			nb_turn_stucked = 2;
		break;
		case 'H':
			stucked_odd = status;
		break;
		case 'R':
			stucked_even = status;	
		break;
		default:
			inStable = status;
		}
	}
	
	/**
	 * Etabli le nombre de tours pendant lequel le cavalier sera bloqué
	 * @param nb
	 */
	public void setNbTurnStucked(int nb) {
		nb_turn_stucked = nb;
	}
	
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Met à jour l'instruction courante
	 * @param nb	index de la nouvelle instruction
	 */
	public void setCurrInstruction(int nb) {
		currInstruction = nb;
	}
	
	
	public int getCoordX() {
		return x;
	}
	
	public int getCoordY() {
		return y;
	}
	
	public boolean getStuckedEven() {
		return stucked_even;
	}
	
	public boolean getStuckedOdd() {
		return stucked_odd;
	}
	
	public boolean getInStable() {
		return inStable;
	}
	
	public int getNbTurnStucked() {
		return nb_turn_stucked;
	}
	
	public boolean getReset() {
		return reset;
	}
	
	public char getSymbol() {
		return symbol;
	}
	
	public ArrayList<int[]> getCourse(){
		return course;
	}
	
	public int getCurrInstruction() {
		return currInstruction;
	}
}
