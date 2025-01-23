package SuperMarioObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import FrameSuperMarios.Animation;
import FrameSuperMarios.Frame;
import FrameSuperMarios.Texture;
import SuperMarioBros.Win;
import SuperMarioBros.GameOver;
import SuperMarioBros.SuperMarioBros;
import SuperMarioObjectUtil.Handler;
import SuperMarioObjectUtil.KeyInput;
import SuperMarioObjectUtil.ObjectID;

public class Castle extends SuperMarioObject {
	  private Player player;
	  private Handler handler;
	    private static final float width = 32;
	    private static final float height = 32;
	    public static boolean touching = false;
	    private boolean soundPlayed = false;
	private int index;
	private BufferedImage[] sprite;
	private Texture tex = SuperMarioBros.getTexture();
	private Animation castleanime;
	public Castle(float x, float y, int scale, Handler handler, Player player) {
		super(x, y, ObjectID.castle, width, height, scale);
		 this.handler = handler;
	     this.player = player;
		sprite = tex.getcastle_1();
		castleanime = new Animation(5,1, sprite[0]);
	}

	
	
	

	@Override
	public void tick() {
		castleanime.runAnimation();
		collision();
		if(touching==true) {
			
			
		}
	    
	    
	}
	    
	    
	private void collision() {
		for (SuperMarioObject temp : handler.getGameObjs()) {
		if (temp.getID() == ObjectID.player) {
		if (getBounds().intersects(temp.getBounds()) && soundPlayed==false) {
			System.out.println("HI2");
			touching=true;
			Player.health=3;
			SuperMarioBros.remainingTime=400*60;
			Block.coinrecieved=0;
			KeyInput.moveleft=false;
			KeyInput.moveright=false;
			win();
	//}
		}
	}
		}
	}
	
	private void win() {
		if (touching) {
			handler.pauseBackground();
			handler.playclear();
			soundPlayed = true;
			touching=false;
			
			
			try {
				Thread.sleep(2000); // Add a 4-second delay
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Frame.dispose();
			Win g = new Win(); // Moves To Game Screen
		}
	}
	

	@Override
	 public void render(Graphics g) {
        castleanime.drawAnimation(g, (int) getX(), (int) getY(), (int) getwidth(), (int) getheight());
    }

	@Override
	public Rectangle getBounds() {
		return new Rectangle ((int) getX()+70, (int) getY(), (int) getwidth()/2, (int) getheight());
	}
	public boolean shouldBeRemoved() {
		return false;

    }





	@Override
	public boolean add() {
		// TODO Auto-generated method stub
		return false;
	}
}
