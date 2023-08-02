package test;



import org.junit.jupiter.api.Assertions;
import sourceCode.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class QATest{
    String next_dir(String Current, String input){  //R2
        if (Current.equals("north") && (input.equals("R") || input.equals("r")))
            return "east";
        else if (Current.equals("north") && (input.equals("L") || input.equals("L")))
            return "west";
        else if (Current.equals("south") && (input.equals("R") || input.equals("r")))
            return "west";
        else if (Current.equals("south") && (input.equals("L") || input.equals("l")))
            return "east";
        else if (Current.equals("east") && (input.equals("R") || input.equals("r")))
            return "south";
        else if (Current.equals("east") && (input.equals("L") || input.equals("l")))
            return "north";
        else if (Current.equals("west") && (input.equals("R") || input.equals("r")))
            return "north";
        else if (Current.equals("west") && (input.equals("L") || input.equals("l")))
            return "south";
        else
            return null;
    }
    @Test
    public void Pentest(){ //R2
        Robot c =  new Robot();
        Random random = new Random();

        int n = random.nextInt(5);


        c.Input_command("I 10");
        for (int i = 0; i < n; i++) {
            c.Input_command("D");
            assert c.Pendown;

            c.Input_command("U");
            assert !c.Pendown;

        }

    }


    @Test
    public void ControlTest(){ //R2
        //Full rotation test
        Robot c =  new Robot();
        int  n= 10;
        int rand;
        String k;
        c.Input_command("I 10");

        for(int r = 0; r < 4; r++){
            String curr = c.Direction;
            c.Input_command("R");
            String s = c.Direction;
            assertEquals(next_dir(curr, "R"),s);
        }// Right Test
        for(int r = 0; r < 4; r++){
            String curr = c.Direction;
            c.Input_command("L");
            String s = c.Direction;
            assertEquals(next_dir(curr, "L"),s);
        }// Left Test


        Random random = new Random();

        for(int r = 0; r < 10; r++){
            rand = random.nextInt(50);
            if (rand%2 == 0) {
                k = "R";
            } else {
                k = "L";
            }

            String curr = c.Direction;
            c.Input_command(k);
            String s = c.Direction;
            assertEquals(next_dir(curr, k),s);
        }
    }
    @Test
    public void InvalidInputTest() {

        Robot c =  new Robot();
        c.Input_command("I 10");
        c.Input_command("M 3");
        c.Input_command("L");
        c.Input_command("R");
        c.Input_command("U");
        c.Input_command("P");
        c.Input_command("C");


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("X");
        String printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, Command not supported", printedOutput);
        System.out.println(printedOutput);

        c.Input_command("I 10");

        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("I");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, I need two values to process", printedOutput);
//
        c.Input_command("I 10");

        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("I 5.5");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, I can only accept Integer values", printedOutput);

        c.Input_command("I 10");

        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("I 1");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, I value should be more than 1", printedOutput);

        c.Input_command("C");

        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("C 2");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, C can't accept any additional values", printedOutput);

        c.Input_command("D");
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("D 2");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, D can't accept any additional values", printedOutput);
//
        c.Input_command("U");
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("U 2");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, U can't accept any additional values", printedOutput);
//
//
        c.Input_command("M 8");
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("M");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, M needs two values to process", printedOutput);
//
        c.Input_command("M 9");
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("M 5.5");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, M can only accept Integer values", printedOutput);
//
        c.Input_command("M 9");
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("M 0");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, M value should be at least 1", printedOutput);
//
        c.Input_command("L");
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("L 5");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, L can't accept any additional values", printedOutput);

        c.Input_command("R");
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("R 5");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, R can't accept any additional values", printedOutput);

        c.Input_command("P");
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        c.Input_command("P 5");
        printedOutput = outputStream.toString().trim();
        System.setOut(System.out);
        Assertions.assertEquals("Error, P can't accept any additional values", printedOutput);


    }

}