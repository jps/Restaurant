/**
 * 
 */
package clients;

import javax.swing.*;

import java.awt.*;
//import java.awt.event.*;

/**
 * @author Administrator
 * 
 */
public class CookGUI {

	/**
	 * @param args
	 */

	private static int H = 300;
	private static int W = 400;
	protected JFrame frame;// = new JFrame();
	private JLabel TestLabel;
	private JLabel textArea;

	public CookGUI() {
		// empty constructor
		this(W, H, "Restaurant");
	}

	public CookGUI(int Width, int Height, String Name) {
		this(0, 0, Width, Height, Name);
	}

	public CookGUI(int X, int Y, int Width, int Height, String Name) {
		Init(X, Y, Width, Height, Name);
	}

	protected void Init(int X, int Y, int Width, int Height, String Name) {
		// 1. Create the frame.

		frame = new JFrame(Name);
		frame.setName(Name);
		frame.setLayout(null);
		// frame.setSize(Width,Height);
		frame.setBounds(X, Y, Width, Height);
		Dimension minSize = new Dimension();
		minSize.setSize(Width, Height);
		frame.setMinimumSize(minSize);

		// frame.setMaximumSize(maximumSize);
		frame.setBounds(X, Y, Width, Height);
		frame.getContentPane().setBackground(new Color(152, 251, 152));

		// 2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Create components and put them in the frame.

		// pendingList = new JList();
		// JScrollPane pendingScroller = new JScrollPane(pendingList);
		// pendingScroller.setBounds(10, 10, 150, 300);
		// frame.add(pendingScroller);

		// ...create emptyLabel...
		Font font = new Font("Verdana", Font.BOLD, 15);
		TestLabel = new JLabel("Currently Cooking");
		TestLabel.setFont(font);
		TestLabel.setBounds(10, 10, 200, 40);
		frame.add(TestLabel);

		textArea = new JLabel("None");
		textArea.setBounds(10, 60, 300, 20);
		frame.add(textArea);

		// 4. Size the frame.

		frame.pack();

		// 5. Show it.
		frame.setVisible(true);
	}

	public void setCurrentlyCooking(String text) {
		textArea.setText(text);
	}

}
