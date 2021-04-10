package com.android.org.bouncycastle.jcajce.provider.asymmetric.x509;

import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.util.encoders.Base64;
import java.io.IOException;
import java.io.InputStream;

class PEMUtil {
    private final String _footer1;
    private final String _footer2;
    private final String _footer3;
    private final String _header1;
    private final String _header2;
    private final String _header3 = "-----BEGIN PKCS7-----";

    PEMUtil(String type) {
        this._header1 = "-----BEGIN " + type + "-----";
        this._header2 = "-----BEGIN X509 " + type + "-----";
        this._footer1 = "-----END " + type + "-----";
        this._footer2 = "-----END X509 " + type + "-----";
        this._footer3 = "-----END PKCS7-----";
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0023 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readLine(java.io.InputStream r6) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
        L_0x0005:
            int r1 = r6.read()
            r2 = r1
            r3 = 10
            r4 = 13
            if (r1 == r4) goto L_0x0019
            if (r2 == r3) goto L_0x0019
            if (r2 < 0) goto L_0x0019
            char r1 = (char) r2
            r0.append(r1)
            goto L_0x0005
        L_0x0019:
            if (r2 < 0) goto L_0x0021
            int r1 = r0.length()
            if (r1 == 0) goto L_0x0005
        L_0x0021:
            if (r2 >= 0) goto L_0x0025
            r1 = 0
            return r1
        L_0x0025:
            if (r2 != r4) goto L_0x003a
            r1 = 1
            r6.mark(r1)
            int r4 = r6.read()
            r2 = r4
            if (r4 != r3) goto L_0x0035
            r6.mark(r1)
        L_0x0035:
            if (r2 <= 0) goto L_0x003a
            r6.reset()
        L_0x003a:
            java.lang.String r1 = r0.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.jcajce.provider.asymmetric.x509.PEMUtil.readLine(java.io.InputStream):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public ASN1Sequence readPEMObject(InputStream in) throws IOException {
        String line;
        StringBuffer pemBuf = new StringBuffer();
        do {
            line = readLine(in);
            if (line == null || line.startsWith(this._header1) || line.startsWith(this._header2)) {
            }
        } while (!line.startsWith(this._header3));
        while (true) {
            String line2 = readLine(in);
            if (line2 != null && !line2.startsWith(this._footer1) && !line2.startsWith(this._footer2) && !line2.startsWith(this._footer3)) {
                pemBuf.append(line2);
            }
        }
        if (pemBuf.length() == 0) {
            return null;
        }
        try {
            return ASN1Sequence.getInstance(Base64.decode(pemBuf.toString()));
        } catch (Exception e) {
            throw new IOException("malformed PEM data encountered");
        }
    }
}
