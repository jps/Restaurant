/**
 * 
 */
package clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * @author Administrator
 *
 */
public class CashierClient  {

	/**
	 * 
	 */
	
	private static int H = 400;
	private static int W = 200;
	protected JFrame frame;// = new JFrame();
	private static JList pendingList;
	private static JLabel TestLabel; 
	
	public CashierClient() {
		this(W,H, "Restaurant");
	}

	/**
	 * @param Height
	 * @param Width
	 */
	public CashierClient(int Height, int Width, String Name) {
		this(0,0, Height, Width, Name);
	}
	
	
	public CashierClient(int X, int Y, int Height, int Width, String Name) {
		Init( X, Y, Height, Width, Name);
	}
	
	private void Init(int X, int Y, int Width, int Height, String Name)
	{
		//1. Create the frame.
		
	    frame = new JFrame(Name); 
		frame.setName(Name);
		//frame.setSize(Width,Height);
		frame.setBounds(X, Y, Width, Height);
		Dimension minSize = new Dimension();
		minSize.setSize(Width,Height);
		frame.setMinimumSize(minSize);
		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(205,92,92));
		frame.setLayout(null);
		//3. Create components and put them in the frame.
		
		pendingList = new JList();
		JScrollPane pendingScroller = new JScrollPane(pendingList);
		pendingScroller.setBounds(25, 60, 150, 300);
	    frame.add(pendingScroller);
		
	    
	    TestLabel = new JLabel("Test Label");
	    TestLabel.setBounds(0, 0, 150, 300);
	    TestLabel.setForeground(Color.BLACK);
		frame.add(TestLabel);
		
		
		
		// TODO Auto-generated constructor stub
		
		//4. Size the frame.
		
		frame.pack();

		//5. Show it.
	    frame.setVisible(true);
		
	}

}
