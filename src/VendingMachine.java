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
    public ArrayList<Double> returnChange(double price)
    {
        //empty change bin
        change.clear();

        //dispense largest coin that is smaller than remaining sum until 0 remains
        for(int i = COINS.length-1; i >= 0; i-- )
        {
            while(COINS[i] < credit)
            {
                credit -= COINS[i];
                change.add(COINS[i]);
            }
        }
        
        return change;
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
                credit += Integer.parseInt(in);
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
        int selection;

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
