package java.util;

public class MissingFormatWidthException extends IllegalFormatException {
    private static final long serialVersionUID = 15560123;
    private String s;

    public MissingFormatWidthException(String str) {
        if (str != null) {
            this.s = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.s;
    }
}
