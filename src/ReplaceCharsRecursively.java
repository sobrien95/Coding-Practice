import java.util.Scanner;

public class ReplaceCharsRecursively {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String text;
        char character;
        int n;

        System.out.println("Enter some text!");
        text = input.nextLine();
        System.out.println("Character to swap in: ");
        character = input.next().charAt(0);
        System.out.println("Interval: ");
        n = input.nextInt();

        text = swap(text, character, n, -1);

        System.out.print(text);
        input.close();
    }

    public static String swap(String text, char c, int n, int index)
    {
        index++;
        if(index < text.length())
        {
            if(index%n == 0)
            {
                char[] chars = text.toCharArray();
                chars[index] = c;
                text = new String(chars);
            }
            text = swap(text, c, n, index);
        }
        return text;
    }
}
