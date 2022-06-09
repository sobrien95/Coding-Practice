import java.util.ArrayList;
import java.util.Iterator;

public class FibonacciSequence {
    public static void main(String[] args) 
    {
        //ArrayList to store numbers in fibonacci sequence
        ArrayList<Integer> numbers = new ArrayList<>();

        //each number in the sequence is the sum of the previous 2 numbers
        int current = 1;
        int prev1 = 0;
        int prev2 =  0;

        //add numbers from the sequence up to 100 to the list
        while(current <= 100)
        {
            numbers.add(current);
            prev2 = prev1;
            prev1 = current;
            current = prev1 + prev2;
        }

        //iterator for output
        Iterator<Integer> it = numbers.iterator();

        //output loop using iterator
        while(it.hasNext())
        {
            System.out.print(it.next());
            if(it.hasNext())
            {
                System.out.print(", ");
            }
        }
    }
}
