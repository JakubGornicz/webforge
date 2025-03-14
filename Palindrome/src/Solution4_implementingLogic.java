import java.util.Scanner;
import java.util.InputMismatchException;

public class Solution4_implementingLogic {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // initialise the value for n and k
        int n = 0, k = 0;
        // create a boolean variable for the try-catch block
        boolean validInput = false;

        //check if user input is valid
        while (!validInput) {
            try {
                // Read the integer number of digits in the number
                n = scanner.nextInt();
                // Read the maximum number of changes allowed
                k = scanner.nextInt();

                // Check constraints for n and k
                if (!(0 < n && n <= Math.pow(10, 5)) || !(0 <= k && k <= Math.pow(10, 5))) {
                    System.out.println("Invalid input for n or k.");
                    // Move to the next line
                    scanner.nextLine();
                } else {
                    validInput = true;
                }
            } catch (Exception e) {
                // If the input is not an integer, inform the user
                System.out.println("Please enter correct integer values for n and k.");
                // Clear the input buffer
                scanner.nextLine();
            }
        }

        // Move to the next line to prompt the user for the string
        scanner.nextLine();

        // Read the next line as a string, representing the number
        String s = scanner.nextLine();

        // Check if the length of s matches n and if each character is a digit between 0 and 9
        while (s.length() != n || !s.matches("[0-9]+")) {
            System.out.println("Invalid input for s.");
            s = scanner.nextLine();
        }

        // Call the function to get the highest value palindrome
        String result = highestValuePalindrome(s, n, k);

        // Output the result to the console
        System.out.println(result);

        // Close the Scanner to prevent resource leak
        scanner.close();
    }
    //Finds the highest value palindrome that can be made from the input string with at most k changes.
    public static String highestValuePalindrome(String s, int n, int k) {
        // Before proceeding to make changes, check if it's even possible to create the palindrome

        // Create a variable to hold the number of changes necessary to create the palindrome
        int changesMade = 0;

        // Count the number of necessary changes by comparing characters from both ends, moving towards the center
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                changesMade++;
            }
        }

        // If the length of the string is odd and there are possible changes left, check if the middle character
        // has the greatest digit value
        if (n % 2 != 0 && changesMade < k) {
            if (s.charAt(n / 2) != '9') {
                changesMade++;
            }
        }

        // If there are more changes necessary than allowed then it's not possible to create a palindrome
        if (changesMade > k) {
            return "not ok"; // this is a placeholder
            //return "-1";
        }

        return "-1";
    }

}