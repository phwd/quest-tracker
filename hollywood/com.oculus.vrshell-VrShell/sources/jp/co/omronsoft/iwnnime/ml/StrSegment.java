package jp.co.omronsoft.iwnnime.ml;

public class StrSegment {
    public int from;
    public String string;
    public int to;

    public StrSegment(String str) {
        this(str, -1, -1);
    }

    public StrSegment(char[] cArr) {
        this(new String(cArr), -1, -1);
    }

    public StrSegment(String str, int i, int i2) {
        this.string = str;
        this.from = i;
        this.to = i2;
    }
}
