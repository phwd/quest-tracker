package defpackage;

import java.util.Map;
import java.util.Objects;

/* renamed from: lm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3603lm0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f10372a = new Object();
    public final Object b = new Object();
    public C5248vO0 c = new C5248vO0();
    public volatile Object d;
    public volatile Object e;
    public int f;
    public boolean g;
    public boolean h;

    public C3603lm0() {
        Object obj = f10372a;
        this.e = obj;
        this.d = obj;
        this.f = -1;
    }

    public void a(Object obj) {
        if (C3224ja.b().b.a()) {
            this.f++;
            this.d = obj;
            if (this.g) {
                this.h = true;
                return;
            }
            this.g = true;
            do {
                this.h = false;
                C4738sO0 a2 = this.c.a();
                if (a2.hasNext()) {
                    C5859z.a(((Map.Entry) a2.next()).getValue());
                    Objects.requireNonNull(null);
                    throw null;
                }
            } while (this.h);
            this.g = false;
            return;
        }
        throw new IllegalStateException("Cannot invoke setValue on a background thread");
    }
}
