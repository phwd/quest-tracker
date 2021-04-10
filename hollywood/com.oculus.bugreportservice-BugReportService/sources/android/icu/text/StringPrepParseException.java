package android.icu.text;

import java.text.ParseException;

public class StringPrepParseException extends ParseException {
    static final long serialVersionUID = 7160264827701651255L;
    private int error;
    private int line;
    private StringBuffer postContext;
    private StringBuffer preContext;

    public int hashCode() {
        return 42;
    }

    public boolean equals(Object obj) {
        if ((obj instanceof StringPrepParseException) && ((StringPrepParseException) obj).error == this.error) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.getMessage() + ". line:  " + this.line + ". preContext:  " + this.preContext + ". postContext: " + this.postContext + "\n";
    }
}
