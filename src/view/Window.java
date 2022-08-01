package view;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Controller;


public class Window extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	/********** ATTRIBUTS **********/
	private int length_board;
		
	private JLabel rider1_status;
	private JLabel rider2_status;
	private JLabel rider_turn;
	private JPanel center;
	
	private JButton draw;
	private JButton manual_draw;
	
	private JTextField number;
	
	private Controller cont;
	
	
	public Window(int length) {
		length_board = length;
		
		
		
		setTitle("Petits Chevaux");
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Récupération du conteneur de la fenêtre
		Container c = this.getContentPane();
		
		c.setLayout(new BorderLayout());
		
		//Création des différents principaux JPanel
		JPanel top = new JPanel();
		
		center = new JPanel();
		center.setLayout(new GridLayout(7,length));
		
		JPanel bottom = new JPanel();
		
		rider1_status = new JLabel("",SwingConstants.CENTER);
		rider2_status = new JLabel("",SwingConstants.CENTER);
		rider_turn = new JLabel();
		
		
		top.setLayout(new BorderLayout());
		
		//Panel pour le tour du cavalier
		JPanel panel_rider_turn = new JPanel();
		panel_rider_turn.setLayout(new FlowLayout());
		
		//Création d'un panel pour le bouton de lancé du dé
		JPanel panel_button = new JPanel();
		panel_button.setLayout(new FlowLayout());
		draw = new JButton("rouler le dé");
		
		panel_button.add(draw);
		panel_rider_turn.add(rider_turn);
		
		//Ajout des boutons dans le panel du haut
		top.add(panel_button,BorderLayout.NORTH);
		top.add(panel_rider_turn,BorderLayout.SOUTH);
		
		manual_draw = new JButton("exécuter mouvement");
		panel_button.add(manual_draw);
		
		number = new JTextField("");
		panel_button.add(number);
        number.setPreferredSize(new Dimension(20, 20));

		
		
		bottom.setLayout(new BorderLayout());
		
		//Ajout des panel de status des cavaliers dans le panel du bas
		bottom.add(rider1_status,BorderLayout.NORTH);
		bottom.add(rider2_status,BorderLayout.SOUTH);


		//Ajout des différents panel dans le conteneur principal
		c.add(top,BorderLayout.NORTH);
		c.add(center,BorderLayout.CENTER);
		c.add(bottom,BorderLayout.SOUTH);
		
		//Création d'un controleur pour gérer les actions entre le jeu et la fenêtre graphique
		cont = new Controller(this);
		draw.addActionListener(cont);
		manual_draw.addActionListener(cont);
		
		setBoard(length, center, cont);
		
		//Affichage de la fenêtre
		this.setVisible(true);
	}
	
	
	/********** GETTERS ET SETTERS **********/

	
	public JLabel getRider1Status() {
		return rider1_status;
	}
	
	public JLabel getRider2Status() {
		return rider2_status;
	}
	
	public JLabel getRiderTurn() {
		return rider_turn;
	}
	
	public JButton getDraw() {
		return draw;
	}
	
	public JButton getManualDraw() {
		return manual_draw;
	}
	
	public JTextField getNumber() {
		return number;
	}
	
	public int getLengthBoard() {
		return length_board;
	}
	
	public JPanel getGridLayout() {
		return center;
	}
	
	public Controller getController() {
		return cont;
	}
	
	public void setBoard(int length, JPanel panel, Controller cont) {
		for(int i = 0; i<7; ++i) {
			for(int j = 0; j<length; ++j) {
				panel.add(cont.getBoard()[j][i]);
			}
		}	
	}
	
	public void setRiderTurn(String turn) {
		rider_turn.setText(turn);
	}
	
	public void setRider1Status(String status) {
		rider1_status.setText(status);
	}
	
	public void setRider2Status(String status) {
		rider2_status.setText(status);
	}
}
