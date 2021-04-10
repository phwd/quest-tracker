package com.oculus.browser;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.WindowManager;
import com.oculus.vrapi.SystemProps;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VrShellDelegate implements Z9 {
    public static VrShellDelegate F;
    public long G;
    public Activity H;
    public AbstractC1404Xa1 I;

    /* renamed from: J  reason: collision with root package name */
    public final Context f9714J;
    public VrShellImpl K;
    public boolean L;
    public boolean M;
    public int N;
    public int O = -1;
    public Tab P;
    public Tab Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public WebVRNavigationDescriptor U;
    public Runnable V;
    public boolean W;
    public Handler X;
    public int Y;
    public boolean Z;

    public VrShellDelegate(Context context) {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "VrShellDelegate CTOR", new Object[0]);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "Device: %s %s", SystemProps.getDeviceManufacturer(), SystemProps.getDeviceModel());
        this.L = false;
        this.M = false;
        this.N = -1;
        this.O = -1;
        this.T = false;
        this.W = false;
        this.X = new Handler();
        this.Y = 0;
        this.f9714J = context;
        F = this;
        this.Z = false;
        this.I = new C4659rw1(this);
    }

    public static VrShellDelegate getInstance() {
        return F;
    }

    public static native void nativeOnLibraryAvailable();

    public final boolean A() {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "startWaitingForPresent, nav status = %d", Integer.valueOf(this.U.getStatus()));
        B();
        TraceEvent.Y("VrShellDelegate.waitingForPresent", null);
        Tab a2 = this.U.a();
        if (a2 != null) {
            PanelApp.B(a2);
            this.X.postDelayed(new RunnableC5339vw1(this), 5000);
            return true;
        }
        h();
        return false;
    }

    public void B() {
        TraceEvent.f0("VrShellDelegate.waitingForPresent");
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "stopShutdownVRTimer", new Object[0]);
        for (Map.Entry entry : ((HydraApplication) this.f9714J).Q.entrySet()) {
            ((PanelApp) entry.getValue()).h();
        }
        this.X.removeCallbacksAndMessages(null);
    }

    public void a(String str) {
        this.Z = false;
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", Uri.parse("com.oculus.browser/com.oculus.browser.PanelService"));
        intent.putExtra("blackscreen", false);
        intent.putExtra("uri", str);
        i().sendBroadcast(intent);
    }

    public final boolean b() {
        if (this.G == 0) {
            return false;
        }
        if (C2474f80.f9900a.f()) {
            return true;
        }
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "can't enter VR: !LibraryLoader.isInitialized()", new Object[0]);
        return false;
    }

    public void c(Tab tab) {
        WebVRNavigationDescriptor webVRNavigationDescriptor = this.U;
        if (webVRNavigationDescriptor == null) {
            return;
        }
        if (tab == null || webVRNavigationDescriptor.c.equals(tab.getUrl().h())) {
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "cancelVrNavigation for %s", this.U.c);
            WebVRNavigationDescriptor webVRNavigationDescriptor2 = this.U;
            if (webVRNavigationDescriptor2 != null) {
                webVRNavigationDescriptor2.f = 5;
            }
            q();
            this.U = null;
        }
    }

    public final void d() {
        Activity activity = this.H;
        if (activity != null) {
            activity.getWindow().clearFlags(128);
            if (this.N != -1) {
                this.H.getWindow().getDecorView().setSystemUiVisibility(this.N);
            }
            this.N = -1;
        }
    }

    public final void e(Tab tab) {
        if (this.K == null) {
            this.K = new VrShellImpl(k().P(), tab, this);
        }
    }

    public final boolean exitWebVRPresent() {
        if (!this.L || this.K == null) {
            return false;
        }
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "exitWebVRPresent", new Object[0]);
        z();
        return true;
    }

    public final void f(boolean z) {
        if (this.G != 0) {
            Tab tab = this.Q;
            this.Q = null;
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "enterVRWithOrientationSet " + z + " curTab = " + tab, new Object[0]);
            this.T = false;
            if (tab == null || tab.x()) {
                AbstractC1220Ua0.d("VrShellDelegate.Oculus", "Tab is closing or closed, abort entering VR...", new Object[0]);
                v(false, z);
                return;
            }
            Tab tab2 = this.P;
            if (!(tab2 == null || tab2 == tab)) {
                tab2.I(this.I);
                this.P = null;
            }
            e(tab);
            q();
            this.K.b(this.H);
            this.L = true;
            this.W = SystemProps.getHmtMounted();
            RunnableC4999tw1 tw1 = new RunnableC4999tw1(this);
            this.V = tw1;
            ThreadUtils.e(tw1, 500);
            if (this.P == null) {
                this.P = tab;
                tab.A(this.I);
            }
            TraceEvent.Y("VrShellDelegate.addVrViewsIfNeeded", null);
            SurfaceHolder$CallbackC1926bw1 bw1 = this.K.d;
            if (bw1 != null && !bw1.isAttachedToWindow()) {
                StringBuilder i = AbstractC2531fV.i("addVrViewsIfNeeded, view = ");
                i.append(this.K.d);
                AbstractC1220Ua0.d("VrShellDelegate.Oculus", i.toString(), new Object[0]);
                this.H.setContentView(this.K.d);
            } else {
                StringBuilder i2 = AbstractC2531fV.i("No need in addVrViewsIfNeeded, view = ");
                i2.append(this.K.d);
                AbstractC1220Ua0.d("VrShellDelegate.Oculus", i2.toString(), new Object[0]);
            }
            TraceEvent.f0("VrShellDelegate.addVrViewsIfNeeded");
            this.K.a();
            C1786b61.l(this.P);
            v(true, z);
        }
    }

    public final void fireControllersOnlyToast() {
        Intent intent = new Intent();
        Application i = i();
        intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.MainActivity"));
        intent.setData(Uri.parse("systemux://dialog/app_launch_blocked_controller_required"));
        intent.setFlags(268435456);
        i.startActivity(intent);
    }

    public void forceExitVR() {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "forceExitVR", new Object[0]);
        c(null);
        z();
    }

    public int g() {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "enterVrInternal, mInVr = %b canEnterVR = %b", Boolean.valueOf(this.L), Boolean.valueOf(b()));
        if (this.L || this.T) {
            return 0;
        }
        if (!b()) {
            return 1;
        }
        if (l()) {
            StringBuilder i = AbstractC2531fV.i("enterVR activity = ");
            i.append(this.H);
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", i.toString(), new Object[0]);
            TraceEvent.Y("VrShellDelegate.enterVR", null);
            this.M = false;
            if (this.L) {
                v(true, this.R);
            } else if (!b()) {
                v(false, this.R);
            } else {
                this.T = true;
                this.O = this.H.getRequestedOrientation();
                this.H.setRequestedOrientation(0);
                y();
                new Handler().post(new RunnableC4829sw1(this, this.R));
            }
        }
        return 2;
    }

    public final long getNativePointer() {
        return this.G;
    }

    public final long getPanelAppFromNative() {
        return k().f9704J;
    }

    public final void h() {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "executeShutdownVR, present wasn't requested in %d ms", 5000);
        B();
        WebVRNavigationDescriptor webVRNavigationDescriptor = this.U;
        if (webVRNavigationDescriptor != null) {
            webVRNavigationDescriptor.f = 5;
        }
        q();
        this.U = null;
        z();
    }

    public Application i() {
        return (Application) this.f9714J.getApplicationContext();
    }

    public int j() {
        Tab tab = this.P;
        if (tab == null) {
            tab = this.Q;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("getCurrentHistoryIndex, tab = ");
        sb.append(tab);
        sb.append(" tab.getWebContents() = ");
        sb.append(tab != null ? tab.l() : null);
        sb.toString();
        if (tab == null || tab.x() || tab.l() == null) {
            return 0;
        }
        return tab.l().f().y().b;
    }

    public final PanelApp k() {
        return ((HydraApplication) this.f9714J).N;
    }

    public boolean l() {
        return this.H != null;
    }

    public boolean m(Activity activity) {
        return activity != null && this.H == activity;
    }

    public boolean n(String str) {
        WebVRNavigationDescriptor webVRNavigationDescriptor = this.U;
        return webVRNavigationDescriptor != null && webVRNavigationDescriptor.isDeepLinking() && this.U.c.equals(str);
    }

    public final native void nativeDisplayActivate(long j, String str);

    public final native void nativeFinalizeShutdownVR(long j);

    public final native long nativeInit();

    public final native void nativeOnHandsModeChanged(long j, boolean z);

    public final native void nativeOnMouseModeChanged(long j, boolean z);

    public final native void nativeSetPresentResult(long j, boolean z);

    public void o() {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "maybePauseVR", new Object[0]);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "maybePauseVR: set mListeningForWebVrActivateBeforePause to " + this.S, new Object[0]);
        if (this.L) {
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "pauseVR", new Object[0]);
            VrShellImpl vrShellImpl = this.K;
            long j = vrShellImpl.c;
            if (j != 0) {
                vrShellImpl.nativeOnPause(j);
            }
            this.M = true;
        }
    }

    public final void onDestroyCompleted() {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onDestroyCompleted! " + this, new Object[0]);
    }

    public final void onEnteredVR() {
        WebVRNavigationDescriptor webVRNavigationDescriptor = this.U;
        if (webVRNavigationDescriptor != null) {
            webVRNavigationDescriptor.d = false;
        }
    }

    public void p() {
        if (this.G != 0) {
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "maybeResumeVR, mInVr? = %b, mRequestedWebVR? = %b", Boolean.valueOf(this.L), Boolean.valueOf(this.R));
            if (this.L) {
                this.M = false;
                y();
                StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
                try {
                    this.K.a();
                } catch (IllegalArgumentException e) {
                    AbstractC1220Ua0.a("VrShellDelegate.Oculus", "Unable to resume VrShell", e);
                } catch (Throwable th) {
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    throw th;
                }
                StrictMode.setThreadPolicy(allowThreadDiskWrites);
                return;
            }
            g();
        }
    }

    public final void presentRequested() {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "presentRequested", new Object[0]);
        x(true);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "stopVrNavigation", new Object[0]);
        WebVRNavigationDescriptor webVRNavigationDescriptor = this.U;
        if (webVRNavigationDescriptor != null) {
            webVRNavigationDescriptor.f = 4;
            q();
        }
        WebVRNavigationDescriptor webVRNavigationDescriptor2 = this.U;
        this.Z = webVRNavigationDescriptor2 != null && webVRNavigationDescriptor2.e;
        this.Q = ((AbstractC0246Ea1) k().P()).j();
        this.R = true;
        if (!l()) {
            TraceEvent.Y("WebVRActivity.create", null);
            Intent intent = new Intent();
            intent.setClass(this.f9714J, WebVRActivity.class);
            intent.setFlags(268435456);
            intent.addFlags(65536);
            intent.putExtra("WEBVR_PRESENT_REQUESTED", (short) 1);
            this.f9714J.startActivity(intent);
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "presentRequestedInternal: Activity has started! ", new Object[0]);
            return;
        }
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "presentRequestedInternal: Activity is already started! ", new Object[0]);
        w();
    }

    public final void q() {
        VrShellImpl vrShellImpl;
        WebVRNavigationDescriptor webVRNavigationDescriptor = this.U;
        if (webVRNavigationDescriptor != null && (vrShellImpl = this.K) != null) {
            vrShellImpl.nativeNavigationStatusUpdate(vrShellImpl.c, webVRNavigationDescriptor);
        }
    }

    public final void r() {
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onActivityStarted! " + this, new Object[0]);
        if (this.L) {
            u();
        }
    }

    public final void s(NavigationHandle navigationHandle) {
        WebVRNavigationDescriptor webVRNavigationDescriptor;
        boolean z = false;
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onNavigationFinish to %s, error page = %b, errorCode = %d", navigationHandle.e.h(), Boolean.valueOf(navigationHandle.g), Integer.valueOf(navigationHandle.j));
        WebVRNavigationDescriptor webVRNavigationDescriptor2 = this.U;
        if (webVRNavigationDescriptor2 != null) {
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "mNavigationSession to %s", webVRNavigationDescriptor2.c);
        }
        if (navigationHandle.f10940a && (webVRNavigationDescriptor = this.U) != null) {
            if (webVRNavigationDescriptor.f == 2 && webVRNavigationDescriptor.b == navigationHandle) {
                z = true;
            }
            if (!z) {
                return;
            }
            if (webVRNavigationDescriptor.c.startsWith("chrome://oculus-ntp") || navigationHandle.g) {
                forceExitVR();
            }
        }
    }

    public final void setListeningForWebVrActivate(boolean z) {
        WebVRNavigationDescriptor webVRNavigationDescriptor;
        Object[] objArr = new Object[3];
        objArr[0] = Boolean.valueOf(z);
        objArr[1] = Boolean.valueOf(this.M);
        objArr[2] = Boolean.valueOf(this.U == null);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "setListeningForWebVrActivate(%b), inPause = %b, navSession.isNull? %b", objArr);
        this.S = z;
        if (z && (webVRNavigationDescriptor = this.U) != null) {
            int i = webVRNavigationDescriptor.f;
            if (i == 1 || i == 4 || i == 3 || i == 2) {
                if (A()) {
                    nativeDisplayActivate(this.G, (this.U.isDeepLinking() || this.U.c.startsWith("chrome://oculus-ntp")) ? "deeplink" : "navigation");
                    this.U.f = 4;
                    q();
                    return;
                }
                return;
            }
        }
        int i2 = -1;
        if (z) {
            Object[] objArr2 = new Object[1];
            WebVRNavigationDescriptor webVRNavigationDescriptor2 = this.U;
            if (webVRNavigationDescriptor2 != null) {
                i2 = webVRNavigationDescriptor2.getStatus();
            }
            objArr2[0] = Integer.valueOf(i2);
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "setListeningForWebVrActivate: no navigation - no activation. Status %d", objArr2);
            return;
        }
        Object[] objArr3 = new Object[1];
        WebVRNavigationDescriptor webVRNavigationDescriptor3 = this.U;
        if (webVRNavigationDescriptor3 != null) {
            i2 = webVRNavigationDescriptor3.getStatus();
        }
        objArr3[0] = Integer.valueOf(i2);
        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "setListeningForWebVrActivate: not listening. Status %d", objArr3);
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        switch (i) {
            case 1:
                String str = "ActivityState.CREATED: ac: " + activity + ", curAc: " + this.H;
                this.H = activity;
                return;
            case 2:
                String str2 = "ActivityState.STARTED: ac: " + activity + ", curAc: " + this.H;
                if (activity instanceof WebVRActivity) {
                    r();
                    return;
                }
                return;
            case 3:
                String str3 = "ActivityState.RESUMED: ac: " + activity + ", curAc: " + this.H;
                if (m(activity)) {
                    AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onActivityResumed! " + this, new Object[0]);
                    p();
                    return;
                }
                return;
            case 4:
                String str4 = "ActivityState.PAUSED: ac: " + activity + ", curAc: " + this.H;
                if (m(activity)) {
                    AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onActivityPaused! " + this, new Object[0]);
                    o();
                    return;
                }
                return;
            case 5:
                String str5 = "ActivityState.STOPPED: ac: " + activity + ", curAc: " + this.H;
                if (m(activity)) {
                    AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onActivityStopped! " + this, new Object[0]);
                    if (this.K != null) {
                        AbstractC1220Ua0.d("VrShellDelegate.Oculus", "suspendVR", new Object[0]);
                        VrShellImpl vrShellImpl = this.K;
                        if (vrShellImpl != null) {
                            vrShellImpl.b(null);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            case 6:
                String str6 = "ActivityState.DESTROYED: ac: " + activity + ", curAc: " + this.H;
                if (m(activity)) {
                    AbstractC1220Ua0.d("VrShellDelegate.Oculus", "onActivityDestroyed! " + this, new Object[0]);
                    HydraApplication hydraApplication = (HydraApplication) this.f9714J;
                    if (!hydraApplication.S.isEmpty()) {
                        for (PanelApp panelApp : hydraApplication.S) {
                            Log.i("HydraApplication", "finalizeHiding");
                            panelApp.n();
                        }
                        hydraApplication.S.clear();
                    }
                    ApplicationStatus.h(this);
                    this.H = null;
                    return;
                }
                return;
            default:
                String str7 = "unk state = " + i + ": ac: " + activity + ", curAc: " + this.H;
                return;
        }
    }

    public void u() {
        this.T = true;
        this.O = this.H.getRequestedOrientation();
        this.H.setRequestedOrientation(0);
        y();
        this.R = false;
        this.Q = ((AbstractC0246Ea1) k().P()).j();
        f(false);
    }

    public final void v(boolean z, boolean z2) {
        TraceEvent.f0("VrShellDelegate.enterVR");
        if (this.G != 0) {
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "setEnterVRResult " + z + ", " + z2, new Object[0]);
            if (z2) {
                nativeSetPresentResult(this.G, z);
                if (z) {
                    B();
                }
            }
            if (!z) {
                if (l()) {
                    this.H.setRequestedOrientation(this.O);
                    ((WebVRActivity) this.H).e();
                }
                d();
                x(false);
            }
            this.Q = null;
            this.R = false;
        }
    }

    public void w() {
        if (this.G != 0) {
            StringBuilder i = AbstractC2531fV.i("setRequestPresentResult, mBoundActivity = ");
            i.append(this.H);
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", i.toString(), new Object[0]);
            String str = "setHostActivity to " + this.H + ", mVrShell = " + this.K;
            VrShellImpl vrShellImpl = this.K;
            if (vrShellImpl != null) {
                vrShellImpl.b(this.H);
            }
            int g = g();
            if (g == 0) {
                nativeSetPresentResult(this.G, true);
                this.R = false;
                B();
            } else if (g == 1) {
                nativeSetPresentResult(this.G, false);
                this.R = false;
            } else if (g == 2) {
            } else {
                if (g != 3) {
                    AbstractC1220Ua0.a("VrShellDelegate.Oculus", "Unexpected enum.", new Object[0]);
                    return;
                }
                nativeSetPresentResult(this.G, true);
                this.R = false;
                B();
            }
        }
    }

    public void x(boolean z) {
        for (Map.Entry entry : ((HydraApplication) this.f9714J).Q.entrySet()) {
            PanelApp panelApp = (PanelApp) entry.getValue();
            panelApp.f0.O.C0(z);
            panelApp.f0.P.C0(z);
        }
    }

    public final void y() {
        if (this.N == -1) {
            this.N = this.H.getWindow().getDecorView().getSystemUiVisibility();
        }
        this.H.getWindow().addFlags(128);
        this.H.getWindow().getDecorView().setSystemUiVisibility(5894);
        WindowManager.LayoutParams attributes = this.H.getWindow().getAttributes();
        attributes.screenBrightness = 1.0f;
        this.H.getWindow().setAttributes(attributes);
    }

    public final void z() {
        if (this.P != null) {
            AbstractC1220Ua0.d("VrShellDelegate.Oculus", "shutdownVR", new Object[0]);
            TraceEvent.Y("VrShellDelegate.shutdownVR", null);
            WebVRActivity webVRActivity = (WebVRActivity) this.H;
            WebVRNavigationDescriptor webVRNavigationDescriptor = this.U;
            boolean z = webVRNavigationDescriptor != null && webVRNavigationDescriptor.isNavigating();
            this.R = false;
            Activity activity = this.H;
            if (activity != null) {
                activity.setRequestedOrientation(this.O);
            }
            VrShellImpl vrShellImpl = this.K;
            if (vrShellImpl != null) {
                long j = vrShellImpl.c;
                if (j != 0) {
                    vrShellImpl.nativeOnPause(j);
                }
            }
            if (this.K == null || !z) {
                d();
                VrShellImpl vrShellImpl2 = this.K;
                if (vrShellImpl2 != null) {
                    vrShellImpl2.b(null);
                }
                if (this.K != null) {
                    AbstractC1220Ua0.d("VrShellDelegate.Oculus", "destroyVrShell", new Object[0]);
                    VrShellImpl vrShellImpl3 = this.K;
                    Objects.requireNonNull(vrShellImpl3);
                    AbstractC1220Ua0.d("VrShellImpl", "artb: shutdown, this=" + vrShellImpl3, new Object[0]);
                    vrShellImpl3.b(null);
                    long j2 = vrShellImpl3.c;
                    if (j2 != 0) {
                        vrShellImpl3.nativeOnDestroyRequest(j2);
                        vrShellImpl3.c = 0;
                    }
                    vrShellImpl3.e = null;
                    vrShellImpl3.h = null;
                    this.K = null;
                }
                this.P.I(this.I);
                C1786b61.l(this.P);
                nativeFinalizeShutdownVR(this.G);
                this.P = null;
                this.U = null;
                x(false);
            }
            this.M = false;
            this.L = false;
            if (!z) {
                if (webVRActivity != null) {
                    webVRActivity.e();
                }
                if (this.Z) {
                    a("ovrweb://exit");
                } else {
                    a("ovrweb://onWebVRExit");
                }
            }
            this.Z = false;
            TraceEvent.f0("VrShellDelegate.shutdownVR");
        }
    }
}
