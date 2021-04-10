package jp.co.omronsoft.iwnnime.ml;

public class WnnWord {
    public String additional;
    public int attribute;
    public String candidate;
    public int candidateType;
    public int characterType;
    public int frequency;
    public String hint;
    public int id;
    public int inputLen;
    public int lexicalCategory;
    public String stroke;
    public int viewId;

    public WnnWord() {
        this(0, "", "", "", 0, 0, null);
    }

    public WnnWord(String str, String str2) {
        this(0, str, str2, "", 0, 0, null);
    }

    public WnnWord(int i, String str, String str2) {
        this(i, str, str2, "", 0, 0, null);
    }

    public WnnWord(int i, String str, String str2, int i2) {
        this(i, str, str2, "", i2, 0, null);
    }

    public WnnWord(int i, int i2, String str, String str2, int i3) {
        this(i, str, str2, "", i3, 0, null);
        this.inputLen = i2;
    }

    public WnnWord(int i, String str, String str2, int i2, int i3, String str3) {
        this(i, str, str2, "", i2, i3, str3);
    }

    public WnnWord(int i, int i2, String str, String str2, int i3, int i4, int i5, int i6) {
        this(i, str, str2, "", i3, 0, null);
        this.inputLen = i2;
        this.frequency = i4;
        this.candidateType = i5;
        this.characterType = i6;
    }

    public WnnWord(int i, String str, String str2, String str3, int i2, int i3, String str4) {
        this.inputLen = 0;
        this.viewId = 2147483646;
        this.id = i;
        this.candidate = str;
        this.stroke = str2;
        this.hint = str3;
        this.attribute = i2;
        this.lexicalCategory = i3;
        this.additional = str4;
    }
}
