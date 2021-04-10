package org.chromium.chrome.browser.browserservices.verification;

import J.N;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OriginVerifier {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f10625a = "0123456789ABCDEF".toCharArray();
    public static final AtomicReference b = new AtomicReference();
    public final String c;
    public final String d;
    public final int e;
    public long f;
    public final Map g = new HashMap();
    public long h;
    public final AbstractC4989tt0 i;
    public WebContents j;
    public YM k;

    public OriginVerifier(String str, int i2, WebContents webContents, YM ym, AbstractC4989tt0 tt0) {
        this.c = str;
        this.d = c(str);
        this.e = i2;
        this.j = webContents;
        this.k = ym;
        this.i = tt0;
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder((bArr.length * 3) - 1);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            char[] cArr = f10625a;
            sb.append(cArr[(bArr[i2] & 240) >>> 4]);
            sb.append(cArr[bArr[i2] & 15]);
            if (i2 < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static String c(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = ContextUtils.getApplicationContext().getPackageManager().getPackageInfo(str, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            return null;
        }
        try {
            return a(MessageDigest.getInstance("SHA256").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getEncoded()));
        } catch (CertificateEncodingException unused2) {
            AbstractC1220Ua0.f("OriginVerifier", "Certificate type X509 encoding failed", new Object[0]);
            return null;
        } catch (NoSuchAlgorithmException | CertificateException unused3) {
            return null;
        }
    }

    public static void clearBrowsingData() {
        Object obj = ThreadUtils.f10596a;
        Hs1.b(Collections.emptySet());
    }

    public static boolean e(String str, C4649rt0 rt0, int i2) {
        AtomicReference atomicReference = b;
        if (atomicReference.get() == null) {
            return false;
        }
        return ((Set) atomicReference.get()).contains(new AL0(str, "", rt0, i2).toString());
    }

    public void b() {
        if (this.g.isEmpty()) {
            long j2 = this.f;
            if (j2 != 0) {
                N.MSfT_7mi(j2, this);
                this.f = 0;
            }
        }
    }

    public final void d(C4649rt0 rt0, boolean z, Boolean bool) {
        if (z) {
            AL0 al0 = new AL0(this.c, this.d, rt0, this.e);
            Set a2 = Hs1.a();
            ((HashSet) a2).add(al0.toString());
            Hs1.b(a2);
        }
        AL0 al02 = new AL0(this.c, this.d, rt0, this.e);
        if (z) {
            Set a3 = Hs1.a();
            ((HashSet) a3).add(al02.toString());
            Hs1.b(a3);
        } else {
            Set a4 = Hs1.a();
            ((HashSet) a4).remove(al02.toString());
            Hs1.b(a4);
        }
        if (this.g.containsKey(rt0)) {
            for (AbstractC5159ut0 ut0 : (Set) this.g.get(rt0)) {
                ut0.a(this.c, rt0, z, bool);
            }
            this.g.remove(rt0);
        }
        if (bool != null) {
            long uptimeMillis = SystemClock.uptimeMillis() - this.h;
            AbstractC4989tt0 tt0 = this.i;
            boolean booleanValue = bool.booleanValue();
            Objects.requireNonNull((C3938nk) tt0);
            if (booleanValue) {
                AbstractC3364kK0.k("BrowserServices.VerificationTime.Online", uptimeMillis);
            } else {
                AbstractC3364kK0.k("BrowserServices.VerificationTime.Offline", uptimeMillis);
            }
        }
        b();
    }

    public final void onOriginVerificationResult(String str, int i2) {
        C4649rt0 c2 = C4649rt0.c(str);
        if (i2 == 0) {
            this.i.a(0);
            d(c2, true, Boolean.TRUE);
            return;
        } else if (i2 != 1) {
            int i3 = 2;
            if (i2 == 2) {
                AbstractC1220Ua0.d("OriginVerifier", "Device is offline, checking saved verification result.", new Object[0]);
                P21 f0 = P21.f0();
                try {
                    AL0 al0 = new AL0(this.c, this.d, c2, this.e);
                    boolean contains = ((HashSet) Hs1.a()).contains(al0.toString());
                    AbstractC4989tt0 tt0 = this.i;
                    if (!contains) {
                        i3 = 3;
                    }
                    tt0.a(i3);
                    d(c2, contains, Boolean.FALSE);
                    f0.close();
                    return;
                } catch (Throwable th) {
                    AbstractC0754Mh1.f8495a.a(th, th);
                }
            } else {
                return;
            }
        } else {
            this.i.a(1);
            d(c2, false, Boolean.TRUE);
            return;
        }
        throw th;
    }
}
