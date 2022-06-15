public class VendingMachineProduct {
    private double price;
    private String name;

    public VendingMachineProduct(double price, String name)
    {
        this.price = price;
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public String getName()
    {
        return name;
    }
}
