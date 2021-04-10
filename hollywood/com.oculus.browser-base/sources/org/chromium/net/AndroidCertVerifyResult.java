package org.chromium.net;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AndroidCertVerifyResult {

    /* renamed from: a  reason: collision with root package name */
    public final int f10999a;
    public final boolean b;
    public final List c;

    public AndroidCertVerifyResult(int i, boolean z, List list) {
        this.f10999a = i;
        this.b = z;
        this.c = new ArrayList(list);
    }

    public byte[][] getCertificateChainEncoded() {
        byte[][] bArr = new byte[this.c.size()][];
        for (int i = 0; i < this.c.size(); i++) {
            try {
                bArr[i] = ((X509Certificate) this.c.get(i)).getEncoded();
            } catch (CertificateEncodingException unused) {
                return new byte[0][];
            }
        }
        return bArr;
    }

    public int getStatus() {
        return this.f10999a;
    }

    public boolean isIssuedByKnownRoot() {
        return this.b;
    }

    public AndroidCertVerifyResult(int i) {
        this.f10999a = i;
        this.b = false;
        this.c = Collections.emptyList();
    }
}
