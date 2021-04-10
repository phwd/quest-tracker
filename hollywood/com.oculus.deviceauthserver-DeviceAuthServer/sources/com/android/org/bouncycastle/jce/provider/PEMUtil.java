package com.android.org.bouncycastle.jce.provider;

import com.android.org.bouncycastle.asn1.ASN1InputStream;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.util.encoders.Base64;
import java.io.IOException;
import java.io.InputStream;

public class PEMUtil {
    private final String _footer1;
    private final String _footer2;
    private final String _header1;
    private final String _header2;

    PEMUtil(String type) {
        this._header1 = "-----BEGIN " + type + "-----";
        this._header2 = "-----BEGIN X509 " + type + "-----";
        this._footer1 = "-----END " + type + "-----";
        this._footer2 = "-----END X509 " + type + "-----";
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0026 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readLine(java.io.InputStream r5) throws java.io.IOException {
        /*
            r4 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L_0x0005:
            int r1 = r5.read()
            r2 = r1
            r3 = 13
            if (r1 == r3) goto L_0x001c
            r1 = 10
            if (r2 == r1) goto L_0x001c
            if (r2 < 0) goto L_0x001c
            if (r2 != r3) goto L_0x0017
            goto L_0x0005
        L_0x0017:
            char r1 = (char) r2
            r0.append(r1)
            goto L_0x0005
        L_0x001c:
            if (r2 < 0) goto L_0x0024
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0005
        L_0x0024:
            if (r2 >= 0) goto L_0x0028
            r1 = 0
            return r1
        L_0x0028:
            java.lang.String r1 = r0.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.jce.provider.PEMUtil.readLine(java.io.InputStream):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public ASN1Sequence readPEMObject(InputStream in) throws IOException {
        String line;
        StringBuffer pemBuf = new StringBuffer();
        do {
            line = readLine(in);
            if (line == null || line.startsWith(this._header1)) {
            }
        } while (!line.startsWith(this._header2));
        while (true) {
            String line2 = readLine(in);
            if (line2 != null && !line2.startsWith(this._footer1) && !line2.startsWith(this._footer2)) {
                pemBuf.append(line2);
            }
        }
        if (pemBuf.length() == 0) {
            return null;
        }
        ASN1Primitive o = new ASN1InputStream(Base64.decode(pemBuf.toString())).readObject();
        if (o instanceof ASN1Sequence) {
            return (ASN1Sequence) o;
        }
        throw new IOException("malformed PEM data encountered");
    }
}
