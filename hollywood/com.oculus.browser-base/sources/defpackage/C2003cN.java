package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.base.PackageManagerUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: cN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2003cN implements AbstractC1652aN {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9603a = ContextUtils.getApplicationContext();
    public final Tab b;
    public final AbstractC1404Xa1 c;
    public boolean d;

    public C2003cN(Tab tab) {
        this.b = tab;
        C1832bN bNVar = new C1832bN(this);
        this.c = bNVar;
        tab.A(bNVar);
    }

    public static boolean o(String str, Intent intent) {
        return !C3198jN.c(PackageManagerUtils.c(intent, 64), str, true).isEmpty();
    }

    public static boolean q(Intent intent, boolean z) {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext.getPackageName().equals(intent.getPackage())) {
            return true;
        }
        if (intent.getComponent() != null && applicationContext.getPackageName().equals(intent.getComponent().getPackageName())) {
            return true;
        }
        ResolveInfo d2 = PackageManagerUtils.d(intent, z ? 65536 : 0);
        if (d2 == null || !d2.activityInfo.packageName.equals(applicationContext.getPackageName())) {
            return false;
        }
        return true;
    }

    @Override // defpackage.AbstractC1652aN
    public void a(Intent intent) {
    }

    @Override // defpackage.AbstractC1652aN
    public boolean b(Intent intent) {
        return false;
    }

    @Override // defpackage.AbstractC1652aN
    public int c(Intent intent, boolean z) {
        return 2;
    }

    @Override // defpackage.AbstractC1652aN
    public boolean d(String str) {
        return false;
    }

    @Override // defpackage.AbstractC1652aN
    public boolean e() {
        return ApplicationStatus.getStateForApplication() == 1;
    }

    public boolean f() {
        Tab tab = this.b;
        return tab != null && !tab.x() && this.b.isInitialized();
    }

    public void g() {
        if (m()) {
            Context context = (Context) this.b.i().f11022J.get();
            if (context instanceof ChromeActivity) {
                ((AbstractC0246Ea1) ((ChromeActivity) context).P()).h(this.b);
            }
        }
    }

    public void h(Intent intent) {
        Intent intent2 = new Intent("android.intent.action.MAIN");
        intent2.setClass(i(), AbstractActivityC4594rb.class);
        intent.setFlags(268435456);
        intent2.putExtra("org.chromium.chrome.browser.instantapps.AUTH_INTENT", intent);
        i().startActivity(intent2);
    }

    public final Context i() {
        Activity a2 = ContextUtils.a(j());
        return a2 == null ? ContextUtils.getApplicationContext() : a2;
    }

    public Context j() {
        if (this.b.i() == null) {
            return null;
        }
        return (Context) this.b.i().f11022J.get();
    }

    public WebContents k() {
        Tab tab = this.b;
        if (tab == null) {
            return null;
        }
        return tab.l();
    }

    public WindowAndroid l() {
        Tab tab = this.b;
        if (tab == null) {
            return null;
        }
        return tab.i();
    }

    public boolean m() {
        return this.b != null && !this.d;
    }

    public boolean n(Intent intent) {
        Object obj = N20.f8522a;
        if ("com.google.android.instantapps.supervisor".equals(intent.getPackage())) {
            return true;
        }
        String action = intent.getAction();
        for (String str : N20.c) {
            if (str.equals(action)) {
                return true;
            }
        }
        return false;
    }

    public boolean p(String str, String str2, boolean z, boolean z2) {
        C2171dL0 dl0;
        if (m() && this.b.l() != null) {
            N20 a2 = N20.a();
            C2341eL0 el0 = (C2341eL0) this.b.M().c(C2341eL0.class);
            Uri uri = null;
            if (el0 == null) {
                dl0 = null;
            } else {
                dl0 = el0.G;
            }
            Intent intent = dl0 != null ? dl0.f9773a : null;
            boolean z3 = true;
            if (z && intent != null && "android.intent.action.VIEW".equals(intent.getAction())) {
                Intent intent2 = new Intent(intent);
                intent2.setData(Uri.parse(str));
                Context i = i();
                boolean e = Z60.e(intent2);
                Objects.requireNonNull(a2);
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (e && !U20.d(intent2, "android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", false)) {
                    AbstractC1220Ua0.d("InstantAppsHandler", "Not handling with Instant Apps (missing CUSTOM_APPS_INSTANT_APP_EXTRA)", new Object[0]);
                } else if (U20.d(intent2, "com.google.android.gms.instantapps.DO_NOT_LAUNCH_INSTANT_APP", false) || (Build.VERSION.SDK_INT >= 26 && (intent2.getFlags() & 512) != 0)) {
                    Long valueOf = Long.valueOf(U20.i(intent2, "org.chromium.chrome.INSTANT_APP_START_TIME", 0));
                    if (valueOf.longValue() > 0) {
                        AbstractC3364kK0.k("Android.InstantApps.FallbackDuration", SystemClock.elapsedRealtime() - valueOf.longValue());
                        intent2.removeExtra("org.chromium.chrome.INSTANT_APP_START_TIME");
                    }
                    int h = U20.h(intent2, "com.google.android.gms.instantapps.BROWSER_LAUNCH_REASON", 0);
                    if (h > 0 && h < 3) {
                        AbstractC3364kK0.g("Android.InstantApps.CallSource", h, 3);
                        intent2.removeExtra("com.google.android.gms.instantapps.BROWSER_LAUNCH_REASON");
                    } else if (h >= 3) {
                        AbstractC1220Ua0.a("InstantAppsHandler", AbstractC2531fV.w("Unexpected call source constant for Instant Apps: ", h), new Object[0]);
                    }
                    AbstractC1220Ua0.d("InstantAppsHandler", "Not handling with Instant Apps (DO_NOT_LAUNCH_EXTRA)", new Object[0]);
                } else {
                    if (!U20.d(intent2, "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", false) && !U20.o(intent2, "org.chromium.chrome.browser.webapp_source")) {
                        if (!(i.getPackageName().equals(U20.n(intent2, "com.android.browser.application_id")) || S20.A(intent2)) && S20.m(intent2) != null) {
                            Intent intent3 = new Intent(intent2);
                            intent3.setComponent(null);
                            Intent selector = intent3.getSelector();
                            if (selector != null) {
                                selector.setComponent(null);
                            }
                            if ((e || NU0.f8549a.d("applink.chrome_default_browser", false)) && !o(null, intent3)) {
                                Intent intent4 = new Intent(intent2);
                                intent4.putExtra("com.google.android.gms.instantapps.DO_NOT_LAUNCH_INSTANT_APP", true);
                                intent4.putExtra("org.chromium.chrome.INSTANT_APP_START_TIME", elapsedRealtime);
                            } else {
                                AbstractC1220Ua0.d("InstantAppsHandler", "Not handling with Instant Apps because Chrome is not default or there's a specialized handler", new Object[0]);
                            }
                        }
                    }
                    AbstractC1220Ua0.d("InstantAppsHandler", "Not handling with Instant Apps (other)", new Object[0]);
                }
                AbstractC3364kK0.k("Android.InstantApps.HandleIntentDuration", SystemClock.elapsedRealtime() - elapsedRealtime);
                return false;
            } else if (z || z2) {
                return false;
            } else {
                i();
                if (!TextUtils.isEmpty(str2)) {
                    uri = Uri.parse(str2);
                }
                Tab tab = this.b;
                Objects.requireNonNull(a2);
                boolean MaqKlsVX = N.MaqKlsVX(tab.l(), str);
                tab.l();
                if (MaqKlsVX) {
                    String host = Uri.parse(str).getHost();
                    if (uri == null || host == null || !host.equals(uri.getHost())) {
                        z3 = false;
                    }
                    if (z3) {
                        uri.toString();
                    }
                }
            }
        }
        return false;
    }
}
