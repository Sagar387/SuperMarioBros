package SuperMarioObjectUtil;

import java.awt.Graphics;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

import FrameSuperMarios.Frame;

import java.util.ArrayList;

import SuperMarioObject.Mushroom;
import SuperMarioObject.Player;
import SuperMarioObject.SuperMarioObject;
import SuperMarioBros.GameOver;
import SuperMarioObjectUtil.SoundManager;

public class Handler {
	
    private List<SuperMarioObject> gameObjs;
    private Mushroom mushroom;
    private Player player;
    private SoundManager soundManager;
   
    public Handler() {
        gameObjs = new LinkedList<>();
        soundManager = new SoundManager();
    }

    public void tick() {
    	 List<SuperMarioObject> copyOfObjects = new ArrayList<>(gameObjs); // Create a copy of the game objects list{
    	    for (SuperMarioObject obj : copyOfObjects) {
    	        obj.tick(); // Tick each object
    	        if (obj.shouldBeRemoved()) {
                  
                }
    	    }
  
        }
   
         
    

    public void render(Graphics g) {
    	
        for (SuperMarioObject obj : gameObjs) {
            obj.render(g);
        }
    }

    public void addObj(SuperMarioObject obj) {
        gameObjs.add(obj);
    }
    
   
    public void addAllObjects(List<SuperMarioObject> objects) {
        gameObjs.addAll(objects);
    }
    
    public void removeAllObjects(List<SuperMarioObject> objects) {
        gameObjs.removeAll(objects);
    }

    public void removeObj(SuperMarioObject obj) {
        gameObjs.remove(obj);
    }

    public List<SuperMarioObject> getGameObjs() {
        return gameObjs;
    }

    public int setPlayer(Player player) {
        if (this.player != null) {
            return -1;
        }

        addObj(player);
        this.player = player;
        return 0;
        

    }

    public int removePlayer() {
        if (player == null) {
            return -1;
        }

        removeObj(player);
        this.player = null;
        return 0;
    }

    public Player getPlayer() {
        return player;
    }
    
    public void playCoin() {
        soundManager.playCoin();
    }
    
    public void playBackground() {
        soundManager.resumeBackground();
    }
    public void playGameOver() {
        soundManager.playGameOver();
    }
    
    public void pauseBackground(){
        soundManager.pauseBackground();
    }
    public void playOneUp() {
        soundManager.playOneUp();
    }

    public void playSuperMushroom() {
        soundManager.playSuperMushroom();
    }

    public void playMarioDies() {
        soundManager.playMarioDies();
    }

    public void playJump() {
        soundManager.playJump();
    }

    public void playFireFlower() {
        soundManager.playFireFlower();
    }

    public void playFireball() {
        soundManager.playFireball();
    }

    public void playStomp() {
        soundManager.playStomp();
    }
    public void playbreakbrick() {
        soundManager.playbreakbrick();
    }
    public void playPowerUpAppears() {
        soundManager.playPowerUpAppears();
    }
    public void playshrink() {
        soundManager.playshrink();
    }
    public void playbump() {
        soundManager.bump();
    }
    public void playclear() {
        soundManager.stageClear();
    }
    
        // Create a new instance of the GameOverScreen class
    	
    
    
}