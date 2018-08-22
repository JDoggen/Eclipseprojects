import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;


/*
 * Following Class is a class based on JPanel(). Built in features include the capturing of mouse-clicks,
 * as well as  painting the different aspects of the painting.
 *
 */

@SuppressWarnings("serial")
public class Painting extends JPanel{
	
	//the variable currentStage is used for the Graphics component to know what parts to draw, as well as where to store the mouseClicks
	private int currentStage = 0;
	
	public void setCurrentStage(int stage) {
		currentStage = stage;
	}
	public int getCurrentStage() {
		return currentStage;
	}
	
	//All captured mousClicks are stored
	private Coordinate firstClick = new Coordinate(0, 0);
	private Coordinate secondClick = new Coordinate(0, 0);
	private Coordinate thirdClick = new Coordinate(0, 0);
	private Coordinate fourthClick = new Coordinate(0, 0);
	
	//The default colors for the different aspects of the house
    private Color skyColor = new Color(145, 165, 230);
    
    public void setSkyColor(Color c) {
    	skyColor = c;
    }
    public Color getSkyColor() {
    	return skyColor;
    }
    
    private Color grassColor = new Color(50, 110, 50);
   
    public void setGrassColor(Color c) {
    	grassColor = c;
    }
    public Color getGrassColor() {
    	return grassColor;
    }
   
    private Color houseColor = new Color(150, 150, 90);
    
    public void setHouseColor(Color c) {
    	houseColor = c;
    }
    public Color getHouseColor() {
    	return houseColor;
    }
    
    private Color roofColor = new Color(140, 50, 30);
    
    public void setRoofColor(Color c) {
    	roofColor = c;
    }
    public Color getRoofColor() {
    	return roofColor;
    }
    
    private Color chimneyColor = new Color(115, 95, 80); 
    
    public void setChimneyColor(Color c) {
    	chimneyColor = c;
    }
    public Color getChimneyColor() {
    	return chimneyColor;
    }
    
	//Two integers used for reference of the house position
	private int minimumX = 0;
	private int maximumX = 0;
	
	Painting() {

		this.setBackground(skyColor);
		
		/*A MouseAdapter() is attached to the Painting() to capture the mouse-clicks. Each stage has
		 * different checks to validate the mouse-clicks. 
		 */
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getX() <= 800) {
					if(currentStage == 0) {
						firstClick.setX(e.getX());
						firstClick.setY(e.getY());
						repaint();
						currentStage++;
					}	else 
					if(currentStage == 1) {
						
						//Check if the house is above the grass
						if(e.getY() < firstClick.getY()) {
							secondClick.setX(e.getX());
							secondClick.setY(e.getY());
							repaint();
							currentStage++;
						}
					}	else 
					if(currentStage == 2) {
						//Calculating the minimum and maximum X value of the house, for future reference
						minimumX = Math.min(firstClick.getX(), secondClick.getX());
						maximumX = Math.max(firstClick.getX(), secondClick.getX());
						
						//Check if the roof is in between the borders of the house, and above the house
						if(e.getY() < secondClick.getY() && e.getX() > minimumX && e.getX() < maximumX) {
							thirdClick.setX(e.getX());
							thirdClick.setY(e.getY());
							repaint();
							currentStage++;
						}	
					}	else
					if(currentStage == 3) {
						//Check if the roof is on the left side of the roof. Then it checks if the "chimney" is above the roof at that position, yet not exceed the total height of the house
						if((e.getX() > minimumX && e.getX() < thirdClick.getX()) //Check if chimney is "above" the left side of the roof
								&& (e.getY() < (secondClick.getY() - (((float)(e.getX() - minimumX) / (thirdClick.getX()-minimumX)) * (secondClick.getY() - thirdClick.getY())))) //Check if chimney is above the roof
								&& (e.getY() > thirdClick.getY())) { //Check if chimney doesn't surpass the roof
							fourthClick.setX(e.getX());
							fourthClick.setY(e.getY());
							repaint();
							currentStage++;						
						}  else
							//Check if the roof is on the right side of the roof. Then it checks if the "chimney" is above the roof at that position, yet not exceed the total height of the house
						if((e.getX() < maximumX && e.getX() > thirdClick.getX()) //Check if chimney is "above" the right side of the roof
								&& (e.getY() < (secondClick.getY() - (((float)(maximumX - e.getX()) / (maximumX - thirdClick.getX())) * (secondClick.getY() - thirdClick.getY())))) 
								&& (e.getY() > thirdClick.getY())){ //Check if chimney doesn't surpass the roof
								fourthClick.setX(e.getX());
								fourthClick.setY(e.getY());
								repaint();
								currentStage++;						
						}				
					}
				}
			}
		});	
	}
			
	@Override
	public void paintComponent(Graphics g) {
		//At different stages, the picture is repainted up until that stage
		if(currentStage >= 0) {
			//Paint the sky
			g.setColor(skyColor);		
			g.fillRect(0, 0, 800, 800);
		}
		if(currentStage >= 1) {
			//Paint the grass
			g.setColor(grassColor);
			g.fillRect(0, firstClick.getY(), 800, 800-firstClick.getY());
		}
		if(currentStage >= 2) {
			//Paint the base of the house
			g.setColor(houseColor);
			g.fillRect(Math.min(firstClick.getX(), secondClick.getX()), secondClick.getY(), Math.abs((secondClick.getX()-firstClick.getX())), firstClick.getY()-secondClick.getY());
		}
		if(currentStage >= 3) {
			//Paint the roof
			int[] xPoints = {firstClick.getX(), secondClick.getX(), thirdClick.getX()};
			int[] yPoints = {secondClick.getY(), secondClick.getY(), thirdClick.getY()};
			g.setColor(roofColor);
			g.fillPolygon(xPoints,  yPoints, 3);
		}
		if(currentStage >= 4) {
			//First calculate at what points the chimney is attached to the roof. Then draw the chimney
			if(fourthClick.getX() < thirdClick.getX()) {	
				int[] xPoints = {fourthClick.getX(), 
						fourthClick.getX(), 
						Math.round( (minimumX +  ((float)(secondClick.getY() - fourthClick.getY()) / (secondClick.getY() - thirdClick.getY() ) * (thirdClick.getX() - minimumX))))};
				
				int[] yPoints = {fourthClick.getY(), 
						Math.round( (secondClick.getY() - ( (float) (fourthClick.getX() - minimumX ) / (thirdClick.getX() - minimumX)) * (secondClick.getY() - thirdClick.getY()))),
						fourthClick.getY()};
				g.setColor(chimneyColor);
				g.fillPolygon(xPoints, yPoints, 3);
			} else {
				int[] xPoints = {fourthClick.getX(),
						fourthClick.getX(),
						Math.round( (maximumX - ((float) (secondClick.getY() - fourthClick.getY()) / (secondClick.getY() - thirdClick.getY()) * (maximumX - thirdClick.getX()))))};
				
				int[] yPoints = {fourthClick.getY(),
						Math.round( (secondClick.getY() - ((float) (maximumX - fourthClick.getX()) / (maximumX - thirdClick.getX()) * (secondClick.getY() - thirdClick.getY())))),
						fourthClick.getY()};
				g.setColor(chimneyColor);
				g.fillPolygon(xPoints,yPoints, 3);
			}		
		}			
	}
}		