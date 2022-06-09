import java.util.ArrayList;
import java.util.Scanner;

public class CheckIfPrime {
    public static void main(String[] args)
    {
        int number = 0;
        boolean isPrime;
        boolean isValid; //boolean for input validation loops
        boolean goAgain = true; //boolean for repeating goes
        Scanner input = new Scanner(System.in);
        ArrayList<String> divisors = new ArrayList<>();//list of divisors for non-prime numbers

        //main loop to allow repeating checks
        while(goAgain)
        {
            isValid = false;
            isPrime = true;
            //validation loop for user input
            while(!isValid)
            {
                System.out.print("Enter a number and I'll tell you if it's prime: ");
                try
                {
                    number = Integer.parseInt(input.next());
                    isValid = true;
                }
                catch(NumberFormatException e)
                {
                    System.out.println("That's not a number.");
                }
            }

            //loop from 2 up to number and check if any number is divisible without remainder
            for(int i = 2; i < number; i++)
            {
                if(number%i == 0)
                {
                    isPrime = false;
                    //store number if it's prime
                    divisors.add(Integer.toString(i));
                }
            }
            //2 is prime but won't work with previous loop
            if(number == 2)
            {
                isPrime = true;
            }

            //output if number is prime
            if(isPrime)
            {
                System.out.printf("%d is a prime number.", number);
            }
            //formatted output for divisors depending on length
            else
            {
                System.out.printf("%d isn't a prime number. It's also divisible by ", number);
                //loop doesn't run for size of 1
                for(int i = 0; i < divisors.size() - 1; i++)
                {
                    if(divisors.size() == 2)
                    {
                        System.out.printf("%s ", divisors.get(i));
                    }
                    else
                    {
                        System.out.printf("%s, ", divisors.get(i));
                    }
                }
                //different output for last one if there's multiple divisors
                if(divisors.size() != 1)
                {
                    System.out.print("and ");
                }
                System.out.printf("%s\n", divisors.get(divisors.size() - 1));   
            }
            
            //check if user wants to go again
            String s = "";
            isValid = false;
            System.out.print("Wanna go again? Y/N: ");
            while(!isValid)
            {
                s = input.next();

                switch(s)
                {
                    case "Y":
                    case "y":
                    case "yes":
                    case "Yes":
                        //reset and go again
                        System.out.println("Have at it.");
                        goAgain = true;
                        isValid = true;
                        divisors.clear();
                        break;

                    case "N":
                    case "n":
                    case "no":
                    case "No":
                        //acknowledge and exit loops
                        System.out.println("Alright seeya");
                        goAgain = false;
                        isValid = true;
                        break;

                    default:
                        System.out.print("I said do you want to go again? Y/N: ");
                }
            }//end validation loop for going again
        }//end repeating loop 
    input.close();
    }//end main()
}//end CheckIfPrime class
