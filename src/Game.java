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
		graphic.drawRect(20, 20, 851, 630);
		//background
		graphic.setColor(Color.BLACK);
		graphic.fillRect(21, 21, 850, 629);
		
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
					if(snakeX[i] > 850) {
						snakeX[i] = 25;
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
						snakeX[i] = 850;
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
						snakeY[i] = 625;
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
						snakeY[i] = 25;
					}
				}
				repaint();
			}


		
	}

	@Override
	public void keyPressed(KeyEvent event) {
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
