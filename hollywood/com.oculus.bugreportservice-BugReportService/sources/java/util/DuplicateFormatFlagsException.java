package java.util;

public class DuplicateFormatFlagsException extends IllegalFormatException {
    private static final long serialVersionUID = 18890531;
    private String flags;

    public DuplicateFormatFlagsException(String str) {
        if (str != null) {
            this.flags = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return String.format("Flags = '%s'", this.flags);
    }
}
