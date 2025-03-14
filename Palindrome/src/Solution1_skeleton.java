import java.util.Scanner;

public class Solution1_skeleton {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Read the integer number of digits in the number
        int n = scanner.nextInt();
        // Read the maximum number of changes allowed
        int k = scanner.nextInt();

        // Move to the next line to prompt the user for the string
        scanner.nextLine();

        // Read the next line as a string, representing the number
        String s = scanner.nextLine();

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