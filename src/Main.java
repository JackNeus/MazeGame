import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JFrame {
	public Main(){
		setTitle(""); 
		setSize(300, 200); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		JPanel panel = new MenuPanel(); 
		this.add(panel); 
		setResizable(false);
	}
	
	public static void main(String args[]){
		JFrame frame = new Main();
		frame.setVisible(true);
		//new Maze();
	} 
}
