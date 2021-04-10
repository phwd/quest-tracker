package java.nio.charset;

public class MalformedInputException extends CharacterCodingException {
    private static final long serialVersionUID = -3438823399834806194L;
    private int inputLength;

    public MalformedInputException(int inputLength2) {
        this.inputLength = inputLength2;
    }

    public int getInputLength() {
        return this.inputLength;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Input length = " + this.inputLength;
    }
}
