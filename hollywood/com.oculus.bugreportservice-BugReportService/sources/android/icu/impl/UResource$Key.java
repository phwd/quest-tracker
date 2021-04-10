package android.icu.impl;

public final class UResource$Key implements CharSequence, Cloneable, Comparable {
    private byte[] bytes;
    private int length;
    private int offset;
    private String s;

    public UResource$Key() {
        this.s = "";
    }

    private UResource$Key(byte[] bArr, int i, int i2) {
        this.bytes = bArr;
        this.offset = i;
        this.length = i2;
    }

    public UResource$Key setBytes(byte[] bArr, int i) {
        this.bytes = bArr;
        this.offset = i;
        int i2 = 0;
        while (true) {
            this.length = i2;
            int i3 = this.length;
            if (bArr[i + i3] != 0) {
                i2 = i3 + 1;
            } else {
                this.s = null;
                return this;
            }
        }
    }

    public UResource$Key setToEmpty() {
        this.bytes = null;
        this.length = 0;
        this.offset = 0;
        this.s = "";
        return this;
    }

    public UResource$Key setString(String str) {
        if (str.isEmpty()) {
            setToEmpty();
        } else {
            this.bytes = new byte[str.length()];
            this.offset = 0;
            this.length = str.length();
            for (int i = 0; i < this.length; i++) {
                char charAt = str.charAt(i);
                if (charAt <= 127) {
                    this.bytes[i] = (byte) charAt;
                } else {
                    throw new IllegalArgumentException('\"' + str + "\" is not an ASCII string");
                }
            }
            this.s = str;
        }
        return this;
    }

    public UResource$Key clone() {
        try {
            return (UResource$Key) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return (char) this.bytes[this.offset + i];
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    @Override // java.lang.CharSequence
    public UResource$Key subSequence(int i, int i2) {
        return new UResource$Key(this.bytes, this.offset + i, i2 - i);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        if (this.s == null) {
            this.s = internalSubString(0, this.length);
        }
        return this.s;
    }

    private String internalSubString(int i, int i2) {
        StringBuilder sb = new StringBuilder(i2 - i);
        while (i < i2) {
            sb.append((char) this.bytes[this.offset + i]);
            i++;
        }
        return sb.toString();
    }

    private boolean regionMatches(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.bytes[this.offset + i3] != bArr[i + i3]) {
                return false;
            }
        }
        return true;
    }

    private boolean regionMatches(int i, CharSequence charSequence, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.bytes[this.offset + i + i3] != charSequence.charAt(i3)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UResource$Key)) {
            return false;
        }
        UResource$Key uResource$Key = (UResource$Key) obj;
        int i = this.length;
        return i == uResource$Key.length && regionMatches(uResource$Key.bytes, uResource$Key.offset, i);
    }

    public boolean contentEquals(CharSequence charSequence) {
        int i;
        if (charSequence == null) {
            return false;
        }
        return this == charSequence || (charSequence.length() == (i = this.length) && regionMatches(0, charSequence, i));
    }

    public boolean endsWith(CharSequence charSequence) {
        int length2 = charSequence.length();
        int i = this.length;
        return length2 <= i && regionMatches(i - length2, charSequence, length2);
    }

    public int hashCode() {
        if (this.length == 0) {
            return 0;
        }
        int i = 1;
        byte b = this.bytes[this.offset];
        while (i < this.length) {
            i++;
            b = (b * 37) + this.bytes[this.offset];
        }
        return b == 1 ? 1 : 0;
    }

    public int compareTo(UResource$Key uResource$Key) {
        return compareTo((CharSequence) uResource$Key);
    }

    public int compareTo(CharSequence charSequence) {
        int length2 = charSequence.length();
        int i = this.length;
        if (i > length2) {
            i = length2;
        }
        for (int i2 = 0; i2 < i; i2++) {
            int charAt = charAt(i2) - charSequence.charAt(i2);
            if (charAt != 0) {
                return charAt;
            }
        }
        return this.length - length2;
    }
}
