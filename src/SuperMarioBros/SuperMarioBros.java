package SuperMarioBros;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import FrameSuperMarios.BufferedImageLoader;
import FrameSuperMarios.Camera;
import FrameSuperMarios.Frame;
import FrameSuperMarios.Texture;
import SuperMarioObject.Block;
import SuperMarioObject.Castle;
import SuperMarioObject.Fireball;
import SuperMarioObject.Mushroom;
import SuperMarioObject.Pipe;
import SuperMarioObject.Player;
import SuperMarioObject.SuperMarioObject;
import SuperMarioObject.enemy;
import SuperMarioObjectUtil.Handler;
import SuperMarioObjectUtil.KeyInput;
import FrameSuperMarios.ScrollingBack;
import java.awt.Graphics2D;

public class SuperMarioBros extends Canvas implements Runnable {
	
	private static final int MILLISECONDS = 1000;
	private static final int NANOSECONDS = 1000000000;
	private static final int TICKS_PER_SECOND = 60;
	private static final int SECONDS_PER_TICK = TICKS_PER_SECOND / 60; // Adjust tick rate to achieve 1 second per tick
	public static int remainingTime = 400 * 60; // 400 seconds
	public static int points =0;
	private static final int TICKS = 60; 
	private boolean running;
	private Thread thread;
	private Font gameFont;
	private static final String name = "Super Mario Bros";
	private static final int windowwidth = 960;
	private static final int windowheight = 720;
	 private BufferedImage heartIcon;
	 private BufferedImage coinIcon;
	private static final int screenwidth = windowwidth - 67;
	private static final int screenheight = windowheight;
	private static final int screenoffset  = 16*3;
	public static Handler handler;
	private Player player;
	private Mushroom m;
	private Camera cam;
	private static Texture tex;
	private Frame frame;
	public ScrollingBack p = new ScrollingBack(); //Background for Stageone
	private BufferedImageLoader loader;
	public SuperMarioBros() {
		loader = new BufferedImageLoader();
		tex = new Texture();
		initialize();
		
	}
	//public void paintComponent(Graphics g){
		 //Graphics2D g2 = (Graphics2D) g.create();
	//}
	public static void main(String args[]){
		SuperMarioBros i = new SuperMarioBros();
	}
	
	private void initialize() {
		tex = new Texture();
		handler = new Handler();
		handler.playBackground();
		this.coinIcon = loader.loadImage("/media/audio/coin8.png");
		this.heartIcon = loader.loadImage("/media/audio/heart-icon.png");
		   try {
	            InputStream in = getClass().getResourceAsStream("/media/audio/mario-font.ttf");
	            gameFont = Font.createFont(Font.TRUETYPE_FONT, in);
	        } catch (FontFormatException | IOException e) {
	            gameFont = new Font("Verdana", Font.PLAIN, 12);
	            e.printStackTrace();
	        }
		for (int i =0; i<1; i++) {
			handler.addObj(new Castle (1270,82,5,handler,player));
		}
		handler.setPlayer(new Player (255,32,2,handler));
		this.addKeyListener(new KeyInput(handler));
		cam = new Camera (0, screenoffset);
		frame = new Frame(windowwidth,windowheight,name,this);
		  p = new ScrollingBack(); // Initialize scrolling background
		  Thread scrollingThread = new Thread(p);
	        scrollingThread.start();
	        start();
	
		for (int i =0; i<69; i++) {
			handler.addObj(new Block (i*32,30*20,32,32,0,1,handler));

		}
		for (int i =0; i<69; i++) {
			handler.addObj(new Block (i*32,30*19,32,32,0,1,handler));

		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (512,30*13,32,32,1,1,handler));

		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (635,30*13,32,32,1,1,handler));

		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (668,30*13,32,32,24,1,handler));

		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (700,30*13,32,32,1,1,handler));

		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (700,30*9,32,32,24,1,handler));

		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (733,30*13,32,32,24,1,handler));

		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (765,30*13,32,32,1,1,handler));
			
		}
		
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (380,270,2,handler,player));
		}
		//for (int i =0; i<1; i++) {
			//m.add=true;
			//handler.addObj1(new Mushroom (380,270,2,handler,player));
		//}
		for (int i =0; i<1; i++) {
			//handler.addObj1(new Mushroom (255,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (700,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (780,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (785,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1400,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1380,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1550,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1570,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1780,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1800,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1900,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1920,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1960,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (1980,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (2720,270,2,handler,player));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new enemy (2700,270,2,handler,player));
		}
		
		for (int i =0; i<1; i++) {
			handler.addObj(new Pipe (895,505,64,64,1,0,false));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Pipe (1215,470,64,64,1,0,false));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Pipe (1215,505,64,64,1,4,false));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Pipe (1471,505,64,64,1,4,false));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Pipe (1471,442,64,64,1,0,false));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Pipe (1823,505,64,64,1,4,false));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Pipe (1823,442,64,64,1,0,false));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (2015,30*13,32,32,24,1,handler));
		}
		for (int i =0; i<15; i++) {
			handler.addObj(new Block (2280+(i*32),30*20,32,32,0,1,handler));
		}
		for (int i =0; i<15; i++) {
			handler.addObj(new Block (2280+(i*32),30*19,32,32,0,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (2475,30*13,32,32,1,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (2507,30*13,32,32,24,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (2539,30*13,32,32,1,1,handler));
		}
		for (int i =0; i<8; i++) {
			handler.addObj(new Block (2570+((i*32)),30*8,32,32,1,1,handler));
		}
		
		for (int i =0; i<61; i++) {
			handler.addObj(new Block (2860+(i*32),30*19,32,32,0,1,handler));
		}
		for (int i =0; i<61; i++) {
			handler.addObj(new Block (2860+(i*32),30*20,32,32,0,1,handler));
		}
		for (int i =0; i<3; i++) {
			handler.addObj(new Block (2892+((i*32)),30*8,32,32,1,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (2988+((i*32)),30*8,32,32,24,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (2988+((i*32)),30*13,32,32,1,1,handler));
		}
		for (int i =0; i<2; i++) {
			handler.addObj(new Block (3180+((i*32)),30*13,32,32,1,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (3340+((i*32)),30*13,32,32,24,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (3436+((i*32)),30*13,32,32,24,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (3436+((i*32)),30*8,32,32,24,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (3532+((i*32)),30*13,32,32,24,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (3724+((i*32)),30*13,32,32,1,1,handler));
		}
		for (int i =0; i<3; i++) {
			handler.addObj(new Block (3820+((i*32)),30*8,32,32,1,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (3980+((i*32)),30*8,32,32,1,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (4004+((i*32)),30*8,32,32,24,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (4036+((i*32)),30*8,32,32,24,1,handler));
		}
		for (int i =0; i<2; i++) {
			handler.addObj(new Block (4004+((i*32)),30*13,32,32,1,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (4068+((i*32)),30*8,32,32,1,1,handler));
		}
		for (int i =0; i<4; i++) {
			handler.addObj(new Block (4171+((i*32)),30*18,32,32,28,1,handler));
		}
		for (int i =0; i<3; i++) {
			handler.addObj(new Block (4203+((i*32)),30*17,32,32,28,1,handler));
		}
		for (int i =0; i<2; i++) {
			handler.addObj(new Block (4235+((i*32)),30*16,32,32,28,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (4267+((i*32)),30*15,32,32,28,1,handler));
		}
		for (int i =0; i<4; i++) {
			handler.addObj(new Block (4363+((i*32)),30*18,32,32,28,1,handler));
		}
		for (int i =0; i<3; i++) {
			handler.addObj(new Block (4363+((i*32)),30*17,32,32,28,1,handler));
		}
		for (int i =0; i<2; i++) {
			handler.addObj(new Block (4363+((i*32)),30*16,32,32,28,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (4363+((i*32)),30*15,32,32,28,1,handler));
		}
		for (int i =0; i<5; i++) {
			handler.addObj(new Block (4651+((i*32)),30*18,32,32,28,1,handler));
		}
		for (int i =0; i<4; i++) {
			handler.addObj(new Block (4683+((i*32)),30*17,32,32,28,1,handler));
		}
		for (int i =0; i<3; i++) {
			handler.addObj(new Block (4715+((i*32)),30*16,32,32,28,1,handler));
		}
		for (int i =0; i<2; i++) {
			handler.addObj(new Block (4747+((i*32)),30*15,32,32,28,1,handler));
		}
		for (int i =0; i<53; i++) {
			handler.addObj(new Block (4881+(i*32),30*19,32,32,0,1,handler));
		}
		for (int i =0; i<53; i++) {
			handler.addObj(new Block (4881+(i*32),30*20,32,32,0,1,handler));
		}
		for (int i =0; i<4; i++) {
			handler.addObj(new Block (4881+((i*32)),30*18,32,32,28,1,handler));
		}
		for (int i =0; i<3; i++) {
			handler.addObj(new Block (4881+((i*32)),30*17,32,32,28,1,handler));
		}
		for (int i =0; i<2; i++) {
			handler.addObj(new Block (4881+((i*32)),30*16,32,32,28,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (4881+((i*32)),30*15,32,32,28,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Pipe (5169,505,64,64,1,0,false));
		}
		for (int i =0; i<2; i++) {
			handler.addObj(new Block (5329+((i*32)),30*15,32,32,1,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (5393+((i*32)),30*15,32,32,24,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Block (5425+((i*32)),30*15,32,32,1,1,handler));
		}
		for (int i =0; i<1; i++) {
			handler.addObj(new Pipe (5681,505,64,64,1,0,false));
		}
		for (int i =0; i<9; i++) {
			handler.addObj(new Block (5745+((i*32)),30*18,32,32,28,1,handler));
		}
		for (int i =0; i<8; i++) {
			handler.addObj(new Block (5777+((i*32)),30*17,32,32,28,1,handler));
		}
		for (int i =0; i<7; i++) {
			handler.addObj(new Block (5809+((i*32)),30*16,32,32,28,1,handler));
		}
		for (int i =0; i<6; i++) {
			handler.addObj(new Block (5841+((i*32)),30*15,32,32,28,1,handler));
		}
		for (int i =0; i<5; i++) {
			handler.addObj(new Block (5873+((i*32)),30*14,32,32,28,1,handler));
		}
		for (int i =0; i<4; i++) {
			handler.addObj(new Block (5905+((i*32)),30*13,32,32,28,1,handler));
		}
		for (int i =0; i<3; i++) {
			handler.addObj(new Block (5937+((i*32)),30*12,32,32,28,1,handler));
		}
		for (int i =0; i<2; i++) {
			handler.addObj(new Block (5969+((i*32)),30*11,32,32,28,1,handler));
		}
		for (int i =0; i<1; i++) {
			//handler.addObj(new Block (6385+((i*32)),535,32,32,2,1,handler));
		}
		for (int i =0; i<1; i++) {
			//handler.addObj(new Block (6385+((i*32)),505,32,32,2,1,handler));
		}
		
		for (int i =0; i<1; i++) {
			//handler.addObj(new Block (6417+((i*32)),505,32,32,12,1,handler));
		}
		for (int i =0; i<1; i++) {
			//handler.addObj(new Block (6449+((i*32)),535,32,32,14,1,handler));
		}
		for (int i =0; i<1; i++) {
			//handler.addObj(new Block (6449+((i*32)),505,32,32,14,1,handler));
		}
		for (int i =0; i<1; i++) {
			//handler.addObj(new Block (6481+((i*32)),535,32,32,2,1,handler));
		}
		for (int i =0; i<1; i++) {
			//.addObj(new Block (6481+((i*32)),505,32,32,2,1,handler));
		}
		for (int i =0; i<1; i++) {
			//handler.addObj(new Block (6481+((i*32)),475,32,32,11,1,handler));
		}
		for (int i =0; i<1; i++) {
			//handler.addObj(new Block (6433+((i*32)),475,32,32,40,1,handler));
		}
		for (int i =0; i<1; i++) {
			//handler.addObj(new Block (6449+((i*32)),475,32,32,11,1));
		}
		for (int i =0; i<3; i++) {
			//handler.addObj(new Block (6257+((i*32)),540,32,32,28,1,handler));
		}
		
	}

	private synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running =true;
		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		running =false;
	}
	
	
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountofticks = TICKS;
		double ns = NANOSECONDS / amountofticks;
		double delta =0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates=0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime =now;
			
			while (delta >= 1){
				tick();
				updates++;
				delta--;
			}
			if (running) {
				render();
				frames++;
			}
			
			if (System.currentTimeMillis() - timer > MILLISECONDS) {
				timer += MILLISECONDS;
				System.out.println("FPS: " + frames + " TPS: "+ updates);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		remainingTime--;
		if (remainingTime==0) {
			resetToStart();
			Player.health=3;
			remainingTime=400*60;
			Block.coinrecieved=0;
			
		}
		handler.tick();
		cam.tick(handler.getPlayer());
		if(Castle.touching==true||Player.health==0) {
		handler.pauseBackground();
		}
		if(Player.health==0) {
			Player.health=3;
			KeyInput.moveleft=false;
			KeyInput.moveright=false;
			
		}
	}
	
	
	private void render() {
	    BufferStrategy bs = this.getBufferStrategy();
	    if (bs == null) {
	        this.createBufferStrategy(3);
	        return;
	    }

	    Graphics2D g = (Graphics2D) bs.getDrawGraphics();
	    //////////////////////////////////////////////

	    // Draw the scrolling background
	    p.render(g);

	    g.translate(cam.GetX(), cam.GetY()); // Begin of cam

	    handler.render(g);

	    g.translate(-cam.GetX(), -cam.GetY()); // End of cam

	    //////////////////////////////////////////////
	    drawPoints(g);
        drawRemainingLives(g);
        drawAcquiredCoins(g);
        drawRemainingTime(g);
	    g.dispose();
	    bs.show();
	}
    
	private void resetToStart() {    	
    	if (Player.health==0 || SuperMarioBros.remainingTime==0){
        	Player player = SuperMarioBros.handler.getPlayer();
        	handler.getPlayer().setX(3000);
        	player.setY(10);
        	handler.getPlayer().setVelX(0);
    		handler.pauseBackground();
    		handler.playGameOver();
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
	private void drawRemainingTime(Graphics2D g2) {
        g2.setFont(gameFont.deriveFont(25f));
        g2.setColor(Color.WHITE);
        String displayedStr = "TIME: " + remainingTime / 60;
        g2.drawString(displayedStr, 535, 50);
    }

	  private void drawRemainingLives(Graphics2D g2) {
	        g2.setFont(gameFont.deriveFont(30f));
	        g2.setColor(Color.WHITE);
	        String displayedStr = "" + player.health;
	        g2.drawImage(heartIcon, 10, 10, null);
	        g2.drawString(displayedStr, 60, 50);
	    }
	  private void drawAcquiredCoins(Graphics2D g2) {
	        g2.setFont(gameFont.deriveFont(30f));
	        g2.setColor(Color.WHITE);
	        String displayedStr = "" + Block.coinrecieved;
	        g2.drawImage(coinIcon, getWidth()-115, 10, null);
	        g2.drawString(displayedStr, getWidth()-65, 50);
	    }
	  
	  private void drawPoints(Graphics2D g2){
	        g2.setFont(gameFont.deriveFont(25f));
	        g2.setColor(Color.WHITE);
	        String displayedStr = "Points: " + points;
	        int stringLength = g2.getFontMetrics().stringWidth(displayedStr);;
	        //g2.drawImage(coinIcon, 50, 10, null);
	        g2.drawString(displayedStr, 170, 50);
	    }
	public static Texture getTexture() {
		return tex;
	}
	
	public static int getwindowedheight() {
		return windowheight;
	}
	public static int getwindowedwidth() {
		return windowwidth;
	}
	
	public static int getscreenwidth() {
		return screenwidth;
	}
	
	public static int getscreenheight() {
		return screenheight;
	}
	

}
