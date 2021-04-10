package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.ui.display.DisplayAndroidManager;

/* renamed from: YF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class YF {

    /* renamed from: a  reason: collision with root package name */
    public static final XF[] f9264a = new XF[0];
    public final WeakHashMap b = new WeakHashMap();
    public final int c;
    public Point d = new Point();
    public float e;
    public int f;
    public int g;
    public int h;
    public float i;
    public Display.Mode j;
    public List k;
    public boolean l;
    public boolean m;

    public YF(int i2) {
        this.c = i2;
    }

    public static YF b(Context context) {
        Display a2 = DisplayAndroidManager.a(context);
        DisplayAndroidManager b2 = DisplayAndroidManager.b();
        Objects.requireNonNull(b2);
        YF yf = (YF) b2.d.get(a2.getDisplayId());
        if (yf != null) {
            return yf;
        }
        int displayId = a2.getDisplayId();
        C5047uC0 uc0 = new C5047uC0(a2);
        b2.d.put(displayId, uc0);
        uc0.f(a2);
        return uc0;
    }

    public float a() {
        return 1.0f;
    }

    public final XF[] c() {
        return (XF[]) this.b.keySet().toArray(f9264a);
    }

    public int d() {
        int i2 = this.h;
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 1) {
            return 90;
        }
        if (i2 != 2) {
            return i2 != 3 ? 0 : 270;
        }
        return 180;
    }

    public void e(Point point, Float f2, Integer num, Integer num2, Integer num3, Boolean bool, Boolean bool2, Float f3, Display.Mode mode, List list) {
        List list2;
        boolean z = true;
        boolean z2 = point != null && !this.d.equals(point);
        boolean z3 = (f2 == null || this.e == f2.floatValue()) ? false : true;
        boolean z4 = (num == null || this.f == num.intValue()) ? false : true;
        boolean z5 = (num2 == null || this.g == num2.intValue()) ? false : true;
        boolean z6 = (num3 == null || this.h == num3.intValue()) ? false : true;
        boolean z7 = (bool == null || this.l == bool.booleanValue()) ? false : true;
        boolean z8 = (bool2 == null || this.m == bool2.booleanValue()) ? false : true;
        boolean z9 = (f3 == null || this.i == f3.floatValue()) ? false : true;
        boolean z10 = list != null && ((list2 = this.k) == null || list2.equals(list));
        boolean z11 = mode != null && !mode.equals(this.j);
        if (!z2 && !z3 && !z4 && !z5 && !z6 && !z7 && !z8 && !z9 && !z10 && !z11) {
            z = false;
        }
        if (z) {
            if (z2) {
                this.d = point;
            }
            if (z3) {
                this.e = f2.floatValue();
            }
            if (z4) {
                this.f = num.intValue();
            }
            if (z5) {
                this.g = num2.intValue();
            }
            if (z6) {
                this.h = num3.intValue();
            }
            if (z7) {
                this.l = bool.booleanValue();
            }
            if (z8) {
                this.m = bool2.booleanValue();
            }
            if (z9) {
                this.i = f3.floatValue();
            }
            if (z10) {
                this.k = list;
            }
            if (z11) {
                this.j = mode;
            }
            DisplayAndroidManager.b().c(this);
            if (z6) {
                for (XF xf : c()) {
                    xf.h0(this.h);
                }
            }
            if (z3) {
                for (XF xf2 : c()) {
                    xf2.U(this.e);
                }
            }
            if (z9) {
                for (XF xf3 : c()) {
                    xf3.h(this.i);
                }
            }
            if (z10) {
                for (XF xf4 : c()) {
                    xf4.A(this.k);
                }
            }
            if (z11) {
                for (XF xf5 : c()) {
                    xf5.C(this.j);
                }
            }
        }
    }
}
