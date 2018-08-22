import java.awt.Dimension;

import javax.swing.JFrame;


/**
 * This java script is used for creating a "painting" of a house, using a bare minimum of user input.
 * Using four clicks, a house (including a chimney) is painted, on a grass surface.
 * @author Jeroen
 *
 */

//Main class
public class BuildingHouse {
	
	public static void main(String[] args) {

		//A new JFrame() is created
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(1100,800));
		frame.setResizable(false);
		
		//frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Building a house");
		
		//A new  Painting() is created, based on the JPanel() class and added to the JFrame.
		Painting panel = new Painting();
		panel.setSize(800, 800);
		panel.setVisible(true);;
		frame.add(panel);
		
		ControlPanel cPanel = new ControlPanel(panel);
		cPanel.setSize(300,800);
		cPanel.setVisible(true);
		frame.add(cPanel);
		frame.revalidate();

	}
}
	


