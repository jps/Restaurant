/**
 * 
 */
package clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Administrator
 *
 */
public class BaseClient extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	private static int H = 300;
	private static int W = 400;
	//protected JFrame frame;// = new JFrame();
	
	
	public BaseClient()
	{
		//empty constructor
		Init(W,H, "Restaurant");
	}
	
	public BaseClient(int Width, int Height, String Name)
	{
		Init(Width, Height, Name);
	}
	

	
	protected void Init(int Width, int Height, String Name)
	{
		//1. Create the frame.
		
	  //  frame = new JFrame(Name); 
		this.setName(Name);
		this.setSize(Width,Height);
		Dimension minSize = new Dimension();
		minSize.setSize(Width,Height);
		this.setMinimumSize(minSize);
		//2. Optional: What happens when the frame closes?
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		
		
		//...create emptyLabel...
		
		//JLabel TestLabel = new JLabel("Test Label");
		//this.add(TestLabel, BorderLayout.CENTER);

		//4. Size the frame.
		
		this.pack();
	    
		//5. Show it.
	    this.setVisible(true);
	}
	
	
	
	
}
