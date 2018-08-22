import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")

public class ControlPanel extends JPanel{
	Painting paintingComponent;
	JButton skyColorPicker;
	JButton grassColorPicker;
	JButton houseColorPicker;
	JButton roofColorPicker;
	JButton chimneyColorPicker;
	
	
	ControlPanel(Painting paintingComponent) {
		this.paintingComponent = paintingComponent;
		
		//Add JLabels and JButtons for each component of the house
		setLayout(null);
		JLabel skyLabel = new JLabel();
		skyLabel.setText("Sky");
		skyLabel.setBounds(800, 100, 150, 30);
		skyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		skyLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(skyLabel);
		
		skyColorPicker = new JButton();
		skyColorPicker.setText("");
		skyColorPicker.setBounds(1000, 100, 30, 30);
		skyColorPicker.setBackground(paintingComponent.getSkyColor());
		skyColorPicker.setEnabled(true);
		add(skyColorPicker);
		
		JLabel grassLabel = new JLabel();
		grassLabel.setText("Grass");
		grassLabel.setBounds(800, 200, 150, 30);
		grassLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grassLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(grassLabel);
		
		grassColorPicker = new JButton();
		grassColorPicker.setText("");
		grassColorPicker.setBounds(1000, 200, 30, 30);
		grassColorPicker.setBackground(paintingComponent.getGrassColor());
		grassColorPicker.setEnabled(true);
		add(grassColorPicker);
		
		JLabel houseLabel = new JLabel();
		houseLabel.setText("House");
		houseLabel.setBounds(800, 300, 150, 30);
		houseLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		houseLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(houseLabel);
		
		houseColorPicker = new JButton();
		houseColorPicker.setText("");
		houseColorPicker.setBounds(1000, 300, 30, 30);
		houseColorPicker.setBackground(paintingComponent.getHouseColor());
		houseColorPicker.setEnabled(true);
		add(houseColorPicker);
		
		JLabel roofLabel = new JLabel();
		roofLabel.setText("Roof");
		roofLabel.setBounds(800, 400, 150, 30);
		roofLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		roofLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(roofLabel);
		
		roofColorPicker = new JButton();
		roofColorPicker.setText("");
		roofColorPicker.setBounds(1000, 400, 30, 30);
		roofColorPicker.setBackground(paintingComponent.getRoofColor());
		roofColorPicker.setEnabled(true);
		add(roofColorPicker);
		
		JLabel chimneyLabel = new JLabel();
		chimneyLabel.setText("Chimney");
		chimneyLabel.setBounds(800, 500, 150, 30);;
		chimneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		chimneyLabel.setVerticalAlignment(SwingConstants.CENTER);
		add(chimneyLabel);
		
		chimneyColorPicker = new JButton();
		chimneyColorPicker.setText("");
		chimneyColorPicker.setBounds(1000, 500, 30, 30);
		chimneyColorPicker.setBackground(paintingComponent.getChimneyColor());
		chimneyColorPicker.setEnabled(true);
		add(chimneyColorPicker);
		
		JButton reset = new JButton();
		reset.setText("Reset");
		reset.setBounds(900, 650, 100, 20);
		add(reset);
		
		//For each ColorPicker the pickColor() function should be called
		skyColorPicker.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
			    pickColor("sky");
			  } 
		} );
		
		grassColorPicker.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    pickColor("grass");
				  } 
			} );
			
		
		houseColorPicker.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    pickColor("house");
				  } 
			} );
		
		roofColorPicker.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    pickColor("roof");
				  } 
			} );
		
		chimneyColorPicker.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    pickColor("chimney");
				  } 
				
			} );
		System.out.println(skyColorPicker);
		
		//The reset button will reset all variables to its default position
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paintingComponent.setCurrentStage(0);
				paintingComponent.repaint();
				paintingComponent.setSkyColor(new Color(145, 165, 230));
				paintingComponent.setGrassColor(new Color(50, 110, 50));
				paintingComponent.setHouseColor(new Color(150, 150, 90));
				paintingComponent.setRoofColor(new Color(140, 50, 30));
				paintingComponent.setChimneyColor(new Color(115, 95, 80));
				
				skyColorPicker.setBackground(new Color(145, 165, 230));
				grassColorPicker.setBackground(new Color(50, 110, 50));
				houseColorPicker.setBackground(new Color(150, 150, 90));
				roofColorPicker.setBackground(new Color(140, 50, 30));
				chimneyColorPicker.setBackground(new Color(115, 95, 80));
			}
		});
	}

		//pickColor() will open a colorChooser
	void pickColor(String part) {
		//JColorChooser colorChooser = new JColorChooser();
		Color startingColor = new Color(0, 0, 0);
			switch(part) {
				case "sky": startingColor = paintingComponent.getSkyColor();
					break;
				case "grass": startingColor = paintingComponent.getGrassColor();
					break;
				case "house": startingColor = paintingComponent.getHouseColor();
					break;
				case "roof": startingColor = paintingComponent.getRoofColor();
					break;
				case "chimney": startingColor = paintingComponent.getChimneyColor();
					break;
			}

			
		Color color = JColorChooser.showDialog(this, "Choose", startingColor);
		
		if(color!=null) {
			switch(part) {
				case "sky": paintingComponent.setSkyColor(color);
					skyColorPicker.setBackground(color);
					break;
				case "grass": paintingComponent.setGrassColor(color);
					grassColorPicker.setBackground(color);
					break;
				case "house": paintingComponent.setHouseColor(color);
					houseColorPicker.setBackground(color);
					break;
				case "roof": paintingComponent.setRoofColor(color);
					roofColorPicker.setBackground(color);
					break;
				case "chimney": paintingComponent.setChimneyColor(color);
					chimneyColorPicker.setBackground(color);
					break;
					
			}
			
			paintingComponent.repaint();	
			}
	}	
}