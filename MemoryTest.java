package memoryMgmtSystem;

import java.util.ArrayList;

import org.junit.Assert;
import junit.framework.TestCase;

public class MemoryTest extends TestCase {

	public static void test_add_function () {
		ArrayList<MemoryObject> result = new ArrayList<MemoryObject>(32);
		MemoryObject m = new MemoryObject("start", "add", 7, 0, 7);
		result.add(m);
		result.add(new MemoryObject(8, 15));
		result.add(new MemoryObject(16, 31));
		result.add(new MemoryObject(32, 63));
		Object[] array = new Object[1];
		array[0] = m;
		//Assert.assertEquals(result, NewCore.startMemory(array));
	}
	
	public static void test_add_and_remove_function () {
		ArrayList<MemoryObject> result = new ArrayList<MemoryObject>(32);
		result.add(new MemoryObject(0, 15));
		result.add(new MemoryObject("middle", "add", 9, 16, 31));
		result.add(new MemoryObject(32, 63));
		MemoryObject m = new MemoryObject("start", "add", 7);
		MemoryObject n = new MemoryObject("middle", "add", 9);
		MemoryObject o = new MemoryObject("start", "remove", 7);
		Object[] array = new Object[3];
		array[0] = m;
		array[1] = n;
		array[2] = o;
		//Assert.assertEquals(result, NewCore.startMemory(array));
	}
	
}
