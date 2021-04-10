package java.text;

public class ParseException extends Exception {
    private static final long serialVersionUID = 2703218443322787634L;
    private int errorOffset;

    public ParseException(String str, int i) {
        super(str);
        this.errorOffset = i;
    }
}
