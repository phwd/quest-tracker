package org.chromium.content.browser;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.util.Pair;
import com.oculus.os.Version;
import java.util.Map;
import java.util.WeakHashMap;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ScreenOrientationProviderImpl implements Z9 {
    public Map F = new WeakHashMap();
    public Map G = new WeakHashMap();
    public final Map H = new WeakHashMap();

    public static int b(byte b, WindowAndroid windowAndroid, Context context) {
        switch (b) {
            case 0:
                return -1;
            case 1:
                return 1;
            case 2:
                return 9;
            case 3:
                return 0;
            case 4:
                return 8;
            case 5:
                return 10;
            case 6:
                return 6;
            case Version.VERSION_7:
                return 7;
            case Version.VERSION_8:
                YF yf = windowAndroid.I;
                int i = yf.h;
                if (i == 0 || i == 2) {
                    Point point = yf.d;
                    return point.y >= point.x ? 1 : 0;
                }
                Point point2 = yf.d;
                return point2.y < point2.x ? 1 : 0;
            default:
                AbstractC1220Ua0.f("ScreenOrientation", "Trying to lock to unsupported orientation!", new Object[0]);
                return -1;
        }
    }

    public static ScreenOrientationProviderImpl getInstance() {
        return AbstractC1657aP0.f9429a;
    }

    public final void a(WebContents webContents, boolean z, byte b) {
        Zy1 t0 = Zy1.t0(webContents);
        C1837bP0 bp0 = (C1837bP0) this.H.get(webContents);
        if (bp0 != null && !bp0.f9540J) {
            bp0.G.F.c(bp0);
            bp0.f9540J = true;
        }
        this.H.put(webContents, new C1837bP0(this, t0, z, b));
    }

    public void c(WindowAndroid windowAndroid, byte b) {
        int b2;
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity != null && (b2 = b(b, windowAndroid, activity)) != -1) {
            d(activity, true, b2);
        }
    }

    public final void d(Activity activity, boolean z, int i) {
        if (this.G.containsKey(activity)) {
            this.G.put(activity, Pair.create(Boolean.valueOf(z), Integer.valueOf(i)));
        } else {
            activity.setRequestedOrientation(i);
        }
    }

    public void e(WindowAndroid windowAndroid) {
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity != null) {
            int b = b(this.F.containsKey(activity) ? ((Byte) this.F.get(activity)).byteValue() : 0, windowAndroid, activity);
            if (b == -1) {
                try {
                    b = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 128).screenOrientation;
                } catch (PackageManager.NameNotFoundException unused) {
                } catch (Throwable th) {
                    d(activity, false, b);
                    throw th;
                }
            }
            d(activity, false, b);
        }
    }

    public boolean isOrientationLockEnabled() {
        return true;
    }

    public final void lockOrientationForWebContents(WebContents webContents, byte b) {
        WindowAndroid I = webContents.I();
        if (I == null) {
            a(webContents, true, b);
        } else {
            c(I, b);
        }
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 6) {
            this.G.remove(activity);
        }
    }

    public final void unlockOrientationForWebContents(WebContents webContents) {
        WindowAndroid I = webContents.I();
        if (I == null) {
            a(webContents, false, (byte) 0);
        } else {
            e(I);
        }
    }
}
