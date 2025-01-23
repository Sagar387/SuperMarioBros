package FrameSuperMarios;

import java.awt.Dimension;

import javax.swing.JFrame;

import SuperMarioBros.SuperMarioBros;
import SuperMarioObject.Player;

public class Frame {
	private static JFrame frame;
	private Dimension size;
	public Frame(int width, int height, String title, SuperMarioBros game) {
		size = new Dimension (width,height);
		frame = new JFrame(title);
		
		frame.setPreferredSize(size);
		frame.setMaximumSize(size);
		frame.setMinimumSize(size);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		
	}
	
	   public static void dispose() {
			   System.out.println("HELLO");
			   frame.dispose();
	    
	   }
	

}
