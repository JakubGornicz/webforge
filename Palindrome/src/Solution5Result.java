import java.util.Scanner;

public class Solution5Result {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Initialize variables for n (number of digits) and k (maximum changes allowed)
        int n = 0, k = 0;
        // Flag to check if the input for n and k is valid
        boolean validInput = false;

        // Loop until we get valid input for n and k
        while (!validInput) {
            try {
                // Attempt to read n and k from the user
                n = scanner.nextInt();
                k = scanner.nextInt();
                // Check if n and k are within the allowed range
                if (n > 0 && n <= Math.pow(10, 5) && k >= 0 && k <= Math.pow(10, 5)) {
                    // If valid, set the flag to true to exit the loop
                    validInput = true;
                } else {
                    // If not valid, inform the user
                    System.out.println("Invalid input for n or k.");
                }
            } catch (Exception e) {
                // If an exception occurs (e.g., non-integer input), inform the user
                System.out.println("Please enter valid integers for n and k.");
                // Clear the input buffer to avoid infinite loop
                scanner.nextLine();
            }
        }

        // Clear the newline character left in the buffer after reading integers
        scanner.nextLine();
        // Read the string s which represents the number
        String s = scanner.nextLine();

        // Validate the length and content of the string s
        while (s.length() != n || !s.matches("[0-9]+")) {
            // If the string is not of length n or contains non-digit characters, inform the user
            System.out.println("Invalid input for the string.");
            // Prompt for input again
            s = scanner.nextLine();
        }

        // Call the function to find the highest value palindrome
        String result = highestValuePalindrome(s, n, k);
        // Output the result to the console
        System.out.println(result);
        // Close the Scanner to prevent resource leak
        scanner.close();
    }

    public static String highestValuePalindrome(String s, int n, int k) {
        // Convert the input string to a char array for easier manipulation
        char[] result = s.toCharArray();
        // Array to keep track of which characters have been changed
        boolean[] changed = new boolean[n];
        // Variable to count the number of changes needed to make it a palindrome initially
        int changesNeeded = 0;

        // First pass: Make the string a palindrome
        for (int i = 0; i < n / 2; i++) {
            // Compare characters from both ends moving towards the center
            if (result[i] != result[n - 1 - i]) {
                // If they don't match, choose the higher value to minimize changes
                result[i] = result[n - 1 - i] = (char) Math.max(result[i], result[n - 1 - i]);
                // Mark this pair as changed
                changed[i] = true;
                // Decrement k since we made a change
                k--;
            }
        }

        // Check if we've exceeded our change limit after making the string a palindrome
        if (k < 0) {
            // If we have, it's impossible to create a palindrome, return "-1"
            return "-1";
        }

        // Second pass: Maximize the palindrome
        for (int i = 0; i < n / 2 && k > 0; i++) {
            // Check if the current character isn't '9'
            if (result[i] != '9') {
                // If this pair was changed in the first pass, we only need one change to make it '9'
                if (changed[i]) {
                    result[i] = result[n - 1 - i] = '9';
                    k--;
                } else if (k >= 2) {
                    // If this pair wasn't changed, we need two changes to make both '9'
                    result[i] = result[n - 1 - i] = '9';
                    k -= 2;
                }
            }
        }

        // Handle the middle character for odd-length strings
        if (n % 2 != 0 && k > 0) {
            // If there's an odd middle character and we have changes left, set it to '9'
            result[n / 2] = '9';
        }

        // Convert the char array back to a string and return it
        return new String(result);
    }
}