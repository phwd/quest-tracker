package defpackage;

import java.util.Objects;

/* renamed from: kT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3382kT0 {

    /* renamed from: a  reason: collision with root package name */
    public XA f10281a = new JN0();
    public XA b = new JN0();
    public XA c = new JN0();
    public XA d = new JN0();
    public WA e = new C2107d(0.0f);
    public WA f = new C2107d(0.0f);
    public WA g = new C2107d(0.0f);
    public WA h = new C2107d(0.0f);
    public TJ i = new TJ();
    public TJ j = new TJ();
    public TJ k = new TJ();
    public TJ l = new TJ();

    public C3382kT0() {
    }

    public static float b(XA xa) {
        if (xa instanceof JN0) {
            Objects.requireNonNull((JN0) xa);
            return -1.0f;
        }
        if (xa instanceof C5556xC) {
            Objects.requireNonNull((C5556xC) xa);
        }
        return -1.0f;
    }

    public C3553lT0 a() {
        return new C3553lT0(this, null);
    }

    public C3382kT0 c(float f2) {
        this.h = new C2107d(f2);
        return this;
    }

    public C3382kT0 d(float f2) {
        this.g = new C2107d(f2);
        return this;
    }

    public C3382kT0 e(float f2) {
        this.e = new C2107d(f2);
        return this;
    }

    public C3382kT0 f(float f2) {
        this.f = new C2107d(f2);
        return this;
    }

    public C3382kT0(C3553lT0 lt0) {
        this.f10281a = lt0.f10347a;
        this.b = lt0.b;
        this.c = lt0.c;
        this.d = lt0.d;
        this.e = lt0.e;
        this.f = lt0.f;
        this.g = lt0.g;
        this.h = lt0.h;
        this.i = lt0.i;
        this.j = lt0.j;
        this.k = lt0.k;
        this.l = lt0.l;
    }
}
