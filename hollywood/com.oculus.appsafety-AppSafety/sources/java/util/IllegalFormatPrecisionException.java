package java.util;

public class IllegalFormatPrecisionException extends IllegalFormatException {
    private static final long serialVersionUID = 18711008;
    private int p;

    public IllegalFormatPrecisionException(int p2) {
        this.p = p2;
    }

    public int getPrecision() {
        return this.p;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return Integer.toString(this.p);
    }
}
