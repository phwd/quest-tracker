package java.util;

public class MissingFormatArgumentException extends IllegalFormatException {
    private static final long serialVersionUID = 19190115;
    private String s;

    public MissingFormatArgumentException(String str) {
        if (str != null) {
            this.s = str;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Format specifier '" + this.s + "'";
    }
}
