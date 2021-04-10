package java.net;

public class URISyntaxException extends Exception {
    private static final long serialVersionUID = 2137979680897488891L;
    private int index;
    private String input;

    public URISyntaxException(String str, String str2, int i) {
        super(str2);
        if (str == null || str2 == null) {
            throw new NullPointerException();
        } else if (i >= -1) {
            this.input = str;
            this.index = i;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public URISyntaxException(String str, String str2) {
        this(str, str2, -1);
    }

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
