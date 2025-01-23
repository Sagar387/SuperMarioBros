package SuperMarioBros;

import java.awt. *; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import SuperMarioBros.Controls;
import SuperMarioBros.SuperMarioBros;
import SuperMarioBros.Instructions;
import SuperMarioObjectUtil.Handler;


public class Win extends JFrame implements ActionListener {
	//Import Images And Resizing
	//Start Button Image
	ImageIcon Start = new ImageIcon("restart.png");
	Image img1 = Start.getImage() ;    
	Image newimg1 = img1.getScaledInstance(150,150, java.awt.Image.SCALE_SMOOTH ) ; 
	ImageIcon Start1 = new ImageIcon( newimg1 );
	//Control Button Image
	//ImageIcon Controls = new ImageIcon("controls.png");
	//Image img2 = Controls.getImage() ;    
	//Image newimg2 = img2.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH ) ; 
	//ImageIcon Controls1 = new ImageIcon( newimg2 );
	//Instruction Button Image
	//ImageIcon Instructions = new ImageIcon("instuc.png");
	//Image img3 = Instructions.getImage() ;    
	//Image newimg3 = img3.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH ) ; 
	//ImageIcon Instructions1 = new ImageIcon( newimg3 );
	//Menu Background Screen
	ImageIcon Back = new ImageIcon("win.jpg");
	Image img4 = Back.getImage() ;    
	Image newimg4 = img4.getScaledInstance(663, 373, java.awt.Image.SCALE_SMOOTH ) ; 
	ImageIcon Back1 = new ImageIcon( newimg4 );
	//Component Initializing
	JFrame frame = new JFrame("Super Mario Bros");
	JLabel back = new JLabel();
	JButton start = new JButton(Start1);
	//JButton con = new JButton(Controls1);
	//JButton inst = new JButton(Instructions1);
	public Win(){
		//Frame Screen Setup
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension a = new Dimension(663, 373);
		back.setBounds(0,0,663, 373);
		back.setIcon(Back1);
		frame.setLayout(null);
		start.addActionListener(this);
		//con.addActionListener(this);
		///inst.addActionListener(this);
		start.setBounds(70, 30, 150, 150);
		start.setBorder(BorderFactory.createEmptyBorder());
		start.setContentAreaFilled(false);
		//con.setBounds(10, 350, 150, 150);
		//con.setBorder(BorderFactory.createEmptyBorder());
		//con.setContentAreaFilled(false);
		//inst.setBounds(325, 350, 150, 150);
		//inst.setBorder(BorderFactory.createEmptyBorder());
		//inst.setContentAreaFilled(false);
		frame.add(start);
		//frame.add(con);
		//frame.add(inst);
		frame.add(back);
		frame.setSize(663, 410);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		Menu gui = new Menu(); //Creates Screen
		Handler handler = new Handler();
		//handler.playBackground();
	}
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == start){ //Checks If Start is clicked
			frame.dispose();
			Menu g = new Menu(); // Moves To Game Screen
		//}
		//if(event.getSource() == inst){ //Check If Instruction Button is Clicked
			//frame.dispose();
			//Instructions n = new Instructions(); //Move to Instruction Screen
		//}
		//if(event.getSource() == con){ //Checks If Controls Button is Clicked
			//frame.dispose();
			//Controls g = new Controls(); //Moves To Control Screen
		//}
	}

}
}