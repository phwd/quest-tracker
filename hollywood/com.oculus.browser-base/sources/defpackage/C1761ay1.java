package defpackage;

import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: ay1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1761ay1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC3767mk f9504a;
    public Ew1 b;

    public C1761ay1(AbstractC3767mk mkVar) {
        this.f9504a = mkVar;
    }

    public int a() {
        return c().g;
    }

    public final Ew1 b() {
        Ew1 ew1 = this.b;
        if (ew1 != null) {
            return ew1;
        }
        Ew1 ew12 = ((C1932by1) this.f9504a).d;
        this.b = ew12;
        if (ew12 == null) {
            this.b = new Ew1(null, new Zx1(), false, 0, null, null, 2, new HashMap(), null, false, new ArrayList(), 0);
        }
        return this.b;
    }

    public final Yx1 c() {
        return ((C1932by1) this.f9504a).c;
    }

    public String d() {
        return b().c;
    }

    public long e() {
        AbstractC3767mk mkVar = this.f9504a;
        if (((C1932by1) mkVar).b) {
            return (long) ((C1932by1) mkVar).f9574a;
        }
        return 2147483648L;
    }

    public String f() {
        return c().b;
    }

    public String g() {
        return b().f7989a;
    }

    public int h() {
        return b().g;
    }
}
