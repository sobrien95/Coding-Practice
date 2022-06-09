import java.util.Scanner;

public class SwapWithThird {
    static Scanner input;
    static int num1;
    static int num2;

    public static void main(String[] args)
    {
        //initialise Scanner
        input = new Scanner(System.in);
        //call input method to read the numbers
        num1 = readNumber();
        num2 = readNumber();

        //print original numbers
        System.out.printf("Original\nnum1 = %d, num2 = %d\n", num1, num2);

        //call to swap method
        swap();

        //print swapped numbers
        System.out.printf("Swapped\nnum1 = %d, num2 = %d", num1, num2);

        input.close();
    }

    //method to swap 2 numbers using a third variable
    public static void swap()
    {
        int temp = num2;
        num2 = num1;
        num1 = temp;
    }

    //method to read and validate a number from user input
    public static int readNumber()
    {
        int number = 0;
        boolean isValid = false;

        //validation loop
        while(!isValid)
        {
            //try/catch block to deal with input exceptions
            try
            {
                System.out.print("Enter a number: ");
                number = Integer.parseInt(input.nextLine());
                isValid = true;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Not a Number");
            }
        }
        return number;
    }
}
