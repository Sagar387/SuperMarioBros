package SuperMarioObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import FrameSuperMarios.Animation;
import FrameSuperMarios.Texture;
import SuperMarioBros.SuperMarioBros;
import SuperMarioObjectUtil.Handler;
import SuperMarioObjectUtil.ObjectID;

public class Fireball extends SuperMarioObject {
    private static final float WIDTH = 16;
    private static final float HEIGHT = 16;
    private Handler handler;
    private Player player;
    private float velocityX;  // Horizontal velocity of the fireball
    private Texture tex;
    private boolean shouldRemove;
    private Animation firebombmove;
    private float fireballVelocity = 10.0f;  // Velocity of the fireball
    private boolean shooting;  // Flag to indicate if the player is shooting
    private BufferedImage[] spritebomb;
    public static boolean startanimation = false;
    private boolean forward = false;
    private long creationTime;  // Time when the fireball was created
    private List<SuperMarioObject> objectsToRemove; // Added objectsToRemove list
    public Fireball(float x, float y, float velocityX, Handler handler) {
        super(x, y, ObjectID.fireball, 16, 16, 1);
        tex = SuperMarioBros.getTexture();
        this.velocityX = velocityX;
        this.handler = handler;
        spritebomb = tex.fire_bomb();
        firebombmove = new Animation(5,1,spritebomb[0]);
        creationTime = System.currentTimeMillis();  // Record the creation time
        objectsToRemove = new ArrayList<>(); 
    }

    @Override
    public void tick() {
    	System.out.println(startanimation);
        forward = true;
        shooting = true;
        setX(velocityX + getX());
        setY(getvelY() + getY());

        collision();
        if (startanimation) {
            firebombmove.runAnimation();
           // Fireball.startanimation = false;
        }

        // Check if the fireball should be removed
        long elapsedTime = System.currentTimeMillis() - creationTime;
        if (elapsedTime >= 3000) {
            handler.removeObj(this);
        }
        
        if (shouldBeRemoved()) {
            handler.removeAllObjects(objectsToRemove);
        }
    }

    @Override
    public void render(Graphics g) {
        System.out.println(startanimation);
        firebombmove.drawAnimation(g, (int) getX() + 20, (int) getY(), (int) getwidth(), (int) getheight());
        //Fireball.startanimation = false;
    }

    private void collision() {
    	
    	List<SuperMarioObject> objectsToAdd = new ArrayList<>(); // Create a list to store objects to be added

        for (SuperMarioObject temp : handler.getGameObjs()) {

            if (temp.getID() == ObjectID.block || temp.getID() == ObjectID.pipe) {
                if (getBounds().intersects(temp.getBounds())) {
                	 objectsToRemove.add(this);
                }
            } else if (temp.getID() == ObjectID.enemy) {
                if (getBounds().intersects(temp.getBounds())) {
                	 shouldRemove = true;
                     objectsToRemove.add(temp);
                     objectsToRemove.add(this); 
                }
            }
        }
        handler.removeAllObjects(objectsToRemove);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getwidth()-10, (int) getheight()-10);
    }

    @Override
    public boolean shouldBeRemoved() {
        // TODO Auto-generated method stub
        return shouldRemove;
    }

    @Override
    public boolean add() {
        // TODO Auto-generated method stub
        return false;
    }
}
