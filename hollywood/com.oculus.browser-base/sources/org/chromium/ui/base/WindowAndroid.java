package org.chromium.ui.base;

import J.N;
import android.animation.Animator;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Process;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.base.PackageManagerUtils;
import org.chromium.chrome.browser.compositor.CompositorView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WindowAndroid extends WF implements AbstractC2809h6 {
    public static final YZ F = new YZ(null);
    public C3493l60 G;
    public long H;
    public final YF I;

    /* renamed from: J  reason: collision with root package name */
    public final YZ f11022J;
    public HashMap K;
    public HashSet L;
    public View M;
    public final AccessibilityManager N;
    public C2712ga O;
    public boolean P;
    public Ny1 Q;
    public AbstractC2809h6 R;
    public boolean S;
    public List T;
    public final Hp1 U;
    public Callback V;
    public C1322Vq0 W;
    public boolean X;
    public C1322Vq0 Y;
    public final boolean Z;
    public final C1322Vq0 a0;

    public WindowAndroid(Context context) {
        this(context, YF.b(context));
    }

    public static long createForTesting() {
        return new WindowAndroid(ContextUtils.getApplicationContext()).getNativePointer();
    }

    @Override // defpackage.XF, defpackage.WF
    public void A(List list) {
        z0();
    }

    public final void A0() {
        boolean z = !this.P && this.L.isEmpty();
        if (this.M.willNotDraw() != z) {
            this.M.setWillNotDraw(z);
        }
    }

    @Override // defpackage.AbstractC2809h6
    public boolean B(int i, String[] strArr, int[] iArr) {
        AbstractC2809h6 h6Var = this.R;
        if (h6Var != null) {
            return h6Var.B(i, strArr, iArr);
        }
        return false;
    }

    public boolean B0(Ky1 ky1) {
        return false;
    }

    @Override // defpackage.XF, defpackage.WF
    public void C(Display.Mode mode) {
        z0();
    }

    public void C0(boolean z) {
        if (this.S != z) {
            this.S = z;
            long j = this.H;
            if (j != 0) {
                N.MotttR54(j, this, z);
            }
        }
    }

    public void D0(String str) {
        C1184Ti1.b(ContextUtils.getApplicationContext(), str, 0).b.show();
    }

    @Override // defpackage.AbstractC2809h6
    public final boolean E(String str) {
        AbstractC2809h6 h6Var = this.R;
        if (h6Var != null) {
            return h6Var.E(str);
        }
        AbstractC1220Ua0.f("WindowAndroid", "Cannot determine the policy permission state as the context is not an Activity", new Object[0]);
        return false;
    }

    public int E0(PendingIntent pendingIntent, Ky1 ky1, Integer num) {
        String str = "Can't show intent as context is not an Activity: " + pendingIntent;
        return -1;
    }

    public int F0(Intent intent, Ky1 ky1, Integer num) {
        String str = "Can't show intent as context is not an Activity: " + intent;
        return -1;
    }

    public void G0(int i) {
        String string = ContextUtils.getApplicationContext().getString(i);
        if (string != null) {
            C1184Ti1.b(ContextUtils.getApplicationContext(), string, 0).b.show();
        }
    }

    public boolean H0(Intent intent, Ky1 ky1, Integer num) {
        return F0(intent, ky1, null) >= 0;
    }

    public void I0(Animator animator) {
        if (this.M != null) {
            if (animator.isStarted()) {
                throw new IllegalArgumentException("Already started.");
            } else if (this.L.add(animator)) {
                animator.start();
                A0();
                animator.addListener(new Iy1(this));
            } else {
                throw new IllegalArgumentException("Already Added.");
            }
        }
    }

    @Override // defpackage.AbstractC2809h6
    public final boolean canRequestPermission(String str) {
        AbstractC2809h6 h6Var = this.R;
        if (h6Var != null) {
            return h6Var.canRequestPermission(str);
        }
        AbstractC1220Ua0.f("WindowAndroid", "Cannot determine the request permission state as the context is not an Activity", new Object[0]);
        return false;
    }

    public final void clearNativePointer() {
        this.H = 0;
    }

    public void destroy() {
        long j = this.H;
        if (j != 0) {
            N.MV00Qksi(j, this);
        }
        Hp1 hp1 = this.U;
        Objects.requireNonNull(hp1.f8184a);
        if (!(hp1.c == null)) {
            Iterator it = new HashSet(hp1.c.keySet()).iterator();
            while (it.hasNext()) {
                ((Ip1) it.next()).c(hp1);
            }
            hp1.c = null;
            hp1.b = null;
            C1881bh1 bh1 = hp1.f8184a;
            bh1.a();
            bh1.b = true;
        }
        Ny1 ny1 = this.Q;
        if (ny1 != null) {
            ny1.b.N.removeTouchExplorationStateChangeListener(ny1.f8588a);
        }
        C2712ga gaVar = this.O;
        for (AbstractC0956Pq0 pq0 : gaVar.f10005J) {
            ((C1078Rq0) pq0).I.c(gaVar.K);
        }
        gaVar.f10005J.clear();
    }

    public final long getNativePointer() {
        Window x0;
        if (this.H == 0) {
            int i = this.I.c;
            TypedValue typedValue = new TypedValue();
            Context context = (Context) this.f11022J.get();
            float dimension = (context == null || !context.getTheme().resolveAttribute(16842829, typedValue, true)) ? 0.0f : typedValue.getDimension(context.getResources().getDisplayMetrics());
            boolean z = false;
            if (Build.VERSION.SDK_INT >= 29 && (x0 = x0()) != null) {
                z = C4008o7.a(x0);
            }
            long MFjTMMS_ = N.MFjTMMS_(this, i, dimension, z);
            this.H = MFjTMMS_;
            N.MotttR54(MFjTMMS_, this, this.S);
        }
        return this.H;
    }

    public final float getRefreshRate() {
        return this.I.i;
    }

    public final float[] getSupportedRefreshRates() {
        List list = this.T;
        if (list == null || !this.Z) {
            return null;
        }
        float[] fArr = new float[list.size()];
        for (int i = 0; i < this.T.size(); i++) {
            fArr[i] = ((Display.Mode) this.T.get(i)).getRefreshRate();
        }
        return fArr;
    }

    public IBinder getWindowToken() {
        View peekDecorView;
        Window x0 = x0();
        if (x0 == null || (peekDecorView = x0.peekDecorView()) == null) {
            return null;
        }
        return peekDecorView.getWindowToken();
    }

    @Override // defpackage.XF, defpackage.WF
    public void h(float f) {
        long j = this.H;
        if (j != 0) {
            N.MWNjxKcW(j, this, f);
        }
    }

    @Override // defpackage.AbstractC2809h6
    public final boolean hasPermission(String str) {
        AbstractC2809h6 h6Var = this.R;
        if (h6Var != null) {
            return h6Var.hasPermission(str);
        }
        return AbstractC3153j7.a(ContextUtils.getApplicationContext(), str, Process.myPid(), Process.myUid()) == 0;
    }

    @Override // defpackage.AbstractC2809h6
    public final void i(String[] strArr, HB0 hb0) {
        AbstractC2809h6 h6Var = this.R;
        if (h6Var != null) {
            h6Var.i(strArr, hb0);
        } else {
            AbstractC1220Ua0.f("WindowAndroid", "Cannot request permissions as the context is not an Activity", new Object[0]);
        }
    }

    public final void onSelectionHandlesStateChanged(boolean z) {
        this.X = z;
        Iterator it = this.Y.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((CompositorView) ((Ly1) uq0.next())).d(z);
            } else {
                return;
            }
        }
    }

    public boolean r0(Intent intent) {
        return !PackageManagerUtils.c(intent, 0).isEmpty();
    }

    public WeakReference s0() {
        return F;
    }

    public final void setPreferredRefreshRate(float f) {
        Callback callback = this.V;
        if (callback != null) {
            callback.onResult(Float.valueOf(f));
        }
        Window x0 = x0();
        int i = 0;
        if (x0 == null) {
            AbstractC1220Ua0.a("WindowAndroid", "Trying to set refresh rate " + f + " to null window.", new Object[0]);
        } else if (this.T != null && this.Z) {
            if (f != 0.0f) {
                Display.Mode mode = null;
                float f2 = Float.MAX_VALUE;
                for (int i2 = 0; i2 < this.T.size(); i2++) {
                    Display.Mode mode2 = (Display.Mode) this.T.get(i2);
                    float abs = Math.abs(f - mode2.getRefreshRate());
                    if (abs < f2) {
                        mode = mode2;
                        f2 = abs;
                    }
                }
                if (f2 > 2.0f) {
                    AbstractC1220Ua0.a("WindowAndroid", "Refresh rate not supported : " + f, new Object[0]);
                } else {
                    i = mode.getModeId();
                }
            }
            WindowManager.LayoutParams attributes = x0.getAttributes();
            if (attributes.preferredDisplayModeId != i) {
                attributes.preferredDisplayModeId = i;
                x0.setAttributes(attributes);
            }
        }
    }

    public void setWideColorEnabled(boolean z) {
        Window x0;
        if (Build.VERSION.SDK_INT >= 29 && (x0 = x0()) != null) {
            C3837n7.h(x0, z ? 1 : 0);
        }
    }

    public int t0() {
        return 6;
    }

    public C3493l60 u0() {
        return this.G;
    }

    public C2746gl0 v0() {
        return null;
    }

    public View w0() {
        return null;
    }

    public final Window x0() {
        Activity a2 = ContextUtils.a((Context) this.f11022J.get());
        if (a2 == null) {
            return null;
        }
        return a2.getWindow();
    }

    public void y0(boolean z) {
        long j = this.H;
        if (j != 0) {
            N.MrnNdVRa(j, this, z);
        }
    }

    public final void z0() {
        YF yf = this.I;
        Display.Mode mode = yf.j;
        List list = yf.k;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (mode.equals(list.get(i))) {
                arrayList.add((Display.Mode) list.get(i));
            } else if (mode.getPhysicalWidth() == ((Display.Mode) list.get(i)).getPhysicalWidth() && mode.getPhysicalHeight() == ((Display.Mode) list.get(i)).getPhysicalHeight() && mode.getRefreshRate() != ((Display.Mode) list.get(i)).getRefreshRate()) {
                arrayList.add((Display.Mode) list.get(i));
            }
        }
        if (!arrayList.equals(this.T)) {
            this.T = arrayList;
            long j = this.H;
            if (j != 0) {
                N.MTDQeb$o(j, this, getSupportedRefreshRates());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0063, code lost:
        if ((r1 != null && r1.getCurrentModeType() == 4) == false) goto L_0x0067;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WindowAndroid(android.content.Context r12, defpackage.YF r13) {
        /*
        // Method dump skipped, instructions count: 191
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.WindowAndroid.<init>(android.content.Context, YF):void");
    }
}
