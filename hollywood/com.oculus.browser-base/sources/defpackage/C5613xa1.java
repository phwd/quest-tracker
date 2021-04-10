package defpackage;

import android.content.Context;
import android.graphics.Point;
import com.oculus.browser.PanelApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.chromium.ui.display.DisplayAndroidManager;

/* renamed from: xa1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5613xa1 implements AbstractC3226ja1, AbstractC0849Ny {
    public static Map F = new HashMap();
    public static Q31 G = new C4933ta1();
    public AbstractC3226ja1 H;
    public AbstractC0849Ny I;

    /* renamed from: J  reason: collision with root package name */
    public C3623lt f11616J;
    public C3623lt K;
    public AbstractC1566Zq0 L;
    public C0551Ja1 M;
    public C3390kX0 N;
    public C0054Aw0 O;
    public C0054Aw0 P;
    public boolean Q;
    public boolean R;
    public AbstractC5443wa1 S;

    public C5613xa1(AbstractC3226ja1 ja1) {
        this.H = ja1;
    }

    public static C0054Aw0 a(int i, int i2, Context context) {
        DisplayAndroidManager b = DisplayAndroidManager.b();
        int i3 = b.f;
        b.f = i3 + 1;
        Av1 av1 = new Av1(i3);
        b.d.put(i3, av1);
        b.c(av1);
        YF b2 = YF.b(context);
        Point point = b2.d;
        av1.e(new Point(point.x, point.y), Float.valueOf(b2.e), Integer.valueOf(b2.f), Integer.valueOf(b2.g), Integer.valueOf(b2.h), Boolean.valueOf(b2.l), Boolean.valueOf(b2.m), Float.valueOf(b2.i), b2.j, b2.k);
        av1.n = b2.a();
        av1.f(new Point(i, i2), Float.valueOf(1.0f), null, null, null, null, null, null, null, null, new ArrayList());
        return new C0054Aw0(context, av1);
    }

    @Override // defpackage.AbstractC3226ja1
    public AbstractC0124Ca1 P() {
        boolean z = this.Q;
        return this.Q ? this.N : this.M;
    }

    @Override // defpackage.AbstractC3226ja1
    public A61 S(boolean z) {
        return z(z, this.Q);
    }

    @Override // defpackage.AbstractC0849Ny
    public float b() {
        return this.I.b();
    }

    public PanelApp c() {
        AbstractC3226ja1 ja1 = this.H;
        if (ja1 instanceof PanelApp) {
            return (PanelApp) ja1;
        }
        return null;
    }

    public boolean d(boolean z) {
        AbstractC5443wa1 wa1;
        if (this.Q == z) {
            return false;
        }
        AbstractC0124Ca1 P2 = P();
        this.Q = z;
        AbstractC0124Ca1 P3 = P();
        if (P2 == P3 || (wa1 = this.S) == null) {
            return true;
        }
        ((PanelApp) wa1).u(P3, P2);
        return true;
    }

    @Override // defpackage.AbstractC3226ja1
    public boolean j() {
        return this.H.j();
    }

    @Override // defpackage.AbstractC3226ja1
    public boolean y() {
        return this.H.y();
    }

    @Override // defpackage.AbstractC3226ja1
    public A61 z(boolean z, boolean z2) {
        if (z2) {
            return this.L;
        }
        return z ? this.K : this.f11616J;
    }
}
