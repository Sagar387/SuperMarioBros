package SuperMarioBros;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Instructions extends JFrame implements ActionListener {
	//Importing Image And Resizing
	//Back Button
	ImageIcon Back = new ImageIcon("back.png");
	Image img1 = Back.getImage() ;    
	Image newimg1 = img1.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH ) ; 
	ImageIcon back = new ImageIcon( newimg1 );
	//Instruction Screen BackGround
	ImageIcon inst = new ImageIcon("cintruc.png");
	Image img2 = inst.getImage() ;    
	Image newimg2 = img2.getScaledInstance(600, 338, java.awt.Image.SCALE_SMOOTH ) ; 
	ImageIcon inst2 = new ImageIcon( newimg2 );
	//Components Initializing
	JFrame frame = new JFrame("SuperMarioBros"); //Frame Setup
	JLabel label = new JLabel(); //Label For Backgroubd
	JButton bck = new JButton(back); //Button for back action
	public Instructions(){
		//Setups Frame For Instruction Screen
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(null);
		label.setBounds(0,0,600,338);
		label.setIcon(inst2);
		bck.addActionListener(this);
		bck.setBounds(370,180,150,150);
		bck.setBorder(BorderFactory.createEmptyBorder());
		bck.setContentAreaFilled(false);
		frame.add(bck);
		frame.add(label);
		frame.setSize(616,375);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		Instructions n = new Instructions(); //Creates Instruction Screen
	}
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == bck){ //Checks if user pushes Back button
			frame.dispose();
			Menu gui = new Menu(); //Goes Back To Menu Screen
		}
	}
}
