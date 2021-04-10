package org.chromium.chrome.browser.tracing.settings;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.accessibility.AccessibilityManager;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tracing.TracingNotificationService;
import org.chromium.content.browser.TracingControllerAndroidImpl;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TracingSettings extends AbstractC2324eF0 implements Jm1 {
    public static final Map G0;
    public Preference H0;
    public Preference I0;
    public ListPreference J0;
    public Preference K0;
    public Preference L0;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("record-until-full", "Record until full");
        linkedHashMap.put("record-as-much-as-possible", "Record until full (large buffer)");
        linkedHashMap.put("record-continuously", "Record continuously");
        G0 = linkedHashMap;
    }

    public static int k1(String str) {
        return str.startsWith("disabled-by-default-") ? 1 : 0;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:10:0x0030 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.HashSet] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Set l1() {
        /*
            PU0 r0 = defpackage.NU0.f8549a
            r1 = 0
            java.lang.String r2 = "tracing_categories"
            java.util.Set r0 = r0.k(r2, r1)
            if (r0 != 0) goto L_0x0030
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            Lm1 r1 = defpackage.Lm1.a()
            java.util.Set r1 = r1.e
            java.util.Iterator r1 = r1.iterator()
        L_0x001a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0030
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            int r3 = k1(r2)
            if (r3 != 0) goto L_0x001a
            r0.add(r2)
            goto L_0x001a
        L_0x0030:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tracing.settings.TracingSettings.l1():java.util.Set");
    }

    public static Set m1(int i) {
        HashSet hashSet = new HashSet();
        for (String str : l1()) {
            if (i == k1(str)) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    public static String n1() {
        return NU0.f8549a.i("tracing_mode", (String) G0.keySet().iterator().next());
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        q1();
        Lm1.a().c.b(this);
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        u().setTitle("Tracing");
        AbstractC2870hT0.a(this, R.xml.f76450_resource_name_obfuscated_RES_2132213801);
        this.H0 = f1("default_categories");
        this.I0 = f1("non_default_categories");
        this.J0 = (ListPreference) f1("mode");
        this.K0 = f1("start_recording");
        this.L0 = f1("tracing_status");
        this.H0.j().putInt("type", 0);
        this.I0.j().putInt("type", 1);
        ListPreference listPreference = this.J0;
        Map map = G0;
        listPreference.A0 = (CharSequence[]) map.keySet().toArray(new String[map.size()]);
        ListPreference listPreference2 = this.J0;
        listPreference2.z0 = (String[]) map.values().toArray(new String[map.values().size()]);
        listPreference2.f9480J = new Rm1(this);
        this.K0.K = new Sm1(this);
    }

    public final boolean o1(Object obj) {
        NU0.f8549a.p("tracing_mode", (String) obj);
        q1();
        return true;
    }

    public final boolean p1() {
        Lm1 a2 = Lm1.a();
        Objects.requireNonNull(a2);
        a2.b = new TracingControllerAndroidImpl(ContextUtils.getApplicationContext());
        a2.b(2);
        Context applicationContext = ContextUtils.getApplicationContext();
        Om1.b = 0;
        String format = String.format("Trace buffer usage: %s%%", 0);
        AccessibilityManager accessibilityManager = (AccessibilityManager) applicationContext.getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            format = "Tracing is active.";
        }
        AbstractC3615lq0 u = Om1.b().H("Chrome trace is being recorded").F(format).u(true);
        Intent intent = new Intent(applicationContext, TracingNotificationService.class);
        intent.setAction("org.chromium.chrome.browser.tracing.STOP_RECORDING");
        AbstractC3615lq0 l = u.l(R.drawable.f32730_resource_name_obfuscated_RES_2131231313, "Stop recording", PendingIntent.getService(applicationContext, 0, intent, 134217728));
        Om1.f8648a = l;
        Om1.d(l.c());
        Im1 im1 = new Im1(a2, null);
        Executor executor = AbstractC2032cb.f9616a;
        im1.f();
        ((ExecutorC1463Ya) executor).execute(im1.e);
        q1();
        return true;
    }

    public final void q1() {
        int i = Lm1.a().d;
        boolean z = i != 0;
        boolean z2 = i == 1 || !z;
        boolean a2 = Om1.a();
        this.H0.K(z);
        this.I0.K(z);
        this.J0.K(z);
        this.K0.K(z2 && z && a2);
        if (z) {
            int i2 = 0;
            int i3 = 0;
            for (String str : Lm1.a().e) {
                if (k1(str) == 0) {
                    i2++;
                } else {
                    i3++;
                }
            }
            int size = ((HashSet) m1(0)).size();
            int size2 = ((HashSet) m1(1)).size();
            this.H0.T(String.format("%s out of %s enabled", Integer.valueOf(size), Integer.valueOf(i2)));
            this.I0.T(String.format("%s out of %s enabled", Integer.valueOf(size2), Integer.valueOf(i3)));
            this.J0.c0(n1());
            this.J0.T((CharSequence) G0.get(n1()));
        }
        if (!a2) {
            this.K0.V("Record trace");
            this.L0.V("Please enable Chrome browser notifications to record a trace.");
        } else if (z2) {
            this.K0.V("Record trace");
            this.L0.V("Traces may contain user or site data related to the active browsing session, including incognito tabs.");
        } else {
            this.K0.V("Recording…");
            this.L0.V("A trace is being recorded. Use the notification to stop and share the result.");
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void w0() {
        this.i0 = true;
        Lm1.a().c.c(this);
    }
}
