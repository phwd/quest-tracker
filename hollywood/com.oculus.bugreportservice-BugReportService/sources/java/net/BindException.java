package java.net;

public class BindException extends SocketException {
    private static final long serialVersionUID = -5945005768251722951L;

    public BindException(String str) {
        super(str);
    }

    public BindException() {
    }

    public BindException(String str, Throwable th) {
        super(str, th);
    }
}
