package java.lang.invoke;

public class LambdaConversionException extends Exception {
    public static final long serialVersionUID = 300;

    public LambdaConversionException() {
    }

    public LambdaConversionException(String str) {
        super(str);
    }

    public LambdaConversionException(String str, Throwable th) {
        super(str, th);
    }

    public LambdaConversionException(String str, Throwable th, boolean z, boolean z2) {
        super(str, th, z, z2);
    }

    public LambdaConversionException(Throwable th) {
        super(th);
    }
}
