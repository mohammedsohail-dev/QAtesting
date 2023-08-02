package sourceCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Robot {
    public List<int[]> way = new ArrayList<int[]>();
    public int x_Coordinate, y_Coordinate;
    public boolean Pendown;
    public int[][] Room;
    public int Room_size;

    public final String NORTH = "north", EAST = "east", SOUTH = "south", WEST = "west";
    public String Direction = NORTH;

   
    Scanner Temp = new Scanner(System.in);

    public static void main(String[] args) {
        Robot Robo = new Robot();
        System.out.println("\t\t\t\t\t Bonjour");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Inital Conditions : Position(0,0), Robo is facing North, Pen is up");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("Please enter a command to process");
        Robo.Start();
    }
   

    public void Start() {
        while (true) {
            System.out.print("> ");
            String Input = Temp.nextLine();
            Input_command(Input);
        }
    }

    public void Input_command(String command) {
        String[] Value = command.split(" ");
        String k = Value[0].toUpperCase();
        int Positions = 0;

        if (k.equals("I")) {//done
            if (Value.length < 2) {
                System.out.println("Error, I need two values to process");
                return;
            }
            if (!(Value[1].matches("\\d+"))) {//done
                System.out.println("Error, I can only accept Integer values");
                return;
            }
            if (Integer.parseInt(Value[1]) <= 1) {//done
                System.out.println("Error, I value should be more than 1");
                return;
            }
            Room_size = Integer.parseInt(Value[1]);
            Room = new int[Room_size][Room_size];
            for (int i = 0; i < Room_size; i++) {
                for (int j = 0; j < Room_size; j++) {
                    Room[i][j] = 0;
                }
            }
            x_Coordinate = 0;
            y_Coordinate = 0;
            Pendown = false;
            Direction = NORTH;
            System.out.println("Room is set to " + Room_size + " x " + Room_size + " array.");
        } else if (k.equals("C")) {//done
            if (Value.length > 1) {
                System.out.println("Error, C can't accept any additional values");
                return;
            }
            System.out.println("Position: " + x_Coordinate + ", " + y_Coordinate + " - Pen: " + (Pendown ? "down" : "up")
                    + " - Facing: " + Direction);
        } else if (k.equals("D")) {
            if (Room_size == 0) {
                System.out.println("Error, initialize Room to move");
                return;
            }
            if (Value.length > 1) {// done
                System.out.println("Error, D can't accept any additional values");
                return;
            }
            Pendown = true;
            System.out.println("Pen is down.");
            updateRoom();
        } else if (k.equals("U")) {
            if (Room_size == 0) {
                System.out.println("Error, initialize Room to move");
                return;
            }
            if (Value.length > 1) {// done
                System.out.println("Error, U can't accept any additional values");
                return;
            }
            Pendown = false;
            System.out.println("Pen is up.");
            updateRoom();
        } else if (k.equals("M")) {
            if (Room_size == 0) {
                System.out.println("Error, initialize Room to move");
                return;
            }
            if (Value.length < 2) {//done
                System.out.println("Error, M needs two values to process");
                return;
            }
            if (!(Value[1].matches("\\d+"))) {//done
                System.out.println("Error, M can only accept Integer values");
                return;
            }
            Positions = Integer.parseInt(Value[1]);
            if (Positions < 1) {//done
                System.out.println("Error, M value should be at least 1");
                return;
            }
            move(Positions);
        } else if (k.equals("L")) {
            if (Value.length > 1) {//done
                System.out.println("Error, L can't accept any additional values");
                return;
            }
            Move_Left();
        } else if (k.equals("R")) {
            if (Value.length > 1) {//done
                System.out.println("Error, R can't accept any additional values");
                return;
            }
            
            Move_Right();
        } else if (k.equals("P")) {
            if (Value.length > 1) {//done
                System.out.println("Error, P can't accept any additional values");
                return;
            }
            updateRoom();
            Room_Map();
        } else if (k.equals("Q")) {
            if (Value.length > 1) {//done
                System.out.println("Error, Q can't accept any additional values");
                return;
            }
            System.out.println("Terminating Robot");
            System.exit(0);
        
        } else {
            System.out.println("Error, Command not supported");
        }
    }

    public void move(int Positions) {
        updateRoom();
        int Temp_X = x_Coordinate;
        int Temp_Y = y_Coordinate;

        if (Direction.equals(NORTH)) {
            Temp_Y = y_Coordinate + Positions;
            if (Temp_Y >= Room_size) {
                System.out.println("Movement out of Room not allowed");
                return;
            }
        } else if (Direction.equals(EAST)) {
            Temp_X = x_Coordinate + Positions;
            if (Temp_X >= Room_size) {
                System.out.println("Movement out of Room not allowed");
                return;
            }
        } else if (Direction.equals(SOUTH)) {
            Temp_Y = y_Coordinate - Positions;
            if (Temp_Y < 0) {
                System.out.println("Movement out of Room not allowed");
                return;
            }
        } else if (Direction.equals(WEST)) {
            Temp_X = x_Coordinate - Positions;
            if (Temp_X < 0) {
                System.out.println("Movement out of Room not allowed");
                return;
            }
        }

        if (Temp_X >= 0 && Temp_X < Room_size && Temp_Y >= 0 && Temp_Y < Room_size) {
            int xDiff = Temp_X - x_Coordinate;
            int yDiff = Temp_Y - y_Coordinate;

            if (Pendown) {
                for (int i = 0; i <= Positions; i++) {
                    way.add(new int[]{x_Coordinate + i * xDiff / Positions, y_Coordinate + i * yDiff / Positions});
                }
                x_Coordinate = Temp_X;
                y_Coordinate = Temp_Y;
                System.out.println("Moved to position: " + x_Coordinate + ", " + y_Coordinate + "\n");
                way.add(new int[]{x_Coordinate, y_Coordinate});
                updateRoom();
            } else {
                System.out.println("Robot is moving freely as pen is up. The new coordinates are: " + Temp_X + ", "
                        + Temp_Y + " (not updated to print in Room map)\n");
                x_Coordinate = Temp_X;
                y_Coordinate = Temp_Y;
            }
        }
    }

    public void Move_Right() {
        if (Direction.equals(NORTH)) {
            Direction = EAST;
        } else if (Direction.equals(EAST)) {
            Direction = SOUTH;
        } else if (Direction.equals(SOUTH)) {
            Direction = WEST;
        } else if (Direction.equals(WEST)) {
            Direction = NORTH;
        }
        System.out.println("Rotated right. Robo is currently Facing: " + Direction);
    }

    public void Move_Left() {
        if (Direction.equals(NORTH)) {
            Direction = WEST;
        } else if (Direction.equals(WEST)) {
            Direction = SOUTH;
        } else if (Direction.equals(SOUTH)) {
            Direction = EAST;
        } else if (Direction.equals(EAST)) {
            Direction = NORTH;
        }
        System.out.println("Rotated Left. Robo is currently Facing: " + Direction);
    }

    public void updateRoom() {
        if (Pendown) {
            Room[x_Coordinate][y_Coordinate] = 1;
        }
    }
    

    public void Room_Map() {
        System.out.println("");
        for (int j = Room_size - 1; j >= 0; j--) {
            System.out.print(" " + String.format("%02d", j) + " ");
            for (int i = 0; i < Room_size; i++) {
                boolean hold = false;
                for (int[] pos : way) {
                    if (i == pos[0] && j == pos[1]) {
                        System.out.print(" * ");
                        hold = true;
                        break;
                    }
                }
                if (!hold) {
                    System.out.print(" - ");
                }
            }
            System.out.println("");
        }
        System.out.print("    ");
        for (int i = 0; i < Room_size; i++) {
            System.out.print(String.format(" %02d", i));
        }
        System.out.println("");
    }
    
}
