package com.stanford.game;


public class Projectile {
	private int x, y, speedX;
	private boolean visible;
	
	public Projectile(int startX, int startY){
		this.x = startX;
		this.y = startY;
		speedX = Constants.PROJECTILE_SPEED_DEFAULT;
		visible = true;
	}
	
	public void update(){
		x += speedX;
		if (x > Constants.WINDOW_WIDTH || x < 0) {
		   visible = false;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
