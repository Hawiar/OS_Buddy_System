package memoryMgmtSystem;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MemoryGUIData {
	public NewCore mem;
	public JFrame frame;
	public JMenuBar menubar;
	public Container contentPane;
	public JPanel p1;
	public JPanel p2;
	public JPanel p3;
	public JTextArea t;
	public float tempR;
	public float tempG;
	public float tempB;
	public int currentSize;
	public boolean startUsed;
	public int heightAdjustment;
	public int MEM_BLOCK_SCALE;
	public int MEM_BLOCK_WIDTH;
	public int MEM_BLOCK_WIDTH_ADJUST;
	public JLabel freeSpace;

	public MemoryGUIData(int currentSize,
			boolean startUsed, int heightAdjustment, int mEM_BLOCK_SCALE,
			int mEM_BLOCK_WIDTH, int mEM_BLOCK_WIDTH_ADJUST) {
		this.currentSize = currentSize;
		this.startUsed = startUsed;
		this.heightAdjustment = heightAdjustment;
		MEM_BLOCK_SCALE = mEM_BLOCK_SCALE;
		MEM_BLOCK_WIDTH = mEM_BLOCK_WIDTH;
		MEM_BLOCK_WIDTH_ADJUST = mEM_BLOCK_WIDTH_ADJUST;
	}
}
