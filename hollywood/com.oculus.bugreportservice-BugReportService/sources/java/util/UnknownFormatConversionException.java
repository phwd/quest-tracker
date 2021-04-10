package java.util;

public class UnknownFormatConversionException extends IllegalFormatException {
    private static final long serialVersionUID = 19060418;
    private String s;

    public UnknownFormatConversionException(String str) {
        if (str != null) {
            this.s = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format("Conversion = '%s'", this.s);
    }
}
