package java.lang;

public class Exception extends Throwable {
    static final long serialVersionUID = -3387516993124229948L;

    public Exception() {
    }

    public Exception(String str) {
        super(str);
    }

    public Exception(String str, Throwable th) {
        super(str, th);
    }

    public Exception(Throwable th) {
        super(th);
    }
}
