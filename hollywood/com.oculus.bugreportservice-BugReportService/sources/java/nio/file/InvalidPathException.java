package java.nio.file;

public class InvalidPathException extends IllegalArgumentException {
    static final long serialVersionUID = 4355821422286746137L;
    private int index;
    private String input;

    public String getReason() {
        return super.getMessage();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getReason());
        if (this.index > -1) {
            stringBuffer.append(" at index ");
            stringBuffer.append(this.index);
        }
        stringBuffer.append(": ");
        stringBuffer.append(this.input);
        return stringBuffer.toString();
    }
}
