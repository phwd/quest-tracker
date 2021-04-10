package defpackage;

import J.N;
import android.content.pm.ResolveInfo;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.payments.PaymentManifestWebDataService;
import org.chromium.components.payments.PaymentManifestDownloader$ManifestDownloadCallback;
import org.chromium.components.payments.PaymentManifestParser;
import org.chromium.components.payments.WebAppManifestSection;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* renamed from: Vz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1340Vz0 implements PaymentManifestDownloader$ManifestDownloadCallback, PaymentManifestParser.ManifestParseCallback, PaymentManifestWebDataService.PaymentManifestWebDataServiceCallback {

    /* renamed from: a  reason: collision with root package name */
    public final Origin f9120a;
    public final GURL b;
    public final Map c = new HashMap();
    public final Set d;
    public final Set e = new HashSet();
    public final List f = new ArrayList();
    public final PaymentManifestWebDataService g;
    public final C1096Rz0 h;
    public final PaymentManifestParser i;
    public final C3456ku0 j;
    public final AbstractC1279Uz0 k;
    public final MessageDigest l;
    public Origin m;
    public int n;
    public boolean o;
    public boolean p;

    public C1340Vz0(Origin origin, GURL gurl, Set set, Set set2, PaymentManifestWebDataService paymentManifestWebDataService, C1096Rz0 rz0, PaymentManifestParser paymentManifestParser, C3456ku0 ku0, AbstractC1279Uz0 uz0) {
        HashSet hashSet;
        this.f9120a = origin;
        this.b = gurl;
        MessageDigest messageDigest = null;
        if (set != null) {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                ResolveInfo resolveInfo = (ResolveInfo) it.next();
                C1218Tz0 tz0 = new C1218Tz0(null);
                tz0.f9000a = resolveInfo;
                this.c.put(resolveInfo.activityInfo.packageName, tz0);
            }
        }
        if (set2 != null) {
            hashSet = new HashSet(set2);
        }
        this.d = Collections.unmodifiableSet(hashSet);
        this.h = rz0;
        this.g = paymentManifestWebDataService;
        this.i = paymentManifestParser;
        this.j = ku0;
        this.k = uz0;
        if (!this.c.isEmpty()) {
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException unused) {
                AbstractC1220Ua0.a("PaymentManifest", "Unable to generate SHA-256 hashes.", new Object[0]);
            }
        }
        this.l = messageDigest;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        Formatter formatter = new Formatter(sb);
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            formatter.format("%02x", Byte.valueOf(bArr[i2]));
        }
        String sb2 = sb.toString();
        formatter.close();
        return sb2;
    }

    public static String b(Set set) {
        StringBuilder sb = new StringBuilder("[");
        Iterator it = set.iterator();
        while (it.hasNext()) {
            sb.append(' ');
            sb.append((String) it.next());
        }
        sb.append(" ]");
        return sb.toString();
    }

    public final Set c(WebAppManifestSection[] webAppManifestSectionArr) {
        ArrayList arrayList = new ArrayList();
        for (WebAppManifestSection webAppManifestSection : webAppManifestSectionArr) {
            HashSet hashSet = new HashSet();
            int i2 = 0;
            while (true) {
                byte[][] bArr = webAppManifestSection.c;
                if (i2 >= bArr.length) {
                    break;
                }
                hashSet.add(a(bArr[i2]));
                i2++;
            }
            arrayList.add(hashSet);
        }
        HashSet hashSet2 = new HashSet();
        for (int i3 = 0; i3 < webAppManifestSectionArr.length; i3++) {
            WebAppManifestSection webAppManifestSection2 = webAppManifestSectionArr[i3];
            C1218Tz0 tz0 = (C1218Tz0) this.c.get(webAppManifestSection2.f10876a);
            if (tz0 != null) {
                long j2 = tz0.b;
                if (j2 < webAppManifestSection2.b) {
                    AbstractC1220Ua0.a("PaymentManifest", "\"%s\" version is %d, but at least %d is required.", webAppManifestSection2.f10876a, Long.valueOf(j2), Long.valueOf(webAppManifestSection2.b));
                } else {
                    Set set = tz0.c;
                    if (set == null) {
                        AbstractC1220Ua0.a("PaymentManifest", "Unable to determine fingerprints of \"%s\".", webAppManifestSection2.f10876a);
                    } else if (!set.equals(arrayList.get(i3))) {
                        AbstractC1220Ua0.a("PaymentManifest", "\"%s\" fingerprints don't match the manifest. Expected %s, but found %s.", webAppManifestSection2.f10876a, b((Set) arrayList.get(i3)), b(tz0.c));
                    } else {
                        hashSet2.add(webAppManifestSection2.f10876a);
                    }
                }
            }
        }
        return hashSet2;
    }

    @Override // org.chromium.components.payments.PaymentManifestDownloader$ManifestDownloadCallback
    public void onManifestDownloadFailure(String str) {
        if (!this.p) {
            this.p = true;
            ((C2296e6) this.k).h.k(str);
            if (this.o) {
                ((C2296e6) this.k).f();
            }
            ((C2296e6) this.k).e();
        }
    }

    @Override // org.chromium.components.payments.PaymentManifestParser.ManifestParseCallback
    public void onManifestParseFailure() {
        if (!this.p) {
            this.p = true;
            if (this.o) {
                ((C2296e6) this.k).f();
            }
            ((C2296e6) this.k).e();
        }
    }

    @Override // org.chromium.components.payments.PaymentManifestDownloader$ManifestDownloadCallback
    public void onPaymentMethodManifestDownloadSuccess(GURL gurl, Origin origin, String str) {
        this.m = origin;
        PaymentManifestParser paymentManifestParser = this.i;
        Objects.requireNonNull(paymentManifestParser);
        Object obj = ThreadUtils.f10596a;
        N.M$4TUaJ7(paymentManifestParser.f10874a, gurl, str, this);
    }

    @Override // org.chromium.chrome.browser.payments.PaymentManifestWebDataService.PaymentManifestWebDataServiceCallback
    public void onPaymentMethodManifestFetched(String[] strArr) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2] == null) {
                this.o = true;
                this.h.a(this.f9120a, this.b, this);
                return;
            }
            GURL gurl = new GURL(strArr[i2]);
            if (AbstractC5324vr1.a(gurl)) {
                hashSet2.add(gurl);
            } else {
                hashSet.add(strArr[i2]);
            }
        }
        if (strArr.length == 0 || !hashSet.containsAll(this.c.keySet()) || !hashSet2.containsAll(this.d)) {
            this.o = true;
            this.h.a(this.f9120a, this.b, this);
            return;
        }
        hashSet2.retainAll(this.d);
        Iterator it = hashSet2.iterator();
        while (it.hasNext()) {
            ((C2296e6) this.k).a(this.b).b.add((GURL) it.next());
        }
        if (this.c.isEmpty()) {
            ((C2296e6) this.k).f();
            this.h.a(this.f9120a, this.b, this);
            return;
        }
        this.n = this.c.size();
        for (String str : this.c.keySet()) {
            PaymentManifestWebDataService paymentManifestWebDataService = this.g;
            if (!N.MRdDiZyv(paymentManifestWebDataService.f10745a, paymentManifestWebDataService, str, this)) {
                this.o = true;
                this.n = 0;
                this.h.a(this.f9120a, this.b, this);
                return;
            }
        }
    }

    @Override // org.chromium.components.payments.PaymentManifestParser.ManifestParseCallback
    public void onPaymentMethodManifestParseSuccess(GURL[] gurlArr, GURL[] gurlArr2) {
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < gurlArr2.length; i2++) {
            hashSet.add(gurlArr2[i2]);
            this.e.add(gurlArr2[i2].h());
        }
        if (this.o) {
            hashSet.retainAll(this.d);
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ((C2296e6) this.k).a(this.b).b.add((GURL) it.next());
            }
        }
        if (gurlArr.length == 0) {
            if (this.o) {
                ((C2296e6) this.k).f();
            }
            PaymentManifestWebDataService paymentManifestWebDataService = this.g;
            String h2 = this.b.h();
            Set set = this.e;
            N.MKb_1VDz(paymentManifestWebDataService.f10745a, paymentManifestWebDataService, h2, (String[]) set.toArray(new String[set.size()]));
            ((C2296e6) this.k).e();
            return;
        }
        this.n = gurlArr.length;
        for (int i3 = 0; i3 < gurlArr.length && !this.p; i3++) {
            C1096Rz0 rz0 = this.h;
            Origin origin = this.m;
            GURL gurl = gurlArr[i3];
            Objects.requireNonNull(rz0);
            Object obj = ThreadUtils.f10596a;
            N.MpedIYcV(rz0.f8866a, rz0, origin, gurl, this);
        }
    }

    @Override // org.chromium.chrome.browser.payments.PaymentManifestWebDataService.PaymentManifestWebDataServiceCallback
    public void onPaymentWebAppManifestFetched(WebAppManifestSection[] webAppManifestSectionArr) {
        if (!this.o) {
            if (webAppManifestSectionArr == null || webAppManifestSectionArr.length == 0) {
                this.o = true;
                this.n = 0;
                this.h.a(this.f9120a, this.b, this);
                return;
            }
            Iterator it = ((HashSet) c(webAppManifestSectionArr)).iterator();
            while (it.hasNext()) {
                AbstractC1279Uz0 uz0 = this.k;
                GURL gurl = this.b;
                ((C2296e6) uz0).a(gurl).f9747a.add(((C1218Tz0) this.c.get((String) it.next())).f9000a);
            }
            int i2 = this.n - 1;
            this.n = i2;
            if (i2 == 0) {
                ((C2296e6) this.k).f();
                this.h.a(this.f9120a, this.b, this);
            }
        }
    }

    @Override // org.chromium.components.payments.PaymentManifestDownloader$ManifestDownloadCallback
    public void onWebAppManifestDownloadSuccess(String str) {
        if (!this.p) {
            PaymentManifestParser paymentManifestParser = this.i;
            Objects.requireNonNull(paymentManifestParser);
            Object obj = ThreadUtils.f10596a;
            N.MhPu7GL6(paymentManifestParser.f10874a, str, this);
        }
    }

    @Override // org.chromium.components.payments.PaymentManifestParser.ManifestParseCallback
    public void onWebAppManifestParseSuccess(WebAppManifestSection[] webAppManifestSectionArr) {
        if (!this.p) {
            for (WebAppManifestSection webAppManifestSection : webAppManifestSectionArr) {
                this.e.add(webAppManifestSection.f10876a);
            }
            this.f.add(webAppManifestSectionArr);
            if (this.o) {
                Iterator it = ((HashSet) c(webAppManifestSectionArr)).iterator();
                while (it.hasNext()) {
                    ((C2296e6) this.k).a(this.b).f9747a.add(((C1218Tz0) this.c.get((String) it.next())).f9000a);
                }
            }
            int i2 = this.n - 1;
            this.n = i2;
            if (i2 == 0) {
                if (this.o) {
                    ((C2296e6) this.k).f();
                }
                PaymentManifestWebDataService paymentManifestWebDataService = this.g;
                String obj = this.b.toString();
                Set set = this.e;
                N.MKb_1VDz(paymentManifestWebDataService.f10745a, paymentManifestWebDataService, obj, (String[]) set.toArray(new String[set.size()]));
                PaymentManifestWebDataService paymentManifestWebDataService2 = this.g;
                List list = this.f;
                int i3 = 0;
                for (int i4 = 0; i4 < list.size(); i4++) {
                    i3 += ((WebAppManifestSection[]) list.get(i4)).length;
                }
                WebAppManifestSection[] webAppManifestSectionArr2 = new WebAppManifestSection[i3];
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    int i7 = 0;
                    while (i7 < ((WebAppManifestSection[]) list.get(i6)).length) {
                        webAppManifestSectionArr2[i5] = ((WebAppManifestSection[]) list.get(i6))[i7];
                        i7++;
                        i5++;
                    }
                }
                N.MrEdT70Z(paymentManifestWebDataService2.f10745a, paymentManifestWebDataService2, webAppManifestSectionArr2);
                ((C2296e6) this.k).e();
            }
        }
    }
}
