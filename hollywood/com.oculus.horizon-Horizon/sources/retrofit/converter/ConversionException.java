package retrofit.converter;

public class ConversionException extends Exception {
    public ConversionException(String str) {
        super(str);
    }

    public ConversionException(String str, Throwable th) {
        super(str, th);
    }

    public ConversionException(Throwable th) {
        super(th);
    }
}
