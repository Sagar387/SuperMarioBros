package SuperMarioObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import FrameSuperMarios.Texture;
import SuperMarioBros.SuperMarioBros;
import SuperMarioObjectUtil.Handler;
import SuperMarioObjectUtil.ObjectID;

public class Pipe extends SuperMarioObject {
	//Texture tex = SuperMarioBros.getInstance();
	private Texture tex = SuperMarioBros.getTexture();
	private boolean enterable;
	private int index;
	private BufferedImage[] sprite;
	
	public Pipe(int x, int y, int width, int height, int scale,int index, boolean enterable) {
		super(x, y, ObjectID.pipe, width, height, scale);
		this.enterable = enterable;
		this.index = index;
		sprite = tex.getpipe_1();
	}

	
	
	

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sprite[index], (int)getX(), (int)getY(),(int) getwidth(), (int) getheight(), null);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle ((int) getX(), (int) getY(), (int) getwidth(), (int) getheight());
	}
	public boolean shouldBeRemoved() {
		boolean shouldRemove = false;
		return shouldRemove;
    }





	@Override
	public boolean add() {
		// TODO Auto-generated method stub
		return false;
	}
}
