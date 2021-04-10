package defpackage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: jS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3209jS0 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10206a;
    public boolean b;
    public Set c = new HashSet();
    public C1322Vq0 d = new C1322Vq0();

    public void a() {
        this.b = false;
        this.c.clear();
        e();
    }

    public List b() {
        return new ArrayList(this.c);
    }

    public boolean c(Object obj) {
        return this.c.contains(obj);
    }

    public boolean d() {
        return !this.c.isEmpty() || this.b;
    }

    public final void e() {
        List b2 = b();
        Iterator it = this.d.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC3039iS0) uq0.next()).b(b2);
            } else {
                return;
            }
        }
    }

    public boolean f(Object obj) {
        if (this.c.contains(obj)) {
            this.c.remove(obj);
        } else {
            if (this.f10206a) {
                this.c.clear();
            }
            this.c.add(obj);
        }
        if (this.c.isEmpty()) {
            this.b = false;
        }
        e();
        return this.c.contains(obj);
    }
}
