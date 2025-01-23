package SuperMarioObjectUtil;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import SuperMarioObject.Fire;
import SuperMarioObject.Fireball;

public class KeyInput extends KeyAdapter {
    public static boolean moveright;
    public static boolean moveleft;
    public static boolean[] keyDown = new boolean[4];
    private Handler handler;
    private boolean spacePressed;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (key == KeyEvent.VK_UP) {
            if (!handler.getPlayer().hasJumped()) {
                handler.getPlayer().setVelY(-15);
                keyDown[0] = true;
                handler.getPlayer().setJumped(true);
                handler.playJump();
            }
        }

        if (key == KeyEvent.VK_I && !spacePressed) {
        	if(Fire.hasfire==true)
            Fireball.startanimation = true;
            spacePressed = true;
        }

        if (key == KeyEvent.VK_LEFT) {
            handler.getPlayer().setVelX(-8);
            keyDown[1] = true;
            moveleft = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            handler.getPlayer().setVelX(8);
            keyDown[2] = true;
            moveright = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            keyDown[0] = false;
        }

        if (key == KeyEvent.VK_I) {
            spacePressed = false;
            try {
                Thread.sleep(10);  // Delay for 2000 milliseconds (2 seconds)
            } catch (InterruptedException d) {
                d.printStackTrace();
            }
            Fireball.startanimation = false;
        }

        if (key == KeyEvent.VK_LEFT) {
            keyDown[1] = false;
            moveleft = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            keyDown[2] = false;
            moveright = false;
        }

        if (!keyDown[1] && !keyDown[2]) {
            handler.getPlayer().setVelX(0);
        }
    }
}
