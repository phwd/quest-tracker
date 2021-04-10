package defpackage;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* renamed from: pL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4221pL0 extends AbstractC0202Dg0 implements AbstractC3537lL0 {
    public final String f;
    public String g;
    public String h;
    public boolean i;
    public int j = -1;
    public int k;
    public C3366kL0 l;
    public int m = -1;
    public final /* synthetic */ ServiceConnectionC4562rL0 n;

    public C4221pL0(ServiceConnectionC4562rL0 rl0, String str) {
        this.n = rl0;
        this.f = str;
    }

    @Override // defpackage.AbstractC3537lL0
    public int a() {
        return this.m;
    }

    @Override // defpackage.AbstractC3537lL0
    public void b(C3366kL0 kl0) {
        C4050oL0 ol0 = new C4050oL0(this);
        this.l = kl0;
        String str = this.f;
        int i2 = kl0.e;
        kl0.e = i2 + 1;
        int i3 = kl0.d;
        kl0.d = i3 + 1;
        Bundle bundle = new Bundle();
        bundle.putString("memberRouteId", str);
        kl0.b(11, i3, i2, null, bundle);
        kl0.h.put(i3, ol0);
        this.m = i2;
        if (this.i) {
            kl0.a(i2);
            int i4 = this.j;
            if (i4 >= 0) {
                kl0.d(this.m, i4);
                this.j = -1;
            }
            int i5 = this.k;
            if (i5 != 0) {
                kl0.f(this.m, i5);
                this.k = 0;
            }
        }
    }

    @Override // defpackage.AbstractC3537lL0
    public void c() {
        C3366kL0 kl0 = this.l;
        if (kl0 != null) {
            int i2 = this.m;
            int i3 = kl0.d;
            kl0.d = i3 + 1;
            kl0.b(4, i3, i2, null, null);
            this.l = null;
            this.m = 0;
        }
    }

    @Override // defpackage.AbstractC0385Gg0
    public void d() {
        ServiceConnectionC4562rL0 rl0 = this.n;
        rl0.k.remove(this);
        c();
        rl0.p();
    }

    @Override // defpackage.AbstractC0385Gg0
    public void e() {
        this.i = true;
        C3366kL0 kl0 = this.l;
        if (kl0 != null) {
            kl0.a(this.m);
        }
    }

    @Override // defpackage.AbstractC0385Gg0
    public void f(int i2) {
        C3366kL0 kl0 = this.l;
        if (kl0 != null) {
            kl0.d(this.m, i2);
            return;
        }
        this.j = i2;
        this.k = 0;
    }

    @Override // defpackage.AbstractC0385Gg0
    public void g() {
        h(0);
    }

    @Override // defpackage.AbstractC0385Gg0
    public void h(int i2) {
        this.i = false;
        C3366kL0 kl0 = this.l;
        if (kl0 != null) {
            kl0.e(this.m, i2);
        }
    }

    @Override // defpackage.AbstractC0385Gg0
    public void i(int i2) {
        C3366kL0 kl0 = this.l;
        if (kl0 != null) {
            kl0.f(this.m, i2);
        } else {
            this.k += i2;
        }
    }

    @Override // defpackage.AbstractC0202Dg0
    public String j() {
        return this.g;
    }

    @Override // defpackage.AbstractC0202Dg0
    public String k() {
        return this.h;
    }

    @Override // defpackage.AbstractC0202Dg0
    public void m(String str) {
        C3366kL0 kl0 = this.l;
        if (kl0 != null) {
            int i2 = this.m;
            Objects.requireNonNull(kl0);
            Bundle bundle = new Bundle();
            bundle.putString("memberRouteId", str);
            int i3 = kl0.d;
            kl0.d = i3 + 1;
            kl0.b(12, i3, i2, null, bundle);
        }
    }

    @Override // defpackage.AbstractC0202Dg0
    public void n(String str) {
        C3366kL0 kl0 = this.l;
        if (kl0 != null) {
            int i2 = this.m;
            Objects.requireNonNull(kl0);
            Bundle bundle = new Bundle();
            bundle.putString("memberRouteId", str);
            int i3 = kl0.d;
            kl0.d = i3 + 1;
            kl0.b(13, i3, i2, null, bundle);
        }
    }

    @Override // defpackage.AbstractC0202Dg0
    public void o(List list) {
        C3366kL0 kl0 = this.l;
        if (kl0 != null) {
            int i2 = this.m;
            Objects.requireNonNull(kl0);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("memberRouteIds", new ArrayList<>(list));
            int i3 = kl0.d;
            kl0.d = i3 + 1;
            kl0.b(14, i3, i2, null, bundle);
        }
    }
}
