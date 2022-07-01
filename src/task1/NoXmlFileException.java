package task1;

public class NoXmlFileException extends Exception {
    @Override
    public String getMessage() {
        return "There are no XML files in the directory!";
    }

    @Override
    public String toString() {
        return "NoXmlFileException" + getMessage();
    }
}
