package task1;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;


public class MainClass {
    public static void main(String[] args) {
        try {
            String directoryPath = Input.input("Enter directory path: ");
            String path = WorkWithFiles.findXmlFile(directoryPath);
            int number = Integer.parseInt(Input.input("Choose parse variant: 1 - SAX, 2 - DOM, 3 - JAXB: "));
            Sonnet sonnet = WorkWithFiles.choseParse(number, path);
            File file = new File(sonnet.getAuthor().getFirstName() + "_" + sonnet.getAuthor().getLastName() + "_" + sonnet.getTitle() + ".txt");
            WorkWithFiles.writeLinesInFile(file.getPath(), sonnet.getLines().getLine());
        } catch (IsNotDirectoryException | NoXmlFileException | LotOfFilesException | IOException e) {
            System.out.println(e);
        }
    }
}
