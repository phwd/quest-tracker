package java.util;

public class UnknownFormatFlagsException extends IllegalFormatException {
    private static final long serialVersionUID = 19370506;
    private String flags;

    public UnknownFormatFlagsException(String str) {
        if (str != null) {
            this.flags = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Flags = " + this.flags;
    }
}
