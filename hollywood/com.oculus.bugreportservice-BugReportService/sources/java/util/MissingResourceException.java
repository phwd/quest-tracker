package java.util;

public class MissingResourceException extends RuntimeException {
    private static final long serialVersionUID = -4876345176062000401L;
    private String className;
    private String key;

    public MissingResourceException(String str, String str2, String str3) {
        super(str);
        this.className = str2;
        this.key = str3;
    }

    MissingResourceException(String str, String str2, String str3, Throwable th) {
        super(str, th);
        this.className = str2;
        this.key = str3;
    }
}
