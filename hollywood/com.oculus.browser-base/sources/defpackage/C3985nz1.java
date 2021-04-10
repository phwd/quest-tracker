package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;

/* renamed from: nz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3985nz1 {

    /* renamed from: a  reason: collision with root package name */
    public static final C3985nz1 f10524a;
    public final C3814mz1 b;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            f10524a = C3643lz1.n;
        } else {
            f10524a = C3814mz1.f10466a;
        }
    }

    public C3985nz1(WindowInsets windowInsets) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.b = new C3643lz1(this, windowInsets);
        } else if (i >= 29) {
            this.b = new C3472kz1(this, windowInsets);
        } else if (i >= 28) {
            this.b = new C3301jz1(this, windowInsets);
        } else {
            this.b = new C3130iz1(this, windowInsets);
        }
    }

    public static X10 e(X10 x10, int i, int i2, int i3, int i4) {
        int max = Math.max(0, x10.b - i);
        int max2 = Math.max(0, x10.c - i2);
        int max3 = Math.max(0, x10.d - i3);
        int max4 = Math.max(0, x10.e - i4);
        if (max == i && max2 == i2 && max3 == i3 && max4 == i4) {
            return x10;
        }
        return X10.a(max, max2, max3, max4);
    }

    public static C3985nz1 h(WindowInsets windowInsets) {
        return i(windowInsets, null);
    }

    public static C3985nz1 i(WindowInsets windowInsets, View view) {
        Objects.requireNonNull(windowInsets);
        C3985nz1 nz1 = new C3985nz1(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            nz1.b.k(AbstractC1920bu1.g(view));
            nz1.b.d(view.getRootView());
        }
        return nz1;
    }

    @Deprecated
    public int a() {
        return this.b.g().e;
    }

    @Deprecated
    public int b() {
        return this.b.g().b;
    }

    @Deprecated
    public int c() {
        return this.b.g().d;
    }

    @Deprecated
    public int d() {
        return this.b.g().c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3985nz1)) {
            return false;
        }
        return Objects.equals(this.b, ((C3985nz1) obj).b);
    }

    public boolean f() {
        return this.b.i();
    }

    public WindowInsets g() {
        C3814mz1 mz1 = this.b;
        if (mz1 instanceof AbstractC2960hz1) {
            return ((AbstractC2960hz1) mz1).i;
        }
        return null;
    }

    public int hashCode() {
        C3814mz1 mz1 = this.b;
        if (mz1 == null) {
            return 0;
        }
        return mz1.hashCode();
    }

    public C3985nz1(C3985nz1 nz1) {
        this.b = new C3814mz1(this);
    }
}
