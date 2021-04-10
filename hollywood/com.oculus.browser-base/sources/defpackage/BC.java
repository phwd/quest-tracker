package defpackage;

import android.content.Context;
import java.util.Objects;

/* renamed from: BC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BC {

    /* renamed from: a  reason: collision with root package name */
    public final C2931hq f7722a;
    public final X8 b;
    public volatile AbstractC2675gI0 c;
    public volatile C4294po1 d;
    public volatile Object e = new C2566fi0();
    public volatile Object f = new C2566fi0();
    public volatile AbstractC2675gI0 g;
    public volatile Object h = new C2566fi0();
    public volatile Object i = new C2566fi0();
    public volatile Object j = new C2566fi0();
    public volatile Object k = new C2566fi0();
    public volatile Object l = new C2566fi0();
    public volatile Object m = new C2566fi0();
    public volatile Object n = new C2566fi0();
    public volatile AbstractC2675gI0 o;
    public volatile Object p = new C2566fi0();
    public volatile AbstractC2675gI0 q;
    public volatile Object r = new C2566fi0();

    public BC(C2931hq hqVar, X8 x8, AbstractC5896zC zCVar) {
        this.f7722a = hqVar;
        this.b = x8;
    }

    public final C0672La0 a() {
        Object obj;
        Object obj2 = this.j;
        if (obj2 instanceof C2566fi0) {
            synchronized (obj2) {
                obj = this.j;
                if (obj instanceof C2566fi0) {
                    obj = new C0672La0(g(), e(), j());
                    KG.b(this.j, obj);
                    this.j = obj;
                }
            }
            obj2 = obj;
        }
        return (C0672La0) obj2;
    }

    public final C0893Op0 b() {
        Object obj;
        Object obj2 = this.i;
        if (obj2 instanceof C2566fi0) {
            synchronized (obj2) {
                obj = this.i;
                if (obj instanceof C2566fi0) {
                    obj = new C0893Op0(g(), e());
                    KG.b(this.i, obj);
                    this.i = obj;
                }
            }
            obj2 = obj;
        }
        return (C0893Op0) obj2;
    }

    public C1622aC c() {
        Object obj;
        Object obj2 = this.p;
        if (obj2 instanceof C2566fi0) {
            synchronized (obj2) {
                obj = this.p;
                if (obj instanceof C2566fi0) {
                    Context a2 = AbstractC3101iq.a(this.f7722a);
                    AbstractC2675gI0 gi0 = this.o;
                    if (gi0 == null) {
                        gi0 = new AC(this, 2);
                        this.o = gi0;
                    }
                    obj = new C1622aC(a2, KG.a(gi0));
                    KG.b(this.p, obj);
                    this.p = obj;
                }
            }
            obj2 = obj;
        }
        return (C1622aC) obj2;
    }

    public RS0 d() {
        Object obj;
        Object obj2 = this.r;
        if (obj2 instanceof C2566fi0) {
            synchronized (obj2) {
                obj = this.r;
                if (obj instanceof C2566fi0) {
                    AbstractC2675gI0 gi0 = this.q;
                    if (gi0 == null) {
                        gi0 = new AC(this, 3);
                        this.q = gi0;
                    }
                    obj = new RS0(KG.a(gi0));
                    KG.b(this.r, obj);
                    this.r = obj;
                }
            }
            obj2 = obj;
        }
        return (RS0) obj2;
    }

    public C2414eo1 e() {
        Object obj;
        Object obj2 = this.m;
        if (obj2 instanceof C2566fi0) {
            synchronized (obj2) {
                obj = this.m;
                if (obj instanceof C2566fi0) {
                    obj = new C2414eo1(i(), g(), j());
                    KG.b(this.m, obj);
                    this.m = obj;
                }
            }
            obj2 = obj;
        }
        return (C2414eo1) obj2;
    }

    public C1207Tu f() {
        AbstractC2675gI0 gi0 = this.c;
        if (gi0 == null) {
            gi0 = new AC(this, 0);
            this.c = gi0;
        }
        M70 a2 = KG.a(gi0);
        Objects.requireNonNull(this.f7722a);
        C1321Vq b2 = C1321Vq.b();
        Objects.requireNonNull(b2, "Cannot return null from a non-@Nullable @Provides method");
        return new C1207Tu(a2, b2, j());
    }

    public C2585fo1 g() {
        Object obj;
        Object obj2 = this.h;
        if (obj2 instanceof C2566fi0) {
            synchronized (obj2) {
                obj = this.h;
                if (obj instanceof C2566fi0) {
                    Context a2 = AbstractC3101iq.a(this.f7722a);
                    C2756go1 h2 = h();
                    AbstractC2675gI0 gi0 = this.g;
                    if (gi0 == null) {
                        gi0 = new AC(this, 1);
                        this.g = gi0;
                    }
                    obj = new C2585fo1(a2, h2, KG.a(gi0), j());
                    KG.b(this.h, obj);
                    this.h = obj;
                }
            }
            obj2 = obj;
        }
        return (C2585fo1) obj2;
    }

    public final C2756go1 h() {
        Object obj;
        Object obj2 = this.e;
        if (obj2 instanceof C2566fi0) {
            synchronized (obj2) {
                obj = this.e;
                if (obj instanceof C2566fi0) {
                    Objects.requireNonNull(this.f7722a);
                    obj = AbstractC2957hy1.f10115a.d;
                    Objects.requireNonNull(obj, "Cannot return null from a non-@Nullable @Provides method");
                    KG.b(this.e, obj);
                    this.e = obj;
                }
            }
            obj2 = obj;
        }
        return (C2756go1) obj2;
    }

    public final C3781mo1 i() {
        Object obj;
        Object obj2 = this.l;
        if (obj2 instanceof C2566fi0) {
            synchronized (obj2) {
                obj = this.l;
                if (obj instanceof C2566fi0) {
                    C2931hq hqVar = this.f7722a;
                    Context a2 = AbstractC3101iq.a(hqVar);
                    Objects.requireNonNull(hqVar);
                    obj = new C3781mo1(a2);
                    KG.b(this.l, obj);
                    this.l = obj;
                }
            }
            obj2 = obj;
        }
        return (C3781mo1) obj2;
    }

    public final C4294po1 j() {
        C4294po1 po1 = this.d;
        if (po1 != null) {
            return po1;
        }
        Objects.requireNonNull(this.f7722a);
        C1321Vq b2 = C1321Vq.b();
        Objects.requireNonNull(b2, "Cannot return null from a non-@Nullable @Provides method");
        C4294po1 po12 = new C4294po1(b2);
        this.d = po12;
        return po12;
    }
}
