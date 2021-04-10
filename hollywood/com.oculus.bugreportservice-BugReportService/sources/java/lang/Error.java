package java.lang;

public class Error extends Throwable {
    static final long serialVersionUID = 4980196508277280342L;

    public Error() {
    }

    public Error(String str) {
        super(str);
    }

    public Error(String str, Throwable th) {
        super(str, th);
    }

    public Error(Throwable th) {
        super(th);
    }
}
