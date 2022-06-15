import java.util.ArrayList;
import java.util.ListIterator;

public class VendingMachine {
    private final double[] COINS = {0.01, 0.02, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0};
    private ArrayList<Double> change;
    private ArrayList<VendingMachineProduct> products;

    //initialize vending machine with default products and empty change bin
    public VendingMachine()
    {
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
    public ArrayList<Double> returnChange(double paid, double price)
    {
        //empty change bin
        change.clear();

        //dispense largest coin that is smaller than remaining sum until 0 remains
        for(int i = COINS.length-1; i >= 0; i-- )
        {
            while(COINS[i] < paid)
            {
                paid -= COINS[i];
                change.add(COINS[i]);
            }
        }
        
        return change;
    }

    //method to display a list of all the products in the vending machine.
    public void display()
    {
        ListIterator<VendingMachineProduct> it = products.listIterator();
        System.out.println("|----------------------------------------------|");
        while(it.hasNext())
        {
            VendingMachineProduct p = it.next();
            int index = products.indexOf(p);
            System.out.printf("| %d - %s - €%.2f\n", index, p.getName(), p.getPrice());
        }
        System.out.println("|----------------------------------------------|");
    }

    //method to read and validate user's input
    public void readInput()
    {}

}
