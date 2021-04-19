package utils;

import data.*;
import utils.readers.*;

import java.util.ArrayList;

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

    public static Person createPersonInScript(ArrayList<String> args) {
        if (validateArray(args)) {
            return new Person(args.get(0), new Coordinates(Long.parseLong(args.get(1)), Double.parseDouble(args.get(2))),
                    Double.parseDouble(args.get(3)),
                    args.get(4),
                    Color.valueOf(args.get(5)),
                    Country.valueOf(args.get(6)),
                    new Location(Long.parseLong(args.get(7)), Float.parseFloat(args.get(8)), Long.parseLong(args.get(9))));
        } else
            System.out.println("Один из параметров не соответствует требованиям.");
        return null;
    }

    private static boolean validateArray(ArrayList<String> args) {
        try {
            return !args.get(0).isEmpty()
                    && Long.parseLong(args.get(1)) <= 51 && !args.get(1).isEmpty()
                    && Double.parseDouble(args.get(3)) > 0
                    && !args.get(4).isEmpty()
                    && ColorReader.checkExist(args.get(5))
                    && CountryReader.checkExist(args.get(6))
                    && !args.get(9).isEmpty();
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
