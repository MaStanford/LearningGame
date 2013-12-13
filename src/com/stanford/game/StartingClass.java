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
		
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			System.out.println("Exception on doc base");
		}
		
		// Image Setups
		character = getImage(base, "../data/character.png");
	}

	@Override
	public void start() {
		super.start();

		player = new RedRobot();
		
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

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.jump();
			System.out.println("Key Up Pressed");
			break;
		case KeyEvent.VK_DOWN:
			player.jump();
			System.out.println("Key Down Pressed");
			break;
		case KeyEvent.VK_LEFT:
			player.moveLeft();
			System.out.println("Key Left Pressed");
			break;
		case KeyEvent.VK_RIGHT:
			player.moveRight();
			System.out.println("Key Right Pressed");
			break;
		case KeyEvent.VK_SPACE:
			player.jump();
			System.out.println("Key Space Pressed");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			player.stop();
			System.out.println("Key Up Released");
			break;
		case KeyEvent.VK_DOWN:
			player.stop();
			System.out.println("Key Down Released");
			break;
		case KeyEvent.VK_LEFT:
			player.stop();
			System.out.println("Key Left Released");
			break;
		case KeyEvent.VK_RIGHT:
			player.stop();
			System.out.println("Key Right Released");
			break;
		case KeyEvent.VK_SPACE:
			player.stop();
			System.out.println("Key Space Released");
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
