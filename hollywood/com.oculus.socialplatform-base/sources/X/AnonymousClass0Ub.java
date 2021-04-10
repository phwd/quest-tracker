package X;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.facebook.annotations.OkToExtend;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import javax.net.ssl.X509TrustManager;
import org.thoughtcrime.ssl.pinning.CertificateChainCleaner;
import org.thoughtcrime.ssl.pinning.SystemKeyStore;

@OkToExtend
/* renamed from: X.0Ub  reason: invalid class name */
public class AnonymousClass0Ub implements X509TrustManager {
    @Nullable
    public SystemKeyStore A00;
    public final Set<ByteBuffer> A01 = new HashSet();
    public final X509TrustManager A02;
    public final long A03;
    public final boolean A04;

    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public final void A00(List<X509Certificate> list) throws CertificateException {
        if (this.A04 && System.currentTimeMillis() > this.A03) {
            return;
        }
        if (!list.isEmpty()) {
            for (X509Certificate x509Certificate : list) {
                try {
                    if (this.A01.contains(ByteBuffer.wrap(MessageDigest.getInstance("SHA-256").digest(x509Certificate.getPublicKey().getEncoded())))) {
                        return;
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new CertificateException(e);
                }
            }
            StringBuilder sb = new StringBuilder("pinning error, trusted chain: ");
            for (X509Certificate x509Certificate2 : list) {
                sb.append(Base64.encodeToString(x509Certificate2.getEncoded(), 0));
                sb.append("\n");
            }
            throw new CertificateException(sb.toString());
        }
        throw new CertificateException("pinning error: certificate chain empty");
    }

    public final void A01(X509Certificate[] x509CertificateArr) throws CertificateException {
        SystemKeyStore systemKeyStore = this.A00;
        if (systemKeyStore != null) {
            A00(Arrays.asList(CertificateChainCleaner.getCleanChain(x509CertificateArr, systemKeyStore)));
            return;
        }
        throw new CertificateException("SystemKeystore is not intialized.");
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
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
    public AnonymousClass0Ub(long r7, @javax.annotation.Nullable org.thoughtcrime.ssl.pinning.SystemKeyStore r9) {
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
            java.lang.String[] r4 = X.AnonymousClass0Ua.A00
            int r3 = r4.length
            r2 = 0
        L_0x003b:
            if (r2 >= r3) goto L_0x004f
            r0 = r4[r2]
            byte[] r0 = android.util.Base64.decode(r0, r5)
            java.util.Set<java.nio.ByteBuffer> r1 = r6.A01
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Ub.<init>(long, org.thoughtcrime.ssl.pinning.SystemKeyStore):void");
    }
}
