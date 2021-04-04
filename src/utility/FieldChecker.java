package utility;

import data.Color;
import data.Country;

public class FieldChecker {
    private final Console console;
    private String tempString = null;
    private Long tempLong = null;
    private Double tempDouble = null;

    public FieldChecker(Console console) {
        this.console = console;
    }

    public String readAndCheckString(String name) {
        while (tempString == null) {
            System.out.println("Enter " + name + ":");
            tempString = console.readLine();
            if (name.equals("nationality") || name.equals("hairColor")) {
                if (name.equals("nationality") && tempString == null) break;
                else try {
                    if (name.equals("nationality")) Country.valueOf(tempString);
                    else Color.valueOf(tempString);
                } catch (IllegalArgumentException exception) {
                    System.out.println("Input doesn't contain " + name + ". Please try again.");
                    tempString = null;
                }
            }
        }
        return tempString;
    }

    public Long readAndCheckLong(String name) {
        while (tempLong == null) {
            System.out.println("Enter " + name + ":");
            try {
                tempLong = Long.parseLong(console.readLine());
            } catch (NumberFormatException exception) {
                System.out.println("Input doesn't contain " + name + ". Please try again.");
                tempLong = null;
            }
            if (name.equals("coordinateX")) {
                if (tempLong != null && tempLong > 51) {
                    System.out.println("Coordinate X should be less or equal 51.");
                    tempLong = null;
                }
            }
        }
        return tempLong;
    }

    public Double readAndCheckDouble(String name) {
        while (tempDouble == null) {
            System.out.println("Enter " + name + ":");
            try {
                tempDouble = Double.parseDouble(console.readLine());
            } catch (NumberFormatException exception) {
                System.out.println("Input doesn't contain " + name + ". Please try again.");
                tempDouble = null;
            } catch (NullPointerException exception) {
                System.out.println("Input is null. Please try again.");
                tempDouble = null;
            }
            if (name.equals("height")) {
                if (tempDouble != 0 && tempDouble <= 0){
                    System.out.println("Height should be more than 0.");
                    tempDouble = null;
                }
            }
        }
        return tempDouble;
    }
//TODO поставить ограничение на ввод даты
}
