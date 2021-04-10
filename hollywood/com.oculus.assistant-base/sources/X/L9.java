package X;

public class L9 extends RuntimeException {
    public static final long serialVersionUID = 1;

    public L9() {
    }

    public L9(String str) {
        super(str);
    }

    public L9(Throwable th) {
        super(th);
    }
}
