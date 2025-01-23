package FrameSuperMarios;

import SuperMarioBros.SuperMarioBros;
import SuperMarioObject.SuperMarioObject;

public class Camera {
	private int x,y;
	public Camera(int x, int y) {
		this.x =x;
		this.y=y;
	}
	public void tick (SuperMarioObject player) {
		x = (int) - player.getX()+ SuperMarioBros.getscreenwidth()/2;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.x = y;
	}
	
	public int GetX() {
		return x;
	}
	public int GetY() {
		return y;
	}
}
