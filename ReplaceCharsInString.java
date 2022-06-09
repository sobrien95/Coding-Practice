import java.util.Scanner;

public class ReplaceCharsInString {
    static String thisText;
    static Scanner input;
    static char thisC;
    public static void main(String[] args)
    {
        //initialise scanner and other variables
        input = new Scanner(System.in);

        //read user input
        getInput();

        //call to swap method
        swap(thisText, thisC);
    }

    public static void getInput()
    {
        System.out.println("Enter some text and I'll swap every second character with one of your choice-");
        thisText = input.nextLine();
        System.out.println("And the character: ");
        thisC = input.next().charAt(0);
    }

    public static void swap(String s, char c)
    {
        char[] chars = s.toCharArray();
        System.out.printf("text: %s\nchar: %c\nSwapped text: ", s, c);
        for(int i = 0; i < chars.length; i++)
        {
            if(i%2 == 0)
            {
                chars[i] = c;
            }
        }
        System.out.print(chars);
    }
}
