import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Get the number of simulation seconds from the user
        int n = getIntInput(scanner, "Enter the number of simulation seconds (1 <= n <= 10^9): ", 1, (int) 1e9);

        // Get the grid input from the user
        String[] grid = getGridInput(scanner);

        // Call the bomberMan function with the input parameters
        String[] result = bomberMan(n, grid);

        // Close the scanner to free up resources
        scanner.close();
    }

    // Method to get a valid integer input within a specific range
    private static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        int value;
        // Loop until a valid number is entered
        while (true) {
            try {
                // Prompt the user for input
                System.out.print(prompt);
                // Parse the input string to an integer
                value = Integer.parseInt(scanner.nextLine());
                // Check if the input value is within the allowed range
                if (value >= min && value <= max) {
                    // If valid, return the value
                    return value;
                }
                // Inform the user if the number is out of range
                System.out.println("The number must be between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                // Handle incorrect input format
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    // Method to get grid input from the user
    private static String[] getGridInput(Scanner scanner) {
        String[] grid;
        // Loop until a valid grid is provided
        while (true) {
            // Ask for the number of rows in the grid
            System.out.print("Enter the number of rows: ");
            int rows = getIntInput(scanner, "", 1, Integer.MAX_VALUE);
            // Create a new array to store the grid rows
            grid = new String[rows];

            // Assume the grid is valid until proven otherwise
            boolean valid = true;
            // Promt user to initialise the grid, row by row, specifying the requirements
            System.out.println("Enter rows (only '.' or 'O', maximum 200 characters):");
            // Loop through each row
            for (int i = 0; i < rows; i++) {
                // Prompt for each row of the grid
                System.out.print("Row " + (i + 1) + ": ");
                String row = scanner.nextLine();
                // Check if the row is valid: not empty, not exceeding 200 chars, and contains only '.' or 'O'
                if (row.length() == 0 || row.length() > 200 || !row.matches("[.O]+")) {
                    // If row is invalid, inform the user and set valid to false to break the loop
                    System.out.println("Invalid row. Use only '.' or 'O' and do not exceed 200 characters.");
                    valid = false;
                    break;
                }
                // If valid, add the row to the grid array
                grid[i] = row;
            }
            System.out.println();

            // If all rows are valid, return the grid
            if (valid) {
                return grid;
            }
        }
    }

    // Function to simulate the Bomberman game for 'n' seconds
    public static String[] bomberMan(int n, String[] grid) {
        // Convert String[] to char[][]
        char[][] charGrid = convertToCharGrid(grid);
        // Create a time grid for bombs to keep track of when each bomb was placed
        int[][] timeGrid = new int[charGrid.length][charGrid[0].length];

        // Simulate the game
        for (int t = 1; t <= n; t++) {
            // Print the current state of the simulation
            System.out.println("This is the state of the grid after " + t + " seconds:");

            if (t == 1) {
                // After one second the grid doesn't change so this prints out the grid without any
                //changes
                printGrid(charGrid);
            }
            else if (t % 2 == 0) {
                // Every two seconds bomberman plants the bombs in every empty cell
                //the function that plants the bombs is called plantBombs
                plantBombs(charGrid, timeGrid, t);
                printGrid(charGrid);
            } else {
                // Every odd second after the first, the bombs planted two seconds ago detonate
                //the function that detonates the bombs is called detonateBombs
                detonateBombs(charGrid, timeGrid, t);
                printGrid(charGrid);
            }
        }

        // Convert the result back to String[]
        return convertToStringArray(charGrid);
    }

    // Method to plant bombs on all empty spaces
    private static void plantBombs(char[][] grid, int[][] timeGrid, int t){
        // Iterate through each row of the grid
        for (int i = 0; i < grid.length; i++){
            // Iterate through each column in the current row
            for (int j = 0; j < grid[i].length; j++){
                // Check if the current cell is empty
                if (grid[i][j] == '.'){
                    // Place a bomb in the empty cell
                    grid[i][j] = 'O';
                    // Record the time the bomb was placed
                    timeGrid[i][j] = t;
                }
            }
        }
    }

    // Method to detonate bombs that have been in place for 3 seconds
    private static void detonateBombs(char[][] grid, int[][] timeGrid, int t){
        int rows = grid.length;
        int columns = grid[0].length;
        // Create a boolean grid to mark cells for clearing
        boolean[][] toClear = new boolean[rows][columns];

        // Check each cell in the grid
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                // If there's a bomb and it's been 3 seconds since placement
                if (grid[i][j] == 'O' && (t - timeGrid[i][j]) >= 3){
                    // Mark the bomb for clearing
                    toClear[i][j] = true;
                    // Mark adjacent cells for clearing (if they exist)
                    if (i > 0) toClear[i - 1][j] = true;
                    if (i < rows - 1) toClear[i + 1][j] = true;
                    if (j > 0) toClear[i][j - 1] = true;
                    if (j < columns - 1) toClear[i][j + 1] = true;
                }
            }
        }
        // Clear marked cells and reset bomb timers
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (toClear[i][j]) {
                    grid[i][j] = '.';
                    timeGrid[i][j] = 0;
                }
            }
        }
    }

    // This converts String[] which is an array of strings to char[][] which is a two-dimensional array of characters
    private static char[][] convertToCharGrid(String[] grid) {
        // Determine the number of rows and columns
        int rows = grid.length;
        int cols = grid[0].length();
        // Create a new 2D char array
        char[][] charGrid = new char[rows][cols];
        // Convert each string to a char array and place it in the charGrid
        for (int i = 0; i < rows; i++) {
            charGrid[i] = grid[i].toCharArray();
        }
        return charGrid;
    }

    // Convert char[][] back to String[]
    private static String[] convertToStringArray(char[][] grid) {
        // Create a new String array to hold the result
        String[] result = new String[grid.length];
        // Convert each row of char array back to a String
        for (int i = 0; i < grid.length; i++) {
            result[i] = new String(grid[i]);
        }
        return result;
    }

    // Method to print the current state of the grid
    public static void printGrid(char[][] grid) {
        // This method prints each row of the grid followed by a newline
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        System.out.println();
    }
}