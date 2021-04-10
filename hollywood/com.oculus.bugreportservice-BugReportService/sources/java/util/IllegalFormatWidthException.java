package java.util;

public class IllegalFormatWidthException extends IllegalFormatException {
    private static final long serialVersionUID = 16660902;
    private int w;

    public IllegalFormatWidthException(int i) {
        this.w = i;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return Integer.toString(this.w);
    }
}
