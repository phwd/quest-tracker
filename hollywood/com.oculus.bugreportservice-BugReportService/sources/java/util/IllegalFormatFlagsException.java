package java.util;

public class IllegalFormatFlagsException extends IllegalFormatException {
    private static final long serialVersionUID = 790824;
    private String flags;

    public IllegalFormatFlagsException(String str) {
        if (str != null) {
            this.flags = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Flags = '" + this.flags + "'";
    }
}
