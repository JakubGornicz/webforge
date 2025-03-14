import java.util.Scanner;

public class Solution2_constraints {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Read the integer number of digits in the number
        int n = scanner.nextInt();
        // Read the maximum number of changes allowed
        int k = scanner.nextInt();

        // Check constraints for n and k
        if (!(0 < n && n <= Math.pow(10, 5)) || !(0 <= k && k <= Math.pow(10, 5))) {
            System.out.println("Invalid input for n or k.");
            scanner.close();
            return;
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
        // TODO: Implement the logic here

        // Return "-1" as a placeholder
        return "-1";
    }
}