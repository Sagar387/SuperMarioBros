package FrameSuperMarios;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	private int speed;
	private int frames;
	
	private int index = 0;
	private int count = 0;
	public static boolean isAnimationDone = false;
	private BufferedImage[] images;
	private BufferedImage currentImg;
	private int animationDuration;
	
	public Animation (int speed,int duration, BufferedImage... args) {
		this.speed=speed;
		  this.animationDuration = duration;
		images = new BufferedImage[args.length];
		for (int i = 0; i < args.length; i++) {
			images [i] = args[i];
		}
		frames = args.length;
	}

	public void runAnimation() {
		index++;
		if (index > speed) {
			index = 0;
			nextFrame();
			
		}
	}
	
	private void nextFrame() {
		currentImg = images[count];
		count++;
		
		if (count >= frames) {
			count = 0;
			
		}
	}
	
	public void drawAnimation (Graphics g, int x, int y, int scaleX, int scaleY) {
		g.drawImage(currentImg,x,y,scaleX,scaleY,null);
	}

	public boolean isAnimationDone() {
		return isAnimationDone;
	}
	
	public int getAnimationDuration() {
	    return animationDuration;
	}
}
