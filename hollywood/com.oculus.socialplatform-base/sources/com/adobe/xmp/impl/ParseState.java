package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;

public class ParseState {
    public int pos = 0;
    public String str;

    public int gatherInt(String str2, int i) throws XMPException {
        char ch = ch(this.pos);
        int i2 = 0;
        boolean z = false;
        while ('0' <= ch && ch <= '9') {
            i2 = (i2 * 10) + (ch - '0');
            int i3 = this.pos + 1;
            this.pos = i3;
            ch = ch(i3);
            z = true;
        }
        if (!z) {
            throw new XMPException(str2, 5);
        } else if (i2 > i) {
            return i;
        } else {
            if (i2 < 0) {
                return 0;
            }
            return i2;
        }
    }

    public boolean hasNext() {
        if (this.pos < this.str.length()) {
            return true;
        }
        return false;
    }

    public int length() {
        return this.str.length();
    }

    public void skip() {
        this.pos++;
    }

    public ParseState(String str2) {
        this.str = str2;
    }

    public int pos() {
        return this.pos;
    }

    public char ch() {
        int i = this.pos;
        String str2 = this.str;
        if (i < str2.length()) {
            return str2.charAt(i);
        }
        return 0;
    }

    public char ch(int i) {
        String str2 = this.str;
        if (i < str2.length()) {
            return str2.charAt(i);
        }
        return 0;
    }
}
