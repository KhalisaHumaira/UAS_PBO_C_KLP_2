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
