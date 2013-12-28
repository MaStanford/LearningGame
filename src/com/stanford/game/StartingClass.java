package com.stanford.game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class StartingClass extends Applet implements Runnable, KeyListener {

	/**
	 * Generated ID
	 */
	private static final long serialVersionUID = 427298476106571301L;
	
	private RedRobot player;
	private Heliboy heliboy1,heliboy2;
	private static Background bg1, bg2;
	private Image image,characterSpriteIdle,backgroundSprite,characterDownSprite,
		characterJumpedSprite,currentSprite,enemyHeliboySprite;
	private Graphics second;
	private URL base;
	private int playerSpriteType;



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
		heliboy1 = new Heliboy(100,100);
		heliboy2 = new Heliboy(300,50);
		
		// Image Setups
		characterSpriteIdle = getImage(base, player.getNormalSprite());
		backgroundSprite = getImage(base, bg1.getImageURL());
		characterDownSprite = getImage(base, player.getDuckSprite());
		characterJumpedSprite = getImage(base, player.getJumpSprite());
		enemyHeliboySprite = getImage(base, heliboy1.getNormalSprite());
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
		
		//Draw background
		g.drawImage(backgroundSprite, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(backgroundSprite, bg2.getBgX(), bg2.getBgY(), this);
		
		//Draw character and enemy sprites
		g.drawImage(currentSprite, player.getPlayerCenterX(), player.getPlayerCenterY(), this);
		g.drawImage(enemyHeliboySprite, heliboy1.getCenterX(), heliboy1.getCenterY(), this);
		g.drawImage(enemyHeliboySprite, heliboy2.getCenterX(), heliboy2.getCenterY(), this);
		
		//Draw projectiles
		ArrayList<Projectile> projectiles = player.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), Constants.PROJECTILE_WIDTH_DEFUALT, Constants.PROJECTILE_HEIGHT_DEFUALT);
		}
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
			//Sprite logic
			switch(playerSpriteType){
			case 0:
				currentSprite = characterSpriteIdle;
				break;
			case 1:
				currentSprite = characterJumpedSprite;
				break;
			case 2:
				currentSprite = characterDownSprite;
				break;
			default:
				currentSprite = characterSpriteIdle;
				break;
			}
			
			//updates sprites
			player.update();
			heliboy1.update();
			heliboy2.update();
			bg1.update();
			bg2.update();
			
			//Update the projectiles
			ArrayList<Projectile> projectiles = player.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				if (p.isVisible() == true) {
					p.update();
				} else {
					projectiles.remove(i);
				}
			}
			
			//Built in function that calls the paint method
			repaint();
			
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
			playerSpriteType = Constants.SPRITE_DUCK;
			if(!player.isJumping()){
				player.setDucked(true);
			}
			break;
		case KeyEvent.VK_LEFT:
			player.moveLeft();
			player.setMovingLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			player.moveRight();
			player.setMovingRight(true);
			break;
		case KeyEvent.VK_SPACE:
			playerSpriteType = Constants.SPRITE_JUMP;
			player.jump();
			break;
		case KeyEvent.VK_CONTROL:
			if (player.isDucked() == false) {
				player.shoot();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			playerSpriteType = Constants.SPRITE_NORMAL;
			player.setDucked(false);
			player.stop();
			break;
		case KeyEvent.VK_LEFT:
			player.stopLeft(true);
			player.stop();
			break;
		case KeyEvent.VK_RIGHT:
			player.stopRight(true);
			player.stop();
			break;
		case KeyEvent.VK_SPACE:
			playerSpriteType = Constants.SPRITE_NORMAL;
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