package defpackage;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.download.DownloadBroadcastManager;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: vI  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5234vI {

    /* renamed from: a  reason: collision with root package name */
    public final List f11472a = new ArrayList();
    public AbstractC0711Lp0 b = new C0771Mp0(ContextUtils.getApplicationContext());
    public Bitmap c;
    public EI d = DI.f7880a;
    public C5911zH e = new C5911zH();

    public static boolean a(Context context, BI bi) {
        if (bi == null || !bi.f) {
            return false;
        }
        Set set = DownloadManagerService.F;
        boolean isActiveNetworkMetered = ((ConnectivityManager) context.getSystemService("connectivity")).isActiveNetworkMetered();
        if (bi.d || !isActiveNetworkMetered) {
            return true;
        }
        return false;
    }

    public static int c() {
        PU0 pu0 = NU0.f8549a;
        int i = 1000000;
        int f = pu0.f("NextDownloadNotificationId", 1000000);
        if (f != Integer.MAX_VALUE) {
            i = f + 1;
        }
        pu0.n("NextDownloadNotificationId", i);
        return f;
    }

    public final void b() {
        boolean z = ((BrowserStartupControllerImpl) AbstractC4280pk.a()).f() && Profile.b().e();
        Iterator it = new ArrayList(this.d.f7952a).iterator();
        while (it.hasNext()) {
            BI bi = (BI) it.next();
            if (bi.c) {
                C0788My my = bi.g;
                e(my);
                if (z) {
                    AI i = TG.i(my);
                    AbstractC3364kK0.g("Android.DownloadManager.Cancel", 0, 3);
                    i.h(my, Profile.b().c().f10752a);
                    i.e();
                }
            }
        }
    }

    public final int d(C0788My my) {
        BI b2 = this.d.b(my);
        if (b2 != null) {
            return b2.b;
        }
        return c();
    }

    public void e(C0788My my) {
        BI b2 = this.d.b(my);
        if (b2 != null) {
            this.e.e(ContextUtils.getApplicationContext(), 3, b2.b, null);
            ((C0771Mp0) this.b).b.cancel(b2.b);
            this.d.c(my);
            this.f11472a.remove(my);
        }
    }

    public void f(C0788My my, String str, Bitmap bitmap, String str2, boolean z, boolean z2, int i) {
        if (TextUtils.isEmpty(str)) {
            BI b2 = this.d.b(my);
            if (b2 != null) {
                str = b2.e;
            } else {
                return;
            }
        }
        int d2 = d(my);
        Context applicationContext = ContextUtils.getApplicationContext();
        MI mi = new MI();
        mi.f8469a = my;
        mi.b = str;
        mi.d = bitmap;
        mi.e = z2;
        mi.j = str2;
        mi.k = z;
        mi.r = i;
        Notification b3 = AbstractC4894tI.b(applicationContext, 4, mi.a(), d2);
        k(d2, b3, my, null);
        this.e.e(applicationContext, 4, d2, b3);
        this.f11472a.remove(my);
    }

    public void g(C0788My my, String str, boolean z, boolean z2, boolean z3, boolean z4, Bitmap bitmap, String str2, boolean z5, boolean z6, boolean z7, int i) {
        boolean z8;
        BI b2 = this.d.b(my);
        if (!z) {
            f(my, str, bitmap, str2, z5, z3, 1);
        } else if (b2 == null || b2.f || z7) {
            if (b2 == null) {
                z8 = false;
            } else {
                z8 = b2.d;
            }
            if (z2 || i != 0) {
                h(my, str, z3, z8, z4, bitmap, str2, z5, z6, i);
                this.f11472a.remove(my);
                return;
            }
            int d2 = b2 == null ? d(my) : b2.b;
            Context applicationContext = ContextUtils.getApplicationContext();
            MI mi = new MI();
            mi.f8469a = my;
            mi.b = str;
            mi.e = z3;
            mi.h = z4;
            mi.d = bitmap;
            mi.j = str2;
            mi.k = z5;
            mi.i = d2;
            Notification b3 = AbstractC4894tI.b(applicationContext, 1, mi.a(), d2);
            k(d2, b3, my, new BI(my, d2, z3, z8, str, z2, z4));
            this.e.e(applicationContext, 1, d2, b3);
            this.f11472a.remove(my);
        }
    }

    public void h(C0788My my, String str, boolean z, boolean z2, boolean z3, Bitmap bitmap, String str2, boolean z4, boolean z5, int i) {
        j(my, str, C0288Er0.a(), 0, 0, z, z2, z3, bitmap, str2, z4, i);
    }

    public void i() {
        if (!CachedFeatureFlags.isEnabled("DownloadsAutoResumptionNative")) {
            C5914zI.b().a();
            PU0 pu0 = NU0.f8549a;
            int f = pu0.f("ResumptionAttemptLeft", 5);
            if (f > 0) {
                pu0.n("ResumptionAttemptLeft", f - 1);
                List list = this.d.f7952a;
                for (int i = 0; i < list.size(); i++) {
                    BI bi = (BI) list.get(i);
                    if (a(ContextUtils.getApplicationContext(), bi) && !this.f11472a.contains(bi.g)) {
                        h(bi.g, bi.e, bi.c, bi.d, bi.h, null, null, false, false, 1);
                        Intent intent = new Intent();
                        intent.setAction("org.chromium.chrome.browser.download.DOWNLOAD_RESUME");
                        intent.putExtra("org.chromium.chrome.browser.download.DownloadContentId_Id", bi.g.b);
                        intent.putExtra("org.chromium.chrome.browser.download.DownloadContentId_Namespace", bi.g.f8514a);
                        intent.putExtra("org.chromium.chrome.browser.download.IS_AUTO_RESUMPTION", true);
                        Context applicationContext = ContextUtils.getApplicationContext();
                        Intent intent2 = new Intent(intent);
                        intent2.setComponent(new ComponentName(applicationContext, DownloadBroadcastManager.class));
                        applicationContext.startService(intent2);
                    }
                }
            }
        }
    }

    public final void j(C0788My my, String str, C0288Er0 er0, long j, long j2, boolean z, boolean z2, boolean z3, Bitmap bitmap, String str2, boolean z4, int i) {
        int d2 = d(my);
        Context applicationContext = ContextUtils.getApplicationContext();
        MI mi = new MI();
        mi.f8469a = my;
        mi.b = str;
        mi.l = er0;
        mi.p = j;
        mi.n = j2;
        mi.e = z;
        mi.h = z3;
        mi.d = bitmap;
        mi.j = str2;
        mi.k = z4;
        mi.i = d2;
        mi.s = i;
        Notification b2 = AbstractC4894tI.b(applicationContext, 0, mi.a(), d2);
        k(d2, b2, my, new BI(my, d2, z, z2, str, true, z3));
        this.e.e(applicationContext, 0, d2, b2);
        if (!this.f11472a.contains(my)) {
            this.f11472a.add(my);
        }
    }

    public final void k(int i, Notification notification, C0788My my, BI bi) {
        P21 f0 = P21.f0();
        try {
            C0771Mp0 mp0 = (C0771Mp0) this.b;
            Objects.requireNonNull(mp0);
            if (notification == null) {
                AbstractC1220Ua0.a("NotifManagerProxy", "Failed to create notification.", new Object[0]);
            } else {
                mp0.b.notify(i, notification);
            }
            f0.close();
            if (!(this.d.b(my) != null)) {
                AbstractC3102iq0.f10166a.b(U70.c(my) ? 1 : 0, notification);
            }
            if (bi != null) {
                this.d.a(bi, false);
                return;
            } else {
                this.d.c(my);
                return;
            }
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
