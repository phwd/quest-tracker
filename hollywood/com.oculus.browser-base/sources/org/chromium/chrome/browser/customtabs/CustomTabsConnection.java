package org.chromium.chrome.browser.customtabs;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CustomTabsConnection {

    /* renamed from: a  reason: collision with root package name */
    public static final Set f10648a = new HashSet(Arrays.asList("/bg_non_interactive", "/apps/bg_non_interactive", "/background"));
    public static final String[] b = {"No request", "Success", "Chrome not initialized", "Not authorized", "Invalid URL", "Invalid referrer", "Invalid referrer for session"};
    public static CustomTabsConnection c;
    public final C3218jX d = new C3218jX();
    public final RS0 e = AbstractApplicationC3785mq.g().d();
    public final C3287jv f = new C3287jv();
    public final boolean g = AbstractC1575Zv.e().g("custom-tabs-log-service-requests");
    public final AtomicBoolean h = new AtomicBoolean();
    public final AtomicBoolean i = new AtomicBoolean();
    public volatile C4630rn j;

    public static JSONObject a(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null) {
            return jSONObject;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            try {
                if (obj instanceof Bundle) {
                    jSONObject.put(str, a((Bundle) obj));
                } else {
                    if (!(obj instanceof Integer) && !(obj instanceof Long)) {
                        if (!(obj instanceof Boolean)) {
                            if (obj == null) {
                                jSONObject.put(str, JSONObject.NULL);
                            } else {
                                jSONObject.put(str, obj.toString());
                            }
                        }
                    }
                    jSONObject.put(str, obj);
                }
            } catch (JSONException unused) {
            }
        }
        return jSONObject;
    }

    public static void c() {
        if (!SysUtils.isLowEndDevice()) {
            Bw1 a2 = Bw1.a();
            Objects.requireNonNull(a2);
            Object obj = ThreadUtils.f10596a;
            if (C2474f80.f9900a.f() && a2.f == null) {
                a2.i = true;
                WebContents webContents = (WebContents) N.MDMZjIJS(Profile.b(), true, true);
                a2.f = webContents;
                Aw1 aw1 = new Aw1(a2, null);
                a2.h = aw1;
                webContents.c0(aw1);
                a2.g = SystemClock.elapsedRealtime();
                a2.d(0);
            }
        }
    }

    public static CustomTabsConnection f() {
        if (c == null) {
            Objects.requireNonNull(AppHooks.get());
            c = new CustomTabsConnection();
        }
        return c;
    }

    public static boolean i(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.normalizeScheme().getScheme();
        if (scheme == null || scheme.equals("http") || scheme.equals("https")) {
            return true;
        }
        return false;
    }

    public static void notifyClientOfDetachedRequestCompletion(CustomTabsSessionToken customTabsSessionToken, String str, int i2) {
        if (N.M09VlOh_("CCTReportParallelRequestStatus")) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("url", Uri.parse(str));
            bundle.putInt("net_error", i2);
            CustomTabsConnection f2 = f();
            f2.o(customTabsSessionToken, "onDetachedRequestCompleted", bundle);
            if (f2.g) {
                f2.k("onDetachedRequestCompleted", a(bundle).toString());
            }
        }
    }

    public void b(CustomTabsSessionToken customTabsSessionToken) {
        Object obj = ThreadUtils.f10596a;
        this.d.a(customTabsSessionToken);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x005d A[SYNTHETIC, Splitter:B:24:0x005d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void d(boolean r12, androidx.browser.customtabs.CustomTabsSessionToken r13, int r14, java.lang.String r15, android.os.Bundle r16, java.util.List r17, boolean r18) {
        /*
        // Method dump skipped, instructions count: 105
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.CustomTabsConnection.d(boolean, androidx.browser.customtabs.CustomTabsSessionToken, int, java.lang.String, android.os.Bundle, java.util.List, boolean):void");
    }

    public Bundle e() {
        return null;
    }

    public final void g(CustomTabsSessionToken customTabsSessionToken, int i2, String str, Bundle bundle, List list) {
        int i3;
        Object obj = ThreadUtils.f10596a;
        if (TextUtils.isEmpty(str)) {
            b(customTabsSessionToken);
            return;
        }
        boolean z = true;
        if (!C5052uE.c().e) {
            i3 = 5;
        } else if (N.MzGf81GW(Wr1.a(Profile.b()).f10883a, "profile.cookie_controls_mode") == 1) {
            i3 = 6;
        } else {
            Objects.requireNonNull(WF0.a());
            if (!N.MBIqJabw()) {
                i3 = 7;
            } else if (!DataReductionProxySettings.d().e() || N.M09VlOh_("PredictivePrefetchingAllowedOnAllConnectionTypes")) {
                if (((ConnectivityManager) ContextUtils.getApplicationContext().getSystemService("connectivity")).isActiveNetworkMetered()) {
                    C3287jv jvVar = this.f;
                    synchronized (jvVar) {
                        C3116iv ivVar = (C3116iv) jvVar.b.get(customTabsSessionToken);
                    }
                    if (!N.M09VlOh_("PredictivePrefetchingAllowedOnAllConnectionTypes")) {
                        i3 = 9;
                    }
                }
                i3 = 0;
            } else {
                i3 = 8;
            }
        }
        AbstractC3364kK0.g("CustomTabs.SpeculationStatusOnStart", i3, 10);
        if (i3 != 0) {
            z = false;
        }
        if (z) {
            C3287jv jvVar2 = this.f;
            synchronized (jvVar2) {
                C3116iv ivVar2 = (C3116iv) jvVar2.b.get(customTabsSessionToken);
            }
            p(customTabsSessionToken, str, false, bundle);
        }
        n(list);
    }

    public final boolean h() {
        if (Binder.getCallingUid() == Process.myUid()) {
            return true;
        }
        int i2 = Build.VERSION.SDK_INT;
        int callingPid = Binder.getCallingPid();
        File file = new File(AbstractC2531fV.w("/proc/", callingPid));
        if (!(file.exists() && file.isDirectory() && file.canExecute())) {
            return true;
        }
        Set set = f10648a;
        String str = "/proc/" + callingPid + "/cgroup";
        String str2 = i2 >= 26 ? "cpuset" : "cpu";
        String str3 = null;
        try {
            P21 f0 = P21.f0();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            bufferedReader.close();
                            f0.close();
                            break;
                        }
                        String[] split = readLine.trim().split(":");
                        if (split.length == 3 && split[1].equals(str2)) {
                            String str4 = split[2];
                            bufferedReader.close();
                            f0.close();
                            str3 = str4;
                            break;
                        }
                    } catch (Throwable th) {
                        AbstractC0754Mh1.f8495a.a(th, th);
                    }
                }
            } catch (Throwable th2) {
                AbstractC0754Mh1.f8495a.a(th, th2);
            }
        } catch (IOException unused) {
        }
        return set.contains(str3);
        throw th;
        throw th;
    }

    public void j(String str, Object obj) {
        if (this.g) {
            AbstractC1220Ua0.f("ChromeConnection", "%s = %b, Calling UID = %d", str, obj, Integer.valueOf(Binder.getCallingUid()));
        }
    }

    public void k(String str, Object obj) {
        if (this.g) {
            AbstractC1220Ua0.f("ChromeConnection", "%s args = %s", str, obj);
        }
    }

    public final boolean l(CustomTabsSessionToken customTabsSessionToken, Uri uri, Bundle bundle, List list) {
        C3070if1 if1;
        boolean z;
        boolean z2 = (uri == null || TextUtils.isEmpty(uri.toString())) && list != null;
        String uri2 = i(uri) ? uri.toString() : null;
        if (uri != null && uri2 == null && !z2) {
            return false;
        }
        int callingUid = Binder.getCallingUid();
        C3070if1 if12 = Zo1.f9374a;
        PostTask.b(if12, new RunnableC3339kC(callingUid, uri2, list), 0);
        if (!s(false)) {
            return false;
        }
        C3287jv jvVar = this.f;
        boolean z3 = list != null;
        synchronized (jvVar) {
            C3116iv ivVar = (C3116iv) jvVar.b.get(customTabsSessionToken);
            if (ivVar != null) {
                if (ivVar.f10172a == callingUid) {
                    boolean z4 = TextUtils.isEmpty(uri2) && z3 && !ivVar.h;
                    SystemClock.elapsedRealtime();
                    ivVar.i = (!TextUtils.isEmpty(uri2)) | ivVar.i;
                    ivVar.h = z3 | ivVar.h;
                    if (z4) {
                        z = true;
                        if1 = if12;
                    } else {
                        C2173dM0 a2 = C2173dM0.a(callingUid);
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        if1 = if12;
                        long j2 = elapsedRealtime - a2.c;
                        long j3 = a2.d;
                        if (j2 < j3) {
                            z = false;
                        } else {
                            a2.c = elapsedRealtime;
                            long j4 = j3 * 2;
                            if (j2 < j4) {
                                a2.d = Math.min(10000L, j4);
                            } else {
                                a2.d = 100;
                            }
                            z = true;
                        }
                    }
                }
            }
            if1 = if12;
            z = false;
        }
        if (!z) {
            return false;
        }
        PostTask.b(if1, new RunnableC3510lC(this, z2, customTabsSessionToken, callingUid, uri2, bundle, list), 0);
        return true;
    }

    public int m(CustomTabsSessionToken customTabsSessionToken, String str) {
        int i2;
        WebContents webContents;
        this.h.get();
        if (!h()) {
            Objects.requireNonNull(this.e);
        }
        C3287jv jvVar = this.f;
        synchronized (jvVar) {
            C3116iv ivVar = (C3116iv) jvVar.b.get(customTabsSessionToken);
            i2 = -3;
            if (ivVar != null) {
                IE0 ie0 = ivVar.d;
                MessagePort[] messagePortArr = ie0.d;
                if (messagePortArr != null && !messagePortArr[0].isClosed() && (webContents = ie0.c) != null && !webContents.g()) {
                    PostTask.b(Zo1.f9374a, new HE0(ie0, str), 0);
                    i2 = 0;
                }
            }
        }
        j("postMessage", Integer.valueOf(i2));
        return i2;
    }

    public final boolean n(List list) {
        boolean z = false;
        if (list == null) {
            return false;
        }
        Bw1 a2 = Bw1.a();
        Profile b2 = Profile.b();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                Uri uri = (Uri) U20.j((Bundle) it.next(), "android.support.customtabs.otherurls.URL");
                if (i(uri)) {
                    a2.c(b2, uri.toString());
                    z = true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return z;
    }

    public boolean o(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle) {
        C5216vC b2 = this.f.b(customTabsSessionToken);
        if (b2 == null) {
            return false;
        }
        try {
            try {
                ((C5607xY) b2.f11465a.f9465a).c(str, bundle);
                return true;
            } catch (Exception unused) {
                return false;
            }
        } catch (RemoteException unused2) {
            Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            return true;
        }
    }

    public final void p(CustomTabsSessionToken customTabsSessionToken, String str, boolean z, Bundle bundle) {
        Bw1 a2 = Bw1.a();
        Profile b2 = Profile.b();
        b(null);
        if (z) {
            AbstractC3364kK0.g("CustomTabs.SpeculationStatusOnStart", 3, 10);
            Object obj = ThreadUtils.f10596a;
            C3218jX jXVar = this.d;
            C3287jv jvVar = this.f;
            Objects.requireNonNull(jXVar);
            Intent intent = new Intent();
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            if (S20.f(intent) == null) {
                Context applicationContext = ContextUtils.getApplicationContext();
                C2128d61 d61 = new C2128d61();
                d61.e = new WindowAndroid(applicationContext);
                d61.d(8);
                d61.l = new OB(null, false, false, null, 1, false, null, null, null, null, null, new GB(), true);
                d61.m = true;
                Tab a3 = d61.a();
                Rect a4 = AbstractC5112ud1.a(applicationContext);
                TabImpl tabImpl = (TabImpl) a3;
                tabImpl.L.p0(a4.right - a4.left, a4.bottom - a4.top);
                WL0.e(a3).c();
                tabImpl.P.b(new C2877hX(jXVar, tabImpl.f10773J));
                WebContents webContents = tabImpl.L;
                synchronized (jvVar) {
                    C3116iv ivVar = (C3116iv) jvVar.b.get(customTabsSessionToken);
                    if (ivVar != null) {
                        IE0 ie0 = ivVar.d;
                        Objects.requireNonNull(ie0);
                        if (webContents == null || webContents.g()) {
                            ie0.b();
                        } else if (!webContents.equals(ie0.c)) {
                            ie0.c = webContents;
                            if (ie0.e != null) {
                                new GE0(ie0, webContents, webContents);
                            }
                        }
                    }
                }
                LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
                String h2 = S20.h(intent);
                if (h2 == null && jvVar.d(customTabsSessionToken) != null) {
                    h2 = jvVar.d(customTabsSessionToken).f9916a;
                }
                if (h2 == null) {
                    h2 = "";
                }
                if (!h2.isEmpty()) {
                    loadUrlParams.d = new C2512fL0(h2, 1);
                }
                jXVar.f10209a = new C3048iX(customTabsSessionToken, str, a3, h2, null);
                tabImpl.c(loadUrlParams);
            }
        } else {
            c();
        }
        a2.c(b2, str);
    }

    public Uri q(int i2) {
        if (i2 == Process.myUid()) {
            return Uri.EMPTY;
        }
        return null;
    }

    public boolean r() {
        TraceEvent j0 = TraceEvent.j0("CustomTabsConnection.warmup");
        try {
            boolean s = s(true);
            j("warmup()", Boolean.valueOf(s));
            if (j0 != null) {
                j0.close();
            }
            return s;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public final boolean s(boolean z) {
        if (!h()) {
            return false;
        }
        int callingUid = Binder.getCallingUid();
        C3287jv jvVar = this.f;
        synchronized (jvVar) {
            jvVar.c.put(callingUid, true);
        }
        boolean z2 = !this.h.compareAndSet(false, true);
        C4630rn rnVar = new C4630rn();
        if (!z2) {
            rnVar.a(Zo1.e, new RunnableC1802bC(this));
        }
        if (z) {
            if (!(this.d.f10209a != null)) {
                rnVar.a(Zo1.e, new RunnableC2656gC());
            }
        }
        C3070if1 if1 = Zo1.e;
        rnVar.a(if1, new RunnableC2827hC());
        if (!z2) {
            rnVar.a(if1, new RunnableC2998iC());
        }
        rnVar.a(if1, new RunnableC3168jC(this, callingUid));
        rnVar.b(false);
        this.j = rnVar;
        return true;
    }
}
