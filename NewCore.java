package memoryMgmtSystem;

import java.util.*;

public class NewCore extends Observable {
	
	private ArrayList<MemoryObject> memory;
	private final static int size = 64;
	private MemoryGUI memGUI;
	
	public NewCore (MemoryGUI observer)
	{
		memory = new ArrayList<MemoryObject> (size / 2);
		memory.add(new MemoryObject(0, 63));
		memGUI = observer;
		addObserver(memGUI);
	}
	
	public NewCore (Object [] array) 
	{
		memory = new ArrayList<MemoryObject> (size / 2);
		memory.add(new MemoryObject(0, 63));
		startMemory(array);
	}
	
	public ArrayList<MemoryObject> startMemory (Object [] array) throws MemoryException 
	{
		memory = new ArrayList<MemoryObject> (size / 2);
		memory.add(new MemoryObject(0, 63));
		for (int i = 0; i < array.length; i++) {
			MemoryObject current = (MemoryObject) array [i];
			if (current == null) {
				;
			}
			else if (current.getFunction().equals("add")) {
				add(current);
			}
			else if (current.getFunction().equals("remove")) {
				remove(current);
			}
			else {
				throw new MemoryException("Function String Not Allowed.");
			}
		}
		return memory;
	}
	
	public void add (MemoryObject item) 
	{
		int memSize = findSubSize(item.getSize());
		while (true) {
			for (int i = 0; i < memory.size(); i++) {
				MemoryObject temp = memory.get(i);
				if (temp.getName() == null && 
						temp.getSize() == memSize) {
					item.setStart(temp.getStart());
					item.setEnd(temp.getEnd());
					item.setSize(memSize);
					memory.set(i, item);
					System.out.println("Item inserted");
					setChanged();
					notifyObservers(memory);
					return;
				}
			}
			if (breakDown(memSize)) {
				;
			}
			else {
				System.out.println("Item cannot fit");
				return;
			}
		}
	}
	
	public boolean breakDown (int thisSize)
	{
		int currSize = thisSize;
		while (currSize <= size) {
			for (int i = 0; i < memory.size(); i++) {
				MemoryObject temp = memory.get(i);
				if (temp.getName() == null && 
						temp.getSize() == currSize) {
					while (currSize > thisSize) {
						int start = temp.getStart();
						int mid = start + (temp.getSize() / 2);
						int end = temp.getEnd();
						memory.add(i, new MemoryObject(mid, end));
						memory.add(i, new MemoryObject(start, mid - 1));
						memory.remove(i + 2);
						temp = memory.get(i);
						currSize /= 2;
					}
					return true;
				}
			}
			currSize *= 2;
		}
		return false;
	}
	
	public static int findSubSize (int value) 
	{
		int subSize = size;
		while (subSize > 1) {
			if (value < subSize && subSize / 2 >= value) {
				subSize = subSize / 2;
			}
			else {
				return subSize;
			}
		}
		return 0;
	}
	
	public void remove (MemoryObject item) 
	{
		for (int i = 0; i < memory.size(); i++) 
		{
			MemoryObject current = memory.get(i);
			if (current.getName() == item.getName()) 
			{
				MemoryObject newOne = new MemoryObject(current.getStart(), current.getEnd());
				memory.set(i, newOne);
				System.out.println("item removed");
				buddyUp();
				setChanged();
				notifyObservers(memory);
				break;
			}
		}
	}
	
	//Removes item from memory by name, view is currently using this remove function
	public void removeOne (String name)
	{
		if(exists(name) != -1)
		{
			int pos = exists(name);
			MemoryObject current = memory.get(pos);
			MemoryObject newOne = new MemoryObject(current.getStart(), current.getEnd());
			memory.set(pos, newOne);
			buddyUp();
			setChanged();
			notifyObservers(memory);
		}
	}
	
	//Checks if item exists in memory or not
	public int exists(String name)
	{
		boolean exists = false;
		int i = 0;
		while(!exists && i < memory.size())
		{
			MemoryObject current = memory.get(i);
			try {
				if(current.getName().equals(name)) {
					exists = true;
					return i;
				}
			}
			catch (NullPointerException e) {
				;
			}
			i++;
		}
		return -1;
	}
	
	public void buddyUp () 
	{
		boolean finished = false;
		while (!finished) {
			finished = true;
			try {
				for (int i = 0; i < memory.size(); i++) {
					MemoryObject first = memory.get(i);
					MemoryObject second = memory.get(i + 1);
					if (findSubSize(first.getSize()) == findSubSize(second.getSize()) &&
							first.getName() == null &&
							second.getName() == null) {
								finished = false;
								MemoryObject bigger = new MemoryObject(first.getStart(), second.getEnd());
								memory.remove(i + 1);
								memory.set(i, bigger);
					}
				}
			}
			catch (IndexOutOfBoundsException e) {
				;
			}
		}
	}
	
	//Prints each memory item in order along with its size, currently used for testing purposes
	public void print()
	{
		for(int i = 0; i <memory.size(); i++)
		{
			System.out.println(memory.get(i).getName() + " " + memory.get(i).getSize());
		}
	}
	
}
