package android.icu.impl;

import android.icu.text.SymbolTable;
import android.icu.text.UTF16;
import java.text.ParsePosition;

public class RuleCharacterIterator {
    public static final int DONE = -1;
    public static final int PARSE_ESCAPES = 2;
    public static final int PARSE_VARIABLES = 1;
    public static final int SKIP_WHITESPACE = 4;
    private char[] buf;
    private int bufPos;
    private boolean isEscaped;
    private ParsePosition pos;
    private SymbolTable sym;
    private String text;

    public RuleCharacterIterator(String text2, SymbolTable sym2, ParsePosition pos2) {
        if (text2 == null || pos2.getIndex() > text2.length()) {
            throw new IllegalArgumentException();
        }
        this.text = text2;
        this.sym = sym2;
        this.pos = pos2;
        this.buf = null;
    }

    public boolean atEnd() {
        return this.buf == null && this.pos.getIndex() == this.text.length();
    }

    public int next(int options) {
        int c;
        SymbolTable symbolTable;
        this.isEscaped = false;
        while (true) {
            c = _current();
            _advance(UTF16.getCharCount(c));
            if (c == 36 && this.buf == null && (options & 1) != 0 && (symbolTable = this.sym) != null) {
                String str = this.text;
                String name = symbolTable.parseReference(str, this.pos, str.length());
                if (name == null) {
                    break;
                }
                this.bufPos = 0;
                this.buf = this.sym.lookup(name);
                char[] cArr = this.buf;
                if (cArr == null) {
                    throw new IllegalArgumentException("Undefined variable: " + name);
                } else if (cArr.length == 0) {
                    this.buf = null;
                }
            } else if ((options & 4) == 0 || !PatternProps.isWhiteSpace(c)) {
            }
        }
        if (c == 92 && (options & 2) != 0) {
            int[] offset = {0};
            c = Utility.unescapeAt(lookahead(), offset);
            jumpahead(offset[0]);
            this.isEscaped = true;
            if (c < 0) {
                throw new IllegalArgumentException("Invalid escape");
            }
        }
        return c;
    }

    public boolean isEscaped() {
        return this.isEscaped;
    }

    public boolean inVariable() {
        return this.buf != null;
    }

    public Object getPos(Object p) {
        if (p == null) {
            return new Object[]{this.buf, new int[]{this.pos.getIndex(), this.bufPos}};
        }
        Object[] a = (Object[]) p;
        a[0] = this.buf;
        int[] v = (int[]) a[1];
        v[0] = this.pos.getIndex();
        v[1] = this.bufPos;
        return p;
    }

    public void setPos(Object p) {
        Object[] a = (Object[]) p;
        this.buf = (char[]) a[0];
        int[] v = (int[]) a[1];
        this.pos.setIndex(v[0]);
        this.bufPos = v[1];
    }

    public void skipIgnored(int options) {
        if ((options & 4) != 0) {
            while (true) {
                int a = _current();
                if (PatternProps.isWhiteSpace(a)) {
                    _advance(UTF16.getCharCount(a));
                } else {
                    return;
                }
            }
        }
    }

    public String lookahead() {
        char[] cArr = this.buf;
        if (cArr == null) {
            return this.text.substring(this.pos.getIndex());
        }
        int i = this.bufPos;
        return new String(cArr, i, cArr.length - i);
    }

    public void jumpahead(int count) {
        if (count >= 0) {
            char[] cArr = this.buf;
            if (cArr != null) {
                this.bufPos += count;
                int i = this.bufPos;
                if (i > cArr.length) {
                    throw new IllegalArgumentException();
                } else if (i == cArr.length) {
                    this.buf = null;
                }
            } else {
                int i2 = this.pos.getIndex() + count;
                this.pos.setIndex(i2);
                if (i2 > this.text.length()) {
                    throw new IllegalArgumentException();
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String toString() {
        int b = this.pos.getIndex();
        return this.text.substring(0, b) + '|' + this.text.substring(b);
    }

    private int _current() {
        char[] cArr = this.buf;
        if (cArr != null) {
            return UTF16.charAt(cArr, 0, cArr.length, this.bufPos);
        }
        int i = this.pos.getIndex();
        if (i < this.text.length()) {
            return UTF16.charAt(this.text, i);
        }
        return -1;
    }

    private void _advance(int count) {
        char[] cArr = this.buf;
        if (cArr != null) {
            this.bufPos += count;
            if (this.bufPos == cArr.length) {
                this.buf = null;
                return;
            }
            return;
        }
        ParsePosition parsePosition = this.pos;
        parsePosition.setIndex(parsePosition.getIndex() + count);
        if (this.pos.getIndex() > this.text.length()) {
            this.pos.setIndex(this.text.length());
        }
    }
}
