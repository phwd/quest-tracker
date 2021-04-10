package com.android.org.kxml2.io;

import android.icu.impl.PatternTokenizer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import javax.xml.XMLConstants;
import org.xmlpull.v1.XmlSerializer;

public class KXmlSerializer implements XmlSerializer {
    private static final int BUFFER_LEN = 8192;
    private int auto;
    private int depth;
    private String[] elementStack = new String[12];
    private String encoding;
    private boolean[] indent = new boolean[4];
    private int mPos;
    private final char[] mText = new char[8192];
    private int[] nspCounts = new int[4];
    private String[] nspStack = new String[8];
    private boolean pending;
    private boolean unicode;
    private Writer writer;

    private void append(char c) throws IOException {
        if (this.mPos >= 8192) {
            flushBuffer();
        }
        char[] cArr = this.mText;
        int i = this.mPos;
        this.mPos = i + 1;
        cArr[i] = c;
    }

    private void append(String str, int i, int length) throws IOException {
        while (length > 0) {
            if (this.mPos == 8192) {
                flushBuffer();
            }
            int batch = 8192 - this.mPos;
            if (batch > length) {
                batch = length;
            }
            str.getChars(i, i + batch, this.mText, this.mPos);
            i += batch;
            length -= batch;
            this.mPos += batch;
        }
    }

    private void append(String str) throws IOException {
        append(str, 0, str.length());
    }

    private final void flushBuffer() throws IOException {
        int i = this.mPos;
        if (i > 0) {
            this.writer.write(this.mText, 0, i);
            this.writer.flush();
            this.mPos = 0;
        }
    }

    private final void check(boolean close) throws IOException {
        if (this.pending) {
            this.depth++;
            this.pending = false;
            boolean[] zArr = this.indent;
            int length = zArr.length;
            int i = this.depth;
            if (length <= i) {
                boolean[] hlp = new boolean[(i + 4)];
                System.arraycopy((Object) zArr, 0, (Object) hlp, 0, i);
                this.indent = hlp;
            }
            boolean[] zArr2 = this.indent;
            int i2 = this.depth;
            zArr2[i2] = zArr2[i2 - 1];
            int i3 = this.nspCounts[i2 - 1];
            while (true) {
                int[] iArr = this.nspCounts;
                int i4 = this.depth;
                if (i3 < iArr[i4]) {
                    append(" xmlns");
                    if (!this.nspStack[i3 * 2].isEmpty()) {
                        append(':');
                        append(this.nspStack[i3 * 2]);
                    } else if (getNamespace().isEmpty() && !this.nspStack[(i3 * 2) + 1].isEmpty()) {
                        throw new IllegalStateException("Cannot set default namespace for elements in no namespace");
                    }
                    append("=\"");
                    writeEscaped(this.nspStack[(i3 * 2) + 1], 34);
                    append('\"');
                    i3++;
                } else {
                    if (iArr.length <= i4 + 1) {
                        int[] hlp2 = new int[(i4 + 8)];
                        System.arraycopy((Object) iArr, 0, (Object) hlp2, 0, i4 + 1);
                        this.nspCounts = hlp2;
                    }
                    int[] iArr2 = this.nspCounts;
                    int i5 = this.depth;
                    iArr2[i5 + 1] = iArr2[i5];
                    if (close) {
                        append(" />");
                        return;
                    } else {
                        append('>');
                        return;
                    }
                }
            }
        }
    }

    private final void writeEscaped(String s, int quot) throws IOException {
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '\t' || c == '\n' || c == '\r') {
                if (quot == -1) {
                    append(c);
                } else {
                    append("&#" + ((int) c) + ';');
                }
            } else if (c == '&') {
                append("&amp;");
            } else if (c == '<') {
                append("&lt;");
            } else if (c == '>') {
                append("&gt;");
            } else if (c == quot) {
                append(c == '\"' ? "&quot;" : "&apos;");
            } else {
                if ((c >= ' ' && c <= 55295) || (c >= 57344 && c <= 65533)) {
                    if (this.unicode || c < 127) {
                        append(c);
                    } else {
                        append("&#" + ((int) c) + ";");
                    }
                } else if (!Character.isHighSurrogate(c) || i >= s.length() - 1) {
                    reportInvalidCharacter(c);
                } else {
                    writeSurrogate(c, s.charAt(i + 1));
                    i++;
                }
            }
            i++;
        }
    }

    private static void reportInvalidCharacter(char ch) {
        throw new IllegalArgumentException("Illegal character (U+" + Integer.toHexString(ch) + ")");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void docdecl(String dd) throws IOException {
        append("<!DOCTYPE");
        append(dd);
        append('>');
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void endDocument() throws IOException {
        while (true) {
            int i = this.depth;
            if (i > 0) {
                String[] strArr = this.elementStack;
                endTag(strArr[(i * 3) - 3], strArr[(i * 3) - 1]);
            } else {
                flush();
                return;
            }
        }
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void entityRef(String name) throws IOException {
        check(false);
        append('&');
        append(name);
        append(';');
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public boolean getFeature(String name) {
        if ("http://xmlpull.org/v1/doc/features.html#indent-output".equals(name)) {
            return this.indent[this.depth];
        }
        return false;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getPrefix(String namespace, boolean create) {
        try {
            return getPrefix(namespace, false, create);
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    private final String getPrefix(String namespace, boolean includeDefault, boolean create) throws IOException {
        String prefix;
        for (int i = (this.nspCounts[this.depth + 1] * 2) - 2; i >= 0; i -= 2) {
            if (this.nspStack[i + 1].equals(namespace) && (includeDefault || !this.nspStack[i].isEmpty())) {
                String cand = this.nspStack[i];
                int j = i + 2;
                while (true) {
                    if (j >= this.nspCounts[this.depth + 1] * 2) {
                        break;
                    } else if (this.nspStack[j].equals(cand)) {
                        cand = null;
                        break;
                    } else {
                        j++;
                    }
                }
                if (cand != null) {
                    return cand;
                }
            }
        }
        if (!create) {
            return null;
        }
        if (namespace.isEmpty()) {
            prefix = "";
        } else {
            do {
                StringBuilder sb = new StringBuilder();
                sb.append("n");
                int i2 = this.auto;
                this.auto = i2 + 1;
                sb.append(i2);
                prefix = sb.toString();
                int i3 = (this.nspCounts[this.depth + 1] * 2) - 2;
                while (true) {
                    if (i3 < 0) {
                        break;
                    } else if (prefix.equals(this.nspStack[i3])) {
                        prefix = null;
                        continue;
                        break;
                    } else {
                        i3 -= 2;
                    }
                }
            } while (prefix == null);
        }
        boolean p = this.pending;
        this.pending = false;
        setPrefix(prefix, namespace);
        this.pending = p;
        return prefix;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public Object getProperty(String name) {
        throw new RuntimeException("Unsupported property");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void ignorableWhitespace(String s) throws IOException {
        text(s);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setFeature(String name, boolean value) {
        if ("http://xmlpull.org/v1/doc/features.html#indent-output".equals(name)) {
            this.indent[this.depth] = value;
            return;
        }
        throw new RuntimeException("Unsupported Feature");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setProperty(String name, Object value) {
        throw new RuntimeException("Unsupported Property:" + value);
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setPrefix(String prefix, String namespace) throws IOException {
        check(false);
        if (prefix == null) {
            prefix = "";
        }
        if (namespace == null) {
            namespace = "";
        }
        if (!prefix.equals(getPrefix(namespace, true, false))) {
            int[] iArr = this.nspCounts;
            int i = this.depth + 1;
            int i2 = iArr[i];
            iArr[i] = i2 + 1;
            int pos = i2 << 1;
            String[] strArr = this.nspStack;
            if (strArr.length < pos + 1) {
                String[] hlp = new String[(strArr.length + 16)];
                System.arraycopy(strArr, 0, hlp, 0, pos);
                this.nspStack = hlp;
            }
            String[] strArr2 = this.nspStack;
            strArr2[pos] = prefix;
            strArr2[pos + 1] = namespace;
        }
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(Writer writer2) {
        this.writer = writer2;
        int[] iArr = this.nspCounts;
        iArr[0] = 2;
        iArr[1] = 2;
        String[] strArr = this.nspStack;
        strArr[0] = "";
        strArr[1] = "";
        strArr[2] = XMLConstants.XML_NS_PREFIX;
        strArr[3] = "http://www.w3.org/XML/1998/namespace";
        this.pending = false;
        this.auto = 0;
        this.depth = 0;
        this.unicode = false;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void setOutput(OutputStream os, String encoding2) throws IOException {
        OutputStreamWriter outputStreamWriter;
        if (os != null) {
            if (encoding2 == null) {
                outputStreamWriter = new OutputStreamWriter(os);
            } else {
                outputStreamWriter = new OutputStreamWriter(os, encoding2);
            }
            setOutput(outputStreamWriter);
            this.encoding = encoding2;
            if (encoding2 != null && encoding2.toLowerCase(Locale.US).startsWith("utf")) {
                this.unicode = true;
                return;
            }
            return;
        }
        throw new IllegalArgumentException("os == null");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void startDocument(String encoding2, Boolean standalone) throws IOException {
        append("<?xml version='1.0' ");
        if (encoding2 != null) {
            this.encoding = encoding2;
            if (encoding2.toLowerCase(Locale.US).startsWith("utf")) {
                this.unicode = true;
            }
        }
        if (this.encoding != null) {
            append("encoding='");
            append(this.encoding);
            append("' ");
        }
        if (standalone != null) {
            append("standalone='");
            append(standalone.booleanValue() ? "yes" : "no");
            append("' ");
        }
        append("?>");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer startTag(String namespace, String name) throws IOException {
        String prefix;
        check(false);
        if (this.indent[this.depth]) {
            append("\r\n");
            for (int i = 0; i < this.depth; i++) {
                append("  ");
            }
        }
        int esp = this.depth * 3;
        String[] strArr = this.elementStack;
        if (strArr.length < esp + 3) {
            String[] hlp = new String[(strArr.length + 12)];
            System.arraycopy(strArr, 0, hlp, 0, esp);
            this.elementStack = hlp;
        }
        if (namespace == null) {
            prefix = "";
        } else {
            prefix = getPrefix(namespace, true, true);
        }
        if (namespace != null && namespace.isEmpty()) {
            for (int i2 = this.nspCounts[this.depth]; i2 < this.nspCounts[this.depth + 1]; i2++) {
                if (this.nspStack[i2 * 2].isEmpty() && !this.nspStack[(i2 * 2) + 1].isEmpty()) {
                    throw new IllegalStateException("Cannot set default namespace for elements in no namespace");
                }
            }
        }
        String[] strArr2 = this.elementStack;
        int esp2 = esp + 1;
        strArr2[esp] = namespace;
        strArr2[esp2] = prefix;
        strArr2[esp2 + 1] = name;
        append('<');
        if (!prefix.isEmpty()) {
            append(prefix);
            append(':');
        }
        append(name);
        this.pending = true;
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer attribute(String namespace, String name, String value) throws IOException {
        String prefix;
        if (this.pending) {
            if (namespace == null) {
                namespace = "";
            }
            if (namespace.isEmpty()) {
                prefix = "";
            } else {
                prefix = getPrefix(namespace, false, true);
            }
            append(' ');
            if (!prefix.isEmpty()) {
                append(prefix);
                append(':');
            }
            append(name);
            append('=');
            char q = '\"';
            if (value.indexOf(34) != -1) {
                q = PatternTokenizer.SINGLE_QUOTE;
            }
            append(q);
            writeEscaped(value, q);
            append(q);
            return this;
        }
        throw new IllegalStateException("illegal position for attribute");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void flush() throws IOException {
        check(false);
        flushBuffer();
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer endTag(String namespace, String name) throws IOException {
        if (!this.pending) {
            this.depth--;
        }
        if ((namespace != null || this.elementStack[this.depth * 3] == null) && ((namespace == null || namespace.equals(this.elementStack[this.depth * 3])) && this.elementStack[(this.depth * 3) + 2].equals(name))) {
            if (this.pending) {
                check(true);
                this.depth--;
            } else {
                if (this.indent[this.depth + 1]) {
                    append("\r\n");
                    for (int i = 0; i < this.depth; i++) {
                        append("  ");
                    }
                }
                append("</");
                String prefix = this.elementStack[(this.depth * 3) + 1];
                if (!prefix.isEmpty()) {
                    append(prefix);
                    append(':');
                }
                append(name);
                append('>');
            }
            int[] iArr = this.nspCounts;
            int i2 = this.depth;
            iArr[i2 + 1] = iArr[i2];
            return this;
        }
        throw new IllegalArgumentException("</{" + namespace + "}" + name + "> does not match start");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getNamespace() {
        if (getDepth() == 0) {
            return null;
        }
        return this.elementStack[(getDepth() * 3) - 3];
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public String getName() {
        if (getDepth() == 0) {
            return null;
        }
        return this.elementStack[(getDepth() * 3) - 1];
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public int getDepth() {
        return this.pending ? this.depth + 1 : this.depth;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(String text) throws IOException {
        check(false);
        this.indent[this.depth] = false;
        writeEscaped(text, -1);
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public XmlSerializer text(char[] text, int start, int len) throws IOException {
        text(new String(text, start, len));
        return this;
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void cdsect(String data) throws IOException {
        check(false);
        String data2 = data.replace("]]>", "]]]]><![CDATA[>");
        append("<![CDATA[");
        int i = 0;
        while (i < data2.length()) {
            char ch = data2.charAt(i);
            if ((ch >= ' ' && ch <= 55295) || ch == '\t' || ch == '\n' || ch == '\r' || (ch >= 57344 && ch <= 65533)) {
                append(ch);
            } else if (!Character.isHighSurrogate(ch) || i >= data2.length() - 1) {
                reportInvalidCharacter(ch);
            } else {
                append("]]>");
                i++;
                writeSurrogate(ch, data2.charAt(i));
                append("<![CDATA[");
            }
            i++;
        }
        append("]]>");
    }

    private void writeSurrogate(char high, char low) throws IOException {
        if (Character.isLowSurrogate(low)) {
            int codePoint = Character.toCodePoint(high, low);
            append("&#" + codePoint + ";");
            return;
        }
        throw new IllegalArgumentException("Bad surrogate pair (U+" + Integer.toHexString(high) + " U+" + Integer.toHexString(low) + ")");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void comment(String comment) throws IOException {
        check(false);
        append("<!--");
        append(comment);
        append("-->");
    }

    @Override // org.xmlpull.v1.XmlSerializer
    public void processingInstruction(String pi) throws IOException {
        check(false);
        append("<?");
        append(pi);
        append("?>");
    }
}
