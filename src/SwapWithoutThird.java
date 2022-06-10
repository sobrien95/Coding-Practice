import java.util.Scanner;

public class SwapWithoutThird {
    static Scanner input;

    public static void main(String[] args)
    {
        //declare variables and initialise scanner
        int num1 = 0;
        int num2 = 0;

        //initialise Scanner
        input = new Scanner(System.in);

        //read 2 numbers with method
        num1 = readNumber();
        num2 = readNumber();

        //print original numbers
        System.out.printf("Original\nnum1 = %d, num2 = %d\n", num1, num2);
        
        //swap numbers without third variable
        num2 += num1;
        num1 = num2 - num1;
        num2 = num2 - num1;

        //print swapped numbers
        System.out.printf("Swapped\nnum1 = %d, num2 = %d\n", num1, num2);

        input.close();
    }

    //method to read and validate a number from user input
    public static int readNumber()
    {
        int number = 0;
        boolean isValid = false;

        //validation loop
        while(!isValid)
        {
            //try-catch block to deal with input exceptions
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
