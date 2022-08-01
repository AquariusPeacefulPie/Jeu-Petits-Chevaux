import view.Window;
import java.util.Scanner;


public class goMain {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez entrer la taille du plateau : ");
		int length = sc.nextInt();
		Window window = new Window(length);	
		window.setVisible(true);
		
		sc.close();
	}
}