import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, ActionListener{

	private int[] snakeX = new int[625];
	private int[] snakeY = new int[625];
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	
	private ImageIcon upimg;
	private ImageIcon downimg;
	private ImageIcon leftimg;
	private ImageIcon rightimg;
	private ImageIcon snakebodyimg;
	private int snakelength = 3;
	private int move = 0;
	
	private Timer timer;
	private int delay = 100;
	
	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	//draws on the panel
	public void paint(Graphics graphic) {
		if(move == 0) {
			snakeX[2] = 50;
			snakeX[1] = 75;
			snakeX[0] = 100;
			snakeY[2] = 100;
			snakeY[1] = 100;
			snakeY[0] = 100;
		}
		//game border
		graphic.setColor(Color.WHITE);
		graphic.drawRect(20, 20, 850, 625);
		//background
		graphic.setColor(Color.BLACK);
		graphic.drawRect(21, 21, 848, 623);
		
		rightimg = new ImageIcon("right.png");
		rightimg.paintIcon(this, graphic, snakeX[0], snakeY[0]);
		
		for(int i=0; i < snakelength; i++) {
			if(i==0 && right) {
				rightimg = new ImageIcon("right.png");
				rightimg.paintIcon(this, graphic, snakeX[i], snakeY[i]);
			}
			
			if(i==0 && left) {
				leftimg = new ImageIcon("left.png");
				leftimg.paintIcon(this, graphic, snakeX[i], snakeY[i]);
			}
			
			if(i==0 && up) {
				upimg = new ImageIcon("up.png");
				upimg.paintIcon(this, graphic, snakeX[i], snakeY[i]);
			}
			
			if(i==0 && down) {
				downimg = new ImageIcon("down.png");
				downimg.paintIcon(this, graphic, snakeX[i], snakeY[i]);
			}
			
			if(i!=0) {
				snakebodyimg = new ImageIcon("body.png");
				snakebodyimg.paintIcon(this, graphic, snakeX[i], snakeY[i]);
			}
		}
		
		graphic.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
