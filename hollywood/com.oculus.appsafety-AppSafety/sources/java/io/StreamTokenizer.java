package java.io;

import android.icu.impl.PatternTokenizer;
import java.util.Arrays;

public class StreamTokenizer {
    private static final byte CT_ALPHA = 4;
    private static final byte CT_COMMENT = 16;
    private static final byte CT_DIGIT = 2;
    private static final byte CT_QUOTE = 8;
    private static final byte CT_WHITESPACE = 1;
    private static final int NEED_CHAR = Integer.MAX_VALUE;
    private static final int SKIP_LF = 2147483646;
    public static final int TT_EOF = -1;
    public static final int TT_EOL = 10;
    private static final int TT_NOTHING = -4;
    public static final int TT_NUMBER = -2;
    public static final int TT_WORD = -3;
    private int LINENO;
    private char[] buf;
    private byte[] ctype;
    private boolean eolIsSignificantP;
    private boolean forceLower;
    private InputStream input;
    public double nval;
    private int peekc;
    private boolean pushedBack;
    private Reader reader;
    private boolean slashSlashCommentsP;
    private boolean slashStarCommentsP;
    public String sval;
    public int ttype;

    private StreamTokenizer() {
        this.reader = null;
        this.input = null;
        this.buf = new char[20];
        this.peekc = Integer.MAX_VALUE;
        this.LINENO = 1;
        this.eolIsSignificantP = false;
        this.slashSlashCommentsP = false;
        this.slashStarCommentsP = false;
        this.ctype = new byte[256];
        this.ttype = -4;
        wordChars(97, 122);
        wordChars(65, 90);
        wordChars(160, 255);
        whitespaceChars(0, 32);
        commentChar(47);
        quoteChar(34);
        quoteChar(39);
        parseNumbers();
    }

    @Deprecated
    public StreamTokenizer(InputStream is) {
        this();
        if (is != null) {
            this.input = is;
            return;
        }
        throw new NullPointerException();
    }

    public StreamTokenizer(Reader r) {
        this();
        if (r != null) {
            this.reader = r;
            return;
        }
        throw new NullPointerException();
    }

    public void resetSyntax() {
        int i = this.ctype.length;
        while (true) {
            i--;
            if (i >= 0) {
                this.ctype[i] = 0;
            } else {
                return;
            }
        }
    }

    public void wordChars(int low, int hi) {
        if (low < 0) {
            low = 0;
        }
        byte[] bArr = this.ctype;
        if (hi >= bArr.length) {
            hi = bArr.length - 1;
        }
        while (low <= hi) {
            byte[] bArr2 = this.ctype;
            bArr2[low] = (byte) (bArr2[low] | 4);
            low++;
        }
    }

    public void whitespaceChars(int low, int hi) {
        if (low < 0) {
            low = 0;
        }
        byte[] bArr = this.ctype;
        if (hi >= bArr.length) {
            hi = bArr.length - 1;
        }
        while (low <= hi) {
            this.ctype[low] = 1;
            low++;
        }
    }

    public void ordinaryChars(int low, int hi) {
        if (low < 0) {
            low = 0;
        }
        byte[] bArr = this.ctype;
        if (hi >= bArr.length) {
            hi = bArr.length - 1;
        }
        while (low <= hi) {
            this.ctype[low] = 0;
            low++;
        }
    }

    public void ordinaryChar(int ch) {
        if (ch >= 0) {
            byte[] bArr = this.ctype;
            if (ch < bArr.length) {
                bArr[ch] = 0;
            }
        }
    }

    public void commentChar(int ch) {
        if (ch >= 0) {
            byte[] bArr = this.ctype;
            if (ch < bArr.length) {
                bArr[ch] = 16;
            }
        }
    }

    public void quoteChar(int ch) {
        if (ch >= 0) {
            byte[] bArr = this.ctype;
            if (ch < bArr.length) {
                bArr[ch] = 8;
            }
        }
    }

    public void parseNumbers() {
        for (int i = 48; i <= 57; i++) {
            byte[] bArr = this.ctype;
            bArr[i] = (byte) (bArr[i] | 2);
        }
        byte[] bArr2 = this.ctype;
        bArr2[46] = (byte) (bArr2[46] | 2);
        bArr2[45] = (byte) (bArr2[45] | 2);
    }

    public void eolIsSignificant(boolean flag) {
        this.eolIsSignificantP = flag;
    }

    public void slashStarComments(boolean flag) {
        this.slashStarCommentsP = flag;
    }

    public void slashSlashComments(boolean flag) {
        this.slashSlashCommentsP = flag;
    }

    public void lowerCaseMode(boolean fl) {
        this.forceLower = fl;
    }

    private int read() throws IOException {
        Reader reader2 = this.reader;
        if (reader2 != null) {
            return reader2.read();
        }
        InputStream inputStream = this.input;
        if (inputStream != null) {
            return inputStream.read();
        }
        throw new IllegalStateException();
    }

    public int nextToken() throws IOException {
        int c;
        int c2;
        int c3;
        int c4;
        int i;
        if (this.pushedBack) {
            this.pushedBack = false;
            return this.ttype;
        }
        byte[] ct = this.ctype;
        this.sval = null;
        int c5 = this.peekc;
        if (c5 < 0) {
            c5 = Integer.MAX_VALUE;
        }
        if (c5 == SKIP_LF) {
            c5 = read();
            if (c5 < 0) {
                this.ttype = -1;
                return -1;
            } else if (c5 == 10) {
                c5 = Integer.MAX_VALUE;
            }
        }
        int i2 = Integer.MAX_VALUE;
        if (c5 != Integer.MAX_VALUE || (c5 = read()) >= 0) {
            this.ttype = c5;
            this.peekc = Integer.MAX_VALUE;
            byte b = c5 < 256 ? ct[c5] : 4;
            while ((b & 1) != 0) {
                if (c5 == 13) {
                    this.LINENO++;
                    if (this.eolIsSignificantP) {
                        this.peekc = SKIP_LF;
                        this.ttype = 10;
                        return 10;
                    }
                    c5 = read();
                    if (c5 == 10) {
                        c5 = read();
                    }
                } else {
                    if (c5 == 10) {
                        this.LINENO++;
                        if (this.eolIsSignificantP) {
                            this.ttype = 10;
                            return 10;
                        }
                    }
                    c5 = read();
                }
                if (c5 < 0) {
                    this.ttype = -1;
                    return -1;
                }
                b = c5 < 256 ? ct[c5] : 4;
            }
            if ((b & 2) != 0) {
                boolean neg = false;
                if (c5 == 45) {
                    c5 = read();
                    if (c5 == 46 || (c5 >= 48 && c5 <= 57)) {
                        neg = true;
                    } else {
                        this.peekc = c5;
                        this.ttype = 45;
                        return 45;
                    }
                }
                double v = 0.0d;
                int decexp = 0;
                int seendot = 0;
                while (true) {
                    if (c5 == 46 && seendot == 0) {
                        seendot = 1;
                    } else if (48 > c5 || c5 > 57) {
                        this.peekc = c5;
                    } else {
                        decexp += seendot;
                        v = (10.0d * v) + ((double) (c5 - 48));
                    }
                    c5 = read();
                }
                this.peekc = c5;
                if (decexp != 0) {
                    double denom = 10.0d;
                    for (int decexp2 = decexp - 1; decexp2 > 0; decexp2--) {
                        denom *= 10.0d;
                    }
                    v /= denom;
                }
                this.nval = neg ? -v : v;
                this.ttype = -2;
                return -2;
            } else if ((b & 4) != 0) {
                int i3 = 0;
                while (true) {
                    char[] cArr = this.buf;
                    if (i3 >= cArr.length) {
                        this.buf = Arrays.copyOf(cArr, cArr.length * 2);
                    }
                    i = i3 + 1;
                    this.buf[i3] = (char) c5;
                    c5 = read();
                    if (((c5 < 0 ? 1 : c5 < 256 ? ct[c5] : 4) & 6) == 0) {
                        break;
                    }
                    i3 = i;
                }
                this.peekc = c5;
                this.sval = String.copyValueOf(this.buf, 0, i);
                if (this.forceLower) {
                    this.sval = this.sval.toLowerCase();
                }
                this.ttype = -3;
                return -3;
            } else if ((b & 8) != 0) {
                this.ttype = c5;
                int i4 = 0;
                int d = read();
                while (d >= 0 && d != this.ttype && d != 10 && d != 13) {
                    if (d == 92) {
                        c4 = read();
                        if (c4 < 48 || c4 > 55) {
                            if (c4 == 97) {
                                c4 = 7;
                            } else if (c4 == 98) {
                                c4 = 8;
                            } else if (c4 == 102) {
                                c4 = 12;
                            } else if (c4 == 110) {
                                c4 = 10;
                            } else if (c4 == 114) {
                                c4 = 13;
                            } else if (c4 == 116) {
                                c4 = 9;
                            } else if (c4 == 118) {
                                c4 = 11;
                            }
                            d = read();
                        } else {
                            c4 -= 48;
                            int c22 = read();
                            if (48 > c22 || c22 > 55) {
                                d = c22;
                            } else {
                                c4 = (c4 << 3) + (c22 - 48);
                                int c23 = read();
                                if (48 > c23 || c23 > 55 || c4 > 51) {
                                    d = c23;
                                } else {
                                    c4 = (c4 << 3) + (c23 - 48);
                                    d = read();
                                }
                            }
                        }
                    } else {
                        c4 = d;
                        d = read();
                    }
                    char[] cArr2 = this.buf;
                    if (i4 >= cArr2.length) {
                        this.buf = Arrays.copyOf(cArr2, cArr2.length * 2);
                    }
                    this.buf[i4] = (char) c4;
                    i4++;
                }
                if (d != this.ttype) {
                    i2 = d;
                }
                this.peekc = i2;
                this.sval = String.copyValueOf(this.buf, 0, i4);
                return this.ttype;
            } else if (c5 == 47 && (this.slashSlashCommentsP || this.slashStarCommentsP)) {
                int c6 = read();
                if (c6 == 42 && this.slashStarCommentsP) {
                    int prevc = 0;
                    while (true) {
                        int read = read();
                        int c7 = read;
                        if (read == 47 && prevc == 42) {
                            return nextToken();
                        }
                        if (c7 == 13) {
                            this.LINENO++;
                            c7 = read();
                            if (c7 == 10) {
                                c7 = read();
                            }
                        } else if (c7 == 10) {
                            this.LINENO++;
                            c7 = read();
                        }
                        if (c7 < 0) {
                            this.ttype = -1;
                            return -1;
                        }
                        prevc = c7;
                    }
                } else if (c6 == 47 && this.slashSlashCommentsP) {
                    do {
                        c3 = read();
                        if (c3 == 10 || c3 == 13) {
                            this.peekc = c3;
                        }
                    } while (c3 >= 0);
                    this.peekc = c3;
                    return nextToken();
                } else if ((ct[47] & 16) != 0) {
                    do {
                        c2 = read();
                        if (c2 == 10 || c2 == 13) {
                            this.peekc = c2;
                        }
                    } while (c2 >= 0);
                    this.peekc = c2;
                    return nextToken();
                } else {
                    this.peekc = c6;
                    this.ttype = 47;
                    return 47;
                }
            } else if ((b & 16) != 0) {
                do {
                    c = read();
                    if (c == 10 || c == 13) {
                        this.peekc = c;
                    }
                } while (c >= 0);
                this.peekc = c;
                return nextToken();
            } else {
                this.ttype = c5;
                return c5;
            }
        } else {
            this.ttype = -1;
            return -1;
        }
    }

    public void pushBack() {
        if (this.ttype != -4) {
            this.pushedBack = true;
        }
    }

    public int lineno() {
        return this.LINENO;
    }

    public String toString() {
        String ret;
        int i = this.ttype;
        if (i == -4) {
            ret = "NOTHING";
        } else if (i == -3) {
            ret = this.sval;
        } else if (i == -2) {
            ret = "n=" + this.nval;
        } else if (i == -1) {
            ret = "EOF";
        } else if (i == 10) {
            ret = "EOL";
        } else if (i >= 256 || (this.ctype[i] & 8) == 0) {
            char[] s = new char[3];
            s[2] = PatternTokenizer.SINGLE_QUOTE;
            s[0] = PatternTokenizer.SINGLE_QUOTE;
            s[1] = (char) this.ttype;
            ret = new String(s);
        } else {
            ret = this.sval;
        }
        return "Token[" + ret + "], line " + this.LINENO;
    }
}
