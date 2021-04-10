package sun.security.x509;

import android.icu.impl.PatternTokenizer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.security.AccessController;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import sun.security.action.GetBooleanAction;
import sun.security.pkcs.PKCS9Attribute;
import sun.security.util.Debug;
import sun.security.util.DerEncoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class AVA implements DerEncoder {
    static final int DEFAULT = 1;
    private static final boolean PRESERVE_OLD_DC_ENCODING = ((Boolean) AccessController.doPrivileged(new GetBooleanAction("com.sun.security.preserveOldDCEncoding"))).booleanValue();
    static final int RFC1779 = 2;
    static final int RFC2253 = 3;
    private static final Debug debug = Debug.getInstance(X509CertImpl.NAME, "\t[AVA]");
    private static final String escapedDefault = ",+<>;\"";
    private static final String hexDigits = "0123456789ABCDEF";
    private static final String specialChars1779 = ",=\n+<>#;\\\"";
    private static final String specialChars2253 = ",=+<>#;\\\"";
    private static final String specialCharsDefault = ",=\n+<>#;\\\" ";
    final ObjectIdentifier oid;
    final DerValue value;

    public AVA(ObjectIdentifier type, DerValue val) {
        if (type == null || val == null) {
            throw new NullPointerException();
        }
        this.oid = type;
        this.value = val;
    }

    AVA(Reader in) throws IOException {
        this(in, 1);
    }

    AVA(Reader in, Map<String, String> keywordMap) throws IOException {
        this(in, 1, keywordMap);
    }

    AVA(Reader in, int format) throws IOException {
        this(in, format, Collections.emptyMap());
    }

    AVA(Reader in, int format, Map<String, String> keywordMap) throws IOException {
        int c;
        StringBuilder temp = new StringBuilder();
        while (true) {
            int c2 = readChar(in, "Incorrect AVA format");
            if (c2 == 61) {
                break;
            }
            temp.append((char) c2);
        }
        this.oid = AVAKeyword.getOID(temp.toString(), format, keywordMap);
        temp.setLength(0);
        if (format != 3) {
            while (true) {
                c = in.read();
                if (c != 32 && c != 10) {
                    break;
                }
            }
        } else {
            c = in.read();
            if (c == 32) {
                throw new IOException("Incorrect AVA RFC2253 format - leading space must be escaped");
            }
        }
        if (c == -1) {
            this.value = new DerValue("");
        } else if (c == 35) {
            this.value = parseHexString(in, format);
        } else if (c != 34 || format == 3) {
            this.value = parseString(in, c, format, temp);
        } else {
            this.value = parseQuotedString(in, temp);
        }
    }

    public ObjectIdentifier getObjectIdentifier() {
        return this.oid;
    }

    public DerValue getDerValue() {
        return this.value;
    }

    public String getValueString() {
        try {
            String s = this.value.getAsString();
            if (s != null) {
                return s;
            }
            throw new RuntimeException("AVA string is null");
        } catch (IOException e) {
            throw new RuntimeException("AVA error: " + ((Object) e), e);
        }
    }

    private static DerValue parseHexString(Reader in, int format) throws IOException {
        int c;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte b = 0;
        int cNdx = 0;
        while (true) {
            c = in.read();
            if (isTerminator(c, format)) {
                break;
            } else if (c != 32 && c != 10) {
                int cVal = hexDigits.indexOf(Character.toUpperCase((char) c));
                if (cVal != -1) {
                    if (cNdx % 2 == 1) {
                        b = (byte) ((b * 16) + ((byte) cVal));
                        baos.write(b);
                    } else {
                        b = (byte) cVal;
                    }
                    cNdx++;
                } else {
                    throw new IOException("AVA parse, invalid hex digit: " + ((char) c));
                }
            }
        }
        do {
            if (c == 32 || c == 10) {
                c = in.read();
            } else {
                throw new IOException("AVA parse, invalid hex digit: " + ((char) c));
            }
        } while (!isTerminator(c, format));
        if (cNdx == 0) {
            throw new IOException("AVA parse, zero hex digits");
        } else if (cNdx % 2 != 1) {
            return new DerValue(baos.toByteArray());
        } else {
            throw new IOException("AVA parse, odd number of hex digits");
        }
    }

    private DerValue parseQuotedString(Reader in, StringBuilder temp) throws IOException {
        int c;
        int c2 = readChar(in, "Quoted string did not end in quote");
        List<Byte> embeddedHex = new ArrayList<>();
        boolean isPrintableString = true;
        while (c2 != 34) {
            if (c2 == 92) {
                c2 = readChar(in, "Quoted string did not end in quote");
                Byte hexByte = getEmbeddedHexPair(c2, in);
                if (hexByte != null) {
                    isPrintableString = false;
                    embeddedHex.add(hexByte);
                    c2 = in.read();
                } else if (specialChars1779.indexOf((char) c2) < 0) {
                    throw new IOException("Invalid escaped character in AVA: " + ((char) c2));
                }
            }
            if (embeddedHex.size() > 0) {
                temp.append(getEmbeddedHexString(embeddedHex));
                embeddedHex.clear();
            }
            isPrintableString &= DerValue.isPrintableStringChar((char) c2);
            temp.append((char) c2);
            c2 = readChar(in, "Quoted string did not end in quote");
        }
        if (embeddedHex.size() > 0) {
            temp.append(getEmbeddedHexString(embeddedHex));
            embeddedHex.clear();
        }
        while (true) {
            c = in.read();
            if (c != 10 && c != 32) {
                break;
            }
        }
        if (c != -1) {
            throw new IOException("AVA had characters other than whitespace after terminating quote");
        } else if (this.oid.equals((Object) PKCS9Attribute.EMAIL_ADDRESS_OID) || (this.oid.equals((Object) X500Name.DOMAIN_COMPONENT_OID) && !PRESERVE_OLD_DC_ENCODING)) {
            return new DerValue((byte) 22, temp.toString());
        } else {
            if (isPrintableString) {
                return new DerValue(temp.toString());
            }
            return new DerValue((byte) 12, temp.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x0174 A[LOOP:0: B:1:0x0016->B:74:0x0174, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0119 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private sun.security.util.DerValue parseString(java.io.Reader r17, int r18, int r19, java.lang.StringBuilder r20) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 376
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.x509.AVA.parseString(java.io.Reader, int, int, java.lang.StringBuilder):sun.security.util.DerValue");
    }

    private static Byte getEmbeddedHexPair(int c1, Reader in) throws IOException {
        if (hexDigits.indexOf(Character.toUpperCase((char) c1)) < 0) {
            return null;
        }
        int c2 = readChar(in, "unexpected EOF - escaped hex value must include two valid digits");
        if (hexDigits.indexOf(Character.toUpperCase((char) c2)) >= 0) {
            return new Byte((byte) ((Character.digit((char) c1, 16) << 4) + Character.digit((char) c2, 16)));
        }
        throw new IOException("escaped hex value must include two valid digits");
    }

    private static String getEmbeddedHexString(List<Byte> hexList) throws IOException {
        int n = hexList.size();
        byte[] hexBytes = new byte[n];
        for (int i = 0; i < n; i++) {
            hexBytes[i] = hexList.get(i).byteValue();
        }
        return new String(hexBytes, "UTF8");
    }

    private static boolean isTerminator(int ch, int format) {
        if (ch != -1) {
            return ch != 59 ? ch == 43 || ch == 44 : format != 3;
        }
    }

    private static int readChar(Reader in, String errMsg) throws IOException {
        int c = in.read();
        if (c != -1) {
            return c;
        }
        throw new IOException(errMsg);
    }

    private static boolean trailingSpace(Reader in) throws IOException {
        boolean trailing;
        if (!in.markSupported()) {
            return true;
        }
        in.mark(9999);
        while (true) {
            int nextChar = in.read();
            if (nextChar == -1) {
                trailing = true;
                break;
            } else if (nextChar != 32) {
                if (nextChar != 92) {
                    trailing = false;
                    break;
                } else if (in.read() != 32) {
                    trailing = false;
                    break;
                }
            }
        }
        in.reset();
        return trailing;
    }

    AVA(DerValue derval) throws IOException {
        if (derval.tag == 48) {
            this.oid = X500Name.intern(derval.data.getOID());
            this.value = derval.data.getDerValue();
            if (derval.data.available() != 0) {
                throw new IOException("AVA, extra bytes = " + derval.data.available());
            }
            return;
        }
        throw new IOException("AVA not a sequence");
    }

    AVA(DerInputStream in) throws IOException {
        this(in.getDerValue());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AVA)) {
            return false;
        }
        return toRFC2253CanonicalString().equals(((AVA) obj).toRFC2253CanonicalString());
    }

    public int hashCode() {
        return toRFC2253CanonicalString().hashCode();
    }

    public void encode(DerOutputStream out) throws IOException {
        derEncode(out);
    }

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        DerOutputStream tmp2 = new DerOutputStream();
        tmp.putOID(this.oid);
        this.value.encode(tmp);
        tmp2.write((byte) 48, tmp);
        out.write(tmp2.toByteArray());
    }

    private String toKeyword(int format, Map<String, String> oidMap) {
        return AVAKeyword.getKeyword(this.oid, format, oidMap);
    }

    public String toString() {
        return toKeywordValueString(toKeyword(1, Collections.emptyMap()));
    }

    public String toRFC1779String() {
        return toRFC1779String(Collections.emptyMap());
    }

    public String toRFC1779String(Map<String, String> oidMap) {
        return toKeywordValueString(toKeyword(2, oidMap));
    }

    public String toRFC2253String() {
        return toRFC2253String(Collections.emptyMap());
    }

    public String toRFC2253String(Map<String, String> oidMap) {
        StringBuilder typeAndValue = new StringBuilder(100);
        typeAndValue.append(toKeyword(3, oidMap));
        typeAndValue.append('=');
        if ((typeAndValue.charAt(0) < '0' || typeAndValue.charAt(0) > '9') && isDerString(this.value, false)) {
            try {
                String valStr = new String(this.value.getDataBytes(), "UTF8");
                StringBuilder sbuffer = new StringBuilder();
                for (int i = 0; i < valStr.length(); i++) {
                    char c = valStr.charAt(i);
                    if (DerValue.isPrintableStringChar(c) || ",=+<>#;\"\\".indexOf(c) >= 0) {
                        if (",=+<>#;\"\\".indexOf(c) >= 0) {
                            sbuffer.append(PatternTokenizer.BACK_SLASH);
                        }
                        sbuffer.append(c);
                    } else if (c == 0) {
                        sbuffer.append("\\00");
                    } else if (debug == null || !Debug.isOn("ava")) {
                        sbuffer.append(c);
                    } else {
                        try {
                            byte[] valueBytes = Character.toString(c).getBytes("UTF8");
                            for (int j = 0; j < valueBytes.length; j++) {
                                sbuffer.append(PatternTokenizer.BACK_SLASH);
                                sbuffer.append(Character.toUpperCase(Character.forDigit((valueBytes[j] >>> 4) & 15, 16)));
                                sbuffer.append(Character.toUpperCase(Character.forDigit(valueBytes[j] & 15, 16)));
                            }
                        } catch (IOException e) {
                            throw new IllegalArgumentException("DER Value conversion");
                        }
                    }
                }
                char[] chars = sbuffer.toString().toCharArray();
                StringBuilder sbuffer2 = new StringBuilder();
                int lead = 0;
                while (lead < chars.length && (chars[lead] == ' ' || chars[lead] == '\r')) {
                    lead++;
                }
                int trail = chars.length - 1;
                while (trail >= 0 && (chars[trail] == ' ' || chars[trail] == '\r')) {
                    trail--;
                }
                for (int i2 = 0; i2 < chars.length; i2++) {
                    char c2 = chars[i2];
                    if (i2 < lead || i2 > trail) {
                        sbuffer2.append(PatternTokenizer.BACK_SLASH);
                    }
                    sbuffer2.append(c2);
                }
                typeAndValue.append(sbuffer2.toString());
            } catch (IOException e2) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        } else {
            try {
                byte[] data = this.value.toByteArray();
                typeAndValue.append('#');
                for (byte b : data) {
                    typeAndValue.append(Character.forDigit((b >>> 4) & 15, 16));
                    typeAndValue.append(Character.forDigit(b & 15, 16));
                }
            } catch (IOException e3) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        }
        return typeAndValue.toString();
    }

    public String toRFC2253CanonicalString() {
        StringBuilder typeAndValue = new StringBuilder(40);
        typeAndValue.append(toKeyword(3, Collections.emptyMap()));
        typeAndValue.append('=');
        if ((typeAndValue.charAt(0) < '0' || typeAndValue.charAt(0) > '9') && (isDerString(this.value, true) || this.value.tag == 20)) {
            try {
                String valStr = new String(this.value.getDataBytes(), "UTF8");
                StringBuilder sbuffer = new StringBuilder();
                boolean previousWhite = false;
                for (int i = 0; i < valStr.length(); i++) {
                    char c = valStr.charAt(i);
                    if (DerValue.isPrintableStringChar(c) || ",+<>;\"\\".indexOf(c) >= 0 || (i == 0 && c == '#')) {
                        if ((i == 0 && c == '#') || ",+<>;\"\\".indexOf(c) >= 0) {
                            sbuffer.append(PatternTokenizer.BACK_SLASH);
                        }
                        if (!Character.isWhitespace(c)) {
                            previousWhite = false;
                            sbuffer.append(c);
                        } else if (!previousWhite) {
                            previousWhite = true;
                            sbuffer.append(c);
                        }
                    } else if (debug == null || !Debug.isOn("ava")) {
                        previousWhite = false;
                        sbuffer.append(c);
                    } else {
                        previousWhite = false;
                        try {
                            byte[] valueBytes = Character.toString(c).getBytes("UTF8");
                            for (int j = 0; j < valueBytes.length; j++) {
                                sbuffer.append(PatternTokenizer.BACK_SLASH);
                                sbuffer.append(Character.forDigit((valueBytes[j] >>> 4) & 15, 16));
                                sbuffer.append(Character.forDigit(valueBytes[j] & 15, 16));
                            }
                        } catch (IOException e) {
                            throw new IllegalArgumentException("DER Value conversion");
                        }
                    }
                }
                typeAndValue.append(sbuffer.toString().trim());
            } catch (IOException e2) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        } else {
            try {
                byte[] data = this.value.toByteArray();
                typeAndValue.append('#');
                for (byte b : data) {
                    typeAndValue.append(Character.forDigit((b >>> 4) & 15, 16));
                    typeAndValue.append(Character.forDigit(b & 15, 16));
                }
            } catch (IOException e3) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        }
        return Normalizer.normalize(typeAndValue.toString().toUpperCase(Locale.US).toLowerCase(Locale.US), Normalizer.Form.NFKD);
    }

    private static boolean isDerString(DerValue value2, boolean canonical) {
        if (canonical) {
            byte b = value2.tag;
            return b == 12 || b == 19;
        }
        byte b2 = value2.tag;
        return b2 == 12 || b2 == 22 || b2 == 27 || b2 == 30 || b2 == 19 || b2 == 20;
    }

    /* access modifiers changed from: package-private */
    public boolean hasRFC2253Keyword() {
        return AVAKeyword.hasKeyword(this.oid, 3);
    }

    private String toKeywordValueString(String keyword) {
        char trailChar;
        char c;
        char c2;
        boolean previousWhite;
        char c3;
        StringBuilder retval = new StringBuilder(40);
        retval.append(keyword);
        retval.append("=");
        try {
            String valStr = this.value.getAsString();
            if (valStr == null) {
                byte[] data = this.value.toByteArray();
                retval.append('#');
                for (int i = 0; i < data.length; i++) {
                    retval.append(hexDigits.charAt((data[i] >> 4) & 15));
                    retval.append(hexDigits.charAt(data[i] & 15));
                }
            } else {
                boolean quoteNeeded = false;
                StringBuilder sbuffer = new StringBuilder();
                boolean previousWhite2 = false;
                int length = valStr.length();
                boolean alreadyQuoted = length > 1 && valStr.charAt(0) == '\"' && valStr.charAt(length + -1) == '\"';
                for (int i2 = 0; i2 < length; i2++) {
                    char c4 = valStr.charAt(i2);
                    if (alreadyQuoted) {
                        if (i2 != 0) {
                            if (i2 != length - 1) {
                                c2 = c4;
                            }
                        }
                        sbuffer.append(c4);
                        c = '\"';
                    } else {
                        c2 = c4;
                    }
                    if (!DerValue.isPrintableStringChar(c2)) {
                        if (",+=\n<>#;\\\"".indexOf(c2) < 0) {
                            if (debug == null || !Debug.isOn("ava")) {
                                sbuffer.append(c2);
                                previousWhite2 = false;
                                c = '\"';
                            } else {
                                previousWhite2 = false;
                                byte[] valueBytes = Character.toString(c2).getBytes("UTF8");
                                for (int j = 0; j < valueBytes.length; j++) {
                                    sbuffer.append(PatternTokenizer.BACK_SLASH);
                                    sbuffer.append(Character.toUpperCase(Character.forDigit((valueBytes[j] >>> 4) & 15, 16)));
                                    sbuffer.append(Character.toUpperCase(Character.forDigit(valueBytes[j] & 15, 16)));
                                }
                                c = '\"';
                            }
                        }
                    }
                    if (!quoteNeeded && ((i2 == 0 && (c2 == ' ' || c2 == '\n')) || ",+=\n<>#;\\\"".indexOf(c2) >= 0)) {
                        quoteNeeded = true;
                    }
                    if (c2 == ' ' || c2 == '\n') {
                        c = '\"';
                        if (!quoteNeeded && previousWhite2) {
                            quoteNeeded = true;
                        }
                        previousWhite = true;
                    } else {
                        c = '\"';
                        if (c2 != '\"') {
                            c3 = PatternTokenizer.BACK_SLASH;
                            if (c2 == '\\') {
                            }
                            previousWhite = false;
                        } else {
                            c3 = PatternTokenizer.BACK_SLASH;
                        }
                        sbuffer.append(c3);
                        previousWhite = false;
                    }
                    sbuffer.append(c2);
                    previousWhite2 = previousWhite;
                }
                if (sbuffer.length() > 0 && ((trailChar = sbuffer.charAt(sbuffer.length() - 1)) == ' ' || trailChar == '\n')) {
                    quoteNeeded = true;
                }
                if (alreadyQuoted || !quoteNeeded) {
                    retval.append(sbuffer.toString());
                } else {
                    retval.append("\"" + sbuffer.toString() + "\"");
                }
            }
            return retval.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("DER Value conversion");
        }
    }
}
