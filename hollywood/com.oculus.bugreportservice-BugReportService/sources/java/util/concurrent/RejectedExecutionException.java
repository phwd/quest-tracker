package java.util.concurrent;

public class RejectedExecutionException extends RuntimeException {
    private static final long serialVersionUID = -375805702767069545L;

    public RejectedExecutionException() {
    }

    public RejectedExecutionException(String str) {
        super(str);
    }
}
