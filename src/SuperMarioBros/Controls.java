package SuperMarioBros;
/**
 * Controls Screen
 * Andy Lin and Aharan Uththaradevan 
 * 2019/1/15
 */
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Controls extends JFrame implements ActionListener{
	//Importing Pictures and Resizing
	//Back Button
	ImageIcon Back = new ImageIcon("back.png");
	Image img1 = Back.getImage() ;    
	Image newimg1 = img1.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH ) ; 
	ImageIcon back = new ImageIcon( newimg1 );
	//Control Screen Background
	ImageIcon inst = new ImageIcon("cback.png");
	Image img2 = inst.getImage() ;    
	Image newimg2 = img2.getScaledInstance(600, 338, java.awt.Image.SCALE_SMOOTH ) ; 
	ImageIcon inst2 = new ImageIcon( newimg2 );
	//Components Intializing 
	JFrame frame = new JFrame("Fall Of Gods"); //JFrame 
	JLabel label = new JLabel(); //Label for background
	JButton bck = new JButton(back); //Button for action
	public Controls(){
		//Setups the Frame
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLayout(null);
		label.setBounds(0,0,600,338);
		label.setIcon(inst2);
		bck.addActionListener(this);
		bck.setBounds(10,100,150,150);
		bck.setBorder(BorderFactory.createEmptyBorder());
		bck.setContentAreaFilled(false);
		frame.add(bck);
		frame.add(label);
		frame.setSize(616,375);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		Controls g = new Controls(); //Creates The Frame
	}
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == bck){ //Checks If User Presses Back Button
			frame.dispose();
			Menu gui = new Menu(); //Goes Back To menu screen
		}
	}
}
