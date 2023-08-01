package test;



import sourceCode.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


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

}