/**
 * 
 */
package clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * @author Administrator
 *
 */
public class CashierClient extends BaseClient {

	/**
	 * 
	 */
	
	
	private static JList pendingList;
	private static JLabel TestLabel; 
	
	
	public CashierClient() {
		super();
	}

	/**
	 * @param Height
	 * @param Width
	 */
	public CashierClient(int Height, int Width, String Name) {
		super(Width, Height, Name );
		
		pendingList = new JList();
		JScrollPane pendingScroller = new JScrollPane(pendingList);
		pendingScroller.setBounds(25, 60, 150, 300);
	    this.add(pendingScroller);
		
	    this.getContentPane().setBackground(new Color(20,200,200));
	    
	    TestLabel = new JLabel("Test Label");
		this.add(TestLabel, BorderLayout.CENTER);
		
		// TODO Auto-generated constructor stub
	}

}
