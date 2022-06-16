import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class VendingMachine {

    private final double[] COINS = {0.01, 0.02, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0}; //unchanging array of all coin denominations
    private ArrayList<Double> change; //list of coins in the change bin
    private ArrayList<VendingMachineProduct> products; //list of products in the machine
    double credit; //money input by the user
    Scanner input; //Scanner to read user inputs

    /**
     * default constructor to initialize the machine with a set of products,
     * empty change bin, set up the user input etc.
     */
    public VendingMachine()
    {
        input = new Scanner(System.in);
        credit = 0;
        change = new ArrayList<>();
        products = new ArrayList<>();
        products.add(new VendingMachineProduct(1.5, "Chocolate Bar"));
        products.add(new VendingMachineProduct(1.3, "Crisps"));
        products.add(new VendingMachineProduct(1.0, "Granola Bar"));
        products.add(new VendingMachineProduct(1.99, "Coke"));
        products.add(new VendingMachineProduct(1.75, "Water"));
        products.add(new VendingMachineProduct(0.2, "Crackers"));
    }

    /**
     * method to print out the contents of the change bin and empty it
     */
    public void returnChange()
    {
        //add largest coin which is smaller than remaining credit to the change bin, until 0 remains
        for(int i = COINS.length-1; i >= 0; i--)
        {
            //using a small epsilon value to compare if double values are equal, rather than a comparison operator
            double epsilon = .000001d;
            boolean isEqual = Math.abs(COINS[i] - credit) < epsilon;

            //continue dispensing a coin denomination until credit is less than coin value
            while(COINS[i] < credit || isEqual)
            {
                change.add(COINS[i]);
                credit -= COINS[i];
                isEqual = Math.abs(COINS[i] - credit) < epsilon; //recheck if values are equal after subtracting
            }
        }

        //iterate through change bin and display each coin
        System.out.print("Calculating change: ");
        ListIterator<Double> it = change.listIterator();
        //acknowledge if empty at start
        if(!it.hasNext())
        {
            System.out.print("€0.00");
        }
        else
        {
            while(it.hasNext())
            {
                System.out.printf("€%.2f", it.next());
                //only punctuate output if there's another coin to dispense
                if(it.hasNext())
                {
                    System.out.print(", ");
                }
            }
        }
        //floating point issues mean credit has to be reset to exactly 0 after dispensing change to prevent a negative sign
        credit = 0;
        //empty change bin
        change.clear();
    }

    /**
     * method to display all the products in the vending machine and their prices
     */
    public void display()
    {
        ListIterator<VendingMachineProduct> it = products.listIterator();
        System.out.println("\nEnter 'X' at any time to cancel the program.");
        System.out.println("|----------------------------------------------|");
        while(it.hasNext())
        {
            VendingMachineProduct p = it.next();
            int index = products.indexOf(p);
            System.out.printf("| %d - %s - €%.2f\n", index+1, p.getName(), p.getPrice());
        }
        System.out.println("|----------------------------------------------|");
        System.out.printf("Credit: €%.2f\n", credit);
    }

    /**
     * displays the main menu and allows the user to select a mode, add credit or select product
     */
    public void mainMenu()
    {
        int in = 0;
        boolean isValid = false;

        //validation loop to continually prompt user for a choice, ony "1" or "2" are accepted
        while(!isValid)
        {
            this.display();
            System.out.print("Enter 1 to submit credit or 2 to select a product: ");
            String s = readInput();

            //try-catch block to deal with parsing numbers from input
            try
            {
                in = Integer.parseInt(s);
                //throw exception if user inputs a number out of expected bounds
                if(in > 2 || in < 1)
                {
                    throw new Exception("Input out of bounds.");
                }
                else
                {
                    isValid = true;
                }
            }
            catch (Exception e)
            {
                System.out.println("Error, invalid input.");
            }

            //take action depending on user choice
            switch(in)
            {
                case 1: submitCredit();
                    break;
                case 2: selectProduct();
                    break;
            }
        }
    }

    /**
     * prompts the user to submit money and validates input
     */
    public void submitCredit()
    {
        String in;//input string from user
        boolean isValid = false;//to validate user's input as a number
        //continually prompt until input is valid
        while(!isValid)
        {
            System.out.print("Enter the value you would like to add as credit: €");
            in = readInput();

            try
            {
                credit += Double.parseDouble(in);
                isValid = true;
            }
            catch(Exception e)
            {
                System.out.println("Error, invalid input.");
            }
        }
        //return to main menu after adding credit
        mainMenu();
    }

    /**
     * Allows the user to select a product from the machine using its displayed value, not its actual list index value.
     * List index 0 is displayed as number 1 on the menu
     */
    public void selectProduct()
    {
        int selection = 0;
        boolean isValid = false;

        while(!isValid)
        {
            System.out.print("Enter the number of the product you wish to buy: ");
            try
            {
                selection = (Integer.parseInt(readInput()) - 1);
                if(selection < 0 || selection >= products.size())
                {
                    throw new Exception("Error, selection out of bounds.");
                }
                isValid = true;
            }
            catch(Exception e)
            {
                System.out.println("Error, invalid input.");
            }
        }

        //validate enough credit
        VendingMachineProduct p = products.get(selection);
        System.out.printf("Selection: %d - %s - €%.2f\n", selection+1, p.getName(), p.getPrice());
        if(credit >= p.getPrice())
        {
            //dispense product
            System.out.printf("Dispensing %s.\n", p.getName());
            credit -= p.getPrice();
            //dispense change
            returnChange();
        }
        else
        {
            System.out.println("Error, not enough credit.");
        }
        mainMenu();
        //main menu
    }

    /**
     * handles all inputs from the Scanner, user can say "x" at any time to close the program
     * @return the input String if it is not equal to "x"
     */
    private String readInput()
    {
        String s = input.next();
        if(s.equalsIgnoreCase("x"))
        {
            //return credit if user cancels with money in machine
            if(credit > 0)
            {
                returnChange();
            }
            System.exit(0);
        }
        return s;
    }
}
