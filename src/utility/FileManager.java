package utility;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import data.*;

public class FileManager {
    private final CollectionManager collectionManager;

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

    public void parseToXml(String fileName, Collection<Person> personCollection) throws TransformerException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element elementCollection = doc.createElement("collection");

        for (Person person : personCollection) {
            Element elementPerson = doc.createElement("person");

            Element elementId = doc.createElement("id");
            elementId.appendChild(doc.createTextNode(person.getId().toString()));
            elementPerson.appendChild(elementId);

            Element elementName = doc.createElement("name");
            elementName.appendChild(doc.createTextNode(person.getName()));
            elementPerson.appendChild(elementName);

            Element elementCoordinates = doc.createElement("coordinates");
            Element elementCoordinateX = doc.createElement("coordinateX");
            elementCoordinateX.appendChild(doc.createTextNode(person.getCoordinates().getCoordinateX().toString()));
            elementCoordinates.appendChild(elementCoordinateX);
            Element elementCoordinateY = doc.createElement("coordinateY");
            elementCoordinateY.appendChild(doc.createTextNode(((Double) person.getCoordinates().getCoordinateY()).toString()));
            elementCoordinates.appendChild(elementCoordinateY);
            elementPerson.appendChild(elementCoordinates);

            Element elementCreationDate = doc.createElement("creationDate");
            elementCreationDate.appendChild(doc.createTextNode(person.getCreationDate().toString()));
            elementPerson.appendChild(elementCreationDate);

            Element elementHeight = doc.createElement("height");
            elementHeight.appendChild(doc.createTextNode(((Double) person.getHeight()).toString()));
            elementPerson.appendChild(elementHeight);

            Element elementPassportID = doc.createElement("passportID");
            elementPassportID.appendChild(doc.createTextNode(person.getPassportID()));
            elementPerson.appendChild(elementPassportID);

            Element elementHairColor = doc.createElement("hairColor");
            elementHairColor.appendChild(doc.createTextNode(person.getHairColor().toString()));
            elementPerson.appendChild(elementHairColor);

            Element elementNationality = doc.createElement("nationality");
            elementNationality.appendChild(doc.createTextNode(person.getNationality().toString()));
            elementPerson.appendChild(elementNationality);

            Element elementLocation = doc.createElement("location");
            Element elementLocationX = doc.createElement("locationX");
            elementLocationX.appendChild(doc.createTextNode(((Long) person.getLocation().getLocationX()).toString()));
            elementLocation.appendChild(elementLocationX);
            Element elementLocationY = doc.createElement("locationY");
            elementLocationY.appendChild(doc.createTextNode(((Float) person.getLocation().getLocationY()).toString()));
            elementLocation.appendChild(elementLocationY);
            Element elementLocationZ = doc.createElement("locationZ");
            elementLocationZ.appendChild(doc.createTextNode((person.getLocation().getLocationZ().toString())));
            elementLocation.appendChild(elementLocationZ);
            elementPerson.appendChild(elementLocation);

            elementCollection.appendChild(elementPerson);
        }

        doc.appendChild(elementCollection);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(new BufferedOutputStream(new FileOutputStream(fileName))));
    }

//    public void parseToXml(String fileName, Collection<Person> collection) throws IOException {
//        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileName))) {
//            out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n".getBytes());
//            out.write("<collection>\n".getBytes());
//            for (Person person : collection) {
//                out.write("<person>\n".getBytes());
//                out.write("<id>".getBytes());
//                out.write(person.getId().toString().getBytes());
//                out.write("</id>\n".getBytes());
//                out.write("<name>".getBytes());
//                out.write(person.getName().getBytes());
//                out.write("</name>\n".getBytes());
//                out.write("<coordinates>\n".getBytes());
//                out.write("<coordinateX>".getBytes());
//                out.write(person.getCoordinates().getCoordinateX().toString().getBytes());
//                out.write("</coordinateX>\n".getBytes());
//                out.write("<coordinateY>".getBytes());
//                out.write(((Double) person.getCoordinates().getCoordinateY()).toString().getBytes());
//                out.write("</coordinateY>\n".getBytes());
//                out.write("</coordinates>\n".getBytes());
//                out.write("<creationDate>".getBytes());
//                out.write(person.getCreationDate().toString().getBytes());
//                out.write("</creationDate>\n".getBytes());
//                out.write("<height>".getBytes());
//                out.write(((Double) person.getHeight()).toString().getBytes());
//                out.write("</height>\n".getBytes());
//                out.write("<passportID>".getBytes());
//                out.write(person.getPassportID().getBytes());
//                out.write("</passportID>\n".getBytes());
//                out.write("<hairColor>".getBytes());
//                out.write(person.getHairColor().toString().getBytes());
//                out.write("</hairColor>\n".getBytes());
//                out.write("<nationality>".getBytes());
//                out.write(person.getNationality().toString().getBytes());
//                out.write("</nationality>\n".getBytes());
//                out.write("<location>\n".getBytes());
//                out.write("<locationX>".getBytes());
//                out.write(((Long) person.getLocation().getLocationX()).toString().getBytes());
//                out.write("</locationX>\n".getBytes());
//                out.write("<locationY>".getBytes());
//                out.write(((Float) person.getLocation().getLocationY()).toString().getBytes());
//                out.write("</locationY>\n".getBytes());
//                out.write("<locationZ>".getBytes());
//                out.write(person.getLocation().getLocationZ().toString().getBytes());
//                out.write("</locationZ>\n".getBytes());
//                out.write("</location>\n".getBytes());
//                out.write("</person>\n".getBytes());
//            }
//            out.write("</collection>".getBytes());
//        } catch (FileNotFoundException exception) {
//            System.out.println(exception.getMessage());
//        }
//    }
}
