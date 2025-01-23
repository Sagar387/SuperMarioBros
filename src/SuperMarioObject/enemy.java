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
import SuperMarioBros.GameOver;
import SuperMarioBros.SuperMarioBros;
import SuperMarioObjectUtil.Handler;
import SuperMarioObjectUtil.KeyInput;
import SuperMarioObjectUtil.ObjectID;
import SuperMarioObject.Player;
public class enemy extends SuperMarioObject {
    boolean forward = false;
    boolean done = false;
    private BufferedImage[] sprite;
    private Handler handler;
    private GameOver gameover;
    private Texture tex = SuperMarioBros.getTexture();
    private Animation enemyWalk;
    private Animation enemySquash;
    private static final float width = 16;
    private static final float height = 16;
    boolean squash=false;
    private Player player;
    private int animationTimer = 0;
    private List<SuperMarioObject> objectsToRemove;

    public enemy(float x, float y, int scale, Handler handler, Player player) {
        super(x, y, ObjectID.enemy, width, height, scale);
        this.handler = handler;
        this.player = player;
        sprite = tex.getenemy_1();
        enemyWalk = new Animation(5,1, sprite[0], sprite[1]);
        enemySquash = new Animation(5,30,sprite[2]);
        objectsToRemove = new ArrayList<>(); // Initialize the objectsToRemove list
        forward = false;
        
    }
    @Override
    public void tick() {
    	    if (squash) {
    	        enemySquash.runAnimation();
    	        animationTimer++;
    	        if (animationTimer > enemySquash.getAnimationDuration()) {
    	            objectsToRemove.add(this);
    	            handler.removeAllObjects(objectsToRemove);
    	        }
    	        if (Player.health <= 0) {
    	            // Stop the player from moving forward
    	            player.setVelX(0);
    	        } 
        } else {
            if (forward) {
                setX(getX() + getvelX());
            } else {
                setX(getX() - getvelX());
            }
            setY(getY() + getvelY());
            applyGravity();
            move();
            collision();
            enemyWalk.runAnimation();
        }

       
    }
    
   
    
    private void collision() {
    	 for (SuperMarioObject temp : handler.getGameObjs()) {
            if (temp.getID() == ObjectID.player) {
                if (getBounds().intersects(temp.getBounds())) {
                    setY(temp.getY() - getheight());
                    setVelY(0);
                }

                if (getBoundsTop().intersects(temp.getBounds())||temp.getID() == ObjectID.fireball) {
                	SuperMarioBros.points=SuperMarioBros.points+100;
                	handler.playStomp();
                	squash=true;
                	
                }
                if (getBoundsRight().intersects(temp.getBounds())) {
                	if(Player.state == PlayerState.Large||Player.state == PlayerState.Fire) {
                		SuperMarioBros.points=SuperMarioBros.points-100;
                		Player.shrink= true;
                		System.out.println(Player.shrink);
                		Player.setSpriteToSmall();
                		handler.playshrink();
                		//temp.setY(temp.getY()-20);
                		 forward = false; // Change direction when hitting a pipe
                	}
                	else {
                	Player.health--;
                	SuperMarioBros.points=SuperMarioBros.points-100;
                	if(Player.health>0) {
                	handler.playMarioDies();
                	}
                	resetToStart();
                    forward = false; // Change direction when hitting a pipe
                	}
                }
                if (getBoundsLeft().intersects(temp.getBounds())) {
                	if(Player.state == PlayerState.Large||Player.state == PlayerState.Fire) {
                		SuperMarioBros.points=SuperMarioBros.points-100;
                		Player.shrink= true;
                		System.out.println(Player.shrink);
                		Player.setSpriteToSmall();
                		handler.playshrink();
                		temp.setY(temp.getY()-20);
                		forward = true; // Change direction when hitting a pipe
                	}
                	else {
                		SuperMarioBros.points=SuperMarioBros.points-100;
                	Player.health--;
                	if(Player.health>0) {
                	handler.playMarioDies();
                	}
                	resetToStart();
                    forward = true; // Change direction when hitting a pipe
                	}
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
                    setX(temp.getX() - getwidth());
                    forward = false; // Change direction when hitting a pipe
                }
                if (getBoundsLeft().intersects(temp.getBounds())) {
                    setX(temp.getX() + temp.getwidth());
                    forward = true; // Change direction when hitting a pipe
                }
            }
        }
        handler.removeAllObjects(objectsToRemove);
    }
    
    public boolean shouldBeRemoved() {
		return false;
    }
    
    private void resetToStart() {    	
    	if (Player.health==0){
        	Player player = SuperMarioBros.handler.getPlayer();
        	handler.getPlayer().setX(3000);
        	player.setY(10);
        	handler.getPlayer().setVelX(0);
    		handler.pauseBackground();
    		handler.playGameOver();
    		SuperMarioBros.remainingTime=400*60;
			Block.coinrecieved=0;
    		try {
                Thread.sleep(2000);  // Delay for 2000 milliseconds (2 seconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    		Frame.dispose();
    		GameOver g = new GameOver(); // Moves To Game Scree
    	}else if (Player.health>0) {
    	Player player = SuperMarioBros.handler.getPlayer();
    	player.setX(255);
    	player.setY(10);
    	}
    }
  

    public Rectangle getBounds() {
        return new Rectangle((int) (getX() + getwidth() / 2 - getwidth() / 4), (int) (getY() + getheight() / 2),
                (int) getwidth() / 2, (int) getheight() / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (getX() + getwidth() / 2 - getwidth() / 4), (int) getY(), (int) getwidth() / 3,
                (int) getheight());
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) (getX() + getwidth() - 5), (int) getY() + 5, 5, (int) getheight() - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) getX(), (int) (getY() + 5), 5, (int) getheight() - 10);
    }

    @Override
    public void render(Graphics g) {
        if(squash) {
            enemySquash.drawAnimation(g, (int) getX(), (int) getY(), (int) getwidth(), (int) getheight());
            done=true;
        }
        else {
            enemyWalk.drawAnimation(g, (int) getX(), (int) getY(), (int) getwidth(), (int) getheight());    
        }
    	
       
    }
    
	@Override
	public boolean add() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setForward(boolean forward) {
        this.forward = forward;
    }
}