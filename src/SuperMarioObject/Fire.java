package SuperMarioObject;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import FrameSuperMarios.Animation;
import FrameSuperMarios.Texture;
import SuperMarioBros.SuperMarioBros;
import SuperMarioObjectUtil.Handler;
import SuperMarioObjectUtil.ObjectID;
import SuperMarioObject.Player;
public class Fire extends SuperMarioObject {
    public static boolean add;
	private boolean forward = false;
	public static boolean hasfire = false;
    private BufferedImage[] sprite;
    private Handler handler;
    private Texture tex = SuperMarioBros.getTexture();
    private Animation enemyWalk;
    private static final float width = 16;
    private static final float height = 16;
    boolean shouldRemove;
    private Player player;
    private List<SuperMarioObject> objectsToRemove;
    public Fire(float x, float y, int scale, Handler handler, Player player) {
        super(x, y, ObjectID.fire, width, height, scale);
        this.handler = handler;
        this.player = player;
        sprite = tex.fire_1();
        enemyWalk = new Animation(5,1, sprite[0]);
        objectsToRemove = new ArrayList<>(); // Initialize the objectsToRemove list
      
    }
    @Override
    public void tick() {
            setY(getY() + getvelY());
            applyGravity();
            collision();
            enemyWalk.runAnimation();
        }
    
   
    
    private void collision() {
    	for (SuperMarioObject temp : handler.getGameObjs()) {
            if (temp.getID() == ObjectID.player) {
                if (getBounds().intersects(temp.getBounds())) {
                	SuperMarioBros.points=SuperMarioBros.points+100;
                	objectsToRemove.add(this);
                	Player.collectedfire = true;
                     Player player = (Player) temp;
                     player.setSpriteToFire(); // Change the player's sprite to the large sprite
                     handler.playSuperMushroom();
                     hasfire=true;
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
                    setY(temp.getY() - getheight());
                    setVelY(0);
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
    	handler.removeAllObjects(objectsToRemove);
    }
    
    
    public boolean shouldBeRemoved() {
		return shouldRemove;
    }
    
   
    public Rectangle getBounds() {
        return new Rectangle((int) (getX() + getwidth() / 2 - getwidth() / 4), (int) (getY() + getheight() / 2),
                (int) getwidth() / 2, (int) getheight() / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (getX() + getwidth() / 2 - getwidth() / 4), (int) getY(), (int) getwidth() / 2,
                (int) getheight() / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) (getX() + getwidth() - 5), (int) getY() + 5, 5, (int) getheight() - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) getX(), (int) (getY() + 5), 5, (int) (getheight() - 10));
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