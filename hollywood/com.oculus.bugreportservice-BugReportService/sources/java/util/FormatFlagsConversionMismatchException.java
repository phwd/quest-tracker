package java.util;

public class FormatFlagsConversionMismatchException extends IllegalFormatException {
    private static final long serialVersionUID = 19120414;
    private char c;
    private String f;

    public FormatFlagsConversionMismatchException(String str, char c2) {
        if (str != null) {
            this.f = str;
            this.c = c2;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Conversion = " + this.c + ", Flags = " + this.f;
    }
}
