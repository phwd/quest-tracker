package java.lang;

public class IllegalMonitorStateException extends RuntimeException {
    private static final long serialVersionUID = 3713306369498869069L;

    public IllegalMonitorStateException() {
    }

    public IllegalMonitorStateException(String str) {
        super(str);
    }
}
