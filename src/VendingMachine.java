import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class VendingMachine {
    private final double[] COINS = {0.01, 0.02, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0};
    private ArrayList<Double> change;
    private ArrayList<VendingMachineProduct> products;
    double credit;
    Scanner input;

    //initialize vending machine with default products and empty change bin
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

    //method to return an ArrayList of all the coins in the change bin
    public void returnChange(double price)
    {
        //dispense largest coin that is smaller than remaining sum until 0 remains
        for(int i = COINS.length-1; i >= 0; i--)
        {
            //using epsilon to compare if double values are equal, rather than a comparison operator
            double epsilon = .000001d;
            //if remainder is less than epsilon values
            boolean isEqual = Math.abs(COINS[i] - credit) < epsilon;

            //continue dispensing a coin denomination until credit is less than coin value
            while(COINS[i] < credit || isEqual)
            {
                change.add(COINS[i]);
                credit -= COINS[i];
                isEqual = Math.abs(COINS[i] - credit) < epsilon;
            }
        }

        System.out.print("Calculating change: ");
        ListIterator it = change.listIterator();
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
        //floating point issues mean change has to be reset to 0 to prevent a negative sign
        credit = 0;
        //empty change bin
        change.clear();
    }

    //method to display a list of all the products in the vending machine.
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

    //method to allow the user to choose whether to add credit or select a product
    public void mainMenu()
    {
        int in = 0;
        boolean isValid = false;

        //validation loop to continually prompt user for a choice, ony "1" or "2" are accepted
        while(!isValid)
        {
            this.display();
            System.out.println(change.size());
            System.out.print("Enter 1 to submit credit or 2 to select a product: ");
            String s = readInput();

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

            switch(in)
            {
                case 1: submitCredit();
                    break;
                case 2: selectProduct();
                    break;
            }
        }
    }

    //method to read in user's money
    public void submitCredit()
    {
        String in;
        boolean isValid = false;
        while(!isValid)
        {
            System.out.print("Enter the value you would like to add as credit in Euros: €");
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
        mainMenu();
    }

    //method to allow the user to select a product from the list using its index
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
            returnChange(p.getPrice());
        }
        else
        {
            System.out.println("Error, not enough credit.");
        }
        mainMenu();
        //main menu
    }

    //method to read user's input and close program if it is "X", otherwise returns the input String
    private String readInput()
    {
        String s = input.next();
        if(s.equalsIgnoreCase("x"))
        {
            System.exit(0);
        }

        return s;
    }
}
