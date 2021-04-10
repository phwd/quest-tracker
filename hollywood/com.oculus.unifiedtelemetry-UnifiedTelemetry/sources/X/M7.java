package X;

public class M7 extends RuntimeException {
    public static final long serialVersionUID = -4086729973971783390L;

    public M7(String str) {
        super(str);
    }

    public M7(String str, Throwable th) {
        super(str, th);
    }

    public M7(Throwable th) {
        super(th);
    }
}
