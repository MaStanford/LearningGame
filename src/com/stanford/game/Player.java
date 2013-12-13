package com.stanford.game;

public class Player {

	//X and Y of the player
	private int playerCenterX = 100;
	private int playerCenterY = 382;
	//If the player is jumping 
	private boolean isJumping = false;

	//Speed of the player
	private int playerSpeedX = 0;
	private int playerSpeedY = 1;

	/**
	 * Updates the players X and Y and speed
	 */
	public void update() {

		// Moves Character or Scrolls Background accordingly.
		if (playerSpeedX < 0) {
			playerCenterX += playerSpeedX;
		} else if (playerSpeedX == 0) {
			System.out.println("Do not scroll the background.");
		} else {
			if (playerCenterX <= 150) {
				playerCenterX += playerSpeedX;
			} else {
				System.out.println("Scroll Background Here");
			}
		}

		// Updates Y Position
		if (playerCenterY + playerSpeedY >= 382) {
			playerCenterY = 382;
		} else {
			playerCenterY += playerSpeedY;
		}

		// Handles Jumping
		if (isJumping == true) {
			playerSpeedY += 1;

			if (playerCenterY + playerSpeedY >= 382) {
				playerCenterY = 382;
				playerSpeedY = 0;
				isJumping = false;
			}

		}

		// Prevents going beyond X coordinate of 0
		if (playerCenterX + playerSpeedX <= 60) {
			playerCenterX = 61;
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
			playerSpeedY = -15;
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
