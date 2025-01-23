package SuperMarioObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import FrameSuperMarios.Animation;
import FrameSuperMarios.Frame;
import FrameSuperMarios.Texture;
import SuperMarioBros.GameOver;
import SuperMarioBros.SuperMarioBros;
import SuperMarioObjectUtil.Handler;
import SuperMarioObjectUtil.ObjectID;
import SuperMarioObject.enemy;
import SuperMarioObject.Fireball;

public class Player extends SuperMarioObject {
    private static final float WIDTH = 16;
    private static float HEIGHT = 16;
    static boolean collectedMushroom = false;
    static boolean collectedfire = false;
    static boolean shrink = false;
    private Handler handler;
    static boolean jumped = false;
    public static int health = 3;
    private boolean forward = false;

    private Texture tex;
    static PlayerState state;
    private static BufferedImage[] spriteL;
    private static BufferedImage[] firesprite;
	private static BufferedImage[] spriteS;
    private static Animation playerWalkL;
    private static Animation playerWalkfire;
	private static Animation playerWalkS;
    private static BufferedImage[] currSprite;
    private static Animation currAnimation;
    public static boolean startanimation = false;
    boolean shouldRemove;
    boolean add;


    public Player(float x, float y, int scale, Handler handler) {
        super(x, y, ObjectID.player, WIDTH, HEIGHT, scale);
        this.handler = handler;
        tex = SuperMarioBros.getTexture();
        spriteL = tex.getMariol();
        spriteS = tex.getMarios();
        firesprite = tex.mario_f();
        playerWalkL = new Animation(5,1, spriteL[1], spriteL[2], spriteL[3]);
        playerWalkS = new Animation(5,1, spriteS[1], spriteS[2], spriteS[3]);
        playerWalkfire = new Animation(5,1, firesprite[1], firesprite[2], firesprite[3]);
        state = PlayerState.Small;
        currSprite = spriteS;
        currAnimation = playerWalkS;
        Fireball.startanimation = false;

    }

    @Override
    public void tick() {
    	if(Fireball.startanimation) {
            Fireball fireball = new Fireball(getX()-50, getY()+50, 10, handler); // Pass the handler object
            handler.addObj(fireball);
    	}
        
        if (collectedMushroom || collectedfire) {
            setHeight(32);
            collectedMushroom = false;
            collectedfire=false;
        }
        if (shrink) {
            setHeight(16);
            shrink = false;
        }
        setX(getvelX() + getX());
        setY(getvelY() + getY());
        applyGravity();   
        collision();
        currAnimation.runAnimation();
        if (getY() > SuperMarioBros.getscreenheight()) {
        	health--;
        	shrink=true;
        	setSpriteToSmall();
        	handler.playMarioDies();
            resetToStart();
        }
        
        
       
        }
        

    private void resetToStart() {    	
    	if (Player.health==0 || SuperMarioBros.remainingTime==0){
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
   

    @Override
    public void render(Graphics g) {
        if (jumped) {
            if (forward) {
                g.drawImage(currSprite[5], (int) getX(), (int) getY(), (int) getwidth(), (int) getheight(), null);
            } else {
                g.drawImage(currSprite[5], (int) (getX() + getwidth()), (int) getY(), (int) -getwidth(), (int) getheight(), null);
            }
        } else if (getvelX() > 0) {
            currAnimation.drawAnimation(g, (int) getX(), (int) getY(), (int) getwidth(), (int) getheight());
            forward = true;
        } else if (getvelX() < 0) {
            currAnimation.drawAnimation(g, (int) (getX() + getwidth()), (int) getY(), (int) -getwidth(), (int) getheight());
            forward = false;
        } else {
            g.drawImage(currSprite[0], (int) getX(), (int) getY(), (int) getwidth(), (int) getheight(), null);
        }

        
    }

    private void collision() {
        for (int i = 0; i < handler.getGameObjs().size(); i++) {
            SuperMarioObject temp = handler.getGameObjs().get(i);

            if (temp.getID() == ObjectID.block || temp.getID() == ObjectID.pipe) {
                if (getBounds().intersects(temp.getBounds())) {
                    setY(temp.getY() - getheight());
                    setVelY(0);
                    handler.getPlayer().setJumped(false);
                }
                if (getBoundsTop().intersects(temp.getBounds())) {
                    setY(temp.getY() + temp.getheight());
                    setVelY(0);
                }
                if (getBoundsRight().intersects(temp.getBounds())) {
                    setX(temp.getX() - getwidth());
                }
                if (getBoundsLeft().intersects(temp.getBounds())) {
                    setX(temp.getX() + temp.getwidth());
                }
            //} else if (temp.getID() == ObjectID.enemy) {
                //if (getBounds().intersects(temp.getBounds())) {
                	
               // }
                	 //if (getBoundsRight().intersects(temp.getBounds())) {
                		//if(Player.state == PlayerState.Large) {
                 		//Player.shrink= true;
                 		//System.out.println(Player.shrink);
                 		//Player.setSpriteToSmall();
                 		//handler.playshrink();
                 		  //forward=false; // Reverse the enemy's direction
                 	//}
                 	//else {
                     //resetToStart();
                     //health--;
                 	//}
                //}
                     
                     //if (getBoundsLeft().intersects(temp.getBounds())) {
                 	//if(Player.state == PlayerState.Large) {
                		//Player.shrink= true;
                		//System.out.println(Player.shrink);
                		//Player.setSpriteToSmall();
                		//handler.playshrink();
                		  //forward=true; // Reverse the enemy's direction

                	//}
                	//else {
                    //resetToStart();
                    //health--;
                	//}
                }
            
        }
        // Check if the player falls off the map
       
    }
    //}

    

    @Override
    public Rectangle getBounds() {
        if (state == PlayerState.Large || state == PlayerState.Fire) {
            return new Rectangle((int) (getX() + getwidth() / 4), (int) (getY() + getheight() / 4), (int) getwidth() / 2, (int) (getheight() * 3) / 4);
        } else {
            return new Rectangle((int) (getX() + getwidth() / 2 - getwidth() / 4), (int) (getY() + getheight() / 2), (int) getwidth() / 2, (int) getheight() / 2);
        }
    }

    public Rectangle getBoundsTop() {
        if (state == PlayerState.Large || state == PlayerState.Fire) {
            return new Rectangle((int) (getX() + getwidth() / 4), (int) getY(), (int) getwidth() / 2, (int) getheight() / 2);
        } else {
            return new Rectangle((int) (getX() + getwidth() / 2 - getwidth() / 4), (int) getY(), (int) getwidth() / 2, (int) getheight() / 2);
        }
    }

    public Rectangle getBoundsRight() {
        if (state == PlayerState.Large || state == PlayerState.Fire) {
            return new Rectangle((int) (getX() + getwidth() - 5), (int) getY() + 5, 5, (int) getheight() - 10);
        } else {
            return new Rectangle((int) (getX() + getwidth() - 5), (int) getY() + 5, 5, (int) getheight() - 10);
        }
    }

    public Rectangle getBoundsLeft() {
        if (state == PlayerState.Large || state == PlayerState.Fire) {
            return new Rectangle((int) getX(), (int) (getY() + 5), 5, (int) (getheight() - 10));
        } else {
            return new Rectangle((int) getX(), (int) (getY() + 5), 5, (int) (getheight() - 10));
        }
    }

    public boolean hasJumped() {
        return jumped;
    }
    public boolean isJumping() {
        return jumped;
    }

    public void setJumped(boolean hasJumped) {
        jumped = hasJumped;
    }

    public void setSpriteToLarge() {
        state = PlayerState.Large;
        currSprite = spriteL;
        currAnimation = playerWalkL;
        Fire.hasfire=false;
 
    }
    
    public static void setSpriteToSmall() {
        state = PlayerState.Small;
        currSprite = spriteS;
        currAnimation = playerWalkS;
        Fire.hasfire=false;

    }
    
    public void setSpriteToFire() {
        state = PlayerState.Fire;
        currSprite = firesprite;
        currAnimation = playerWalkfire;

    }

    public boolean shouldBeRemoved() {
        return shouldRemove;
    }

    public boolean add() {
        return add;
    }
	
}
