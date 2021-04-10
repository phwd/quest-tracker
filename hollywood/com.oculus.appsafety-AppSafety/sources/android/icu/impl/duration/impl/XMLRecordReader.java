package android.icu.impl.duration.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class XMLRecordReader implements RecordReader {
    private boolean atTag;
    private List<String> nameStack = new ArrayList();
    private Reader r;
    private String tag;

    public XMLRecordReader(Reader r2) {
        this.r = r2;
        if (getTag().startsWith("?xml")) {
            advance();
        }
        if (getTag().startsWith("!--")) {
            advance();
        }
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public boolean open(String title) {
        if (!getTag().equals(title)) {
            return false;
        }
        this.nameStack.add(title);
        advance();
        return true;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public boolean close() {
        int ix = this.nameStack.size() - 1;
        String tag2 = getTag();
        if (!tag2.equals("/" + this.nameStack.get(ix))) {
            return false;
        }
        this.nameStack.remove(ix);
        advance();
        return true;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public boolean bool(String name) {
        String s = string(name);
        if (s != null) {
            return "true".equals(s);
        }
        return false;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public boolean[] boolArray(String name) {
        String[] sa = stringArray(name);
        if (sa == null) {
            return null;
        }
        boolean[] result = new boolean[sa.length];
        for (int i = 0; i < sa.length; i++) {
            result[i] = "true".equals(sa[i]);
        }
        return result;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public char character(String name) {
        String s = string(name);
        if (s != null) {
            return s.charAt(0);
        }
        return 65535;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public char[] characterArray(String name) {
        String[] sa = stringArray(name);
        if (sa == null) {
            return null;
        }
        char[] result = new char[sa.length];
        for (int i = 0; i < sa.length; i++) {
            result[i] = sa[i].charAt(0);
        }
        return result;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public byte namedIndex(String name, String[] names) {
        String sa = string(name);
        if (sa == null) {
            return -1;
        }
        for (int i = 0; i < names.length; i++) {
            if (sa.equals(names[i])) {
                return (byte) i;
            }
        }
        return -1;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public byte[] namedIndexArray(String name, String[] names) {
        String[] sa = stringArray(name);
        if (sa == null) {
            return null;
        }
        byte[] result = new byte[sa.length];
        for (int i = 0; i < sa.length; i++) {
            String s = sa[i];
            int j = 0;
            while (true) {
                if (j >= names.length) {
                    result[i] = -1;
                    break;
                } else if (names[j].equals(s)) {
                    result[i] = (byte) j;
                    break;
                } else {
                    j++;
                }
            }
        }
        return result;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public String string(String name) {
        if (!match(name)) {
            return null;
        }
        String result = readData();
        if (match("/" + name)) {
            return result;
        }
        return null;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public String[] stringArray(String name) {
        if (!match(name + "List")) {
            return null;
        }
        List<String> list = new ArrayList<>();
        while (true) {
            String string = string(name);
            String s = string;
            if (string == null) {
                break;
            }
            if ("Null".equals(s)) {
                s = null;
            }
            list.add(s);
        }
        if (match("/" + name + "List")) {
            return (String[]) list.toArray(new String[list.size()]);
        }
        return null;
    }

    @Override // android.icu.impl.duration.impl.RecordReader
    public String[][] stringTable(String name) {
        if (!match(name + "Table")) {
            return null;
        }
        List<String[]> list = new ArrayList<>();
        while (true) {
            String[] sa = stringArray(name);
            if (sa == null) {
                break;
            }
            list.add(sa);
        }
        if (match("/" + name + "Table")) {
            return (String[][]) list.toArray(new String[list.size()][]);
        }
        return null;
    }

    private boolean match(String target) {
        if (!getTag().equals(target)) {
            return false;
        }
        advance();
        return true;
    }

    private String getTag() {
        if (this.tag == null) {
            this.tag = readNextTag();
        }
        return this.tag;
    }

    private void advance() {
        this.tag = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readData() {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.duration.impl.XMLRecordReader.readData():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readNextTag() {
        /*
            r5 = this;
            r0 = 0
        L_0x0001:
            boolean r1 = r5.atTag
            r2 = -1
            if (r1 != 0) goto L_0x0037
            int r0 = r5.readChar()
            r1 = 60
            if (r0 == r1) goto L_0x0032
            if (r0 != r2) goto L_0x0011
            goto L_0x0032
        L_0x0011:
            boolean r1 = android.icu.lang.UCharacter.isWhitespace(r0)
            if (r1 != 0) goto L_0x0001
            java.io.PrintStream r1 = java.lang.System.err
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unexpected non-whitespace character "
            r3.append(r4)
            java.lang.String r4 = java.lang.Integer.toHexString(r0)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.println(r3)
            goto L_0x0037
        L_0x0032:
            if (r0 != r1) goto L_0x0037
            r1 = 1
            r5.atTag = r1
        L_0x0037:
            boolean r1 = r5.atTag
            if (r1 == 0) goto L_0x0058
            r1 = 0
            r5.atTag = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L_0x0043:
            int r0 = r5.readChar()
            r3 = 62
            if (r0 == r3) goto L_0x0053
            if (r0 != r2) goto L_0x004e
            goto L_0x0053
        L_0x004e:
            char r3 = (char) r0
            r1.append(r3)
            goto L_0x0043
        L_0x0053:
            java.lang.String r2 = r1.toString()
            return r2
        L_0x0058:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.duration.impl.XMLRecordReader.readNextTag():java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public int readChar() {
        try {
            return this.r.read();
        } catch (IOException e) {
            return -1;
        }
    }
}
