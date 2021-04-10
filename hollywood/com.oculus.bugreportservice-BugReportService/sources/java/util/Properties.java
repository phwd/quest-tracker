package java.util;

import java.io.InputStream;
import java.io.Reader;

public class Properties extends Hashtable {
    private static final char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final long serialVersionUID = 4112578634029874840L;
    protected Properties defaults;

    public Properties() {
        this(null);
    }

    public Properties(Properties properties) {
        this.defaults = properties;
    }

    public synchronized void load(Reader reader) {
        load0(new LineReader(reader));
    }

    public synchronized void load(InputStream inputStream) {
        load0(new LineReader(inputStream));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0039, code lost:
        r8 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void load0(java.util.Properties.LineReader r12) {
        /*
            r11 = this;
            r0 = 1024(0x400, float:1.435E-42)
            char[] r0 = new char[r0]
            int r1 = r12.readLine()
            if (r1 < 0) goto L_0x0057
            r2 = 0
            r3 = r2
            r4 = r3
        L_0x000d:
            r5 = 58
            r6 = 61
            r7 = 1
            if (r3 >= r1) goto L_0x0038
            char[] r8 = r12.lineBuf
            char r8 = r8[r3]
            if (r8 == r6) goto L_0x001c
            if (r8 != r5) goto L_0x0022
        L_0x001c:
            if (r4 != 0) goto L_0x0022
            int r4 = r3 + 1
            r8 = r7
            goto L_0x003a
        L_0x0022:
            boolean r9 = java.lang.Character.isWhitespace(r8)
            if (r9 == 0) goto L_0x002d
            if (r4 != 0) goto L_0x002d
            int r4 = r3 + 1
            goto L_0x0039
        L_0x002d:
            r5 = 92
            if (r8 != r5) goto L_0x0034
            r4 = r4 ^ 1
            goto L_0x0035
        L_0x0034:
            r4 = r2
        L_0x0035:
            int r3 = r3 + 1
            goto L_0x000d
        L_0x0038:
            r4 = r1
        L_0x0039:
            r8 = r2
        L_0x003a:
            if (r4 >= r1) goto L_0x0050
            char[] r9 = r12.lineBuf
            char r9 = r9[r4]
            boolean r10 = java.lang.Character.isWhitespace(r9)
            if (r10 != 0) goto L_0x004d
            if (r8 != 0) goto L_0x0050
            if (r9 == r6) goto L_0x004c
            if (r9 != r5) goto L_0x0050
        L_0x004c:
            r8 = r7
        L_0x004d:
            int r4 = r4 + 1
            goto L_0x003a
        L_0x0050:
            char[] r12 = r12.lineBuf
            r11.loadConvert(r12, r2, r3, r0)
            r11 = 0
            throw r11
        L_0x0057:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.Properties.load0(java.util.Properties$LineReader):void");
    }

    /* access modifiers changed from: package-private */
    public class LineReader {
        byte[] inByteBuf;
        char[] inCharBuf;
        int inLimit;
        int inOff;
        InputStream inStream;
        char[] lineBuf;
        Reader reader;

        public LineReader(InputStream inputStream) {
            this.lineBuf = new char[1024];
            this.inLimit = 0;
            this.inOff = 0;
            this.inStream = inputStream;
            this.inByteBuf = new byte[8192];
        }

        public LineReader(Reader reader2) {
            this.lineBuf = new char[1024];
            this.inLimit = 0;
            this.inOff = 0;
            this.reader = reader2;
            this.inCharBuf = new char[8192];
        }

        /* access modifiers changed from: package-private */
        public int readLine() {
            char c;
            int i;
            int i2;
            boolean z = true;
            boolean z2 = true;
            boolean z3 = false;
            int i3 = 0;
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            while (true) {
                if (this.inOff >= this.inLimit) {
                    InputStream inputStream = this.inStream;
                    if (inputStream == null) {
                        i2 = this.reader.read(this.inCharBuf);
                    } else {
                        i2 = inputStream.read(this.inByteBuf);
                    }
                    this.inLimit = i2;
                    this.inOff = 0;
                    if (this.inLimit <= 0) {
                        if (i3 == 0 || z4) {
                            return -1;
                        }
                        return z6 ? i3 - 1 : i3;
                    }
                }
                if (this.inStream != null) {
                    byte[] bArr = this.inByteBuf;
                    int i4 = this.inOff;
                    this.inOff = i4 + 1;
                    c = (char) (bArr[i4] & 255);
                } else {
                    char[] cArr = this.inCharBuf;
                    int i5 = this.inOff;
                    this.inOff = i5 + 1;
                    c = cArr[i5];
                }
                if (z3) {
                    z3 = false;
                    if (c == '\n') {
                        continue;
                    }
                }
                if (z) {
                    if (!Character.isWhitespace(c) && (z5 || !(c == '\r' || c == '\n'))) {
                        z = false;
                        z5 = false;
                    }
                }
                if (z2) {
                    if (c == '#' || c == '!') {
                        z4 = true;
                        z2 = false;
                    } else {
                        z2 = false;
                    }
                }
                if (c != '\n' && c != '\r') {
                    char[] cArr2 = this.lineBuf;
                    int i6 = i3 + 1;
                    cArr2[i3] = c;
                    if (i6 == cArr2.length) {
                        int length = cArr2.length * 2;
                        if (length < 0) {
                            length = Integer.MAX_VALUE;
                        }
                        char[] cArr3 = new char[length];
                        char[] cArr4 = this.lineBuf;
                        System.arraycopy((Object) cArr4, 0, (Object) cArr3, 0, cArr4.length);
                        this.lineBuf = cArr3;
                    }
                    z6 = c == '\\' ? !z6 : false;
                    i3 = i6;
                } else if (z4 || i3 == 0) {
                    z = true;
                    z2 = true;
                    i3 = 0;
                    z4 = false;
                } else {
                    if (this.inOff >= this.inLimit) {
                        InputStream inputStream2 = this.inStream;
                        if (inputStream2 == null) {
                            i = this.reader.read(this.inCharBuf);
                        } else {
                            i = inputStream2.read(this.inByteBuf);
                        }
                        this.inLimit = i;
                        this.inOff = 0;
                        if (this.inLimit <= 0) {
                            return z6 ? i3 - 1 : i3;
                        }
                    }
                    if (!z6) {
                        return i3;
                    }
                    i3--;
                    if (c == '\r') {
                        z3 = true;
                        z = true;
                    } else {
                        z = true;
                    }
                    z5 = z;
                    z6 = false;
                }
            }
        }
    }

    private String loadConvert(char[] cArr, int i, int i2, char[] cArr2) {
        int i3;
        if (cArr2.length < i2) {
            int i4 = i2 * 2;
            if (i4 < 0) {
                i4 = Integer.MAX_VALUE;
            }
            cArr2 = new char[i4];
        }
        int i5 = i2 + i;
        int i6 = 0;
        while (i < i5) {
            int i7 = i + 1;
            char c = cArr[i];
            if (c == '\\') {
                i = i7 + 1;
                char c2 = cArr[i7];
                if (c2 == 'u') {
                    int i8 = 0;
                    int i9 = i;
                    int i10 = 0;
                    while (i10 < 4) {
                        int i11 = i9 + 1;
                        char c3 = cArr[i9];
                        switch (c3) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                i8 = ((i8 << 4) + c3) - 48;
                                break;
                            default:
                                switch (c3) {
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                        i8 = (((i8 << 4) + 10) + c3) - 65;
                                        break;
                                    default:
                                        switch (c3) {
                                            default:
                                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                                            case 'a':
                                            case 'b':
                                            case 'c':
                                            case 'd':
                                            case 'e':
                                            case 'f':
                                                i8 = (((i8 << 4) + 10) + c3) - 97;
                                                break;
                                        }
                                }
                        }
                        i10++;
                        i9 = i11;
                    }
                    cArr2[i6] = (char) i8;
                    i6++;
                    i = i9;
                } else {
                    if (c2 == 't') {
                        c2 = '\t';
                    } else if (c2 == 'r') {
                        c2 = '\r';
                    } else if (c2 == 'n') {
                        c2 = '\n';
                    } else if (c2 == 'f') {
                        c2 = '\f';
                    }
                    i3 = i6 + 1;
                    cArr2[i6] = c2;
                }
            } else {
                i3 = i6 + 1;
                cArr2[i6] = c;
                i = i7;
            }
            i6 = i3;
        }
        new String(cArr2, 0, i6);
        throw null;
    }

    public String getProperty(String str) {
        Properties properties;
        Object obj = super.get(str);
        String str2 = obj instanceof String ? (String) obj : null;
        return (str2 != null || (properties = this.defaults) == null) ? str2 : properties.getProperty(str);
    }

    public String getProperty(String str, String str2) {
        String property = getProperty(str);
        return property == null ? str2 : property;
    }

    public Enumeration propertyNames() {
        Hashtable hashtable = new Hashtable();
        enumerate(hashtable);
        return hashtable.keys();
    }

    private synchronized void enumerate(Hashtable hashtable) {
        if (this.defaults != null) {
            this.defaults.enumerate(hashtable);
        }
        Enumeration keys = keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            hashtable.put(str, get(str));
        }
    }
}
