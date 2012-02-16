package clients;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ServerGUI {

	
	
	private static int H = 410;
	private static int W = 750;
	protected JFrame frame;// = new JFrame();
	
	private JLabel pendingLabel;
	private JLabel cookingLabel;
	private JLabel cookedLabel;
	private JLabel finishedLabel;
	
	private JList pendingList; //those waiting to be cooked
	private JList cookingList; //those that are being cooked
	private JList cookedList; //those that are being cooked
	private JList finishedList; //those that are being cooked
	
	private JScrollPane pendingScroller;
	private JScrollPane cookingScroller;
	private JScrollPane cookedScroller;
	private JScrollPane finishedScroller;
	
	public ServerGUI()
	{
		this(W, H, "ServerClient");		
	}
	
	public ServerGUI(int Width, int Height, String Name)
	{
		this(0,0,Width, Height, Name);
	}
	
	public ServerGUI(int X, int Y, int Width, int Height, String Name)
	{
		Init(X ,Y , Width, Height, Name);
	}
	
	
	protected void Init(int X, int Y, int Width, int Height, String Name)
	{
		//1. Create the frame.
		
	    frame = new JFrame(Name); 
		frame.setLayout(null);
	    frame.setName(Name);
		frame.setSize(Width,Height);
		Dimension minSize = new Dimension();
		minSize.setSize(Width,Height);
		frame.setMinimumSize(minSize);

		frame.setBounds(X, Y, W, H);
		frame.getContentPane().setBackground(new Color(220,220,220));
		
		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		
		pendingList = new JList();
		cookingList = new JList();
		cookedList = new JList();
		finishedList = new JList();
		
		pendingScroller = new JScrollPane(pendingList);
		pendingScroller.setBounds(25, 60, 150, 300);
	    frame.add(pendingScroller);
	    
		cookingScroller = new JScrollPane(cookingList);
		cookingScroller.setBounds(200, 60, 150, 300);
	    frame.add(cookingScroller);
	    
		cookedScroller = new JScrollPane(cookedList);
		cookedScroller.setBounds(375, 60, 150, 300);
	    frame.add(cookedScroller);
	    
		finishedScroller = new JScrollPane(finishedList);
		finishedScroller.setBounds(550, 60, 150, 300);
	    frame.add(finishedScroller);
	    
				
		//...create emptyLabel...
		
		pendingLabel = new JLabel("Pending Orders");
		pendingLabel.setBounds(25, 25,150,20);
		//pendingLabel.setOpaque(true);
		//pendingLabel.setForeground(Color.LIGHT_GRAY);
		frame.add(pendingLabel);

		cookingLabel = new JLabel("Being Cooked");
		cookingLabel.setBounds(200, 25,150,20);
		//cookingLabel.setOpaque(true);
		//cookingLabel.setForeground(Color.LIGHT_GRAY);
		frame.add(cookingLabel);
		
		cookedLabel = new JLabel("Cooked");
		cookedLabel.setBounds(375, 25,150,20);
		//cookedLabel.setOpaque(true);
		//cookedLabel.setHorizontalAlignment(400);
		//cookedLabel.setForeground(Color.LIGHT_GRAY);
		frame.add(cookedLabel);
		
		finishedLabel = new JLabel("Finished Orders");
		finishedLabel.setBounds(550, 25,150,20);
		//finishedLabel.setOpaque(true);
		//finishedLabel.setForeground(Color.LIGHT_GRAY);
		frame.add(finishedLabel);
		
		
		//4. Size the frame.
		
		frame.pack();

		//5. Show it.
	    frame.setVisible(true);
	}

}