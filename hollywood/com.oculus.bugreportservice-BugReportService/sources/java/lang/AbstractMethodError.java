package java.lang;

public class AbstractMethodError extends IncompatibleClassChangeError {
    private static final long serialVersionUID = -1654391082989018462L;

    public AbstractMethodError() {
    }

    public AbstractMethodError(String str) {
        super(str);
    }
}
