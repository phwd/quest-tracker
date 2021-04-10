package java.util;

public class IllegalFormatPrecisionException extends IllegalFormatException {
    private static final long serialVersionUID = 18711008;
    private int p;

    public IllegalFormatPrecisionException(int i) {
        this.p = i;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return Integer.toString(this.p);
    }
}
