package task1;

import com.sun.beans.decoder.ElementHandler;
import org.xml.sax.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFiles {

    public static Sonnet parseJaxbToObj(String filePath) {
        Sonnet unmarshal = null;
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Sonnet.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            unmarshal = (Sonnet) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println(e);
        }
        return unmarshal;
    }

    public static void writeLinesInFile(String filePath, List<String> list) {

        try (BufferedWriter writter = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : list) {
                writter.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static String findXmlFile(String directoryPath) throws IOException, IsNotDirectoryException, LotOfFilesException, NoXmlFileException {
        Path path = Paths.get(directoryPath);
        if (!Files.isDirectory(path)) {
            throw new IsNotDirectoryException();
        }
        int count = 0;
        String result = "";
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
        for (Path p : directoryStream) {
            if (p.toString().endsWith(".xml")) {
                result = p.toString();
                count++;
            }
        }
        if (count > 1) {
            throw new LotOfFilesException();
        }
        if (count == 0) {
            throw new NoXmlFileException();
        }
        return result;
    }

    public static Sonnet choseParse(int n, String filePath) {
        Sonnet sonnet = new Sonnet();
        switch (n) {
            case 1: {
                sonnet = SAXExample.parseSaxToObj(filePath);
                break;
            }
            case 2: {
                sonnet = DOMExample.parseDomToObj(filePath);
                break;
            }
            case 3: {
                sonnet = parseJaxbToObj(filePath);
            }
        }
        return sonnet;
    }
}