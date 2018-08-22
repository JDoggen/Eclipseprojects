import javax.swing.JFrame;

public class Chess {

	public static void main(String[] args) {
		
		int size = 800;
		
		
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		
		ChessBoard board = new ChessBoard(800);
		frame.add(board);
		
	}

}
