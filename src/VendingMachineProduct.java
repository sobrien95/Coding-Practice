public class VendingMachineProduct {
    private double price;
    private String name;

    /**
     * Constructs a VendingMachineProduct with the given name and price
     * @param price the price to be assigned to the product
     * @param name the name to be assigned to the product
     */
    public VendingMachineProduct(double price, String name)
    {
        this.price = price;
        this.name = name;
    }

    /**
     * Returns the price of the this Product
     * @return the price of the product as a Double
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Returns the name of this Product
     * @return the name of the Product as a String
     */
    public String getName()
    {
        return name;
    }
}
