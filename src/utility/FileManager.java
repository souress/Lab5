package utility;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import test.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.HashSet;

import javax.xml.parsers.*;

public class FileManager {
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
            out.write()
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
