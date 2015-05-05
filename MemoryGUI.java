package memoryMgmtSystem;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;


public class MemoryGUI extends JFrame implements ActionListener, Observer {
	//Below is the set of starting user instructions in HTML
	private static final String instructions = "<html><center><font color='red'>Welcome to our Buddy System!</font><br><br>"
    		+ "<h1>Please read the instructions below. Press 'Start' when you are ready to begin: </h1><br> "
    		+ "1. <font color='gray'><em> To add an item to memory, write in its name and size and press 'Add'</em> </font><br>"
    		+ "2. <font color='gray'><em> To remove an item from memory, write in its name and press 'Remove'</em> </font><br>"
    		+ "3. <font color='gray'><em> To perform a random operation on a random item press 'Random'</em> </font><br>"
    		+ "4. <font color='gray'><em> To have a printout of the current items in memory press 'Print'</em> </font><br>"
    		+ "5. <font color='gray'><em> To reset your memory press 'Reset'</em> </font><br>"
    		+ "6. <font color='gray'><em> To quit the program, press 'Quit'</em> </font></center><br><br></html>";
	
	private static JPanel buttonBar = new JPanel(); //Holds all the main user buttons
	private static JPanel startBar = new JPanel(); //Holds start button
	private static JLabel instructionsLabel; //Used for instructional display
	MemoryGUIData data = new MemoryGUIData(0, false, 28, 64, 192, 16);
	private static JTextField nameAdd = new JTextField("");
	private static JTextField sizeBox = new JTextField("");
	private static JTextField nameRemove = new JTextField("");
	private NewCore core;
	
	

	public static void main(String[] args)
	{
		MemoryGUI MGUI = new MemoryGUI();
	}
	
	public MemoryGUI()
	{
		core = new NewCore (this);
		makeFrame();
	}
	
	public void makeFrame()
	{
		//MemoryController controller = new MemoryController();
		data.frame = new JFrame("Memory Management System");
		data.contentPane = data.frame.getContentPane();
		
		data.frame.setLayout(new BorderLayout());
		data.p1 = new JPanel();
		data.p1.setLayout(new GridLayout(64,1));
		data.p2 = new JPanel();
		data.contentPane.add(data.p1, BorderLayout.WEST);
		data.contentPane.add(data.p2, BorderLayout.EAST);
		
		data.frame.pack();
		data.frame.setSize(700, 800);
		data.frame.setVisible(true);
		buttonBar.setLayout(new GridLayout (8,2));
		data.p2.add(buttonBar, BorderLayout.EAST);
		
		if (data.startUsed == false) //Checks if user has already read the instructions
		{
			data.p1.setVisible(false);
			data.p2.setVisible(false);
			startBar.setLayout(new GridLayout (6,4));
			instructionsLabel = new JLabel(instructions);
			data.contentPane.add(startBar, BorderLayout.CENTER);
			data.contentPane.add(instructionsLabel, BorderLayout.NORTH);
			instructionsLabel.setVisible(false);
			Start();
			
		}
	}

	/*
	 * The Start method provides the user with a basic set of instructions
	 * when they first start to use the program. A 'Start' button 
	 * is present once the user is prepared to being using the buddy
	 * sysem for memory management.
	 */
	private void Start()
	{
		instructionsLabel.setVisible(true);
		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startBar.setVisible(false);
				instructionsLabel.setVisible(false);
				data.startUsed = true;
				data.p1.setVisible(true);
				data.p2.setVisible(true);
				populate();}
        });
		
		startBar.add(start);
	}
	
	//Populates the frame with buttons and textFields
	private  void addButtons()
	{
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			add();}
        });
		
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove();}
        });
		
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();}
        });
		
		JButton print = new JButton("Print");
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();}
        });
		
		JButton random = new JButton("Random");
		random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				random();}
        }); 
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quit();}
        });
		
		
		
		//Populate buttons
		JLabel blank1 = new JLabel("    ");
		JLabel blank2 = new JLabel("    ");
		JLabel blank3 = new JLabel("    ");
		JLabel blank4 = new JLabel("    ");
		JLabel name1 = new JLabel("Name");
		JLabel name2 = new JLabel("Name");
		JLabel size = new JLabel("Size");
		buttonBar.add(name1);
		buttonBar.add(size);
		buttonBar.add(nameAdd);
		buttonBar.add(sizeBox);
		buttonBar.add(add);
		buttonBar.add(blank1);
		buttonBar.add(name2);
		buttonBar.add(blank4);
		buttonBar.add(nameRemove);
		buttonBar.add(blank2);
		buttonBar.add(remove);
		buttonBar.add(blank3);
		buttonBar.add(print);
		buttonBar.add(random);
		buttonBar.add(reset);
		buttonBar.add(quit);
	}
	
	private void makeGrid()
	{
		//p1 = new JPanel();
		//p1.setLayout(new FlowLayout());
		data.p1.setLayout(new GridLayout(64,1));
		data.p1.setBackground(Color.WHITE);
		data.p1.setPreferredSize(new Dimension(data.MEM_BLOCK_WIDTH, data.MEM_BLOCK_SCALE));
		data.contentPane.add(data.p1, BorderLayout.WEST);
		
	}
	
	
	public void update(Observable o, Object obj){
		data.contentPane.remove(data.p1);
		data.p1 = new JPanel();
		data.p1.setLayout(new FlowLayout());
		data.p1.setLayout(new GridLayout(64,1));
		data.p1.setBackground(Color.WHITE);
		data.p1.setPreferredSize(new Dimension(data.MEM_BLOCK_WIDTH, data.MEM_BLOCK_SCALE));
		data.contentPane.add(data.p1, BorderLayout.WEST);
		ArrayList<MemoryObject> memory = (ArrayList<MemoryObject>) obj;
		for(int i = 0; i < memory.size(); i++){
			MemoryObject temp = memory.get(i);
			int start = temp.getStart();
			int end = temp.getEnd();
			
			//here: color the section of the grid corresponding
			//to the space from START to END.
			if (temp.getName() == null) {
				for(int j = 0; j <= end - start; j++){
					JTextArea co = new JTextArea();
					Color randomColor = Color.WHITE;
					co.setBackground(randomColor);
					//System.out.println("its called");
					data.p1.add(co);
					data.p1.revalidate();
					data.p1.repaint();
				}
			}
			else {
				for(int j = 0; j <= end - start; j++){
					JTextArea co = new JTextArea();
					Color randomColor = Color.BLUE;
					co.setBackground(randomColor);
					System.out.println("its called");
					data.p1.add(co);
					data.p1.revalidate();
					data.p1.repaint();
				}
			}
		}
	}
	
	private void populate()
	{
		addButtons();
		makeGrid();
		
	}
	
	//User Add selection
	private void add()
	{
		String name = nameAdd.getText();
		String stringSize = sizeBox.getText();
		
		    //Item name must contain at least 1 character
			if(name.equals(""))
			{
			  JOptionPane.showMessageDialog(data.frame,
					    "Please enter a name containing at least 1 character",
					    "Name error",
					    JOptionPane.ERROR_MESSAGE);
			  nameAdd.setText("");
			  return;
			}
			//Checks if the name already exists in memory, asks for a new one if it does
			if(core.exists(name) != -1)
			{
			  JOptionPane.showMessageDialog(data.frame,
					    "That name already exists, please choose another.",
					    "Name error",
					    JOptionPane.ERROR_MESSAGE);
			  nameAdd.setText("");
			  return;
			}
		//Checks to see if user entered a positive number for the size of the memory item	
		try
		{
			int size = Integer.parseInt(stringSize);
			core.add(new MemoryObject(name, "add", size));
			sizeBox.setText("");
			nameAdd.setText("");
	    }
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(data.frame,
				    "Please enter a number in the 'Size' text field",
				    "Number error",
				    JOptionPane.ERROR_MESSAGE);
			sizeBox.setText("");
		}
		
	}
	
	//User remove selection
	private void remove()
	{
		String name = nameRemove.getText();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(data.frame,
				    "Please enter a name in the 'Name' text field",
				    "Name error",
				    JOptionPane.ERROR_MESSAGE);
			nameRemove.setText("");
		}
		else if (core.exists(name) != -1) {
			core.removeOne(name);
			nameRemove.setText("");
		}
		else {
			JOptionPane.showMessageDialog(data.frame,
				    name + " not found in memory.",
				    "Name error",
				    JOptionPane.ERROR_MESSAGE);
			nameRemove.setText("");
		}
	}
	
	private void print()
	{
		core.print();
	}
	
	private void random()
	{
		Random numRand = new Random();
		Random nameRand = new Random();
		Random rand = new Random();
		
		String[] names = { "Terminator", "Slicer","Ninja", "cow", "Robot", "littlegirl", "Apple","Mango","Peach","Banana","Orange","Grapes","Watermelon","Tomato",
				         "Joe", "Tom", "Hawiar", "Baliga", "OS", "a", "A", "B", "b", "C", "D", "c", "d", "e", "f", "g", "h", "E", "F", "G", "H",
				         "bad","easy","lol","Hurt","code","hate","kill","ice","fire","icecream","man","destroy","computer","book","dictionary","technology",
				         "power","thunder","controller","dexterity","keyboard","thunderous","blizzard","hazardous","algorithm","destruction","operation","assignment","despicable"};
		
		boolean choice = rand.nextBoolean();
		if(choice)
		{
			//pick a random size from 1-64
			int size = numRand.nextInt((64 - 1) + 1) + 1;
			String name = names[(int) (Math.random() * names.length)];
			nameAdd.setText(name);
			sizeBox.setText(Integer.toString(size));
			add();
		}
		else
		{	
			String name = names[(int) (Math.random() * names.length)];
			nameRemove.setText(name);
			remove();
			//System.out.println(name);
		}
	}
	
	private void reset()
	{
		data.frame.dispose();
		makeFrame();
		makeGrid();
		core = new NewCore(this);
	}
	
	private void quit() {
		System.exit(0); 
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
