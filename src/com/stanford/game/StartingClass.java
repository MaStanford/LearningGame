package com.stanford.game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {

	/**
	 * Generated ID
	 */
	private static final long serialVersionUID = 427298476106571301L;
	
	private RedRobot player;
	private Image image,character;
	private Graphics second;
	private URL base;


	public StartingClass() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}

	@Override
	public void init() {
		// Set up applet
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setBackground(Color.BLACK);
		setFocusable(true);
		// Get the frame from the parent
		Frame gameframe = (Frame) this.getParent().getParent();
		gameframe.setTitle("2D Shooter");
		//Set the keylistener
		addKeyListener(this);
		
		player = new RedRobot();
		
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			System.out.println("Exception on doc base");
		}
		
		// Image Setups
		character = getImage(base, player.getImageURL());
	}

	@Override
	public void start() {
		super.start();
		
		// Main game thread
		Thread gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void update(Graphics g) {
		
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(character, player.getPlayerCenterX(), player.getPlayerCenterY(), this);
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void run() {
		// Main game loop
		while (true) {
			player.update();
			repaint(); // built in function to call the paint method
			try {
				Thread.sleep(17); // 17 ms is 60 fps
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	boolean left,right,jump = false;

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			player.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			player.moveRight();
			right = true;
			break;
		case KeyEvent.VK_SPACE:
			player.jump();
			jump = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			if(!(left && right && jump)){
				player.stop();
			}
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			if(!(left && right && jump)){
				player.stop();
			}
			break;
		case KeyEvent.VK_SPACE:
			jump = false;
			if(!(left && right && jump)){
				player.stop();
			}
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
