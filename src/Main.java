import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame object = new JFrame();
		Game game = new Game();
		
		//setting up application window sizes
		object.setBounds(10, 10, 900, 700);
		object.setBackground(Color.DARK_GRAY);
		object.setVisible(true);
		object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		object.setResizable(false);
		object.add(game);

	}

}
