package lab.doublylinkedlist;

import org.junit.Test;
import static org.junit.Assert.*;

public class DLLTest {

    @Test
    public void testConstructor() {
        DLL dll = new DLL();
        assertTrue(dll.isEmpty());
        assertEquals(0, dll.getSize());
    }

    @Test
    public void insertTest() {
        DLL dll = new DLL();
        int num = new Integer(5);
        dll.insert(num);
        assertFalse(dll.isEmpty());
        assertEquals(1, dll.getSize());
    }

}