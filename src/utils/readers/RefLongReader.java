package utils.readers;

import java.util.Scanner;

public class RefLongReader {
    public static Long read(String messageForConsole, boolean canBeNull, int limit, String type) {
        System.out.print(messageForConsole);
        Scanner sc = new Scanner(System.in);
        Long result = 0L;
        boolean end = false;
        while (!end) {
            try {
                result = Long.parseLong(sc.nextLine().trim());
                switch (type) {
                    case ("NO LIMIT"): break;
                    case ("MIN"):
                        if (result > limit) end = true;
                        else System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть больше " + limit + ". Попробуйте снова: ");
                        break;
                    case ("MAX"):
                        if (result < limit) end = true;
                        else System.out.print("Вы ввели не подходящее значение. " + "Оно должно быть меньше " + limit + ". Попробуйте снова: ");
                        break;
                }
            } catch (NumberFormatException ex) {
                if (canBeNull && sc.nextLine().trim().equals("")) {
                    return null;
                }
                System.out.print("Вы должны ввести число, попробуйте снова: ");
            }
        }
        return result;
    }

    public static Long read(String messageForConsole, boolean canBeNull) {
        System.out.print(messageForConsole);
        Scanner sc = new Scanner(System.in);
        Long result = 0L;
        boolean end = false;
        while (!end) {
            try {
                result = Long.parseLong(sc.nextLine().trim());
                end = true;
            } catch (NumberFormatException ex) {
                if (canBeNull && sc.nextLine().trim().equals("")) return null;
                System.out.print("Вы должны ввести число, попробуйте снова: ");
            }
        }
        return result;
    }
}
