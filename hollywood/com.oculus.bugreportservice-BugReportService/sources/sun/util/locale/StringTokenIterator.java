package sun.util.locale;

public class StringTokenIterator {
    private char delimiterChar;
    private String dlms;
    private boolean done;
    private int end;
    private int start;
    private String text;
    private String token;

    public StringTokenIterator(String str, String str2) {
        this.text = str;
        if (str2.length() == 1) {
            this.delimiterChar = str2.charAt(0);
        } else {
            this.dlms = str2;
        }
        setStart(0);
    }

    public String current() {
        return this.token;
    }

    public int currentStart() {
        return this.start;
    }

    public int currentEnd() {
        return this.end;
    }

    public boolean isDone() {
        return this.done;
    }

    public String next() {
        if (hasNext()) {
            this.start = this.end + 1;
            this.end = nextDelimiter(this.start);
            this.token = this.text.substring(this.start, this.end);
        } else {
            this.start = this.end;
            this.token = null;
            this.done = true;
        }
        return this.token;
    }

    public boolean hasNext() {
        return this.end < this.text.length();
    }

    public StringTokenIterator setStart(int i) {
        if (i <= this.text.length()) {
            this.start = i;
            this.end = nextDelimiter(this.start);
            this.token = this.text.substring(this.start, this.end);
            this.done = false;
            return this;
        }
        throw new IndexOutOfBoundsException();
    }

    private int nextDelimiter(int i) {
        int length = this.text.length();
        String str = this.dlms;
        if (str == null) {
            while (i < length) {
                if (this.text.charAt(i) == this.delimiterChar) {
                    return i;
                }
                i++;
            }
        } else {
            int length2 = str.length();
            while (i < length) {
                char charAt = this.text.charAt(i);
                for (int i2 = 0; i2 < length2; i2++) {
                    if (charAt == this.dlms.charAt(i2)) {
                        return i;
                    }
                }
                i++;
            }
        }
        return length;
    }
}
