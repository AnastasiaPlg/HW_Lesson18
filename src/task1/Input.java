package task1;

import java.util.Scanner;

public class Input {

    public static String input(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        String line = scanner.nextLine();
        return line;
    }
}
