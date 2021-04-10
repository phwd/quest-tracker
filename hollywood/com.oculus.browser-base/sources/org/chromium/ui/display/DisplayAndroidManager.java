package org.chromium.ui.display;

import J.N;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DisplayAndroidManager {

    /* renamed from: a  reason: collision with root package name */
    public static DisplayAndroidManager f11023a;
    public long b;
    public int c;
    public final SparseArray d = new SparseArray();
    public C1634aG e = new C1634aG(this, null);
    public int f = 1073741823;

    public static Display a(Context context) {
        if (Build.VERSION.SDK_INT < 30) {
            return ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        }
        Display display = null;
        try {
            display = C4691s7.a(context);
        } catch (UnsupportedOperationException unused) {
        }
        if (display != null) {
            return display;
        }
        return ((DisplayManager) ContextUtils.getApplicationContext().getSystemService("display")).getDisplay(0);
    }

    public static DisplayAndroidManager b() {
        Object obj = ThreadUtils.f10596a;
        if (f11023a == null) {
            DisplayAndroidManager displayAndroidManager = new DisplayAndroidManager();
            f11023a = displayAndroidManager;
            Objects.requireNonNull(displayAndroidManager);
            Display display = ((DisplayManager) ContextUtils.getApplicationContext().getSystemService("display")).getDisplay(0);
            if (display == null) {
                display = ((WindowManager) ContextUtils.getApplicationContext().getSystemService("window")).getDefaultDisplay();
            }
            displayAndroidManager.c = display.getDisplayId();
            int displayId = display.getDisplayId();
            C5047uC0 uc0 = new C5047uC0(display);
            displayAndroidManager.d.put(displayId, uc0);
            uc0.f(display);
            C1634aG aGVar = displayAndroidManager.e;
            Objects.requireNonNull(aGVar);
            ((DisplayManager) ContextUtils.getApplicationContext().getSystemService("display")).registerDisplayListener(aGVar, null);
        }
        return f11023a;
    }

    public static void onNativeSideCreated(long j) {
        DisplayAndroidManager b2 = b();
        b2.b = j;
        N.MdOwtyr6(j, b2, b2.c);
        for (int i = 0; i < b2.d.size(); i++) {
            b2.c((YF) b2.d.valueAt(i));
        }
    }

    public void c(YF yf) {
        long j = this.b;
        if (j != 0) {
            int i = yf.c;
            Point point = yf.d;
            N.M2$ANfTC(j, this, i, point.x, point.y, yf.e, yf.d(), yf.f, yf.g, yf.l && yf.m);
        }
    }
}
