package com.oculus.vrcast.googlecast.net.auth;

import android.content.Context;
import com.oculus.vrcast.R;

public class GoogleCastCA {
    private static final int[] CA_RESOURCES = {R.raw.cast_root_ca, R.raw.eureka_root_ca};
    private final Context mContext;

    public GoogleCastCA(Context context) {
        this.mContext = context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        if (r5 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
        r7.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003b, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.security.cert.X509Certificate> getRootCerts() throws java.io.IOException, java.security.cert.CertificateException {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = "X.509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1)
            android.content.Context r7 = r7.mContext
            android.content.res.Resources r7 = r7.getResources()
            int[] r2 = com.oculus.vrcast.googlecast.net.auth.GoogleCastCA.CA_RESOURCES
            int r3 = r2.length
            r4 = 0
        L_0x0015:
            if (r4 >= r3) goto L_0x003c
            r5 = r2[r4]
            java.io.InputStream r5 = r7.openRawResource(r5)
            java.security.cert.Certificate r6 = r1.generateCertificate(r5)     // Catch:{ all -> 0x002e }
            java.security.cert.X509Certificate r6 = (java.security.cert.X509Certificate) r6     // Catch:{ all -> 0x002e }
            r0.add(r6)     // Catch:{ all -> 0x002e }
            if (r5 == 0) goto L_0x002b
            r5.close()
        L_0x002b:
            int r4 = r4 + 1
            goto L_0x0015
        L_0x002e:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r0 = move-exception
            if (r5 == 0) goto L_0x003b
            r5.close()     // Catch:{ all -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r1 = move-exception
            r7.addSuppressed(r1)
        L_0x003b:
            throw r0
        L_0x003c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrcast.googlecast.net.auth.GoogleCastCA.getRootCerts():java.util.List");
    }
}
