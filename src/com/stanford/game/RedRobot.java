package com.stanford.game;

import java.util.ArrayList;

public class RedRobot extends PlayerCharacter {

	// X and Y of the player - Really not center, should be called upper left corner
	private int playerCenterX = Constants.PLAYER_STARTING_X;
	private int playerCenterY = Constants.PLAYER_STARTING_Y - playerSizeY;
	
	//Projectiles
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	//Key Input
	private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;

	// Speed of the player
	private int playerSpeedX = 0;
	private int playerSpeedY = 1;

	// Size of the character in pixels
	private static int playerSizeX = 125;
	private static int playerSizeY = 120;
	
	//Projectile offsets - The offset that will be added to the centerX and centerY for the origin of the projectile
	private static int playerProjectileOffsetX = (playerSizeX) - 7;
	private static int playerProjectileOffsetY = ((playerSizeY / 2) - 22);

	// Bounds
	private static int CEILING_Y = Constants.CEILING_Y + playerSizeY;
	private static int FLOOR_Y = Constants.FLOOR_Y - playerSizeY;
	private static int SCROLL_X_LIMIT = Constants.SCROLL_X_LIMIT - playerSizeX;
	private static int SCROLL_X_MIN = Constants.SCROLL_X_MIN + playerSizeX;

	// He has the hops
	private static int JUMP_SPEED = Constants.JUMP_SPEED - 10;
	// Drops like a rock
	private static int FALL_MODULATOR = Constants.FALL_MODULATOR - 1;
	// Slow robot
	private static int MOVE_SPEED = Constants.MOVE_SPEED - 2;

	// The location of the robot
	private String normalSprite = "../data/character.png";
	private String jumpSprite = "../data/jumped.png";
	private String duckSprite = "../data/down.png";


	/**
	 * Updates the players X and Y and speed
	 */
	public void update() {

		/*
		 * Moves Character or Scrolls Background accordingly.
		 */
		
		// Backgrounds
		Background bg1 = StartingClass.getBg1();
		Background bg2 = StartingClass.getBg2();

		// Updates X coord - Player is moving to the left with a negative speed
		if (playerSpeedX < 0) {
			playerCenterX += playerSpeedX;
		}
		
		//Player is moving backwards, dont scroll until we hit limit
		if (playerSpeedX <= 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}
		
		//Player is moving forward, dont scroll until we hit limit
		if (playerSpeedX >= 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}
		
		//Move right before scrolling
		if (playerCenterX <= SCROLL_X_LIMIT && playerSpeedX > 0) {
			playerCenterX += playerSpeedX;
		}
		
		//Scroll Background - Moving right, scroll left
		if (playerSpeedX > 0 && playerCenterX > SCROLL_X_LIMIT) {
			bg1.setSpeedX(-MOVE_SPEED);
			bg2.setSpeedX(-MOVE_SPEED);
		}
		
		//Scroll Background - Moving left, scroll right
		if (playerSpeedX < 0 && playerCenterX < SCROLL_X_MIN) {
			bg1.setSpeedX(MOVE_SPEED);
			bg2.setSpeedX(MOVE_SPEED);
		}
		

		/*
		 * Updates Y Position - Vertical position
		 */
		// Checks to see if the player will move beyond the floor. IF he will,
		// then make him at the floor
		if (playerCenterY + playerSpeedY >= FLOOR_Y) {
			playerCenterY = FLOOR_Y;
		} else {
			playerCenterY += playerSpeedY;
		}

		// Checks to see if the player hits the ceiling
		if (playerCenterY + playerSpeedY <= CEILING_Y) {
			playerCenterY = CEILING_Y;
			playerSpeedY = 0;
		}

		// Handles Jumping
		if (jumped == true) {
			playerSpeedY += 1 + FALL_MODULATOR; 
			if (playerCenterY + playerSpeedY >= FLOOR_Y) {
				playerCenterY = FLOOR_Y;
				playerSpeedY = 0;
				jumped = false;
			}
		}

		// Prevents going beyond X coordinate of 0
		if (playerCenterX + playerSpeedX <= SCROLL_X_MIN) {
			playerCenterX = SCROLL_X_MIN;
		}
	}

	public void moveRight() {
		if(ducked == false);
			playerSpeedX = MOVE_SPEED;
	}

	public void moveLeft() {
		if(ducked == false);
			playerSpeedX = -MOVE_SPEED;
	}
	
	public void stopLeft(boolean bool){
		setMovingLeft(false);
		stop();
	}
	
	public void stopRight(boolean bool){
		setMovingRight(false);
		stop();
	}

	public void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
            playerSpeedX = 0;
        }

        if (isMovingRight() == false && isMovingLeft() == true) {
            moveLeft();
        }

        if (isMovingRight() == true && isMovingLeft() == false) {
            moveRight();
        }
	}

	public void jump() {
		if (jumped == false) {
			playerSpeedY = JUMP_SPEED;
			jumped = true;
		}
	}
	
 	public void shoot() {
		Projectile p = new Projectile(playerCenterX + playerProjectileOffsetX, playerCenterY + playerProjectileOffsetY);
		projectiles.add(p);
	}
 	
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public int getPlayerCenterX() {
		return playerCenterX;
	}

	public void setPlayerCenterX(int playerCenterX) {
		this.playerCenterX = playerCenterX;
	}

	public int getPlayerCenterY() {
		return playerCenterY;
	}

	public void setPlayerCenterY(int playerCenterY) {
		this.playerCenterY = playerCenterY;
	}

	public boolean isJumping() {
		return jumped;
	}

	public void setJumping(boolean isJumping) {
		this.jumped = isJumping;
	}

	public int getPlayerSpeedX() {
		return playerSpeedX;
	}

	public void setPlayerSpeedX(int playerSpeedX) {
		this.playerSpeedX = playerSpeedX;
	}

	public int getPlayerSpeedY() {
		return playerSpeedY;
	}

	public void setPlayerSpeedY(int playerSpeedY) {
		this.playerSpeedY = playerSpeedY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public String getNormalSprite() {
		return normalSprite;
	}

	public void setNormalSprite(String normalSprite) {
		this.normalSprite = normalSprite;
	}

	public String getJumpSprite() {
		return jumpSprite;
	}

	public void setJumpSprite(String jumpSprite) {
		this.jumpSprite = jumpSprite;
	}

	public String getDuckSprite() {
		return duckSprite;
	}

	public void setDuckSprite(String duckSprite) {
		this.duckSprite = duckSprite;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}	
}
