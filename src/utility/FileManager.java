package utility;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import data.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.HashSet;

import javax.xml.parsers.*;

public class FileManager implements ParseToBytes {
    private CollectionManager collectionManager;

    public FileManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public HashSet<Person> parseFromXml() throws IllegalArgumentException, NullPointerException,
            IOException, SAXException, ParserConfigurationException {
        HashSet<Person> collectionFromFile = new HashSet<>();
        Integer id = null;
        String name = null;
        Long coordinateX = null;
        double coordinateY = 0;
        java.time.ZonedDateTime creationDate = null;
        double height = 0;
        String passportID = null;
        Color hairColor = null;
        Country nationality = null;
        long locationX = 0;
        float locationY = 0;
        Long locationZ = null;
        try {
            File in = new File(collectionManager.getFilePath());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(in);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("person");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    name = element.getElementsByTagName("name").item(0).getTextContent();

                    NodeList nodeListCoordinates = element.getElementsByTagName("coordinates");
                    Element elementCoordinates = (Element) nodeListCoordinates.item(0);
                    coordinateX = Long.parseLong(elementCoordinates.getElementsByTagName("coordinateX").item(0).getTextContent());
                    coordinateY = Double.parseDouble(elementCoordinates.getElementsByTagName("coordinateY").item(0).getTextContent());

                    creationDate = ZonedDateTime.parse(element.getElementsByTagName("creationDate").item(0).getTextContent());
                    height = Double.parseDouble(element.getElementsByTagName("height").item(0).getTextContent());
                    passportID = element.getElementsByTagName("passportID").item(0).getTextContent();
                    hairColor = Color.valueOf(element.getElementsByTagName("hairColor").item(0).getTextContent());
                    nationality = Country.valueOf(element.getElementsByTagName("nationality").item(0).getTextContent());

                    NodeList nodeListLocation = element.getElementsByTagName("location");
                    Element elementLocation = (Element) nodeListLocation.item(0);
                    locationX = Long.parseLong(elementLocation.getElementsByTagName("locationX").item(0).getTextContent());
                    locationY = Float.parseFloat(elementLocation.getElementsByTagName("locationY").item(0).getTextContent());
                    locationZ = Long.parseLong(elementLocation.getElementsByTagName("locationZ").item(0).getTextContent());
                }

                Person person = new Person(id, name, new Coordinates(coordinateX, coordinateY), creationDate, height,
                        passportID, hairColor, nationality, new Location(locationX, locationY, locationZ));
                collectionFromFile.add(person);
            }
        } catch (IllegalArgumentException | NullPointerException exception) {
            System.out.println("Incorrect data contains in fields. Correct the file." + exception.getMessage());
        }
        return collectionFromFile;
    }

    public void parseToXml(String fileName) throws IOException {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));) {
            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n".getBytes());
            out.write("<collection>".getBytes());
            for (Person person : collectionManager.getCollection()) {
                out.write("<person>".getBytes());
                out.write("<id>".getBytes());
                out.write(person.getId().byteValue());
                out.write("</id>\n".getBytes());
                out.write("<name>".getBytes());
                out.write(person.getName().getBytes());
                out.write("</name>\n".getBytes());
                out.write("<coordinates>\n".getBytes());
                out.write("<coordinateX>".getBytes());
                out.write(person.getId().byteValue());
                out.write("</coordinateX>\n".getBytes());
                out.write("<coordinateY>".getBytes());
                out.write(person.getId().byteValue());
                out.write("</coordinateY>\n".getBytes());
                out.write("</coordinates>\n".getBytes());
                out.write("<creationDate>".getBytes());
                out.write(person.getCreationDate().toString().getBytes());
                out.write("</creationDate>\n".getBytes());
                out.write("<height>".getBytes());
                out.write(ParseToBytes.doubleToBytes(person.getHeight()));
                out.write("</height>\n".getBytes());
                out.write("<passportID>".getBytes());
                out.write(person.getPassportID().getBytes());
                out.write("</passportID>\n".getBytes());
                out.write("<hairColor>".getBytes());
                out.write(person.getHairColor().toString().getBytes());
                out.write("</hairColor>\n".getBytes());
                out.write("<nationality>".getBytes());
                out.write(person.getNationality().toString().getBytes());
                out.write("</nationality>\n".getBytes());
                out.write("<location>".getBytes());
                out.write("<locationX>".getBytes());
                out.write(ParseToBytes.longToBytes(person.getLocation().getLocationX()));
                out.write("</locationX>\n".getBytes());
                out.write("<locationY>".getBytes());
                out.write(ParseToBytes.floatToBytes(person.getLocation().getLocationY()));
                out.write("</locationY>\n".getBytes());
                out.write("<locationZ>".getBytes());
                out.write(ParseToBytes.longToBytes(person.getLocation().getLocationZ()));
                out.write("</locationZ>\n".getBytes());
                out.write("</location>\n".getBytes());
                out.write("</person>".getBytes());
            }
            out.write("</collection>".getBytes());
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
