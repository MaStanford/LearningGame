package com.stanford.game;

public class RedRobot extends PlayerCharacter {

	// X and Y of the player
	private int playerCenterX = Constants.PLAYER_STARTING_X;
	private int playerCenterY = Constants.PLAYER_STARTING_Y - playerSizeY;
	// If the player is jumping
	private boolean isJumping = false;
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
	private String imageURL = "../data/character.png";



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
		
		if (playerSpeedX <= 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}
		if (playerCenterX <= SCROLL_X_LIMIT && playerSpeedX > 0) {
			playerCenterX += playerSpeedX;
		}
		if (playerSpeedX > 0 && playerCenterX > SCROLL_X_LIMIT) {
			bg1.setSpeedX(-MOVE_SPEED);
			bg2.setSpeedX(-MOVE_SPEED);
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
		if (isJumping == true) {
			playerSpeedY += 1 + FALL_MODULATOR; // Positive speed makes the
												// character move down

			// Checks to see if the player hits the gound while jumping
			if (playerCenterY + playerSpeedY >= FLOOR_Y) {
				playerCenterY = FLOOR_Y;
				playerSpeedY = 0;
				isJumping = false;
			}
		}

		// Prevents going beyond X coordinate of 0
		if (playerCenterX + playerSpeedX <= SCROLL_X_MIN) {
			playerCenterX = SCROLL_X_MIN;
		}
	}

	public void moveRight() {
		playerSpeedX = MOVE_SPEED;
	}

	public void moveLeft() {
		playerSpeedX = -MOVE_SPEED;
	}

	public void stop() {
		playerSpeedX = 0;
	}

	public void jump() {
		if (isJumping == false) {
			playerSpeedY = JUMP_SPEED;
			isJumping = true;
		}
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
		return isJumping;
	}

	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public boolean isJumped() {
		return jumped;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
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
