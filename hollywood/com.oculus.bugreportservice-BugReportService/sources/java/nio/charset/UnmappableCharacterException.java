package java.nio.charset;

public class UnmappableCharacterException extends CharacterCodingException {
    private static final long serialVersionUID = -7026962371537706123L;
    private int inputLength;

    public UnmappableCharacterException(int i) {
        this.inputLength = i;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Input length = " + this.inputLength;
    }
}
