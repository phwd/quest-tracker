package com.oculus.browser;

import android.app.Activity;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Surface;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class VrShellImpl {

    /* renamed from: a  reason: collision with root package name */
    public Handler f9715a = new Handler();
    public Activity b;
    public long c;
    public SurfaceHolder$CallbackC1926bw1 d;
    public Tab e;
    public AbstractC2603fu1 f;
    public C2171dL0 g;
    public AbstractC0124Ca1 h;

    public VrShellImpl(AbstractC0124Ca1 ca1, Tab tab, VrShellDelegate vrShellDelegate) {
        AbstractC1220Ua0.d("VrShellImpl", "VrShellImpl constructor1, this=" + this, new Object[0]);
        this.h = ca1;
        vrShellDelegate.i();
        this.e = tab;
        tab.u().requestFocus();
        C3115iu1 c2 = C3115iu1.c(this.e.l());
        this.f = c2;
        if (c2 != null) {
            c2.k(true);
        }
        this.c = nativeInit(this.e.l(), vrShellDelegate);
    }

    public static void destroyActivity(Activity activity) {
        if (activity != null && (activity instanceof WebVRActivity)) {
            activity.finish();
        }
    }

    public void a() {
        if (this.c != 0) {
            StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
            try {
                nativeOnResume(this.c);
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        }
    }

    public void b(Activity activity) {
        String str = "artb: swapHostActivity, this=" + this + ", activity = " + activity + ", mHostActivity = " + this.b;
        if (activity != this.b) {
            TraceEvent.Y("VrShell.swapHostActivity", null);
            if (this.b != null) {
                this.d.F = null;
                this.d = null;
                AbstractC2603fu1 fu1 = this.f;
                if (fu1 != null) {
                    ((C3115iu1) fu1).h();
                }
                if (!this.e.x()) {
                    StringBuilder i = AbstractC2531fV.i("calling for swapHandlerFor, ");
                    i.append(this.e);
                    i.toString();
                    C2341eL0.W(this.e, this.g);
                }
                C1786b61.l(((AbstractC0246Ea1) this.h).j());
                long j = this.c;
                if (j != 0) {
                    nativeSetActivity(j, null);
                    nativeSetVrApiSurface(this.c, null);
                }
            }
            this.b = activity;
            if (activity != null) {
                AbstractC2603fu1 fu12 = this.f;
                if (fu12 != null) {
                    ((C3115iu1) fu12).e();
                    ((C3115iu1) this.f).k(true);
                }
                this.g = C2341eL0.W(this.e, new C5509ww1(this));
                this.d = new SurfaceHolder$CallbackC1926bw1(this.b, this);
                long j2 = this.c;
                if (j2 != 0) {
                    nativeSetActivity(j2, activity);
                }
                C1786b61.l(((AbstractC0246Ea1) this.h).j());
            }
            TraceEvent.f0("VrShell.swapHostActivity");
        }
    }

    public final native long nativeInit(WebContents webContents, VrShellDelegate vrShellDelegate);

    public final native void nativeNavigationStatusUpdate(long j, WebVRNavigationDescriptor webVRNavigationDescriptor);

    public final native void nativeOnDestroyRequest(long j);

    public final native void nativeOnPause(long j);

    public final native void nativeOnResume(long j);

    public final native void nativeSetActivity(long j, Activity activity);

    public final native void nativeSetVrApiSurface(long j, Surface surface);

    public final void sendKey(int i, int i2) {
        Activity activity = this.b;
        if (activity != null) {
            this.f9715a.post(new RunnableC5679xw1(this, activity, i, i2));
        }
    }
}
