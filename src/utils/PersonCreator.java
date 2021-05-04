package utils;

import data.*;
import utils.readers.*;

import java.util.ArrayList;

/**
 * Класс, содержащий методы для создание объекта Person
 */
public class PersonCreator {
    public static Person createPerson() {
        String name = StringReader.read("Введите name: ", false);
        Long coordinateX = RefLongReader.read("Введите coordinateX: ", false, 51, "MAX");
        double coordinateY = PrimitiveDoubleReader.read("Введите coordinateY: ");
        double height = PrimitiveDoubleReader.read("Введите height: ", 0, "MIN");
        String passportID = StringReader.read("Введите passportID: ", false);
        Color hairColor = ColorReader.read("", false);
        Country nationality = CountryReader.read("", true);
        long locationX = PrimitiveLongReader.read("Введите locationX: ");
        float locationY = PrimitiveFloatReader.read("Введите locationY: ");
        Long locationZ = RefLongReader.read("Введите locationZ: ", false);

        return new Person(name, new Coordinates(coordinateX, coordinateY), height, passportID, hairColor, nationality,
                new Location(locationX, locationY, locationZ));
    }

//    /**
//     * Метод создающий объект Person на основе полей прочитанных из скрипта
//     * @param parameters Список содержащий поля для будущего объекта Person
//     * @return Объект класса Person
//     */
//    public static Person createScriptPerson(ArrayList<String> parameters) {
//        if (validateArray(parameters)) {
//            return new Person(parameters.get(0), new Coordinates(Long.parseLong(parameters.get(1)), Double.parseDouble(parameters.get(2))),
//                    Double.parseDouble(parameters.get(3)), parameters.get(4), Color.valueOf(parameters.get(5)), CountryReader.readForScript(parameters.get(6)),
//                    new Location(Long.parseLong(parameters.get(7)), Float.parseFloat(parameters.get(8)), Long.parseLong(parameters.get(9))));
//        } else {
//            throw new NullPointerException();
//        }
//    }
//
//    /**
//     * Метод для проверки полей, содержащихся в списке, на валидность
//     * @param parameters Список содержащий поля для будущего объекта Person
//     * @return true, если поля валидны или false, если нет
//     */
//    private static boolean validateArray(ArrayList<String> parameters) {
//        try {
//            return !parameters.get(0).isEmpty()
//                    && Long.parseLong(parameters.get(1)) <= 51
//                    && Long.parseLong(parameters.get(3)) > 0
//                    && !parameters.get(4).isEmpty()
//                    && ColorReader.checkExist(parameters.get(5))
//                    && (CountryReader.checkExist(parameters.get(6)) || parameters.get(6) == null)
//                    && parameters.get(9) != null;
//
//        } catch (NumberFormatException ex) { return false; }
//    }
}
