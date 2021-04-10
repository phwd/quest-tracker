package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.text.Normalizer;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import sun.security.action.GetBooleanAction;
import sun.security.util.Debug;
import sun.security.util.DerEncoder;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class AVA implements DerEncoder {
    private static final boolean PRESERVE_OLD_DC_ENCODING = ((Boolean) AccessController.doPrivileged(new GetBooleanAction("com.sun.security.preserveOldDCEncoding"))).booleanValue();
    private static final Debug debug = Debug.getInstance("x509", "\t[AVA]");
    final ObjectIdentifier oid;
    final DerValue value;

    public ObjectIdentifier getObjectIdentifier() {
        return this.oid;
    }

    AVA(DerValue derValue) {
        if (derValue.tag == 48) {
            this.oid = X500Name.intern(derValue.data.getOID());
            this.value = derValue.data.getDerValue();
            if (derValue.data.available() != 0) {
                throw new IOException("AVA, extra bytes = " + derValue.data.available());
            }
            return;
        }
        throw new IOException("AVA not a sequence");
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

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        derOutputStream.putOID(this.oid);
        this.value.encode(derOutputStream);
        derOutputStream2.write((byte) 48, derOutputStream);
        outputStream.write(derOutputStream2.toByteArray());
    }

    private String toKeyword(int i, Map map) {
        return AVAKeyword.getKeyword(this.oid, i, map);
    }

    public String toString() {
        return toKeywordValueString(toKeyword(1, Collections.emptyMap()));
    }

    public String toRFC1779String(Map map) {
        return toKeywordValueString(toKeyword(2, map));
    }

    public String toRFC2253String(Map map) {
        StringBuilder sb = new StringBuilder(100);
        sb.append(toKeyword(3, map));
        sb.append('=');
        if ((sb.charAt(0) < '0' || sb.charAt(0) > '9') && isDerString(this.value, false)) {
            try {
                new String(this.value.getDataBytes(), "UTF8");
                throw null;
            } catch (IOException unused) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        } else {
            try {
                byte[] byteArray = this.value.toByteArray();
                sb.append('#');
                for (byte b : byteArray) {
                    sb.append(Character.forDigit((b >>> 4) & 15, 16));
                    sb.append(Character.forDigit(b & 15, 16));
                }
                return sb.toString();
            } catch (IOException unused2) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        }
    }

    public String toRFC2253CanonicalString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append(toKeyword(3, Collections.emptyMap()));
        sb.append('=');
        if ((sb.charAt(0) < '0' || sb.charAt(0) > '9') && (isDerString(this.value, true) || this.value.tag == 20)) {
            try {
                new String(this.value.getDataBytes(), "UTF8");
                throw null;
            } catch (IOException unused) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        } else {
            try {
                byte[] byteArray = this.value.toByteArray();
                sb.append('#');
                for (byte b : byteArray) {
                    sb.append(Character.forDigit((b >>> 4) & 15, 16));
                    sb.append(Character.forDigit(b & 15, 16));
                }
                return Normalizer.normalize(sb.toString().toUpperCase(Locale.US).toLowerCase(Locale.US), Normalizer.Form.NFKD);
            } catch (IOException unused2) {
                throw new IllegalArgumentException("DER Value conversion");
            }
        }
    }

    private static boolean isDerString(DerValue derValue, boolean z) {
        if (z) {
            byte b = derValue.tag;
            return b == 12 || b == 19;
        }
        byte b2 = derValue.tag;
        return b2 == 12 || b2 == 22 || b2 == 27 || b2 == 30 || b2 == 19 || b2 == 20;
    }

    /* access modifiers changed from: package-private */
    public boolean hasRFC2253Keyword() {
        return AVAKeyword.hasKeyword(this.oid, 3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0118, code lost:
        if (r2 != '\n') goto L_0x011b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String toKeywordValueString(java.lang.String r17) {
        /*
        // Method dump skipped, instructions count: 334
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.x509.AVA.toKeywordValueString(java.lang.String):java.lang.String");
    }
}
