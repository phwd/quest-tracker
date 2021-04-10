package defpackage;

import J.N;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.chromium.base.ContextUtils;
import org.chromium.net.AndroidCertVerifyResult;

/* renamed from: Dz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Dz1 {

    /* renamed from: a  reason: collision with root package name */
    public static CertificateFactory f7930a;
    public static Bz1 b;
    public static Az1 c;
    public static Bz1 d;
    public static KeyStore e;
    public static KeyStore f;
    public static File g;
    public static Set h;
    public static boolean i;
    public static final Object j = new Object();
    public static final char[] k = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static void a() {
        synchronized (j) {
            b = null;
            h = null;
            e();
        }
        N.MGVAvp19();
    }

    public static X509Certificate b(byte[] bArr) {
        d();
        return (X509Certificate) f7930a.generateCertificate(new ByteArrayInputStream(bArr));
    }

    public static Bz1 c(KeyStore keyStore) {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init(keyStore);
        try {
            TrustManager[] trustManagers = instance.getTrustManagers();
            for (TrustManager trustManager : trustManagers) {
                if (trustManager instanceof X509TrustManager) {
                    try {
                        return new Cz1((X509TrustManager) trustManager);
                    } catch (IllegalArgumentException e2) {
                        AbstractC1220Ua0.a("X509Util", "Error creating trust manager (" + trustManager.getClass().getName() + "): " + e2, new Object[0]);
                    }
                }
            }
            AbstractC1220Ua0.a("X509Util", "Could not find suitable trust manager", new Object[0]);
            return null;
        } catch (RuntimeException e3) {
            AbstractC1220Ua0.a("X509Util", "TrustManagerFactory.getTrustManagers() unexpectedly threw: %s", e3);
            throw new KeyStoreException(e3);
        }
    }

    public static void d() {
        synchronized (j) {
            e();
        }
    }

    public static void e() {
        if (f7930a == null) {
            f7930a = CertificateFactory.getInstance("X.509");
        }
        if (b == null) {
            b = c(null);
        }
        if (!i) {
            try {
                KeyStore instance = KeyStore.getInstance("AndroidCAStore");
                f = instance;
                try {
                    instance.load(null);
                } catch (IOException unused) {
                }
                g = new File(System.getenv("ANDROID_ROOT") + "/etc/security/cacerts");
            } catch (KeyStoreException unused2) {
            }
            i = true;
        }
        if (h == null) {
            h = new HashSet();
        }
        if (e == null) {
            KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
            e = instance2;
            try {
                instance2.load(null);
            } catch (IOException unused3) {
            }
        }
        if (d == null) {
            d = c(e);
        }
        if (c == null) {
            c = new Az1(null);
            IntentFilter intentFilter = new IntentFilter();
            if (Build.VERSION.SDK_INT >= 26) {
                intentFilter.addAction("android.security.action.KEYCHAIN_CHANGED");
                intentFilter.addAction("android.security.action.KEY_ACCESS_CHANGED");
                intentFilter.addAction("android.security.action.TRUST_STORE_CHANGED");
            } else {
                intentFilter.addAction("android.security.STORAGE_CHANGED");
            }
            ContextUtils.getApplicationContext().registerReceiver(c, intentFilter);
        }
    }

    public static boolean f(X509Certificate x509Certificate) {
        if (f == null) {
            return false;
        }
        Pair pair = new Pair(x509Certificate.getSubjectX500Principal(), x509Certificate.getPublicKey());
        if (h.contains(pair)) {
            return true;
        }
        byte[] digest = MessageDigest.getInstance("MD5").digest(x509Certificate.getSubjectX500Principal().getEncoded());
        char[] cArr = new char[8];
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i2 * 2;
            char[] cArr2 = k;
            int i4 = 3 - i2;
            cArr[i3] = cArr2[(digest[i4] >> 4) & 15];
            cArr[i3 + 1] = cArr2[digest[i4] & 15];
        }
        String str = new String(cArr);
        int i5 = 0;
        while (true) {
            String str2 = str + '.' + i5;
            if (!new File(g, str2).exists()) {
                return false;
            }
            Certificate certificate = f.getCertificate("system:" + str2);
            if (certificate != null) {
                if (!(certificate instanceof X509Certificate)) {
                    AbstractC1220Ua0.a("X509Util", "Anchor " + str2 + " not an X509Certificate: " + certificate.getClass().getName(), new Object[0]);
                } else {
                    X509Certificate x509Certificate2 = (X509Certificate) certificate;
                    if (x509Certificate.getSubjectX500Principal().equals(x509Certificate2.getSubjectX500Principal()) && x509Certificate.getPublicKey().equals(x509Certificate2.getPublicKey())) {
                        h.add(pair);
                        return true;
                    }
                }
            }
            i5++;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean g(java.security.cert.X509Certificate r4) {
        /*
            r0 = 0
            java.util.List r4 = r4.getExtendedKeyUsage()     // Catch:{ NullPointerException -> 0x003a }
            r1 = 1
            if (r4 != 0) goto L_0x0009
            return r1
        L_0x0009:
            java.util.Iterator r4 = r4.iterator()
        L_0x000d:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x003a
            java.lang.Object r2 = r4.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "1.3.6.1.5.5.7.3.1"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x0039
            java.lang.String r3 = "2.5.29.37.0"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x0039
            java.lang.String r3 = "2.16.840.1.113730.4.1"
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto L_0x0039
            java.lang.String r3 = "1.3.6.1.4.1.311.10.3.3"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x000d
        L_0x0039:
            return r1
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Dz1.g(java.security.cert.X509Certificate):boolean");
    }

    public static AndroidCertVerifyResult h(byte[][] bArr, String str, String str2) {
        List list;
        if (bArr == null || bArr.length == 0 || bArr[0] == null) {
            StringBuilder i2 = AbstractC2531fV.i("Expected non-null and non-empty certificate chain passed as |certChain|. |certChain|=");
            i2.append(Arrays.deepToString(bArr));
            throw new IllegalArgumentException(i2.toString());
        }
        try {
            d();
            ArrayList arrayList = new ArrayList();
            try {
                arrayList.add(b(bArr[0]));
                for (int i3 = 1; i3 < bArr.length; i3++) {
                    try {
                        arrayList.add(b(bArr[i3]));
                    } catch (CertificateException unused) {
                        AbstractC1220Ua0.f("X509Util", "intermediate " + i3 + " failed parsing", new Object[0]);
                    }
                }
                X509Certificate[] x509CertificateArr = (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
                try {
                    x509CertificateArr[0].checkValidity();
                    if (!g(x509CertificateArr[0])) {
                        return new AndroidCertVerifyResult(-6);
                    }
                    synchronized (j) {
                        Bz1 bz1 = b;
                        if (bz1 == null) {
                            return new AndroidCertVerifyResult(-1);
                        }
                        try {
                            list = ((Cz1) bz1).a(x509CertificateArr, str, str2);
                        } catch (CertificateException e2) {
                            try {
                                list = ((Cz1) d).a(x509CertificateArr, str, str2);
                            } catch (CertificateException unused2) {
                                AbstractC1220Ua0.d("X509Util", "Failed to validate the certificate chain, error: " + e2.getMessage(), new Object[0]);
                                return new AndroidCertVerifyResult(-2);
                            }
                        }
                        return new AndroidCertVerifyResult(0, list.size() > 0 ? f((X509Certificate) list.get(list.size() - 1)) : false, list);
                    }
                } catch (CertificateExpiredException unused3) {
                    return new AndroidCertVerifyResult(-3);
                } catch (CertificateNotYetValidException unused4) {
                    return new AndroidCertVerifyResult(-4);
                } catch (CertificateException unused5) {
                    return new AndroidCertVerifyResult(-1);
                }
            } catch (CertificateException unused6) {
                return new AndroidCertVerifyResult(-5);
            }
        } catch (CertificateException unused7) {
            return new AndroidCertVerifyResult(-1);
        }
    }
}
