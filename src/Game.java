import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

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
	private int snakelength = 1;
	private int move = 0;
	private boolean gameOver = false;
	
	private Timer timer;
	private int delay = 100;
	private int [] foodX = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int [] foodY = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	
	private ImageIcon foodImage;
	private Random random = new Random();
	private int xPosition = random.nextInt(34);
	private int yPosition = random.nextInt(23);
	
	private int score = 0;
	
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
		graphic.drawRect(20, 20, 851, 630);
		//background
		graphic.setColor(Color.BLACK);
		graphic.fillRect(21, 21, 850, 629);
		//score
		graphic.setColor(Color.WHITE);
		graphic.setFont(new Font("arial", Font.PLAIN, 14));
		graphic.drawString("Score: " + score, 25, 35);
		
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
		
		foodImage = new ImageIcon("food.png");
		
		if((foodX[xPosition] == snakeX[0]) && foodY[yPosition] == snakeY[0]) {
			score++;
			snakelength++;
			xPosition = random.nextInt(34);
			yPosition = random.nextInt(23);
		}
		foodImage.paintIcon(this, graphic, foodX[xPosition], foodY[yPosition]);
		
		if(gameOver == true) {
			right = false;
			left = false;
			up = false;
			down = false;
			graphic.setColor(Color.WHITE);
			graphic.setFont(new Font("arial", Font.BOLD, 50));
			graphic.drawString("Game Over", 300, 300);
			graphic.setFont(new Font("arial", Font.BOLD, 20));
			graphic.drawString("Press ENTER to restart", 350, 340);
		}
		
		for(int i = 1; i <snakelength; i++) {
			if(snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]) {
				right = false;
				left = false;
				up = false;
				down = false;
				graphic.setColor(Color.WHITE);
				graphic.setFont(new Font("arial", Font.BOLD, 50));
				graphic.drawString("Game Over", 300, 300);
				graphic.setFont(new Font("arial", Font.BOLD, 20));
				graphic.drawString("Press ENTER to restart", 350, 340);
			}
			
		}
		graphic.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		timer.start();
			if(right) {
				for(int i = snakelength-1; i>=0; i--) {
					snakeY[i+1] = snakeY[i];
				}
				for(int i = snakelength; i>=0; i--) {
					if(i==0) {
						snakeX[i] = snakeX[i] + 25;
					} else {
						snakeX[i] = snakeX[i-1];
					}
					if(snakeX[i] > 825) {
						right = false;
						left = false;
						up = false;
						down = false;
						gameOver=true;
					}
				}
				repaint();
			}
		
			if(left) {
				for(int i = snakelength-1; i>=0; i--) {
					snakeY[i+1] = snakeY[i];
				}
				for(int i = snakelength; i>=0; i--) {
					if(i==0) {
						snakeX[i] = snakeX[i] - 25;
					} else {
						snakeX[i] = snakeX[i-1];
					}
					if(snakeX[i] < 25) {
						right = false;
						left = false;
						up = false;
						down = false;
						gameOver=true;
					}
				}
				repaint();
			}
	
			if(up) {
				for(int i = snakelength-1; i>=0; i--) {
					snakeX[i+1] = snakeX[i];
				}
				for(int i = snakelength; i>=0; i--) {
					if(i==0) {
						snakeY[i] = snakeY[i] - 25;
					} else {
						snakeY[i] = snakeY[i-1];
					}
					if(snakeY[i] < 25) {
						right = false;
						left = false;
						up = false;
						down = false;
						gameOver=true;
					}
				}
				repaint();
			}
	
			if(down) {
				for(int i = snakelength-1; i>=0; i--) {
					snakeX[i+1] = snakeX[i];
				}
				for(int i = snakelength; i>=0; i--) {
					if(i==0) {
						snakeY[i] = snakeY[i] + 25;
					} else {
						snakeY[i] = snakeY[i-1];
					}
					if(snakeY[i] > 625) {
						right = false;
						left = false;
						up = false;
						down = false;
						gameOver=true;
					}
				}
				repaint();
			}		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_ENTER) {
			move = 0;
			score = 0;
			snakelength = 1;
			gameOver = false;
			repaint();
		}
		
		if(event.getKeyCode() == KeyEvent.VK_RIGHT) {
			move++;
			right = true;
			//prevent snake from going opposite direction to prevent collision
			if(!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}
			up= false; 
			down = false;
		}
		
		if(event.getKeyCode() == KeyEvent.VK_LEFT) {
			move++;
			left = true;
			//prevent snake from going opposite direction to prevent collision
			if(!right) {
				left = true;
			} else {
				left = false;
				right = true;
			}
			up= false; 
			down = false; 
		}
		
		if(event.getKeyCode() == KeyEvent.VK_UP) {
			move++;
			up = true;
			//prevent snake from going opposite direction to prevent collision
			if(!down) {
				up = true;
			} else {
				up = false;
				down = true;
			}
			right= false; 
			left = false;
		}
		
		if(event.getKeyCode() == KeyEvent.VK_DOWN) {
			move++;
			down = true;
			//prevent snake from going opposite direction to prevent collision
			if(!up) {
				down = true;
			} else {
				up = true;
				down = false;
			}
			right= false; 
			left = false;
		}
		
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
