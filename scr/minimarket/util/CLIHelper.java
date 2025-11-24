package minimarket.util;

import java.util.Scanner;

/**
 * Utility class untuk membantu tampilan CLI
 */
public class CLIHelper {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Clear screen console
     */
    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Jika gagal, print banyak newline
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }


    /**
     * Print header dengan border
     */
    public static void printHeader(String title) {
        printSeparator();
        System.out.println(centerText(title, 50));
        printSeparator();
    }

    /**
     * Print separator line
     */
    public static void printSeparator() {
        System.out.println("==================================================");
    }

    /**
     * Print separator custom
     */
    public static void printSeparator(char character, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(character);
        }
        System.out.println();
    }

    /**
     * Center text
     */
    public static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            sb.append(" ");
        }
        sb.append(text);
        return sb.toString();
    }
