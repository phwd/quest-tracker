package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;

/* access modifiers changed from: package-private */
/* compiled from: ISO8601Converter */
public class ParseState {
    private int pos = 0;
    private String str;

    public ParseState(String str2) {
        this.str = str2;
    }

    public int length() {
        return this.str.length();
    }

    public boolean hasNext() {
        return this.pos < this.str.length();
    }

    public char ch(int index) {
        if (index < this.str.length()) {
            return this.str.charAt(index);
        }
        return 0;
    }

    public char ch() {
        if (this.pos < this.str.length()) {
            return this.str.charAt(this.pos);
        }
        return 0;
    }

    public void skip() {
        this.pos++;
    }

    public int pos() {
        return this.pos;
    }

    public int gatherInt(String errorMsg, int maxValue) throws XMPException {
        int value = 0;
        boolean success = false;
        char ch = ch(this.pos);
        while ('0' <= ch && ch <= '9') {
            value = (value * 10) + (ch - '0');
            success = true;
            this.pos++;
            ch = ch(this.pos);
        }
        if (!success) {
            throw new XMPException(errorMsg, 5);
        } else if (value > maxValue) {
            return maxValue;
        } else {
            if (value < 0) {
                return 0;
            }
            return value;
        }
    }
}
