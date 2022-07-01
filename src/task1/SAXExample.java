package task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXExample {
    private static Sonnet sonet = new Sonnet();

    public static Sonnet parseSaxToObj(String filePath) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLHandler xmlHandler = new XMLHandler();
            parser.parse(new File(filePath), xmlHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e);
        }
        return sonet;
    }

    private static class XMLHandler extends DefaultHandler {
        private String type;
        private Author author = new Author();
        private String title;
        private Line lines = new Line();
        private String lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
            if (qName.equals("sonnet")) {
                type = attributes.getValue("type");
                sonet.setType(type);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((author != null) && (title != null && !title.isEmpty()) && (lines != null)) {
                sonet.setAuthor(author);
                sonet.setTitle(title);
                sonet.setLines(lines);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("lastName")) {
                    author.setLastName(information);
                }
                if (lastElementName.equals("firstName")) {
                    author.setFirstName(information);
                }
                if (lastElementName.equals("nationality")) {
                    author.setNationality(information);
                }
                if (lastElementName.equals("yearOfBirth")) {
                    author.setYearOfBirth(Integer.parseInt(information));
                }
                if (lastElementName.equals("yearOfDeath")) {
                    author.setYearOfDeath(Integer.parseInt(information));
                }
                if (lastElementName.equals("title")) {
                    title = information;
                }
                if (lastElementName.equals("line")) {
                    lines.getLine().add(information);
                }
            }
        }

    }
}
