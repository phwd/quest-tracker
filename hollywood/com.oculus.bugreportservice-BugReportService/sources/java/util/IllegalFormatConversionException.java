package java.util;

public class IllegalFormatConversionException extends IllegalFormatException {
    private static final long serialVersionUID = 17000126;
    private Class arg;
    private char c;

    public IllegalFormatConversionException(char c2, Class cls) {
        if (cls != null) {
            this.c = c2;
            this.arg = cls;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format("%c != %s", Character.valueOf(this.c), this.arg.getName());
    }
}
