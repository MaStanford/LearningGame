package com.stanford.game;

public abstract class PlayerCharacter {
	// X and Y of the player
	private int playerCenterX = Constants.PLAYER_STARTING_X;
	private int playerCenterY = Constants.PLAYER_STARTING_Y - playerSizeY;
	// If the player is jumping
	private boolean isJumping = false;

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
	private static int FALL_MODULATOR = Constants.FALL_MODULATOR - 1;

	/**
	 * Updates the players X and Y and speed
	 */
	public void update() {

		/*
		 * Moves Character or Scrolls Background accordingly.
		 */

		// Updates X coord - Player is moving to the left with a negative speed
		if (playerSpeedX < 0) {
			playerCenterX += playerSpeedX;
		} else if (playerSpeedX == 0) {
			// Do not scroll Background
		} else { // Speed must be 0 or greater
			// Player is moving to the right with a postive speed
			if (playerCenterX <= SCROLL_X_LIMIT) {
				playerCenterX += playerSpeedX;
			} else { // Player is beyond the scroll X limit for moving the
						// background
				// Scroll background
			}
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
			playerSpeedY += 1; // Positive speed makes the character move down

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
		playerSpeedX = 6;
	}

	public void moveLeft() {
		playerSpeedX = -6;
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
}
