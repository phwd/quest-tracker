package java.io;

public class IOException extends Exception {
    static final long serialVersionUID = 7818375828146090155L;

    public IOException() {
    }

    public IOException(String str) {
        super(str);
    }

    public IOException(String str, Throwable th) {
        super(str, th);
    }

    public IOException(Throwable th) {
        super(th);
    }
}
