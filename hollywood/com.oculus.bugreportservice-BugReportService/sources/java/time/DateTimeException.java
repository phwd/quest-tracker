package java.time;

public class DateTimeException extends RuntimeException {
    private static final long serialVersionUID = -1632418723876261839L;

    public DateTimeException(String str) {
        super(str);
    }

    public DateTimeException(String str, Throwable th) {
        super(str, th);
    }
}
