import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ChessBoard extends JPanel{
	
	private static final long serialVersionUID = 5380115823346218636L;
			
	//Set all variables
	
	private int width;
	private int height;
	private ChessPiece[] game;
	Position firstSelected;
	Position secondSelected;
	
	//All necessary getters and setters
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	//Constructors
	
	public ChessBoard() {
		this(800);
	}
	
	public ChessBoard(int size) {
		width = size;
		height = size; 
		this.setSize(size, size);
		this.setBackground(new Color(100, 100, 100));
		this.setVisible(true);
		CreateAllPieces();
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				 Select(e);
			}
		});
	}
	
	//Painting of the chessboard, including all pieces
	@Override
	public void paintComponent(Graphics	 g) {
		super.paintComponent(g);
		
		//Paint the background
		g.setColor(new Color(50, 50, 50));
		for(int j = 0; j < 8; j++) {
			for(int i = 0; i < 4 ; i++) {
				g.fillRect((i) * (width / 4) + j%2 * height/8, j * (height/8), width/8, height/8);
			}
		}
		
		//Paint the selected square, if selected
		if(firstSelected != null) {
			g.setColor(new Color(120, 80, 80));
			g.fillRect((int)(firstSelected.getXPos() * (float)width/8), (int)((firstSelected.getYPos()) * ((float)height/8)), width/8, height/8);
		}
		
		//Display the chesspieces
		for(ChessPiece piece  : game) {
			String imageURL = piece.getImageURL();
			int xPos = piece.getXPos();
			int yPos = piece.getYPos();
			//Image image = loadImage("/Images/WP.png");
			Image image = loadImage(imageURL);
			g.drawImage(image, xPos * (width/8), height - (yPos+1) * (height / 8), (width/8), (height/8), null); 
		}	
	}
	
	public void paintSelected(Graphics g) {
		if(firstSelected != null) {
			g.setColor(new Color(120, 80, 80));
			g.fillRect((int)(firstSelected.getXPos() * (float)width/8), (int)((firstSelected.getYPos()) * ((float)height/8)), width/8, height/8);
		}
		
	}
	
	//Upon creation, all pieces are added to the game
	private void CreateAllPieces() {
		game = new ChessPiece[]
				{new ChessPiece("Rook", "White", "a1"), new ChessPiece("Knight", "White", "b1"), 
				new ChessPiece("Bisshop", "White", "c1"), new ChessPiece("Queen", "White", "d1"), 
				new ChessPiece("King", "White", "e1"),  new ChessPiece("Bisshop", "White", "f1"), 
				new ChessPiece("Knight", "White","g1"), new ChessPiece("Rook", "White", "h1"),
				new ChessPiece("Pawn", "White", "a2"), new ChessPiece("Pawn", "White", "b2"),
				new ChessPiece("Pawn", "White", "c2"), new ChessPiece("Pawn", "White", "d2"),
				new ChessPiece("Pawn", "White", "e2"), new ChessPiece("Pawn", "White", "f2"),
				new ChessPiece("Pawn", "White", "g2"), new ChessPiece("Pawn", "White", "h2"),
				new ChessPiece("Rook", "Black", "a8"), new ChessPiece("Knight", "Black", "b8"), 
				new ChessPiece("Bisshop", "Black", "c8"), new ChessPiece("Queen", "Black", "d8"), 
				new ChessPiece("King", "Black", "e8"),  new ChessPiece("Bisshop", "Black", "f8"), 
				new ChessPiece("Knight", "Black","g8"), new ChessPiece("Rook", "Black", "h8"),
				new ChessPiece("Pawn", "Black", "a7"), new ChessPiece("Pawn", "Black", "b7"),
				new ChessPiece("Pawn", "Black", "c7"), new ChessPiece("Pawn", "Black", "d7"),
				new ChessPiece("Pawn", "Black", "e7"), new ChessPiece("Pawn", "Black", "f7"),
				new ChessPiece("Pawn", "Black", "g7"), new ChessPiece("Pawn", "Black", "h7"),
				};
	}
	
	//Method for reading images
	private Image loadImage(String path) {
		  try
		    {
		        BufferedImage image = ImageIO.read((getClass().getResource(path)));
		        return image;
		    } catch (IOException e)
		    {
		        e.printStackTrace();
		    }
		    return null;
		
	}
	
	//All methods for manipulating the pieces
	private void Select(MouseEvent e) {	
		//Calculate position of the mouse
		if(0 <= e.getX() && width >= e.getX() && 0 <= e.getY() && height >= e.getY()) {
			String posString = "";
			String[] files = {"a", "b", "c", "d", "e", "f", "g", "h"};
			String[] rows = {"1", "2", "3", "4", "5", "6", "7", "8"};
			
			for(int i = 0; i < 8; i++) {
				if(((float)e.getX() / width) < (float)(i+1) / 8) {
					posString += files[i];
					break;
				}
			}	
			
			for(int i = 0; i < 8; i++) {
				if(((float)e.getY() / height) < (float)(i+1) / 8) {
					posString += rows[i];
					break;
				}
			}	
			
			Position pos = new Position(posString);
			
			for(ChessPiece piece : game) {
				if(piece.getXPos() == pos.getXPos() && piece.getYPos() == pos.getYPos()) {
					firstSelected = pos;;
					break;
				}
			}
			repaint();
		}	
	}
}
