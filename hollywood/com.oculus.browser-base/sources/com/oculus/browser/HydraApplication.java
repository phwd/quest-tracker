package com.oculus.browser;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HydraApplication extends AbstractApplicationC3785mq implements S9, AbstractC1858ba, AbstractC0530Iq0, Oy1 {

    /* renamed from: J  reason: collision with root package name */
    public boolean f9702J;
    public boolean K;
    public boolean L;
    public VrShellDelegate M;
    public PanelApp N;
    public ArrayList O = new ArrayList();
    public C1505Yq0 P;
    public Map Q;
    public Map R;
    public List S;
    public C1755aw1 T = new C1755aw1();
    public BroadcastReceiver U = new C3562lY(this);
    public BroadcastReceiver V = new C3733mY(this);
    public C4417qY W = new C4417qY(this);

    public static native void nativeInitOvrPlatform(Context context, String str);

    @Override // defpackage.AbstractC0530Iq0
    public void a() {
        new Timer().schedule(new C4246pY(this, this), 30000);
    }

    @Override // defpackage.S9
    public void b(boolean z) {
        Log.i("HydraApplication", "onTerminate, restart = " + z);
        VrShellDelegate vrShellDelegate = this.M;
        if (vrShellDelegate != null) {
            vrShellDelegate.forceExitVR();
        }
        for (Map.Entry entry : this.Q.entrySet()) {
            ((PanelApp) entry.getValue()).n();
        }
        this.Q.clear();
        this.R.clear();
        n(null);
        m(null);
        this.N = null;
        Objects.requireNonNull(this.T);
        if (VrApi.nativeIsUsingContextVrApi(this)) {
            Objects.requireNonNull(this.T);
            AbstractC1220Ua0.d("VrApi", "shutdown", new Object[0]);
            if (VrApi.f9713a) {
                VrApi.nativeShutdownVrApi();
                VrApi.f9713a = false;
            }
        }
        AutomationServer automationServer = AutomationServer.f9697a;
        if (automationServer != null) {
            automationServer.b = 3;
            N.M0DQDP_q(automationServer.c);
            AutomationServer.f9697a = null;
        }
    }

    @Override // defpackage.AbstractC0530Iq0
    public void c(String str) {
    }

    @Override // defpackage.Oy1
    public WindowAndroid i() {
        Iterator it = this.Q.entrySet().iterator();
        if (it.hasNext()) {
            return ((PanelApp) ((Map.Entry) it.next()).getValue()).f0.O;
        }
        return null;
    }

    public final void j() {
        boolean z = this.Q.size() < 3;
        for (PanelApp panelApp : this.Q.values()) {
            N.M9fkR7dq(panelApp.f9704J, z);
        }
    }

    public final Object[] k() {
        Object[] array;
        synchronized (this.O) {
            array = this.O.size() > 0 ? this.O.toArray() : null;
        }
        return array;
    }

    public boolean l() {
        VrShellDelegate vrShellDelegate = this.M;
        if (vrShellDelegate != null) {
            boolean z = vrShellDelegate.L;
        }
        VrShellDelegate vrShellDelegate2 = this.M;
        if (vrShellDelegate2 != null) {
            boolean z2 = vrShellDelegate2.R;
        }
        VrShellDelegate vrShellDelegate3 = this.M;
        return vrShellDelegate3 != null && (vrShellDelegate3.L || vrShellDelegate3.R);
    }

    public void m(PanelApp panelApp) {
        Object[] k;
        Log.i("HydraApplication", "onAppDestroyed");
        p(panelApp);
        if (!(panelApp == null || (k = k()) == null)) {
            for (Object obj : k) {
                Objects.requireNonNull((V9) obj);
                ApplicationStatus.b(4);
            }
        }
        q();
    }

    public void n(PanelApp panelApp) {
        Object[] k;
        Log.i("HydraApplication", "onAppHidden");
        if (panelApp != null && !panelApp.Y.isShellFeatureSupported("multiapp-v1")) {
            p(panelApp);
        }
        if (panelApp == this.N) {
            this.S.add(panelApp);
        } else if (panelApp != null) {
            Log.i("HydraApplication", "finalizeHiding");
            panelApp.n();
        }
        Objects.requireNonNull(this.W);
        try {
            TraceEvent.Y("ProfileManagerUtils.commitPendingWritesForAllProfiles", null);
            N.MPpDwRXN();
            if (!(panelApp == null || (k = k()) == null)) {
                for (Object obj : k) {
                    Objects.requireNonNull((V9) obj);
                    ApplicationStatus.b(3);
                }
            }
            q();
        } finally {
            TraceEvent.f0("ProfileManagerUtils.commitPendingWritesForAllProfiles");
        }
    }

    public void o() {
        q();
    }

    public final void p(PanelApp panelApp) {
        if (panelApp != null) {
            int i = 0;
            if (this.R.containsKey(Integer.valueOf(panelApp.Y.l))) {
                i = ((Integer) this.R.remove(Integer.valueOf(panelApp.Y.l))).intValue();
            }
            this.Q.remove(Integer.valueOf(i));
            j();
        }
    }

    public void q() {
        if (this.L) {
            int i = 0;
            for (Map.Entry entry : this.Q.entrySet()) {
                PanelApp panelApp = (PanelApp) entry.getValue();
                EnumC4488qw0 qw0 = panelApp.G;
                if (!panelApp.f0.Q) {
                    i |= qw0.f11172J;
                    Preferences instance = Preferences.getInstance();
                    instance.setInt(qw0 + "_AFFINITY", panelApp.H);
                }
            }
            Preferences.getInstance().setInt("PANEL_RECOVERY", i);
        }
    }
}
