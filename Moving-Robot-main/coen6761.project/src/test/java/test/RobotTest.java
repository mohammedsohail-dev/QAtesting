package test;




import sourceCode.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;


public class RobotTest {
	
	 

	
	 private Robot robot;

	    @Before
	    public void setUp() {
	        robot = new Robot();
	    }
   
    @Test
    public void testInitialPosition() {
    	
        assertEquals(0, robot.x_Coordinate);
        assertEquals(0, robot.y_Coordinate);
        assertEquals("north", robot.Direction);
        assertFalse(robot.Pendown);
        
    }
    	

    @Test
    public void testValidICommand() {
    	 
        robot.Input_command("I 5");
        assertEquals(5, robot.Room_size);
        assertEquals(0, robot.x_Coordinate);
        assertEquals(0, robot.y_Coordinate);
        assertFalse(robot.Pendown);
        assertEquals("north", robot.Direction);
    }
    

    @Test
    public void testInvalidMCommand() {
    	
        robot.Input_command("I 5");
        robot.Input_command("M");
        assertEquals(0, robot.way.size());
    }

    @Test
    public void testValidMCommand() {
    	 
        robot.Input_command("I 5");
        robot.Input_command("D");
        robot.Input_command("M 3");

        assertEquals(0, robot.x_Coordinate);
        assertEquals(3, robot.y_Coordinate);
    }

    @Test
    public void testLeftTurnCommand() {
    	 
        robot.Input_command("I 5");
        robot.Input_command("R");
        robot.Input_command("R");
        robot.Input_command("R");
        robot.Input_command("L");
        robot.Input_command("L");

        assertEquals("east", robot.Direction);
    }
    
    
    @Test
    public void testCurrentPosition() {
    	
    	robot.Input_command("I 10");
    	robot.Input_command("D");
    	robot.Input_command("m 3");
    	robot.Input_command("r");
    	robot.Input_command("m 6");
    	robot.Input_command("c");
    	assertEquals(6,robot.x_Coordinate);
    	assertEquals(3, robot.y_Coordinate);
    	
    }
    
    
    @Test
    public void testRightTurnCommand() {
    	 
        robot.Input_command("I 5");
        robot.Input_command("L");
        robot.Input_command("L");
        robot.Input_command("L");
        robot.Input_command("R");
        robot.Input_command("R");

        assertEquals("west", robot.Direction);
    }

    @Test
    public void testPenUpCommand() {
    	 
        robot.Input_command("I 5");
        robot.Input_command("D");
        robot.Input_command("U");

        assertFalse(robot.Pendown);
    }

    
    @Test
    public void testPenDownCommand() {
    	
    	 robot.Input_command("D");
    	 
        robot.Input_command("I 5");
        robot.Input_command("D");

        assertTrue(robot.Pendown);
    }
    
   
   
    
    @Test
    public void testPrintFloor() {
    	
    	robot.Input_command("I 3");
    	robot.Input_command("D");
    	robot.Input_command("M 1");
    	
    	 ByteArrayOutputStream temp1 = new ByteArrayOutputStream();
         PrintStream temp2 = System.out;
         System.setOut(new PrintStream(temp1));
         robot.Input_command("P");
         String actualoutput = temp1.toString().replace("\r\n", "\n"), expectedoutput=" 02  -  -  - \n" +" 01  *  -  - \n" +" 00  *  -  - \n" +"     00 01 02\n";
         
         assertTrue(actualoutput.contains(expectedoutput));
         System.setOut(temp2);
         
    	
    }
    
    @Test
    public void testOutofRomm() {
    	
    	robot.Input_command("I 5");
    	
    	
    	 
    	 robot.Input_command("M 6");
    	 
    	 robot.Input_command("M 2");
    	 robot.Input_command("R");
    	 robot.Input_command("M 6");
    	 
    	 robot.Input_command("M 2");
    	 robot.Input_command("R");
    	 robot.Input_command("M 6");
    	 
    	 robot.Input_command("M 2");
    	 robot.Input_command("R");
    	 robot.Input_command("M 6");
    	 
    }
    
    @Test
    public void testDirections() {
    	
    	robot.Input_command("I 10");
    	robot.Input_command("D");
    	robot.Input_command("M 5");
    	 assertEquals("north", robot.Direction);
    	 robot.Input_command("R");
    	 robot.Input_command("M 2");
    	 assertEquals("east", robot.Direction);
    	 robot.Input_command("R");
    	 robot.Input_command("M 2");
    	 assertEquals("south", robot.Direction);
    	 robot.Input_command("R");
    	 robot.Input_command("M 2");
    	 assertEquals("west", robot.Direction);
    	 robot.Input_command("L");
    	 robot.Input_command("M 2");
    	 robot.Input_command("L");
    	 robot.Input_command("M 2");
    	 robot.Input_command("L");
    	 robot.Input_command("M 2");
    	 assertEquals("north", robot.Direction);
    	 robot.Input_command("L");
    	 robot.Input_command("M 2");
    	 robot.Input_command("R");
    	 robot.Input_command("M 2");
    	 assertEquals("north", robot.Direction);
    	 
    	
    }
    
    
    @Test
    public void testInvalidCommands() {
    	
    	
    	 robot.Input_command("I");
         
         robot.Input_command("I 5.0");
         
         robot.Input_command("I 1");
    	
    	 
    	 robot.Input_command("1");
    	 
    	 robot.Input_command("C");
    	 
    	 robot.Input_command("C 1");
    	 
    	 robot.Input_command("D");
    	 
    	 robot.Input_command("U");
    	 
    	 robot.Input_command("M 5");
    	 
    	 
    	 
    	 robot.Input_command("I 5");
    	 
    	 robot.Input_command("U 0");
    	 
    	 robot.Input_command("L 1");
    	 
    	 robot.Input_command("R 2");
    	 
    	 robot.Input_command("P 2");
    	 
    	 robot.Input_command("D 1");
    	 
    	 robot.Input_command("Q 0");
    	 
    	 robot.Input_command("M F");
    	 
    	 robot.Input_command("M 0");
    	 
    }

   


   

    
}
