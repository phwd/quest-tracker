package sun.util.locale;

public class ParseStatus {
    int errorIndex;
    String errorMsg;
    int parseLength;

    public ParseStatus() {
        reset();
    }

    public void reset() {
        this.parseLength = 0;
        this.errorIndex = -1;
        this.errorMsg = null;
    }

    public boolean isError() {
        return this.errorIndex >= 0;
    }
}
