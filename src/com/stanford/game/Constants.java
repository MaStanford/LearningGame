package com.stanford.game;

public class Constants {
	
	//Movement limits
	public static final int FLOOR_Y = 470;
	public static final int CEILING_Y = 0;
	public static final int SCROLL_X_LIMIT = 650;
	public static final int SCROLL_X_MIN = 100;
	
	//Window Size
	public static final int WINDOW_HEIGHT = 480;
	public static final int WINDOW_WIDTH = 1000;
	
	//Starting values
	public static final int PLAYER_STARTING_X = 0;
	public static final int PLAYER_STARTING_Y = WINDOW_HEIGHT;
	
	//Movement 
	public static final int JUMP_SPEED = -15;
	public static final int FALL_MODULATOR = 1;
	public static final int MOVE_SPEED = 15;
	
	//Sprites
	public static final int SPRITE_NORMAL = 0;
	public static final int SPRITE_JUMP = 1;
	public static final int SPRITE_DUCK = 2;
	
	//projectile
	public static final int PROJECTILE_SPEED_DEFAULT = 7;
	public static final int PROJECTILE_WIDTH_DEFUALT = 15;
	public static final int PROJECTILE_HEIGHT_DEFUALT = 5;
}
