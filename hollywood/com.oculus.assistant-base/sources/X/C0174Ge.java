package X;

import android.util.Base64;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.Ge  reason: case insensitive filesystem */
public class C0174Ge implements X509TrustManager {
    public C0617d4 A00;
    public final Set A01 = new HashSet();
    public final X509TrustManager A02;
    public final long A03;
    public final boolean A04;

    public final void A00(List list) {
        if (this.A04 && System.currentTimeMillis() > this.A03) {
            return;
        }
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                try {
                    if (this.A01.contains(ByteBuffer.wrap(MessageDigest.getInstance("SHA-256").digest(((Certificate) it.next()).getPublicKey().getEncoded())))) {
                        return;
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new CertificateException(e);
                }
            }
            StringBuilder sb = new StringBuilder("pinning error, trusted chain: ");
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                sb.append(Base64.encodeToString(((Certificate) it2.next()).getEncoded(), 0));
                sb.append("\n");
            }
            throw new CertificateException(sb.toString());
        }
        throw new CertificateException("pinning error: certificate chain empty");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0027, code lost:
        if (r0.getPublicKey().equals(r2.getPublicKey()) == false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(java.security.cert.X509Certificate[] r10) {
        /*
        // Method dump skipped, instructions count: 190
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0174Ge.A01(java.security.cert.X509Certificate[]):void");
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        this.A02.checkServerTrusted(x509CertificateArr, str);
        A01(x509CertificateArr);
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return this.A02.getAcceptedIssuers();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0058, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0060, code lost:
        throw new java.lang.IllegalStateException("Failure initializing TrustManager", r2);
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0058 A[ExcHandler: KeyStoreException | NoSuchAlgorithmException | CertificateException (r2v1 'e' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:10:0x0052] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C0174Ge(long r7, X.C0617d4 r9) {
        /*
            r6 = this;
            r6.<init>()
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r6.A01 = r0
            r6.A00 = r9
            java.lang.String r0 = "X509"
            javax.net.ssl.TrustManagerFactory r1 = javax.net.ssl.TrustManagerFactory.getInstance(r0)
            r0 = 0
            r1.init(r0)
            javax.net.ssl.TrustManager[] r2 = r1.getTrustManagers()
            int r1 = r2.length
            r0 = 1
            if (r1 != r0) goto L_0x0050
            r5 = 0
            r0 = r2[r5]
            javax.net.ssl.X509TrustManager r0 = (javax.net.ssl.X509TrustManager) r0
            r6.A02 = r0
            r2 = 0
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            r0 = 0
            if (r1 <= 0) goto L_0x002d
            r0 = 1
        L_0x002d:
            r6.A04 = r0
            r0 = 31536000000(0x757b12c00, double:1.55808542072E-313)
            long r7 = r7 + r0
            r6.A03 = r7
            java.lang.String[] r4 = X.C0173Gd.A00
            int r3 = r4.length
            r2 = 0
        L_0x003b:
            if (r2 >= r3) goto L_0x004f
            r0 = r4[r2]
            byte[] r0 = android.util.Base64.decode(r0, r5)
            java.util.Set r1 = r6.A01
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)
            r1.add(r0)
            int r2 = r2 + 1
            goto L_0x003b
        L_0x004f:
            return
        L_0x0050:
            java.lang.String r1 = "Unable to create system TrustManger"
            java.security.cert.CertificateException r0 = new java.security.cert.CertificateException     // Catch:{ KeyStoreException | NoSuchAlgorithmException | CertificateException -> 0x0058, KeyStoreException | NoSuchAlgorithmException | CertificateException -> 0x0058 }
            r0.<init>(r1)     // Catch:{ KeyStoreException | NoSuchAlgorithmException | CertificateException -> 0x0058, KeyStoreException | NoSuchAlgorithmException | CertificateException -> 0x0058 }
            throw r0     // Catch:{ KeyStoreException | NoSuchAlgorithmException | CertificateException -> 0x0058, KeyStoreException | NoSuchAlgorithmException | CertificateException -> 0x0058 }
        L_0x0058:
            r2 = move-exception
            java.lang.String r1 = "Failure initializing TrustManager"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0174Ge.<init>(long, X.d4):void");
    }
}
