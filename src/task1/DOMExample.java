package task1;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class DOMExample {
    private static Sonnet sonet = new Sonnet();

    public static Sonnet parseDomToObj(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));

            SchemaFactory sFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource(new File("D:\\Java\\TeachMeSkills\\HW_Lesson18\\src\\schemaDoc.xsd"));
            Schema schema = sFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));

            document.getDocumentElement().normalize();
            String type = document.getElementsByTagName("sonnet").item(0).getAttributes().getNamedItem("type").getNodeValue();
            sonet.setType(type);
            NodeList nodeList = document.getElementsByTagName("author");
            Author author = new Author();
            if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodeList.item(0);
                author.setLastName(getTagValue("lastName", element));
                author.setFirstName(getTagValue("firstName", element));
                author.setNationality(getTagValue("nationality", element));
                author.setYearOfDeath(Integer.parseInt(getTagValue("yearOfDeath", element)));
                author.setYearOfBirth(Integer.parseInt(getTagValue("yearOfBirth", element)));
            }
            sonet.setAuthor(author);
            String title = document.getElementsByTagName("title").item(0).getTextContent();
            sonet.setTitle(title);
            Line l = new Line();
            NodeList lines = document.getDocumentElement().getElementsByTagName("line");
            for (int i = 0; i < lines.getLength(); i++) {
                Node line = lines.item(i);
                if (line.getNodeType() == Node.ELEMENT_NODE) {
                    Element lineElement = (Element) line;
                    l.getLine().add(lineElement.getTextContent());
                }
            }
            sonet.setLines(l);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e);
        }
        return sonet;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
