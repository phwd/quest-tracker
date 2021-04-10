package defpackage;

import android.os.Bundle;

/* renamed from: qL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4392qL0 extends AbstractC0385Gg0 implements AbstractC3537lL0 {

    /* renamed from: a  reason: collision with root package name */
    public final String f11135a;
    public final String b;
    public boolean c;
    public int d = -1;
    public int e;
    public C3366kL0 f;
    public int g;
    public final /* synthetic */ ServiceConnectionC4562rL0 h;

    public C4392qL0(ServiceConnectionC4562rL0 rl0, String str, String str2) {
        this.h = rl0;
        this.f11135a = str;
        this.b = str2;
    }

    @Override // defpackage.AbstractC3537lL0
    public int a() {
        return this.g;
    }

    @Override // defpackage.AbstractC3537lL0
    public void b(C3366kL0 kl0) {
        this.f = kl0;
        String str = this.f11135a;
        String str2 = this.b;
        int i = kl0.e;
        kl0.e = i + 1;
        Bundle bundle = new Bundle();
        bundle.putString("routeId", str);
        bundle.putString("routeGroupId", str2);
        int i2 = kl0.d;
        kl0.d = i2 + 1;
        kl0.b(3, i2, i, null, bundle);
        this.g = i;
        if (this.c) {
            kl0.a(i);
            int i3 = this.d;
            if (i3 >= 0) {
                kl0.d(this.g, i3);
                this.d = -1;
            }
            int i4 = this.e;
            if (i4 != 0) {
                kl0.f(this.g, i4);
                this.e = 0;
            }
        }
    }

    @Override // defpackage.AbstractC3537lL0
    public void c() {
        C3366kL0 kl0 = this.f;
        if (kl0 != null) {
            int i = this.g;
            int i2 = kl0.d;
            kl0.d = i2 + 1;
            kl0.b(4, i2, i, null, null);
            this.f = null;
            this.g = 0;
        }
    }

    @Override // defpackage.AbstractC0385Gg0
    public void d() {
        ServiceConnectionC4562rL0 rl0 = this.h;
        rl0.k.remove(this);
        c();
        rl0.p();
    }

    @Override // defpackage.AbstractC0385Gg0
    public void e() {
        this.c = true;
        C3366kL0 kl0 = this.f;
        if (kl0 != null) {
            kl0.a(this.g);
        }
    }

    @Override // defpackage.AbstractC0385Gg0
    public void f(int i) {
        C3366kL0 kl0 = this.f;
        if (kl0 != null) {
            kl0.d(this.g, i);
            return;
        }
        this.d = i;
        this.e = 0;
    }

    @Override // defpackage.AbstractC0385Gg0
    public void g() {
        h(0);
    }

    @Override // defpackage.AbstractC0385Gg0
    public void h(int i) {
        this.c = false;
        C3366kL0 kl0 = this.f;
        if (kl0 != null) {
            kl0.e(this.g, i);
        }
    }

    @Override // defpackage.AbstractC0385Gg0
    public void i(int i) {
        C3366kL0 kl0 = this.f;
        if (kl0 != null) {
            kl0.f(this.g, i);
        } else {
            this.e += i;
        }
    }
}
