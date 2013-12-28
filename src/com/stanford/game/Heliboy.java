package com.stanford.game;

public class Heliboy extends Enemy {
	
	private String normalSprite = "../data/heliboy.png";

	public Heliboy(int centerX, int centerY) {
		setCenterX(centerX);
		setCenterY(centerY);
	}

	public String getNormalSprite() {
		return normalSprite;
	}

	public void setNormalSprite(String normalSprite) {
		this.normalSprite = normalSprite;
	}

}
