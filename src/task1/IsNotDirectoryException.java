package task1;

public class IsNotDirectoryException extends Exception {
    @Override
    public String getMessage() {
        return "There is not a directory path!";
    }

    @Override
    public String toString() {
        return "IsNotDirectoryException{}" + getMessage();
    }
}
