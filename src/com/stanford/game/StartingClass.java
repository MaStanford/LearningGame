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
	private Image image,character,background;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;


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
		
		player = new RedRobot();
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		
		// Image Setups
		character = getImage(base, player.getImageURL());
		background = getImage(base, bg1.getImageURL());
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
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
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
			bg1.update();
			bg2.update();
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
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_LEFT:
			player.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			player.moveRight();
			break;
		case KeyEvent.VK_SPACE:
			player.jump();
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
			break;
		case KeyEvent.VK_RIGHT:
			break;
		case KeyEvent.VK_SPACE:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
    public static Background getBg1() {
        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }
}
