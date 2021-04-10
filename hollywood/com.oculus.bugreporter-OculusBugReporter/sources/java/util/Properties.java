package java.util;

import android.icu.impl.PatternTokenizer;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

public class Properties extends Hashtable<Object, Object> {
    private static final char[] hexDigit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final long serialVersionUID = 4112578634029874840L;
    protected Properties defaults;

    public Properties() {
        this(null);
    }

    public Properties(Properties defaults2) {
        this.defaults = defaults2;
    }

    public synchronized Object setProperty(String key, String value) {
        return put(key, value);
    }

    public synchronized void load(Reader reader) throws IOException {
        load0(new LineReader(reader));
    }

    public synchronized void load(InputStream inStream) throws IOException {
        load0(new LineReader(inStream));
    }

    private void load0(LineReader lr) throws IOException {
        char[] convtBuf = new char[1024];
        while (true) {
            int limit = lr.readLine();
            if (limit >= 0) {
                int keyLen = 0;
                int valueStart = limit;
                boolean hasSep = false;
                boolean precedingBackslash = false;
                while (true) {
                    boolean z = false;
                    if (keyLen < limit) {
                        char c = lr.lineBuf[keyLen];
                        if ((c != '=' && c != ':') || precedingBackslash) {
                            if (Character.isWhitespace(c) && !precedingBackslash) {
                                valueStart = keyLen + 1;
                                break;
                            }
                            if (c == '\\') {
                                if (!precedingBackslash) {
                                    z = true;
                                }
                                precedingBackslash = z;
                            } else {
                                precedingBackslash = false;
                            }
                            keyLen++;
                        } else {
                            valueStart = keyLen + 1;
                            hasSep = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                while (valueStart < limit) {
                    char c2 = lr.lineBuf[valueStart];
                    if (!Character.isWhitespace(c2)) {
                        if (hasSep || (c2 != '=' && c2 != ':')) {
                            break;
                        }
                        hasSep = true;
                    }
                    valueStart++;
                }
                put(loadConvert(lr.lineBuf, 0, keyLen, convtBuf), loadConvert(lr.lineBuf, valueStart, limit - valueStart, convtBuf));
            } else {
                return;
            }
        }
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

        public LineReader(InputStream inStream2) {
            this.lineBuf = new char[1024];
            this.inLimit = 0;
            this.inOff = 0;
            this.inStream = inStream2;
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
        public int readLine() throws IOException {
            char c;
            int i;
            int i2;
            int len = 0;
            boolean skipWhiteSpace = true;
            boolean isCommentLine = false;
            boolean isNewLine = true;
            boolean appendedLineBegin = false;
            boolean precedingBackslash = false;
            boolean skipLF = false;
            while (true) {
                boolean z = false;
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
                        if (len == 0 || isCommentLine) {
                            return -1;
                        }
                        if (precedingBackslash) {
                            return len - 1;
                        }
                        return len;
                    }
                }
                if (this.inStream != null) {
                    byte[] bArr = this.inByteBuf;
                    int i3 = this.inOff;
                    this.inOff = i3 + 1;
                    c = (char) (bArr[i3] & 255);
                } else {
                    char[] cArr = this.inCharBuf;
                    int i4 = this.inOff;
                    this.inOff = i4 + 1;
                    c = cArr[i4];
                }
                if (skipLF) {
                    skipLF = false;
                    if (c == '\n') {
                        continue;
                    }
                }
                if (skipWhiteSpace) {
                    if (!Character.isWhitespace(c) && (appendedLineBegin || !(c == '\r' || c == '\n'))) {
                        skipWhiteSpace = false;
                        appendedLineBegin = false;
                    }
                }
                if (isNewLine) {
                    isNewLine = false;
                    if (c == '#' || c == '!') {
                        isCommentLine = true;
                    }
                }
                if (c != '\n' && c != '\r') {
                    char[] cArr2 = this.lineBuf;
                    int len2 = len + 1;
                    cArr2[len] = c;
                    if (len2 == cArr2.length) {
                        int newLength = cArr2.length * 2;
                        if (newLength < 0) {
                            newLength = Integer.MAX_VALUE;
                        }
                        char[] buf = new char[newLength];
                        char[] cArr3 = this.lineBuf;
                        System.arraycopy((Object) cArr3, 0, (Object) buf, 0, cArr3.length);
                        this.lineBuf = buf;
                    }
                    if (c == '\\') {
                        if (!precedingBackslash) {
                            z = true;
                        }
                        precedingBackslash = z;
                        len = len2;
                    } else {
                        precedingBackslash = false;
                        len = len2;
                    }
                } else if (isCommentLine || len == 0) {
                    isCommentLine = false;
                    isNewLine = true;
                    skipWhiteSpace = true;
                    len = 0;
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
                            if (precedingBackslash) {
                                return len - 1;
                            }
                            return len;
                        }
                    }
                    if (!precedingBackslash) {
                        return len;
                    }
                    len--;
                    skipWhiteSpace = true;
                    appendedLineBegin = true;
                    precedingBackslash = false;
                    if (c == '\r') {
                        skipLF = true;
                    }
                }
            }
        }
    }

    /* JADX INFO: Multiple debug info for r10v2 char: [D('off' int), D('aChar' char)] */
    /* JADX INFO: Multiple debug info for r4v2 int: [D('outLen' int), D('off' int)] */
    /* JADX INFO: Multiple debug info for r5v2 int: [D('outLen' int), D('i' int)] */
    private String loadConvert(char[] in, int off, int len, char[] convtBuf) {
        int value;
        if (convtBuf.length < len) {
            int newLen = len * 2;
            if (newLen < 0) {
                newLen = Integer.MAX_VALUE;
            }
            convtBuf = new char[newLen];
        }
        int outLen = 0;
        int end = off + len;
        while (off < end) {
            int off2 = off + 1;
            char aChar = in[off];
            if (aChar == '\\') {
                int off3 = off2 + 1;
                char aChar2 = in[off2];
                if (aChar2 == 'u') {
                    int value2 = 0;
                    int i = 0;
                    while (i < 4) {
                        int off4 = off3 + 1;
                        char aChar3 = in[off3];
                        switch (aChar3) {
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
                                value = ((value2 << 4) + aChar3) - 48;
                                break;
                            default:
                                switch (aChar3) {
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                        value = (((value2 << 4) + 10) + aChar3) - 65;
                                        break;
                                    default:
                                        switch (aChar3) {
                                            default:
                                                throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
                                            case 'a':
                                            case 'b':
                                            case 'c':
                                            case 'd':
                                            case 'e':
                                            case 'f':
                                                value = (((value2 << 4) + 10) + aChar3) - 97;
                                                break;
                                        }
                                }
                        }
                        value2 = value;
                        i++;
                        off3 = off4;
                    }
                    convtBuf[outLen] = (char) value2;
                    off = off3;
                    outLen++;
                } else {
                    if (aChar2 == 't') {
                        aChar2 = '\t';
                    } else if (aChar2 == 'r') {
                        aChar2 = '\r';
                    } else if (aChar2 == 'n') {
                        aChar2 = '\n';
                    } else if (aChar2 == 'f') {
                        aChar2 = '\f';
                    }
                    convtBuf[outLen] = aChar2;
                    outLen++;
                    off = off3;
                }
            } else {
                convtBuf[outLen] = aChar;
                off = off2;
                outLen++;
            }
        }
        return new String(convtBuf, 0, outLen);
    }

    private String saveConvert(String theString, boolean escapeSpace, boolean escapeUnicode) {
        int len = theString.length();
        int bufLen = len * 2;
        if (bufLen < 0) {
            bufLen = Integer.MAX_VALUE;
        }
        StringBuffer outBuffer = new StringBuffer(bufLen);
        for (int x = 0; x < len; x++) {
            char aChar = theString.charAt(x);
            if (aChar <= '=' || aChar >= 127) {
                if (aChar == '\t') {
                    outBuffer.append(PatternTokenizer.BACK_SLASH);
                    outBuffer.append('t');
                } else if (aChar == '\n') {
                    outBuffer.append(PatternTokenizer.BACK_SLASH);
                    outBuffer.append('n');
                } else if (aChar == '\f') {
                    outBuffer.append(PatternTokenizer.BACK_SLASH);
                    outBuffer.append('f');
                } else if (aChar == '\r') {
                    outBuffer.append(PatternTokenizer.BACK_SLASH);
                    outBuffer.append('r');
                } else if (aChar == ' ') {
                    if (x == 0 || escapeSpace) {
                        outBuffer.append(PatternTokenizer.BACK_SLASH);
                    }
                    outBuffer.append(' ');
                } else if (aChar == '!' || aChar == '#' || aChar == ':' || aChar == '=') {
                    outBuffer.append(PatternTokenizer.BACK_SLASH);
                    outBuffer.append(aChar);
                } else if ((aChar < ' ' || aChar > '~') && escapeUnicode) {
                    outBuffer.append(PatternTokenizer.BACK_SLASH);
                    outBuffer.append('u');
                    outBuffer.append(toHex((aChar >> '\f') & 15));
                    outBuffer.append(toHex((aChar >> '\b') & 15));
                    outBuffer.append(toHex((aChar >> 4) & 15));
                    outBuffer.append(toHex(aChar & 15));
                } else {
                    outBuffer.append(aChar);
                }
            } else if (aChar == '\\') {
                outBuffer.append(PatternTokenizer.BACK_SLASH);
                outBuffer.append(PatternTokenizer.BACK_SLASH);
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

    private static void writeComments(BufferedWriter bw, String comments) throws IOException {
        bw.write("#");
        int len = comments.length();
        int current = 0;
        int last = 0;
        char[] uu = new char[6];
        uu[0] = PatternTokenizer.BACK_SLASH;
        uu[1] = 'u';
        while (current < len) {
            char c = comments.charAt(current);
            if (c > 255 || c == '\n' || c == '\r') {
                if (last != current) {
                    bw.write(comments.substring(last, current));
                }
                if (c > 255) {
                    uu[2] = toHex((c >> '\f') & 15);
                    uu[3] = toHex((c >> '\b') & 15);
                    uu[4] = toHex((c >> 4) & 15);
                    uu[5] = toHex(c & 15);
                    bw.write(new String(uu));
                } else {
                    bw.newLine();
                    if (c == '\r' && current != len - 1 && comments.charAt(current + 1) == '\n') {
                        current++;
                    }
                    if (current == len - 1 || !(comments.charAt(current + 1) == '#' || comments.charAt(current + 1) == '!')) {
                        bw.write("#");
                    }
                }
                last = current + 1;
            }
            current++;
        }
        if (last != current) {
            bw.write(comments.substring(last, current));
        }
        bw.newLine();
    }

    @Deprecated
    public void save(OutputStream out, String comments) {
        try {
            store(out, comments);
        } catch (IOException e) {
        }
    }

    public void store(Writer writer, String comments) throws IOException {
        BufferedWriter bufferedWriter;
        if (writer instanceof BufferedWriter) {
            bufferedWriter = (BufferedWriter) writer;
        } else {
            bufferedWriter = new BufferedWriter(writer);
        }
        store0(bufferedWriter, comments, false);
    }

    public void store(OutputStream out, String comments) throws IOException {
        store0(new BufferedWriter(new OutputStreamWriter(out, "8859_1")), comments, true);
    }

    private void store0(BufferedWriter bw, String comments, boolean escUnicode) throws IOException {
        if (comments != null) {
            writeComments(bw, comments);
        }
        bw.write("#" + new Date().toString());
        bw.newLine();
        synchronized (this) {
            Enumeration<?> e = keys();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String key2 = saveConvert(key, true, escUnicode);
                String val = saveConvert((String) get(key), false, escUnicode);
                bw.write(key2 + "=" + val);
                bw.newLine();
            }
        }
        bw.flush();
    }

    public synchronized void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
        XMLUtils.load(this, (InputStream) Objects.requireNonNull(in));
        in.close();
    }

    public void storeToXML(OutputStream os, String comment) throws IOException {
        storeToXML(os, comment, "UTF-8");
    }

    public void storeToXML(OutputStream os, String comment, String encoding) throws IOException {
        XMLUtils.save(this, (OutputStream) Objects.requireNonNull(os), comment, (String) Objects.requireNonNull(encoding));
    }

    public String getProperty(String key) {
        Properties properties;
        Object oval = super.get(key);
        String sval = oval instanceof String ? (String) oval : null;
        return (sval != null || (properties = this.defaults) == null) ? sval : properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        String val = getProperty(key);
        return val == null ? defaultValue : val;
    }

    public Enumeration<?> propertyNames() {
        Hashtable<String, Object> h = new Hashtable<>();
        enumerate(h);
        return h.keys();
    }

    public Set<String> stringPropertyNames() {
        Hashtable<String, String> h = new Hashtable<>();
        enumerateStringProperties(h);
        return h.keySet();
    }

    public void list(PrintStream out) {
        out.println("-- listing properties --");
        Hashtable<String, Object> h = new Hashtable<>();
        enumerate(h);
        Enumeration<String> e = h.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            String val = (String) h.get(key);
            if (val.length() > 40) {
                val = val.substring(0, 37) + "...";
            }
            out.println(key + "=" + val);
        }
    }

    public void list(PrintWriter out) {
        out.println("-- listing properties --");
        Hashtable<String, Object> h = new Hashtable<>();
        enumerate(h);
        Enumeration<String> e = h.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            String val = (String) h.get(key);
            if (val.length() > 40) {
                val = val.substring(0, 37) + "...";
            }
            out.println(key + "=" + val);
        }
    }

    private synchronized void enumerate(Hashtable<String, Object> h) {
        if (this.defaults != null) {
            this.defaults.enumerate(h);
        }
        Enumeration<?> e = keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            h.put(key, get(key));
        }
    }

    private synchronized void enumerateStringProperties(Hashtable<String, String> h) {
        if (this.defaults != null) {
            this.defaults.enumerateStringProperties(h);
        }
        Enumeration<?> e = keys();
        while (e.hasMoreElements()) {
            Object k = e.nextElement();
            Object v = get(k);
            if ((k instanceof String) && (v instanceof String)) {
                h.put((String) k, (String) v);
            }
        }
    }

    private static char toHex(int nibble) {
        return hexDigit[nibble & 15];
    }
}
