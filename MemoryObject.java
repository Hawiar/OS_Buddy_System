package memoryMgmtSystem;

public class MemoryObject {

	private String name;
	private String function;
	private int size;
	private int start;
	private int end;
	
	public MemoryObject (String word, String fun, int value, int first, int last) {
		name = word;
		function = fun;
		size = value;
		start = first;
		end = last;
	}
	
	public MemoryObject (int first, int last) {
		name = null;
		function = null;
		size = last - first + 1;
		start = first;
		end = last;
	}
	
	public MemoryObject (String word, String fun, int number) {
		name = word;
		function = fun;
		size = number;
		start = 0;
		end = 0;
	}
	
	public MemoryObject (String word, String fun) {
		name = word;
		function = fun;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String word) {
		name = word;
	}
	
	public String getFunction () {
		return function;
	}
	
	public void setFunction (String fun) {
		function = fun;
	}
	
	public int getSize () {
		return size;
	}
	
	public void setSize (int num) {
		size = num;
	}
	
	public int getStart () {
		return start;
	}
	
	public void setStart (int first) {
		start = first;
	}
	
	public int getEnd () {
		return end;
	}
	
	public void setEnd (int last) {
		end = last;
	}
}
