package SuperMarioObject;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import FrameSuperMarios.Animation;
import FrameSuperMarios.Frame;
import FrameSuperMarios.Texture;
import SuperMarioBros.SuperMarioBros;
import SuperMarioObjectUtil.Handler;
import SuperMarioObjectUtil.ObjectID;
import SuperMarioObject.Player;
public class Coin extends SuperMarioObject {
	private int animationCounter;
	private static final int animationFrames = 30; // Number of animation frames
    public static boolean add;
	private boolean forward = false;
    private BufferedImage[] sprite;
    private Handler handler;
    private Texture tex = SuperMarioBros.getTexture();
    private Animation enemyWalk;
    private static final float width = 16;
    private static final float height = 16;
    boolean shouldRemove1;
    private Player player;
    private List<SuperMarioObject> objectsToRemove;
    public Coin(float x, float y, int scale, Handler handler, Player player) {
        super(x, y, ObjectID.coin, width, height, scale);
        this.handler = handler;
        this.player = player;
        sprite = tex.coin_1();
        enemyWalk = new Animation(5,1, sprite[0]);
        objectsToRemove = new ArrayList<>(); // Initialize the objectsToRemove list
        animationCounter = 0; // Initialize the animation counter
        shouldRemove1=false;
      
    }
    @Override
    public void tick() {
    	
    	 // Increment the animation counter
        animationCounter++;

        // Check if the animation is completed
        if (animationCounter >= animationFrames) {
            shouldRemove1 = true;
        }
            setY(getY() + getvelY());
            collision();
            enemyWalk.runAnimation();
        }
    
   
  
    private void collision() {
    	for (SuperMarioObject temp : handler.getGameObjs()) {
            if (temp.getID() == ObjectID.player) {
                if (getBounds().intersects(temp.getBounds())) {
                	if(Player.state == PlayerState.Small) {
                	//objectsToRemove.add(this);
                		
                	}
                }

                if (getBoundsTop().intersects(temp.getBounds())) {
                	//setVelY(0);
                    // Check if the colliding object is the enemy
                	//shouldRemove =true;
                    //shouldBeRemoved();
                }
                if (getBoundsRight().intersects(temp.getBounds())) {
                	//Player.health--;
                	//resetToStart();
                    //forward = false; // Change direction when hitting a pipe
                }
                if (getBoundsLeft().intersects(temp.getBounds())) {
                	//Player.health--;
                	//resetToStart();
                    //forward = true; // Change direction when hitting a pipe
                }

            } else if (temp.getID() == ObjectID.block || temp.getID() == ObjectID.pipe) {
                if (getBounds().intersects(temp.getBounds())) {
                	//handler.playCoin();
                	//objectsToRemove.add(this);
                	//=true;
                }
                if (getBoundsTop().intersects(temp.getBounds())) {
                    //setY(temp.getY() + temp.getheight());
                }
                if (getBoundsRight().intersects(temp.getBounds())) {
                    //setX(temp.getX() - getwidth());
                    //forward = false; // Change direction when hitting a pipe
                }
                if (getBoundsLeft().intersects(temp.getBounds())) {
                    //setX(temp.getX() + temp.getwidth());
                    //forward = true; // Change direction when hitting a pipe
                }
            }
        }

        if (shouldRemove1) {
            objectsToRemove.add(this);
        }
    	handler.removeAllObjects(objectsToRemove);
    }
    
    
    public boolean shouldBeRemoved() {
		return false;
    }
    

 
    public Rectangle getBounds() {
        return new Rectangle((int) (getX()), (int) (getY()), (int) (getwidth() *2), (int) (getheight() * 2));
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (getX() + getwidth() * 2 / 2 - getwidth() * 2 / 4), (int) getY(),
                (int) (getwidth() * 2 / 2), (int) (getheight() * 2 / 2));
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) (getX() + getwidth() * 2 - 5), (int) getY() + 5, 5, (int) (getheight() * 2 - 10));
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) getX(), (int) (getY() + 5), 5, (int) (getheight() * 2 - 10));
    }
  








    @Override
    public void render(Graphics g) {
            enemyWalk.drawAnimation(g, (int) getX(), (int) getY(), (int) getwidth(), (int) getheight());

    }

	@Override
	public boolean add() {
		// TODO Auto-generated method stub
			return add;
	}
}
