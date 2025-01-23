package FrameSuperMarios;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import SuperMarioObjectUtil.KeyInput;

public class ScrollingBack implements Runnable {
    private int scrollSpeed = 5; // Adjust the scroll speed as desired
    private int backOffset;
    private int backWidth;
    private BufferedImage backgroundImage;
    private BufferedImage buffer;

    public ScrollingBack() {
        backOffset = 0;
        backWidth = 1000; // Adjust the width of the scrolling background as desired
        loadBackgroundImage();
        createBuffer();
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("background.png")); // Replace "background.png" with the path to your background image
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createBuffer() {
        if (backgroundImage != null) {
            int imageWidth = backgroundImage.getWidth();
            int imageHeight = backgroundImage.getHeight();
            buffer = new BufferedImage(backWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        }
    }

    public void run() {
        while (true) {
            scrollBackground();
            try {
                Thread.sleep(10); // Adjust the sleep time to control the scrolling speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void scrollBackground() {
        if (KeyInput.moveright == true) {
            backOffset -= scrollSpeed;
        }
        if (KeyInput.moveleft == true) {
            backOffset += scrollSpeed;
        }
    }

    public void render(Graphics g) {
        if (buffer != null) {
            Graphics bufferGraphics = buffer.getGraphics();
            //bufferGraphics.setColor(Color.BLACK); // Set the background color of the off-screen buffer
            //bufferGraphics.fillRect(0, 0, backWidth, buffer.getHeight());

            if (backgroundImage != null) {
                int imageWidth = backgroundImage.getWidth();
                int imageHeight = backgroundImage.getHeight();
                int x1 = backOffset % imageWidth;

                if (x1 < 0 && KeyInput.moveright==true) {
                    // Wrap the background image by drawing it twice
                    bufferGraphics.drawImage(backgroundImage, x1, 0, null);
                    //bufferGraphics.drawImage(backgroundImage, x1 + imageWidth, 0, null);
                } else {
                    // Draw the background image normally
                    bufferGraphics.drawImage(backgroundImage, x1, 0, null);
                }
                
                //if (-x1 < 0 && KeyInput.moveleft==true) {
                    // Wrap the background image by drawing it twice
                    //bufferGraphics.drawImage(backgroundImage, -x1, 0, null);
                    //bufferGraphics.drawImage(backgroundImage, x1 + imageWidth, 0, null);
                //} else {
                    // Draw the background image normally
                    //bufferGraphics.drawImage(backgroundImage, -x1, 0, null);
                //}
            }

            // Render the off-screen buffer on the screen
            g.drawImage(buffer, 0, 0, null);
        }
    }
        
        
        
        
        
        
        
        
        
    }


