package task1;

public class LotOfFilesException extends Exception {
    @Override
    public String getMessage() {
        return "There are a lot of XML files in the directory!";
    }

    @Override
    public String toString() {
        return "LotOfFilesException{}" + getMessage();
    }
}
