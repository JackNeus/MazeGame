import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MenuPanel extends JPanel { 
	 public MenuPanel() 
	 { 
		 final TextField width, height, tile;
		 
		 JPanel pWidth = new JPanel();
		 pWidth.setLayout(new BorderLayout());
		 JLabel widthLabel = new JLabel("Width (100-1000px): ");
		 width = new TextField("", 20);
		 pWidth.add(widthLabel, BorderLayout.WEST);
		 pWidth.add(width, BorderLayout.EAST);
		 
		 JPanel pHeight = new JPanel();
		 pHeight.setLayout(new BorderLayout());
		 JLabel heightLabel = new JLabel("Height (100-1000px): ");
		 height = new TextField("", 20);
		 pHeight.add(heightLabel, BorderLayout.WEST);
		 pHeight.add(height, BorderLayout.EAST);
		 
		 JPanel pTile = new JPanel();
		 pTile.setLayout(new BorderLayout());
		 JLabel tileLabel = new JLabel("Tile (5+ px): ");
		 tile = new TextField("", 20);
		 pTile.add(tileLabel, BorderLayout.WEST);
		 pTile.add(tile, BorderLayout.EAST);
		 
		 JPanel inputFields = new JPanel();
		 inputFields.setLayout(new BorderLayout());
		 inputFields.add(pWidth, BorderLayout.NORTH);
		 inputFields.add(pHeight, BorderLayout.CENTER);
		 inputFields.add(pTile, BorderLayout.SOUTH);
		 
		 JButton create = new JButton("Go!");
		 
		 create.addActionListener(new ActionListener() {
			 
	            public void actionPerformed(ActionEvent e)
	            {
	            	int widthval = 600, heightval = 400, tileval = 5;
	            	try{
		            	widthval = Integer.parseInt(width.getText());
		            	heightval = Integer.parseInt(height.getText());
		            	tileval = Integer.parseInt(tile.getText());
		            	new Maze(widthval, heightval, tileval);
	            	} catch (NumberFormatException exception){
	            		System.out.println("Please check your input");
	            	}
	            }
	        });
		 
		 this.setLayout( new BorderLayout());
		 this.add(inputFields, BorderLayout.NORTH);
		 this.add( create, BorderLayout.SOUTH ); 
		 
		 Border panelBorder = BorderFactory.createEtchedBorder();
		 panelBorder = BorderFactory.createTitledBorder(panelBorder, "Maze Settings");
		 this.setBorder(panelBorder);
		 
	 } 
	} 
