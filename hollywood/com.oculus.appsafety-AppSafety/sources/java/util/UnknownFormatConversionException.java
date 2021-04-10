package java.util;

public class UnknownFormatConversionException extends IllegalFormatException {
    private static final long serialVersionUID = 19060418;
    private String s;

    public UnknownFormatConversionException(String s2) {
        if (s2 != null) {
            this.s = s2;
            return;
        }
        throw new NullPointerException();
    }

    public String getConversion() {
        return this.s;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format("Conversion = '%s'", this.s);
    }
}
