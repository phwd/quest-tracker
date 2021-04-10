package defpackage;

import android.os.Build;
import android.view.View;
import java.util.Objects;

/* renamed from: mz1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3814mz1 {

    /* renamed from: a  reason: collision with root package name */
    public static final C3985nz1 f10466a;
    public final C3985nz1 b;

    static {
        AbstractC2789gz1 gz1;
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            gz1 = new C2618fz1();
        } else if (i >= 29) {
            gz1 = new C2447ez1();
        } else {
            gz1 = new C2276dz1();
        }
        f10466a = gz1.a().b.a().b.b().b.c();
    }

    public C3814mz1(C3985nz1 nz1) {
        this.b = nz1;
    }

    public C3985nz1 a() {
        return this.b;
    }

    public C3985nz1 b() {
        return this.b;
    }

    public C3985nz1 c() {
        return this.b;
    }

    public void d(View view) {
    }

    public C2156dG e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3814mz1)) {
            return false;
        }
        C3814mz1 mz1 = (C3814mz1) obj;
        return j() == mz1.j() && i() == mz1.i() && Objects.equals(g(), mz1.g()) && Objects.equals(f(), mz1.f()) && Objects.equals(e(), mz1.e());
    }

    public X10 f() {
        return X10.f9186a;
    }

    public X10 g() {
        return X10.f9186a;
    }

    public C3985nz1 h(int i, int i2, int i3, int i4) {
        return f10466a;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), e());
    }

    public boolean i() {
        return false;
    }

    public boolean j() {
        return false;
    }

    public void k(C3985nz1 nz1) {
    }
}
