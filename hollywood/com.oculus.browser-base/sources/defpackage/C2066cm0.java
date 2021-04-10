package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.display.DisplayAndroidManager;

/* renamed from: cm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2066cm0 implements AbstractC0850Ny0, AbstractC3464kx, AbstractC4968tm0, AbstractC3090im0, AbstractC4371qE, AbstractC1244Ui0, AbstractC4183p80 {
    public static int F;
    public final Activity G;
    public final AbstractC0956Pq0 H;
    public final C3261jm0 I;

    /* renamed from: J  reason: collision with root package name */
    public final M2 f9630J;
    public final AbstractC1305Vi0 K;
    public int L;
    public boolean M;
    public DisplayManager.DisplayListener N;
    public boolean O;
    public boolean P;
    public int Q;

    public C2066cm0(Activity activity, AbstractC0956Pq0 pq0, C3261jm0 jm0, M2 m2, AbstractC1305Vi0 vi0) {
        this.G = activity;
        this.H = pq0;
        this.I = jm0;
        jm0.b.b(this);
        this.f9630J = m2;
        m2.a(this);
        this.K = vi0;
        ((ChromeActivity) vi0).e1.add(this);
    }

    @Override // defpackage.AbstractC0850Ny0
    public void b() {
    }

    @Override // defpackage.AbstractC0850Ny0
    public void c() {
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        DisplayManager.DisplayListener displayListener;
        this.I.b.c(this);
        ((ChromeActivity) this.K).e1.remove(this);
        DisplayManager displayManager = (DisplayManager) this.G.getSystemService("display");
        if (displayManager != null && (displayListener = this.N) != null) {
            displayManager.unregisterDisplayListener(displayListener);
        }
    }

    @Override // defpackage.AbstractC1244Ui0
    public boolean f(int i, boolean z) {
        Tab tab;
        Intent intent;
        if (i != R.id.move_to_other_window_menu_id) {
            return false;
        }
        Object obj = ((C1078Rq0) this.H).H;
        if (obj == null) {
            tab = null;
        } else {
            tab = ((AbstractC0246Ea1) ((AbstractC0124Ca1) obj)).j();
        }
        if (tab != null) {
            C3261jm0 jm0 = this.I;
            Class a2 = jm0.a();
            if (a2 == null) {
                intent = null;
            } else {
                intent = new Intent(jm0.f10236a, a2);
                C3432km0.m(intent, jm0.f10236a, a2);
            }
            if (intent != null) {
                F = 0;
                WL0 e = WL0.e(tab);
                Activity activity = this.G;
                Bundle d = C3432km0.d(this.I.f10236a);
                Objects.requireNonNull(e);
                if (intent.getComponent() == null) {
                    intent.setClass(ContextUtils.getApplicationContext(), Lr.class);
                }
                intent.setAction("android.intent.action.VIEW");
                if (TextUtils.isEmpty(intent.getDataString())) {
                    intent.setData(Uri.parse(e.F.s()));
                }
                if (e.F.a()) {
                    intent.putExtra("com.android.browser.application_id", ContextUtils.getApplicationContext().getPackageName());
                    intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", true);
                }
                S20.a(intent);
                if (N.M09VlOh_("TabReparenting")) {
                    intent.putExtra("com.android.chrome.tab_id", e.F.getId());
                    AbstractC1341Wa.f9155a.a(e.F.getId(), new C5446wb1(e.F, null));
                    e.c();
                }
                activity.startActivity(intent, d);
            }
        }
        return true;
    }

    @Override // defpackage.AbstractC3090im0
    public void g(boolean z) {
    }

    public final AbstractActivityC2601fu h() {
        Class a2 = this.I.a();
        Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(a2) && ApplicationStatus.e(activity) == 3) {
                return (AbstractActivityC2601fu) activity;
            }
        }
        return null;
    }

    @Override // defpackage.AbstractC3464kx
    public void onConfigurationChanged(Configuration configuration) {
        if (this.O) {
            if (this.P) {
                this.G.getClass();
            }
            this.O = false;
        }
    }

    @Override // defpackage.AbstractC4968tm0
    public void s() {
        DisplayManager displayManager;
        this.M = true;
        if (N.M09VlOh_("AndroidMultipleDisplay") && (displayManager = (DisplayManager) this.G.getSystemService("display")) != null) {
            this.Q = DisplayAndroidManager.a(this.G).getDisplayId();
            C1895bm0 bm0 = new C1895bm0(this);
            this.N = bm0;
            displayManager.registerDisplayListener(bm0, null);
        }
    }
}
