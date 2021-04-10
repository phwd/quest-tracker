package java.lang;

public class IncompatibleClassChangeError extends LinkageError {
    private static final long serialVersionUID = -4914975503642802119L;

    public IncompatibleClassChangeError() {
    }

    public IncompatibleClassChangeError(String str) {
        super(str);
    }
}
