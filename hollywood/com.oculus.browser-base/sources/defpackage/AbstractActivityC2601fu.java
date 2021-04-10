package defpackage;

import J.N;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.browser.customtabs.CustomTabsSessionToken;
import com.google.android.material.appbar.AppBarLayout;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.incognito.IncognitoTabLauncher;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.TasksView;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.vr.VrModuleProvider;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.Origin;

/* renamed from: fu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractActivityC2601fu extends ChromeActivity implements W {
    public C1570Zs0 A1 = new C1570Zs0();
    public long B1;
    public boolean C1;
    public AbstractC3780mo0 D1;
    public final C1570Zs0 E1 = new C1570Zs0();
    public final C1570Zs0 F1 = new C1570Zs0();
    public AbstractC2431eu0 G1;
    public C1078Rq0 H1 = new C1078Rq0();
    public final C1570Zs0 I1 = new C1570Zs0();
    public C1078Rq0 J1 = new C1078Rq0();
    public C1128Sl K1 = new C1128Sl();
    public C0986Qd1 L1;
    public final J00 M1 = new C1449Xt(this);
    public final C0004Ab0 f1 = new C0004Ab0();
    public final C2066cm0 g1 = new C2066cm0(this, this.p0, this.Z, this.Y, this);
    public C4126op1 h1;
    public AbstractC3838n70 i1;
    public ViewGroup j1;
    public ToolbarControlContainer k1;
    public C0551Ja1 l1;
    public AbstractC1099Sa1 m1;
    public AbstractC0855Oa1 n1;
    public C2202da1 o1;
    public boolean p1;
    public Boolean q1;
    public LocaleManager r1;
    public C1964c9 s1;
    public Runnable t1;
    public CompositorViewHolder u1;
    public C2090cu0 v1;
    public boolean w1;
    public boolean x1;
    public boolean y1;
    public C0348Fr z1;

    public static boolean A1(String str) {
        return TextUtils.equals(str, AbstractActivityC2601fu.class.getName()) || TextUtils.equals(str, AbstractActivityC1715am0.class.getName()) || TextUtils.equals(str, AbstractActivityC2430eu.class.getName()) || TextUtils.equals(str, "com.google.android.apps.chrome.Main");
    }

    public static void K1(Intent intent, ComponentName componentName) {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (TextUtils.equals(componentName.getPackageName(), applicationContext.getPackageName())) {
            if (componentName.getClassName() == null || !TextUtils.equals(componentName.getClassName(), "com.google.android.apps.chrome.Main")) {
                intent.setComponent(componentName);
            } else {
                intent.setClass(applicationContext, AbstractActivityC2601fu.class);
            }
        }
    }

    public static Tab r1(AbstractActivityC2601fu fuVar, String str, String str2, String str3, String str4, boolean z, boolean z2, Origin origin, Intent intent) {
        Tab j;
        String str5;
        if (fuVar.p1 && !AbstractC5154ur1.g(str)) {
            ((AbstractC3838n70) fuVar.G1).A(false);
            Uk1 uk1 = fuVar.b1.V;
            if (uk1.L0) {
                uk1.K.f9104a.e();
            }
        }
        if (TextUtils.equals(str4, fuVar.getPackageName())) {
            boolean d = U20.d(intent, "com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", false);
            LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
            loadUrlParams.k = fuVar.B1;
            loadUrlParams.f = str3;
            loadUrlParams.i = z2;
            loadUrlParams.b = origin;
            Integer j2 = S20.j(intent);
            if (j2 == null) {
                if (U20.d(intent, "com.android.chrome.invoked_from_shortcut", false)) {
                    j2 = 7;
                } else if (IncognitoTabLauncher.a(intent)) {
                    j2 = 10;
                } else {
                    j2 = 0;
                }
            }
            return fuVar.w1(d).i(loadUrlParams, j2.intValue(), null, intent);
        }
        if (C2510fK0.V()) {
            if (U20.g(intent.getExtras(), "org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT", -1) != -1) {
                Bundle extras = intent.getExtras();
                int g = U20.g(extras, "org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT", -1);
                extras.remove("org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT");
                if (!(g == -1 || fuVar.l1 == null)) {
                    return fuVar.v1().i(new LoadUrlParams(str, 0), 0, fuVar.l1.o(g), null);
                }
            }
        }
        C3623lt w12 = fuVar.w1(false);
        long j3 = fuVar.B1;
        boolean equals = TextUtils.equals(str4, w12.f10381a.getPackageName());
        if (!z || equals) {
            if (str4 == null) {
                str4 = "com.google.android.apps.chrome.unknown_app";
            }
            int i = 0;
            while (true) {
                if (i >= w12.f.getCount()) {
                    j = w12.j(str, 1, intent, j3);
                    T51.j(j).G = str4;
                    break;
                }
                Tab tabAt = w12.f.getTabAt(i);
                if (str4.equals(T51.k(tabAt))) {
                    LoadUrlParams loadUrlParams2 = new LoadUrlParams(str, 0);
                    loadUrlParams2.k = j3;
                    j = w12.h(loadUrlParams2, 1, null, i, intent);
                    T51.j(j).G = str4;
                    w12.f.q(tabAt, false, false, false);
                    break;
                }
                i++;
            }
            return j;
        }
        LoadUrlParams loadUrlParams3 = new LoadUrlParams(str, 0);
        loadUrlParams3.k = j3;
        if (str2 != null) {
            ComponentName componentName = S20.f8870a;
            int h = U20.h(intent, "android.support.browser.extra.referrer_policy", 1);
            if (h < 0 || h >= 8) {
                h = 1;
            }
            loadUrlParams3.d = new C2512fL0(str2, h);
        }
        if (S20.A(intent)) {
            String n = U20.n(intent, "com.android.chrome.post_data_type");
            byte[] f = U20.f(intent, "com.android.chrome.post_data");
            if (!(TextUtils.isEmpty(n) || f == null || f.length == 0)) {
                StringBuilder j4 = AbstractC2531fV.j("Content-Type: ", n);
                if (TextUtils.isEmpty(str3)) {
                    str5 = j4.toString();
                } else {
                    StringBuilder j5 = AbstractC2531fV.j(str3, "\r\n");
                    j5.append(j4.toString());
                    str5 = j5.toString();
                }
                str3 = str5;
                loadUrlParams3.h = ResourceRequestBody.a(f);
            }
        }
        loadUrlParams3.f = str3;
        return w12.i(loadUrlParams3, 1, null, intent);
    }

    public static LoadUrlParams s1(String str, String str2, boolean z, long j, Intent intent) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
        loadUrlParams.k = j;
        loadUrlParams.m = z;
        loadUrlParams.c = S20.k(intent, 134217728);
        if (str2 != null) {
            int i = 1;
            int h = U20.h(intent, "android.support.browser.extra.referrer_policy", 1);
            if (h >= 0 && h < 8) {
                i = h;
            }
            loadUrlParams.d = new C2512fL0(str2, i);
        }
        return loadUrlParams;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public boolean A0(Tab tab) {
        if (!tab.isInitialized()) {
            return false;
        }
        int F = tab.F();
        if (F == 0 || F == 1 || F == 4 || F == 5 || (F == 3 && C5383wB.q(tab).Q != -1)) {
            return true;
        }
        return false;
    }

    public final void B1() {
        Profile profile;
        if (N.M09VlOh_("ToolbarIphAndroid") && (profile = (Profile) ((C1078Rq0) this.q0).H) != null) {
            Um1.a(profile).notifyEvent("tab_switcher_button_clicked");
        }
        if (!((ST) S0()).a()) {
            AbstractC2300e70 e70 = this.i1.S;
            if ((e70 instanceof C3565lZ0) && !e70.Q) {
                AbstractC3535lK0.a("MobileToolbarStackViewButtonInStackView");
            } else if (!e1()) {
                AbstractC3535lK0.a("MobileToolbarStackViewButtonInBrowsingView");
            }
            if (!e1() || AbstractC2793h01.b()) {
                Q1(8);
                return;
            }
            AbstractC2300e70 e702 = this.i1.S;
            if (e702 instanceof C3565lZ0) {
                ((C3565lZ0) e702).b0(SystemClock.uptimeMillis());
            }
            if (Q0().getCount() != 0) {
                ((AbstractC3838n70) this.G1).A(true);
                R1(true);
            }
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public R20 C0() {
        return new C2089cu(this, null);
    }

    public final /* synthetic */ void C1() {
        ((AbstractC0246Ea1) P()).l(false).d();
        v1().e();
        this.r1.f(this, null);
        if (((AbstractC0246Ea1) P()).r()) {
            AbstractC3535lK0.a("MobileToolbarStackViewNewIncognitoTab");
        } else {
            AbstractC3535lK0.a("MobileToolbarStackViewNewTab");
        }
        AbstractC3535lK0.a("MobileTopToolbarNewTabButton");
        AbstractC3535lK0.a("MobileNewTabOpened");
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public C0742Md1 D0() {
        return new C0742Md1();
    }

    public final /* synthetic */ void D1() {
        z0(K0());
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public GN0 E0() {
        return new C5285ve1(this, new C5668xt(this), this.A1, this.K0, this.W0, this.H1, this.q0, this.r0, new C5838yt(this), this.p0, this.I1, this.E1, this.J1);
    }

    public final void E1(Intent intent) {
        C1128Sl sl;
        if (N.M09VlOh_("ReengagementNotification") && (sl = this.K1) != null) {
            new C1326Vs0(this.q0, sl.b(new C5328vt()));
        }
        C0004Ab0 ab0 = this.f1;
        long f = this.z1.f();
        long j = -1;
        if (f != -1) {
            j = System.currentTimeMillis() - f;
        }
        Objects.requireNonNull(ab0);
        AbstractC3535lK0.a("MobileStartup.MainIntentReceived");
        if (j >= 86400000) {
            AbstractC3535lK0.a("MobileStartup.MainIntentReceived.After24Hours");
        } else if (j >= 43200000) {
            AbstractC3535lK0.a("MobileStartup.MainIntentReceived.After12Hours");
        } else if (j >= 21600000) {
            AbstractC3535lK0.a("MobileStartup.MainIntentReceived.After6Hours");
        } else if (j >= 3600000) {
            AbstractC3535lK0.a("MobileStartup.MainIntentReceived.After1Hour");
        }
        ab0.a(true);
    }

    @Override // defpackage.AbstractC3083ik, defpackage.AbstractActivityC0731Ma
    public void F() {
        TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.startNativeInitialization");
        try {
            if (N.M09VlOh_("DarkenWebsitesCheckboxInThemesSetting") && C5172ux1.f11449a == null) {
                C5172ux1.f11449a = new C5172ux1();
            }
            M1();
            C3070if1 if1 = Zo1.f9374a;
            PostTask.b(if1, new RunnableC1327Vt(this), 0);
            PostTask.b(if1, new RunnableC1388Wt(this), 0);
            PostTask.b(if1, new RunnableC4478qt(this), 0);
            PostTask.b(if1, new RunnableC4648rt(this), 0);
            PostTask.b(if1, new RunnableC4818st(this), 0);
            PostTask.b(if1, new RunnableC4988tt(this), 0);
            PostTask.b(if1, new RunnableC5158ut(this), 0);
            C0283Ep h = C0283Ep.h();
            h.c().b(this);
            l(h.d());
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public Pair F0() {
        C1918bu buVar = AbstractC2793h01.b() ? new C1918bu(this) : null;
        C2971i3 i3Var = this.b0;
        C4675s11 U0 = U0();
        C0352Ft ft = new C0352Ft(this);
        C1280Va va = AbstractC1341Wa.f9155a;
        return Pair.create(new C3623lt(this, this, i3Var, U0, ft, false, buVar, va), new C3623lt(this, this, this.b0, U0(), new C0413Gt(this), true, buVar, va));
    }

    public final int F1(Intent intent, int i) {
        boolean z;
        Object[] objArr;
        String m;
        Uri uri;
        C4649rt0 b;
        if (!getClass().equals(AbstractActivityC2601fu.class) || !"android.intent.action.VIEW".equals(intent.getAction()) || intent.getComponent() == null || !"com.google.android.apps.chrome.Main".equals(intent.getComponent().getClassName())) {
            return 0;
        }
        Intent r = U20.r(intent);
        if (r != null) {
            ComponentName componentName = S20.f8870a;
            if (r.getLongExtra("org.chromium.chrome.browser.timestamp", -1) == -1) {
                r.putExtra("org.chromium.chrome.browser.timestamp", SystemClock.elapsedRealtime());
            }
        }
        int c = S20.c(r);
        if (r.getPackage() == null && c != 5) {
            AbstractC3100ip1.f10165a.d("Launch.IntentFlags", 268959744 & r.getFlags());
        }
        int intExtra = r.getIntExtra("org.chromium.chrome.browser.metrics.MediaNotificationUma.EXTRA_CLICK_SOURCE", -1);
        if (intExtra != -1 && intExtra < 3) {
            AbstractC3364kK0.g("Media.Notification.Click", intExtra, 3);
        }
        VrModuleProvider.d().b(r);
        if (!Z60.e(r)) {
            z = false;
        } else {
            CustomTabsConnection f = CustomTabsConnection.f();
            CustomTabsSessionToken b2 = CustomTabsSessionToken.b(r);
            Objects.requireNonNull(f);
            String m2 = S20.m(r);
            if (!TextUtils.isEmpty(m2)) {
                if (f.g) {
                    AbstractC1220Ua0.f("ChromeConnection", "onHandledIntent, URL: %s, extras: %s", m2, CustomTabsConnection.a(r.getExtras()));
                }
                if (f.j != null) {
                    C4630rn rnVar = f.j;
                    Objects.requireNonNull(rnVar);
                    if (ThreadUtils.i()) {
                        rnVar.b = true;
                        rnVar.f11221a.clear();
                    } else {
                        throw new IllegalStateException("Must call cancel() from the UI thread.");
                    }
                }
                if (C1321Vq.b().h && N.M09VlOh_("CCTRedirectPreconnect") && (uri = (Uri) r.getParcelableExtra("androidx.browser.REDIRECT_ENDPOINT")) != null && CustomTabsConnection.i(uri) && (b = C4649rt0.b(m2)) != null && f.f.e(b2, b)) {
                    Bw1.a().c(Profile.b(), uri.toString());
                }
                C1321Vq.b().i(new RunnableC2144dC(f, b2, r));
                Object obj = ThreadUtils.f10596a;
                C3287jv jvVar = f.f;
                synchronized (jvVar) {
                    C3116iv ivVar = (C3116iv) jvVar.b.get(b2);
                }
            }
            if (!U20.d(r, "android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", false)) {
                Objects.requireNonNull(AbstractApplicationC3785mq.g().d());
                CustomTabsSessionToken.b(r);
            }
            if (!(r == null || !"android.intent.action.VIEW".equals(r.getAction()) || (m = S20.m(r)) == null)) {
                Bw1 a2 = Bw1.a();
                Objects.requireNonNull(a2);
                Object obj2 = ThreadUtils.f10596a;
                a2.b.add(m);
                C6019zw1 zw1 = new C6019zw1(a2, m);
                Executor executor = AbstractC2032cb.f9616a;
                zw1.f();
                ((ExecutorC1463Ya) executor).execute(zw1.e);
            }
            Intent d = Z60.d(this, r);
            P21 g0 = P21.g0();
            try {
                if (((!U20.d(d, "android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", false) || (U20.k(d, "androidx.browser.trusted.EXTRA_SPLASH_SCREEN_PARAMS") != null ? 1 : null) == null) ? null : 1) == null) {
                    objArr = null;
                } else {
                    if (U20.c(U20.e(d, "androidx.browser.trusted.EXTRA_SPLASH_SCREEN_PARAMS"), "androidx.browser.trusted.KEY_SPLASH_SCREEN_SHOWN_IN_CLIENT", true)) {
                        d.setClassName(this, AbstractActivityC5822yn1.class.getName());
                    }
                    d.addFlags(65536);
                    startActivity(d);
                    overridePendingTransition(0, 0);
                    objArr = 1;
                }
                if (objArr == null) {
                    startActivity(d, null);
                }
                g0.close();
                z = true;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        if (i == 1) {
            AbstractC3100ip1.f10165a.a("Android.MainActivity.ExplicitMainViewIntentDispatched.OnCreate", z);
        } else if (i == 2) {
            AbstractC3100ip1.f10165a.a("Android.MainActivity.ExplicitMainViewIntentDispatched.OnNewIntent", z);
        }
        if (!z) {
            int c2 = S20.c(intent);
            AbstractC3364kK0.g("Android.MainActivity.UndispatchedExplicitMainViewIntentSource", c2, 16);
            if (c2 == 5 && (getApplicationInfo().flags & 2) != 0 && !AbstractC1575Zv.e().g("dont-crash-on-view-main-intents")) {
                String intent2 = intent.toString();
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    StringBuilder j = AbstractC2531fV.j(intent2, ", extras.keySet = [");
                    j.append(TextUtils.join(", ", extras.keySet()));
                    j.append("]");
                    intent2 = j.toString();
                }
                throw new IllegalStateException(String.format(null, "VIEW intent sent to .Main activity alias was not dispatched. PLEASE report the following info to crbug.com/789732: \"%s\". Use --%s flag to disable this check.", intent2, "dont-crash-on-view-main-intents"));
            }
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = z ? 1 : 0;
        return i2;
        throw th;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public AbstractC0124Ca1 G0() {
        Bundle bundle = this.d0;
        int i = 0;
        boolean z = bundle != null && bundle.getBoolean("is_incognito_selected", false);
        if (bundle != null) {
            i = bundle.getInt("window_index", 0);
        }
        this.D1 = new C3449ks(this.F1);
        C0551Ja1 ja1 = (C0551Ja1) AbstractC0621Kd1.a().b(this, this, this.D1, i);
        this.l1 = ja1;
        if (ja1 == null) {
            C1184Ti1.b(this, getString(R.string.f64040_resource_name_obfuscated_RES_2131953721), 1).b.show();
            finish();
            return null;
        }
        ja1.c(new C1571Zt(this));
        this.m1 = new C1747au(this, this.l1);
        this.s1 = new C1964c9(this.l1);
        if (z) {
            this.l1.e(true);
        }
        return this.l1;
    }

    public final void G1() {
        Boolean bool;
        if (this.p1) {
            boolean a2 = C5052uE.a();
            AbstractC2431eu0 eu0 = this.G1;
            if (eu0 != null && ((AbstractC3838n70) eu0).C() && ((bool = this.q1) == null || bool.booleanValue() != C5052uE.a())) {
                ((AbstractC3838n70) this.G1).A(true);
                if (((AbstractC0246Ea1) P()).i().getCount() == 0) {
                    v1().e();
                }
            }
            this.q1 = Boolean.valueOf(a2);
            if (C0283Ep.h().d()) {
                AbstractC3100ip1.f10165a.a("Accessibility.Android.TabSwitcherPreferenceEnabled", this.q1.booleanValue());
            }
        }
    }

    public final void H1(boolean z) {
        ((ShortcutManager) getSystemService(ShortcutManager.class)).reportShortcutUsed(z ? "new-incognito-tab-shortcut" : "new-tab-shortcut");
    }

    public final void I1(Tab tab) {
        AbstractC1220Ua0.d("ChromeTabbedActivity", "sendToBackground(): " + tab, new Object[0]);
        moveTaskToBack(true);
        if (tab != null) {
            this.W.postDelayed(new RunnableC0596Jt(this, tab), 500);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x010b  */
    @Override // defpackage.AbstractC3083ik, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void J() {
        /*
        // Method dump skipped, instructions count: 281
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractActivityC2601fu.J():void");
    }

    public final void J1() {
        if (!this.y1) {
            this.y1 = true;
            boolean C = ((AbstractC3838n70) this.G1).C();
            if (!P1() || C) {
                if (K0() == null && !C) {
                    this.C1 = true;
                    L1(false);
                    Q1(9);
                }
                if (z1(getIntent()) && ((AbstractC3838n70) this.G1).C()) {
                    AbstractC3535lK0.a("MobileStartup.UserEnteredTabSwitcher");
                    return;
                }
                return;
            }
            if (Q0() != null) {
                AbstractC3364kK0.d("Tabs.TabCountOnStartScreenShown", Q0().getCount());
            }
            if (AbstractC4772sd1.b() && !this.h0) {
                M01 m01 = ((C3818n01) ((AbstractC2451f01) this.I1.get())).c;
                m01.G.g(this.a0);
            }
            this.C1 = true;
            L1(false);
            Q1(9);
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public int L0() {
        return 0;
    }

    public final void L1(boolean z) {
        if (z) {
            C2287e3 e3Var = this.V0;
            e3Var.c = ".Tabbed";
            e3Var.f = true;
        } else {
            C2287e3 e3Var2 = this.V0;
            if (e3Var2.f) {
                e3Var2.c = null;
                e3Var2.f = false;
            }
        }
        AbstractC3992o11.f10528a = z;
    }

    public final void M1() {
        TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.setupCompositorContentPostNative");
        try {
            if (!(this.i1 != null)) {
                if (this.h0) {
                    O1();
                } else {
                    N1();
                }
            }
            this.i1.w0 = C5052uE.b();
            b1(this.i1, findViewById(R.id.url_bar), this.j1, this.k1);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public int N0() {
        return R.dimen.f17820_resource_name_obfuscated_RES_2131165401;
    }

    public final void N1() {
        if (!this.h0) {
            TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.setupCompositorContentPreNativeForPhone");
            try {
                CompositorViewHolder compositorViewHolder = this.I0;
                if ((AbstractC4772sd1.b() || AbstractC2793h01.d()) && AbstractC1680aa1.a() != null) {
                    GN0 gn0 = this.b1;
                    new C3818n01(this, gn0.c0, gn0.a0, this.I1, this.J1, this.i0);
                }
                ViewGroup viewGroup = this.j1;
                AbstractC2451f01 f01 = (AbstractC2451f01) this.I1.get();
                C1078Rq0 rq0 = this.w0;
                C4307pt ptVar = new C4307pt(this);
                C1570Zs0 zs0 = this.F1;
                C1570Zs0 zs02 = this.E1;
                GN0 gn02 = this.b1;
                gn02.getClass();
                C4180p70 p70 = new C4180p70(compositorViewHolder, viewGroup, f01, rq0, ptVar, zs0, zs02, new C0047At(gn02));
                this.i1 = p70;
                this.G1 = p70;
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }

    @Override // defpackage.AbstractC4356q9, org.chromium.chrome.browser.app.ChromeActivity
    public boolean O() {
        if (!this.p1) {
            return false;
        }
        Tab K0 = K0();
        if (K0 == null || !C3965nt.n(K0)) {
            return super.O();
        }
        return false;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public int O0() {
        return R.layout.f37590_resource_name_obfuscated_RES_2131624068;
    }

    public final void O1() {
        if (this.h0) {
            TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.setupCompositorContentPreNativeForTablet");
            try {
                CompositorViewHolder compositorViewHolder = this.I0;
                ViewGroup viewGroup = this.j1;
                C1078Rq0 rq0 = this.w0;
                C0717Lt lt = new C0717Lt(this);
                C1570Zs0 zs0 = this.F1;
                C1570Zs0 zs02 = this.E1;
                GN0 gn0 = this.b1;
                gn0.getClass();
                C4521r70 r70 = new C4521r70(compositorViewHolder, viewGroup, rq0, lt, zs0, zs02, new C1022Qt(gn0));
                this.i1 = r70;
                this.G1 = r70;
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0088, code lost:
        if (r0 == 0) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0099, code lost:
        if (java.lang.System.currentTimeMillis() > (r3 + ((long) r0))) goto L_0x008a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean P1() {
        /*
        // Method dump skipped, instructions count: 161
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractActivityC2601fu.P1():boolean");
    }

    public final void Q1(int i) {
        if (this.I1.get() != null) {
            ((C3818n01) ((AbstractC2451f01) this.I1.get())).c.l(i);
        }
        AbstractC2431eu0 eu0 = this.G1;
        if (eu0 != null) {
            if (!((AbstractC3838n70) eu0).C()) {
                if (K0() == null) {
                    ((AbstractC3838n70) this.G1).E(false);
                    return;
                }
                this.I0.u(new RunnableC0778Mt(this));
                R1(false);
                TabModel Q0 = Q0();
                int count = Q0.getCount();
                if (count != 0) {
                    int i2 = 0;
                    int i3 = 0;
                    int i4 = 0;
                    int i5 = 0;
                    for (int i6 = 0; i6 < count; i6++) {
                        Integer num = C5383wB.q(Q0.getTabAt(i6)).X;
                        if (num != null) {
                            if (num.intValue() == 2 || num.intValue() == 12 || num.intValue() == 5 || num.intValue() == 7) {
                                i2++;
                            } else {
                                if (num.intValue() == 4) {
                                    i3++;
                                } else if (num.intValue() == 1 || num.intValue() == 10) {
                                    i4++;
                                }
                            }
                        }
                        i5++;
                    }
                    AbstractC3364kK0.d("Tabs.Tasks.TabCreated.Count.FromManuallyCreated", i2);
                    AbstractC3364kK0.d("Tabs.Tasks.TabCreated.Count.FromTargetBlank", i3);
                    AbstractC3364kK0.d("Tabs.Tasks.TabCreated.Count.FromExternalApp", i4);
                    AbstractC3364kK0.d("Tabs.Tasks.TabCreated.Count.FromOthers", i5);
                    AbstractC3364kK0.g("Tabs.Tasks.TabCreated.Percent.FromManuallyCreated", (i2 * 100) / count, 101);
                    AbstractC3364kK0.g("Tabs.Tasks.TabCreated.Percent.FromTargetBlank", (i3 * 100) / count, 101);
                    AbstractC3364kK0.g("Tabs.Tasks.TabCreated.Percent.FromExternalApp", (i4 * 100) / count, 101);
                    AbstractC3364kK0.g("Tabs.Tasks.TabCreated.Percent.FromOthers", (i5 * 100) / count, 101);
                }
            } else if (this.E0) {
                this.I0.u(new RunnableC0657Kt());
            }
        }
    }

    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void R(Intent intent) {
        try {
            TraceEvent.Y("ChromeTabbedActivity.onNewIntentWithNative", null);
            super.R(intent);
            if (z1(intent)) {
                E1(intent);
            }
            if (AbstractC1575Zv.e().g("enable-test-intents")) {
                y1(intent);
            }
        } finally {
            TraceEvent.f0("ChromeTabbedActivity.onNewIntentWithNative");
        }
    }

    public final void R1(boolean z) {
        Tab K0 = K0();
        WebContents l = K0 != null ? K0.l() : null;
        if (l != null) {
            ((WebContentsAccessibilityImpl) AbstractC3637lx1.a(l)).v(z);
        }
    }

    @Override // defpackage.KO0, org.chromium.chrome.browser.app.ChromeActivity
    public void T(AbstractC2300e70 e70) {
        Objects.requireNonNull(e70);
        if (!(e70 instanceof D11)) {
            Objects.requireNonNull(this.l1);
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public AbstractC1509Ys0 T0() {
        return this.F1;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public int Y0() {
        return this.h0 ? R.layout.f42060_resource_name_obfuscated_RES_2131624515 : R.layout.f42050_resource_name_obfuscated_RES_2131624514;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x006e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006f  */
    @Override // org.chromium.chrome.browser.app.ChromeActivity
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean Z0() {
        /*
        // Method dump skipped, instructions count: 317
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractActivityC2601fu.Z0():boolean");
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public void a1() {
        super.a1();
        C2150dE.b().a(new RunnableC0474Ht(this));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b1, code lost:
        if (r0 == false) goto L_0x00b3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cd A[SYNTHETIC, Splitter:B:35:0x00cd] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01eb  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01f3  */
    /* JADX WARNING: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
        // Method dump skipped, instructions count: 503
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractActivityC2601fu.b():void");
    }

    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void c() {
        this.l1.d();
        try {
            N.M6wmuchs();
        } catch (RuntimeException e) {
            AbstractC0754Mh1.f8495a.b(e);
        }
        LocaleManager localeManager = this.r1;
        Objects.requireNonNull(localeManager);
        localeManager.c = new WeakReference(null);
        Objects.requireNonNull(this.r1);
        N.MnSIHeV3();
        super.c();
    }

    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void d() {
        C0004Ab0 ab0 = this.f1;
        Objects.requireNonNull(ab0);
        if (!C0004Ab0.f7680a) {
            ThreadUtils.b().postDelayed(ab0.b, 10000);
        }
        super.d();
        if (!this.x1) {
            J1();
        }
        if (AbstractC4772sd1.a() || AbstractC3293jx.b()) {
            long f = this.z1.f();
            long c = ((long) AbstractC3293jx.f10251a.c()) + f;
            if (f == -1 || System.currentTimeMillis() > c) {
                PU0 pu0 = NU0.f8549a;
                long h = pu0.h("Chrome.ConditionalTabStrip.LastShownTimeStamp", -1);
                int a2 = AbstractC3293jx.a();
                if (AbstractC3293jx.b()) {
                    AbstractC3293jx.c(4);
                } else if (h == -1) {
                    AbstractC3293jx.c(0);
                } else if (a2 == 2) {
                    AbstractC3293jx.c(1);
                } else if (a2 == 1) {
                    AbstractC3293jx.c(2);
                } else if (a2 == 0) {
                    AbstractC3293jx.c(3);
                }
                int f2 = pu0.f("Chrome.ConditionalTabStrip.ContinuousDismissCounter", 0);
                int i = -1;
                if (f2 != -1) {
                    if (AbstractC3293jx.a() == 1) {
                        AbstractC3293jx.d(0);
                    } else if (AbstractC3293jx.a() == 0) {
                        int i2 = f2 + 1;
                        if (i2 < AbstractC3293jx.b.c()) {
                            i = i2;
                        }
                        AbstractC3293jx.d(i);
                    }
                }
                AbstractC3293jx.e(2);
            }
        }
        this.d0 = null;
        AbstractC2793h01.f();
        PrefChangeRegistrar prefChangeRegistrar = new PrefChangeRegistrar();
        prefChangeRegistrar.f10748a.put("ntp_snippets.list_visible", new C2622g01());
        N.Mrf8X6ah(prefChangeRegistrar.b, prefChangeRegistrar, "ntp_snippets.list_visible");
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3119iw
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z = this.p1;
        int keyCode = keyEvent.getKeyCode();
        Boolean bool = null;
        if (!z) {
            if (keyCode == 84 || keyCode == 82) {
                bool = Boolean.TRUE;
            }
        } else if (keyCode == 82) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                k1(R.id.show_menu, false);
            }
            bool = Boolean.TRUE;
        } else if (keyCode == 84) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                k1(R.id.focus_url_bar, false);
            }
            bool = Boolean.TRUE;
        } else if (keyCode != 111) {
            switch (keyCode) {
                default:
                    switch (keyCode) {
                    }
                case 170:
                case 171:
                case 172:
                case 173:
                    bool = Boolean.FALSE;
                    break;
            }
        } else if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0 && I0()) {
            bool = Boolean.TRUE;
        }
        return bool != null ? bool.booleanValue() : super.dispatchKeyEvent(keyEvent);
    }

    @Override // defpackage.AbstractC2418eq, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void e() {
        super.e();
        this.l1.y();
        this.y1 = false;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public boolean e1() {
        AbstractC2431eu0 eu0 = this.G1;
        return eu0 != null && ((AbstractC3838n70) eu0).C();
    }

    @Override // defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public C2746gl0 i0() {
        C2746gl0 gl0 = new C2746gl0(new J9(this), 0);
        this.o1 = new C2202da1(this, gl0, new C0900Ot(this), new C0961Pt(this));
        return gl0;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public void j1() {
        AbstractC4096of1 of1;
        TasksView tasksView;
        AppBarLayout appBarLayout;
        C1128Sl sl = this.K1;
        if (sl != null) {
            sl.a();
            this.K1 = null;
        }
        AbstractC1099Sa1 sa1 = this.m1;
        if (sa1 != null) {
            sa1.destroy();
            this.m1 = null;
        }
        AbstractC0855Oa1 oa1 = this.n1;
        if (oa1 != null) {
            oa1.destroy();
        }
        C4126op1 op1 = this.h1;
        if (op1 != null) {
            TabModel l = ((AbstractC0246Ea1) op1.f10579a).l(false);
            if (l != null) {
                l.w(op1.b);
            }
            C1128Sl sl2 = op1.e;
            if (sl2 != null) {
                sl2.a();
                op1.e = null;
            }
            this.h1 = null;
        }
        C1964c9 c9Var = this.s1;
        if (c9Var != null) {
            AbstractC1099Sa1 sa12 = c9Var.b;
            if (sa12 != null) {
                sa12.destroy();
            }
            this.s1 = null;
        }
        if (!(this.I1.get() == null || (of1 = ((C3818n01) ((AbstractC2451f01) this.I1.get())).g) == null || (appBarLayout = (tasksView = ((C4778sf1) of1).b).i0) == null)) {
            H7 h7 = tasksView.j0;
            List list = appBarLayout.M;
            if (!(list == null || h7 == null)) {
                list.remove(h7);
            }
        }
        K00.a().b.remove(this.M1);
        if (W0() != null) {
            W0().b.c(this.u1);
            W0().b.c(this.v1);
        }
        if (this.h0) {
            C0283Ep.h().f(this.u1);
        }
        C0283Ep.h().c().c(this);
        C0283Ep.h().f(this.i1);
        C0986Qd1 qd1 = this.L1;
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public final void k0() {
        super.k0();
        if (AbstractC2793h01.b() && AbstractC4772sd1.k(this.h0) && !this.i0) {
            TraceEvent j0 = TraceEvent.j0("ChromeTabbedActivity.prepareToShowStartPagePreNative");
            try {
                N1();
                CompositorViewHolder compositorViewHolder = this.I0;
                compositorViewHolder.K = this.i1;
                compositorViewHolder.B();
                if (P1()) {
                    this.i1.D(this.l1);
                    this.q1 = Boolean.valueOf(C5052uE.a());
                    J1();
                }
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public boolean k1(int i, boolean z) {
        Tab K0 = K0();
        boolean z2 = false;
        boolean z3 = K0 != null && AbstractC5154ur1.g(K0.s());
        if (i == R.id.new_tab_menu_id) {
            ((AbstractC0246Ea1) P()).l(false).d();
            AbstractC3535lK0.a("MobileMenuNewTab");
            AbstractC3535lK0.a("MobileNewTabOpened");
            H1(false);
            if (z) {
                AbstractC3535lK0.a("MobileMenuNewTab.AppMenu");
            }
            w1(false).e();
            this.r1.f(this, null);
        } else if (i == R.id.new_incognito_tab_menu_id) {
            if (N.M$3vpOHw()) {
                ((AbstractC0246Ea1) P()).l(false).d();
                AbstractC3535lK0.a("MobileMenuNewIncognitoTab");
                AbstractC3535lK0.a("MobileNewTabOpened");
                H1(true);
                if (z) {
                    AbstractC3535lK0.a("MobileMenuNewIncognitoTab.AppMenu");
                }
                w1(true).e();
            }
        } else if (i == R.id.all_bookmarks_menu_id) {
            this.I0.u(new RunnableC0535It(this));
            if (z3) {
                AbstractC1499Yn0.a(6);
            }
            AbstractC3535lK0.a("MobileMenuAllBookmarks");
        } else if (i == R.id.recent_tabs_menu_id) {
            LoadUrlParams loadUrlParams = new LoadUrlParams("chrome-native://recent-tabs/", 2);
            if (K0 != null) {
                K0.c(loadUrlParams);
            } else {
                w1(Q0().a()).i(loadUrlParams, 2, null, null);
            }
            if (e1() && !this.h0) {
                ((AbstractC3838n70) this.G1).A(true);
            }
            if (z3) {
                AbstractC1499Yn0.a(4);
            }
            AbstractC3535lK0.a("MobileMenuRecentTabs");
        } else if (i == R.id.close_tab) {
            Q0().q(K0, true, false, true);
            AbstractC3535lK0.a("MobileTabClosed");
        } else if (i == R.id.close_all_tabs_menu_id) {
            ((AbstractC0246Ea1) P()).g(false);
            AbstractC3535lK0.a("MobileMenuCloseAllTabs");
        } else if (i == R.id.close_all_incognito_tabs_menu_id) {
            ((AbstractC0246Ea1) P()).l(true).k();
            AbstractC3535lK0.a("MobileMenuCloseAllTabs");
        } else if (i == R.id.focus_url_bar) {
            if (!((AbstractC3838n70) this.G1).C() && (!this.h0 || Q0().getCount() != 0)) {
                z2 = true;
            }
            if (z2) {
                this.b1.V.p(true, 11);
            }
        } else if (i == R.id.downloads_menu_id) {
            DownloadUtils.e(this, K0, 9);
            if (z3) {
                AbstractC1499Yn0.a(7);
            }
            AbstractC3535lK0.a("MobileMenuDownloadManager");
        } else if (i == R.id.open_recently_closed_tab) {
            TabModel i2 = this.l1.i();
            if (!i2.a()) {
                i2.p();
            }
            AbstractC3535lK0.a("MobileTabClosedUndoShortCut");
        } else if (i != R.id.enter_vr_id) {
            return super.k1(i, z);
        } else {
            Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
        }
        return true;
    }

    @Override // defpackage.W
    public void l(boolean z) {
        G1();
    }

    @Override // defpackage.AbstractC5207v9, org.chromium.chrome.browser.app.ChromeActivity
    public B9 m() {
        return new C0803Nd1(this, this.W0, this.Z, P(), this.b1.V, getWindow().getDecorView(), this, this.F1, this.r0, l0());
    }

    public boolean moveTaskToBack(boolean z) {
        try {
            return super.moveTaskToBack(z);
        } catch (NullPointerException unused) {
            finish();
            return true;
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public void n1(long j) {
        AbstractC3364kK0.k("MobileStartup.IntentToCreationTime", j);
        AbstractC3364kK0.f("MobileStartup.IntentToCreationTime.TabbedMode", j, 1, 30000, 50);
    }

    @Override // defpackage.I7
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z;
        if (!this.p1) {
            return super.onKeyDown(i, keyEvent);
        }
        if (i != 4 || this.h0 || ((ST) S0()).a()) {
            boolean z2 = !((AbstractC3838n70) this.G1).C() && (!this.h0 || Q0().getCount() != 0);
            int keyCode = keyEvent.getKeyCode();
            if (keyEvent.getRepeatCount() == 0 && !KeyEvent.isModifierKey(keyCode) && (!KeyEvent.isGamepadButton(keyCode) ? keyEvent.isCtrlPressed() || keyEvent.isAltPressed() || keyCode == 133 || keyCode == 135 || keyCode == 140 || keyCode == 125 : !CU.f7812a.e)) {
                TabModel Q0 = Q0();
                int count = Q0.getCount();
                int i2 = (keyEvent.isCtrlPressed() ? Integer.MIN_VALUE : 0) | (keyEvent.isAltPressed() ? 1073741824 : 0) | (keyEvent.isShiftPressed() ? 536870912 : 0);
                int i3 = keyCode | i2;
                int i4 = R.id.new_incognito_tab_menu_id;
                switch (i3) {
                    case -2147483606:
                        k1(R.id.new_tab_menu_id, false);
                        z = true;
                        break;
                    case -2147483600:
                        if (!Q0.a()) {
                            i4 = R.id.new_tab_menu_id;
                        }
                        k1(i4, false);
                        z = true;
                        break;
                    case -1610612694:
                        k1(R.id.new_incognito_tab_menu_id, false);
                        z = true;
                        break;
                    case -1610612688:
                        k1(R.id.open_recently_closed_tab, false);
                        z = true;
                        break;
                    case 100:
                    case 140:
                    case 1073741857:
                    case 1073741858:
                        k1(R.id.show_menu, false);
                        z = true;
                        break;
                    default:
                        if (z2) {
                            if (i2 == Integer.MIN_VALUE || i2 == 1073741824) {
                                int i5 = keyCode - 7;
                                if (i5 <= 0 || i5 > Math.min(count, 8)) {
                                    if (i5 == 9 && count != 0) {
                                        Q0.x(count - 1, 3);
                                        z = true;
                                        break;
                                    }
                                } else {
                                    Q0.x(i5 - 1, 3);
                                    z = true;
                                }
                            }
                            switch (i3) {
                                case -2147483641:
                                    Hz1.a(K0().l(), -1.0f);
                                    z = true;
                                    break;
                                case -2147483616:
                                case 174:
                                    k1(R.id.bookmark_this_page_id, false);
                                    z = true;
                                    break;
                                case -2147483614:
                                case -2147483613:
                                case -1610612701:
                                case 133:
                                case 536871045:
                                    k1(R.id.find_in_page_id, false);
                                    z = true;
                                    break;
                                case -2147483612:
                                    k1(R.id.open_history_menu_id, false);
                                    z = true;
                                    break;
                                case -2147483608:
                                case 99:
                                case 1073741856:
                                    k1(R.id.focus_url_bar, false);
                                    z = true;
                                    break;
                                case -2147483604:
                                    k1(R.id.print_id, false);
                                    z = true;
                                    break;
                                case -2147483602:
                                case -1610612690:
                                case 135:
                                case 536871047:
                                    Tab K0 = K0();
                                    if (K0 != null) {
                                        if ((i3 & 536870912) == 536870912) {
                                            K0.B();
                                        } else {
                                            K0.q();
                                        }
                                        if (this.b1.V != null && K0.l() != null && K0.l().Z()) {
                                            this.b1.V.d0.x();
                                        } else if (K0.b() != null) {
                                            K0.b().requestFocus();
                                        }
                                    }
                                    z = true;
                                    break;
                                case -2147483597:
                                case -2147483514:
                                case 97:
                                    Tab c = AbstractC1160Ta1.c(Q0);
                                    if (c != null) {
                                        Q0.h(c);
                                    }
                                    z = true;
                                    break;
                                case -2147483587:
                                case -2147483555:
                                case 103:
                                    if (count > 1) {
                                        Q0.x((Q0.index() + 1) % count, 3);
                                    }
                                    z = true;
                                    break;
                                case -2147483579:
                                case 169:
                                    Hz1.a(K0().l(), 0.8f);
                                    z = true;
                                    break;
                                case -2147483578:
                                case -2147483567:
                                case -1610612666:
                                case -1610612655:
                                case 168:
                                    Hz1.a(K0().l(), 1.25f);
                                    z = true;
                                    break;
                                case -2147483556:
                                case -1610612675:
                                case 102:
                                    if (count > 1) {
                                        Q0.x(((Q0.index() + count) - 1) % count, 3);
                                    }
                                    z = true;
                                    break;
                                case -1610612706:
                                    k1(R.id.all_bookmarks_menu_id, false);
                                    z = true;
                                    break;
                                case -1610612660:
                                    k1(R.id.help_id, false);
                                    z = true;
                                    break;
                                case 108:
                                case 125:
                                case 1073741846:
                                    Tab K02 = K0();
                                    if (K02 != null && K02.k()) {
                                        K02.j();
                                    }
                                    z = true;
                                    break;
                                case 1073741845:
                                    Tab K03 = K0();
                                    if (K03 != null && K03.h()) {
                                        K03.e();
                                    }
                                    z = true;
                                    break;
                            }
                        }
                        break;
                }
                return z || super.onKeyDown(i, keyEvent);
            }
            z = false;
            if (z) {
                return true;
            }
        }
        if (this.t1 == null) {
            this.t1 = new RunnableC0839Nt(this);
        }
        this.W.postDelayed(this.t1, (long) ViewConfiguration.getLongPressTimeout());
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4 && !this.h0) {
            this.W.removeCallbacks(this.t1);
            this.t1 = null;
            if (keyEvent.getEventTime() - keyEvent.getDownTime() >= ((long) ViewConfiguration.getLongPressTimeout())) {
                C5638xj xjVar = this.b1.a0;
                boolean z = false;
                if (xjVar != null && (xjVar.n() instanceof C2240dn0) && xjVar.q()) {
                    z = true;
                }
                if (z) {
                    return true;
                }
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void onNewIntent(Intent intent) {
        Intent intent2 = new Intent(intent);
        intent2.addFlags(268435456);
        if (F1(intent2, 2) != 0) {
            moveTaskToBack(true);
            return;
        }
        this.B1 = SystemClock.uptimeMillis();
        super.onNewIntent(intent);
    }

    @Override // android.view.Window.Callback, android.app.Activity
    public void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
        ArrayList arrayList = new ArrayList();
        KeyboardShortcutGroup keyboardShortcutGroup = new KeyboardShortcutGroup(getString(R.string.f53810_resource_name_obfuscated_RES_2131952698));
        AbstractC2981i60.a(this, keyboardShortcutGroup, R.string.f53740_resource_name_obfuscated_RES_2131952691, 42, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup, R.string.f53790_resource_name_obfuscated_RES_2131952696, 48, 4097);
        AbstractC2981i60.a(this, keyboardShortcutGroup, R.string.f53710_resource_name_obfuscated_RES_2131952688, 42, 4097);
        AbstractC2981i60.a(this, keyboardShortcutGroup, R.string.f53720_resource_name_obfuscated_RES_2131952689, 61, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup, R.string.f53750_resource_name_obfuscated_RES_2131952692, 61, 4097);
        AbstractC2981i60.a(this, keyboardShortcutGroup, R.string.f53670_resource_name_obfuscated_RES_2131952684, 51, 4096);
        arrayList.add(keyboardShortcutGroup);
        KeyboardShortcutGroup keyboardShortcutGroup2 = new KeyboardShortcutGroup(getString(R.string.f53660_resource_name_obfuscated_RES_2131952683));
        AbstractC2981i60.a(this, keyboardShortcutGroup2, R.string.f53730_resource_name_obfuscated_RES_2131952690, 33, 2);
        AbstractC2981i60.a(this, keyboardShortcutGroup2, R.string.f53640_resource_name_obfuscated_RES_2131952681, 30, 4097);
        AbstractC2981i60.a(this, keyboardShortcutGroup2, R.string.f53700_resource_name_obfuscated_RES_2131952687, 36, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup2, R.string.f53680_resource_name_obfuscated_RES_2131952685, 34, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup2, R.string.f53630_resource_name_obfuscated_RES_2131952680, 40, 4096);
        arrayList.add(keyboardShortcutGroup2);
        KeyboardShortcutGroup keyboardShortcutGroup3 = new KeyboardShortcutGroup(getString(R.string.f53820_resource_name_obfuscated_RES_2131952699));
        AbstractC2981i60.a(this, keyboardShortcutGroup3, R.string.f53760_resource_name_obfuscated_RES_2131952693, 44, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup3, R.string.f53780_resource_name_obfuscated_RES_2131952695, 46, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup3, R.string.f53770_resource_name_obfuscated_RES_2131952694, 46, 4097);
        AbstractC2981i60.a(this, keyboardShortcutGroup3, R.string.f53650_resource_name_obfuscated_RES_2131952682, 32, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup3, R.string.f53830_resource_name_obfuscated_RES_2131952700, 70, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup3, R.string.f53840_resource_name_obfuscated_RES_2131952701, 69, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup3, R.string.f53800_resource_name_obfuscated_RES_2131952697, 7, 4096);
        AbstractC2981i60.a(this, keyboardShortcutGroup3, R.string.f53690_resource_name_obfuscated_RES_2131952686, 76, 4097);
        arrayList.add(keyboardShortcutGroup3);
        list.addAll(arrayList);
    }

    @Override // defpackage.I7, defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC3119iw, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void onSaveInstanceState(Bundle bundle) {
        int indexOf;
        byte[] encoded;
        super.onSaveInstanceState(bundle);
        C4821su b = AbstractC5161uu.f11443a.b(false);
        if (!(b == null || (encoded = b.f11307a.getEncoded()) == null || b.b == null)) {
            bundle.putByteArray("org.chromium.content.browser.crypto.CipherFactory.KEY", encoded);
            bundle.putByteArray("org.chromium.content.browser.crypto.CipherFactory.IV", b.b);
        }
        bundle.putBoolean("is_incognito_selected", Q0().a());
        C0560Jd1 a2 = AbstractC0621Kd1.a();
        Objects.requireNonNull(a2);
        int i = -1;
        AbstractC0124Ca1 ca1 = (AbstractC0124Ca1) a2.I.get(this);
        if (!(ca1 == null || (indexOf = a2.H.indexOf(ca1)) == -1)) {
            i = indexOf;
        }
        bundle.putInt("window_index", i);
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (AbstractApplicationC3785mq.h(i)) {
            C5988zm0 zm0 = C5988zm0.f11767a;
            for (int i2 = 0; i2 < zm0.b.size(); i2++) {
                Tab tab = (Tab) ((WeakReference) zm0.b.get(i2)).get();
                if (tab != null) {
                    tab.r();
                }
            }
            zm0.b.clear();
        }
    }

    @Override // defpackage.AbstractC3083ik, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void p() {
        try {
            TraceEvent.Y("ChromeTabbedActivity.initializeCompositor", null);
            super.p();
            LocaleManager instance = LocaleManager.getInstance();
            this.r1 = instance;
            instance.f(this, null);
            this.l1.v(this.x0);
            this.n1 = new C1510Yt(this, this.l1);
        } finally {
            TraceEvent.f0("ChromeTabbedActivity.initializeCompositor");
        }
    }

    @Override // defpackage.AbstractActivityC0731Ma
    public boolean p0(Intent intent) {
        C2066cm0 cm0 = this.g1;
        if (cm0 == null) {
            return true;
        }
        cm0.L = getTaskId();
        int i = C2066cm0.F;
        C2066cm0.F = 0;
        return true;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public void p1() {
        ((AbstractC0246Ea1) P()).l(true).k();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f6, code lost:
        if (defpackage.AbstractC2097cw1.f9731a.contains(r6.substring(3, 7)) == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x012b, code lost:
        if (r7.getPhysicalWidth() != r9.heightPixels) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x013c, code lost:
        if (r7.getPhysicalHeight() != r9.heightPixels) goto L_0x013e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0086 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0143  */
    @Override // defpackage.AbstractActivityC0731Ma
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int q0(android.content.Intent r13, android.os.Bundle r14) {
        /*
        // Method dump skipped, instructions count: 644
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractActivityC2601fu.q0(android.content.Intent, android.os.Bundle):int");
    }

    public final C61 t1() {
        if (this.L1 == null) {
            C3461kw u12 = u1();
            C1078Rq0 rq0 = this.K0;
            C1078Rq0 rq02 = this.H1;
            C5285ve1 ve1 = (C5285ve1) this.b1;
            ve1.getClass();
            this.L1 = new C0986Qd1(this, u12, rq0, rq02, new RunnableC0291Et(ve1), this.b1.a0);
        }
        return this.L1;
    }

    @Override // defpackage.AbstractC3083ik, defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void u() {
        super.u();
        this.u1 = this.I0;
        this.v1 = this.i1.r0;
        C1343Wa1 W0 = W0();
        W0.b.b(this.u1);
        C1343Wa1 W02 = W0();
        W02.b.b(this.v1);
        C0283Ep.h().a(this.i1);
        if (this.h0) {
            C0283Ep.h().a(this.u1);
        }
    }

    @Override // defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void u0() {
        super.u0();
        AbstractC0124Ca1 P = P();
        A00 a00 = new A00(P);
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) P;
        ea1.g.b(a00);
        ea1.g.b(new C5011u00());
        if (!isFinishing()) {
            getWindow().setSoftInputMode(19);
            this.j1 = (ViewGroup) findViewById(16908290);
            this.k1 = (ToolbarControlContainer) findViewById(R.id.control_container);
            C6008zt ztVar = null;
            if (AbstractC4772sd1.g()) {
                ztVar = new C6008zt(this);
            }
            this.h1 = new C4126op1(this, this.l1, new C0108Bt(this), this.F1, ztVar);
            this.z1 = new C0348Fr("ChromeTabbedActivity.BackgroundTimeMs");
            C2971i3 i3Var = this.b0;
            long j = this.a0;
            C1551Zj M0 = M0();
            AbstractC0124Ca1 P2 = P();
            boolean P1 = P1();
            C0169Ct ct = new C0169Ct(this);
            C2287e3 e3Var = this.V0;
            e3Var.getClass();
            C0230Dt dt = new C0230Dt(e3Var);
            boolean z = AbstractC3992o11.f10528a;
            if (CachedFeatureFlags.isEnabled("PaintPreviewShowOnStartup")) {
                if (C3432km0.F.a((Context) i3Var.f11022J.get()) || P1) {
                    AbstractC3992o11.f10528a = false;
                }
                AbstractC3992o11.b.put(i3Var, new C3821n11(i3Var, j, M0, ct, dt));
                ((AbstractC0246Ea1) P2).c(new C3479l11(P2, i3Var));
            }
        }
    }

    public final C3461kw u1() {
        C5285ve1 ve1 = (C5285ve1) this.b1;
        if (ve1.N0 == null) {
            ve1.N0 = new C3461kw(new AbstractC2742gk[0]);
        }
        return ve1.N0;
    }

    @Override // defpackage.AbstractActivityC0731Ma, org.chromium.chrome.browser.app.ChromeActivity
    public void v0() {
        super.v0();
        if (!C2474f80.f9900a.f()) {
            L1(true);
        }
        b0().k(10);
        K00 a2 = K00.a();
        a2.b.add(this.M1);
    }

    public C3623lt v1() {
        return (C3623lt) super.P0();
    }

    public C3623lt w1(boolean z) {
        return (C3623lt) super.S(z);
    }

    public C3623lt x1(boolean z) {
        return (C3623lt) super.S(z);
    }

    public final void y1(Intent intent) {
        if ("com.google.android.apps.chrome.ACTION_CLOSE_TABS".equals(intent.getAction())) {
            ((AbstractC0246Ea1) P()).g(false);
            return;
        }
        String action = intent.getAction();
        C1322Vq0 vq0 = MemoryPressureListener.f10590a;
        if ("org.chromium.base.ACTION_LOW_MEMORY".equals(action)) {
            getApplication().onLowMemory();
            onLowMemory();
        } else if ("org.chromium.base.ACTION_TRIM_MEMORY".equals(action)) {
            getApplication().onTrimMemory(80);
            onTrimMemory(80);
        } else if ("org.chromium.base.ACTION_TRIM_MEMORY_RUNNING_CRITICAL".equals(action)) {
            getApplication().onTrimMemory(15);
            onTrimMemory(15);
        } else if ("org.chromium.base.ACTION_TRIM_MEMORY_MODERATE".equals(action)) {
            getApplication().onTrimMemory(60);
            onTrimMemory(60);
        }
    }

    public final boolean z1(Intent intent) {
        return intent != null && TextUtils.equals(intent.getAction(), "android.intent.action.MAIN") && intent.hasCategory("android.intent.category.LAUNCHER") && (intent.getFlags() & 1048576) == 0;
    }
}
