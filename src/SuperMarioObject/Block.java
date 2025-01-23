package SuperMarioObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import FrameSuperMarios.Animation;
import FrameSuperMarios.Texture;
import SuperMarioBros.SuperMarioBros;
import SuperMarioObjectUtil.Handler;
import SuperMarioObjectUtil.ObjectID;

public class Block extends SuperMarioObject {
    private Texture tex = SuperMarioBros.getTexture();
    private int index;
    private BufferedImage[] sprite, sprite1, sprite2;
    private Handler handler;
    private Player player;
    private Mushroom m;
    private boolean shouldRemove;
    private boolean add;
    private int breakTimer; // Timer for block breaking animation
    private Animation currAnimation;
    private Animation blockbreak;
    private List<SuperMarioObject> objectsToRemove; // Added objectsToRemove list
    private int x; // x-coordinate of the Block
    private int y; // y-coordinate of the Block
    private boolean blockHit; // Flag to track if a block has been hit in the current jump
	public static int coinrecieved=0;

    public Block(int x, int y, int width, int height, int index, int scale, Handler handler) {
        super(x, y, ObjectID.block, width, height, scale);
        this.index = index;
        this.x = x;
        this.y = y;
        sprite = tex.gettile_1();
        this.handler = handler;
        sprite1 = tex.getbrick_1();
        sprite2 = tex.mushroom_1();
        blockbreak = new Animation(5,1, sprite1[0], sprite1[1], sprite1[2]);
        currAnimation = blockbreak;
        objectsToRemove = new ArrayList<>(); // Initialize objectsToRemove list
        blockHit = false; // Initialize blockHit flag to false
    }

    @Override
    public void tick() {
        if (shouldRemove) {
            currAnimation.runAnimation();
            breakTimer++; // Increase the break timer

            if (shouldBeRemoved()) {
                handler.removeAllObjects(objectsToRemove);
            }
        } else {
            collision();
        }
    }

    private void collision() {
        List<SuperMarioObject> objectsToAdd = new ArrayList<>(); // Create a list to store objects to be added

        for (SuperMarioObject temp : handler.getGameObjs()) {
            if (temp.getID() == ObjectID.player) {
                if (getBounds().intersects(temp.getBounds())) {
                    // Handle collision with player
                    Player player = (Player) temp;
                    if (player.isJumping() && !blockHit) {
                        // Only break the block if the player is jumping and no other block has been hit in the current jump
                        shouldRemove = true;
                        objectsToRemove.add(this);
                        blockHit = true; // Set blockHit to true after hitting a block
                    }
                }

                if (getBoundsBottom().intersects(temp.getBounds()) && index == 1 ) {
                	if(Player.state == PlayerState.Fire||Player.state == PlayerState.Large) {
                    shouldRemove = true;
                    objectsToRemove.add(this);
                    handler.playbreakbrick();
                    blockHit = true; // Set blockHit to true after hitting a block
                }
                }
                if (getBoundsBottom().intersects(temp.getBounds()) && index == 24) {
                	Random random = new Random(); 
                	int randomNumber = random.nextInt(3) + 1;
                	if(randomNumber==1) {
                	handler.playbump();
                    sprite = tex.question_1();
                    index = 0;
                    int mushroomX = x; // Use the stored x-coordinate
                    int mushroomY = y - 32; // Use the stored y-coordinate and subtract 32
                    System.out.println(mushroomX);
                    System.out.println(mushroomY);
                    objectsToAdd.add(new Mushroom(mushroomX / 2, mushroomY / 2, 2, handler, player));
                    handler.playPowerUpAppears();
                    blockHit = true; // Set blockHit to true after hitting a block
                    break; // Exit the loop after creating the mushroom
                	}
                	
                	if (randomNumber==2) {
                		handler.playbump();
                        sprite = tex.question_1();
                        index = 0;
                        int FireX = x; // Use the stored x-coordinate
                        int FireY = y - 32; // Use the stored y-coordinate and subtract 32
                        System.out.println(FireX);
                        System.out.println(FireY);
                        objectsToAdd.add(new Fire(FireX / 2, FireY / 2, 2, handler, player));
                        handler.playPowerUpAppears();
                        blockHit = true; // Set blockHit to true after hitting a block
                        break; // Exit the loop after creating the mushroom
                	}
                	
                	if(randomNumber==3) {
                	handler.playbump();
                    sprite = tex.question_1();
                    index = 0;
                    int mushroomX = x+3; // Use the stored x-coordinate
                    int mushroomY = y - 32; // Use the stored y-coordinate and subtract 32
                    System.out.println(mushroomX);
                    System.out.println(mushroomY);
                    handler.playCoin();
                    objectsToAdd.add(new Coin(mushroomX / 2, mushroomY / 2, 2, handler, player));
                    coinrecieved++;
                    SuperMarioBros.points=SuperMarioBros.points+100;
                    break; // Exit the loop after creating the mushroom
                	}
                }
            }
            

        }

        // Add the objects to the handler
        handler.addAllObjects(objectsToAdd);
    }

    @Override
    public void render(Graphics g) {
        if (shouldRemove) {
            currAnimation.drawAnimation(g, (int) getX() - 16, (int) getY() - 16, 64, 64);
        } else {
            if (index >= 0 && index < sprite.length) {
                g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getwidth(), (int) getheight(), null);
            } else {
                // Handle the case where index is out of range
                // For example, you can render a default sprite or handle the error in another way
            }
        }
    }

    public boolean shouldBeRemoved() {
        return shouldRemove && breakTimer >= 30; // Remove the block after 30 ticks of the break animation
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getwidth(), (int) getheight());
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) getX(), (int) getY(), 32, 32);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) (getX() + getwidth() - 32), (int) getY(), 32, 32);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) getX(), (int) getY(), 32, 32);
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle((int) getX(), (int) (getY()) + 32, 32, 32);
    }

    @Override
    public boolean add() {
        return false;
    }
}