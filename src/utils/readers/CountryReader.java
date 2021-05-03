package utils.readers;

import data.Country;
import java.util.*;

/**
 * Считыватель страны.
 */
public class CountryReader {
    public static boolean checkExist(String toBeContained) {
        return Arrays.stream(Country.values()).anyMatch((country) -> country.name().equals(toBeContained));
    }

    public static Country read(String messageForConsole, boolean canBeNull) {
        Scanner in = new Scanner(System.in);
        System.out.print(messageForConsole + " Выберите страну из представленных(" + Arrays.asList(Country.values()) + "): ");
        String toBeContained = in.nextLine().trim();

        if ((!checkExist(toBeContained)) && !canBeNull && !toBeContained.equals("") ||
                !canBeNull && toBeContained.equals("") || canBeNull && !toBeContained.equals("")) {
            while (!checkExist(toBeContained)) {
                System.out.print("Вы ввели несуществующее значение из представленных. Попробуйте снова: ");
                toBeContained = in.nextLine().trim();
                if (canBeNull && toBeContained.equals("")) return null;
                checkExist(toBeContained);
            }
        }

        if (canBeNull && toBeContained.equals("")) return null;

        return Enum.valueOf(Country.class, toBeContained);
    }

    public static Country readForScript(String parameter) {
        if (parameter == null) return null;
        else return Country.valueOf(parameter);
    }
}
