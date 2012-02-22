/**
 * 
 */
package clients;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import models.Cashier;

/**
 * @author Administrator
 * 
 */
public class CashierGUI {

	/**
	 * 
	 */

	private static int H = 350;
	private static int W = 410;
	protected JFrame frame;// = new JFrame();
	private static JList CompletedList;
	private static JLabel CashierLabel;

	public CashierGUI() {
		this(W, H, "Restaurant");
	}

	/**
	 * @param Width
	 * @param Height
	 */
	public CashierGUI(int Width, int Height, String Name) {
		this(0, 0, Width, Height, Name);
	}

	public CashierGUI(int X, int Y, int Width, int Height, String Name) {
		Init(X, Y, Width, Height, Name);
	}

	private void Init(int X, int Y, int Width, int Height, String Name) {
		// 1. Create the frame.

		frame = new JFrame(Name);
		frame.setName(Name);
		frame.setLayout(null);
		// frame.setSize(300,400);
		frame.setBounds(X, Y, Width, Height);
		Dimension minSize = new Dimension();
		minSize.setSize(Width, Height);
		// frame.setMinimumSize(minSize);
		// 2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(205, 92, 92));
		frame.setLayout(null);
		// 3. Create components and put them in the frame.

		Font font = new Font("Verdana", Font.BOLD, 15);
		CashierLabel = new JLabel("cashiers completed orders");
		CashierLabel.setFont(font);
		CashierLabel.setBounds(25, -120, 300, 300);
		CashierLabel.setForeground(Color.BLACK);
		frame.add(CashierLabel);

		CompletedList = new JList();
		JScrollPane pendingScroller = new JScrollPane(CompletedList);
		pendingScroller.setBounds(25, 60, 280, 300);
		frame.add(pendingScroller);

		// TODO Auto-generated constructor stub

		// 4. Size the frame.

		// frame.pack();

		// 5. Show it.
		frame.setVisible(true);

	}

	public void update(Cashier cashier) {
		CompletedList.setListData(cashier.GetCompletedOrdersList());
	}
}