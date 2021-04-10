package defpackage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* renamed from: Ip1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Ip1 {

    /* renamed from: a  reason: collision with root package name */
    public final Class f8251a;
    public final Set b = Collections.newSetFromMap(new WeakHashMap());

    public Ip1(Class cls) {
        this.f8251a = cls;
    }

    public final void a(Hp1 hp1, Fp1 fp1) {
        fp1.getClass();
        hp1.f8184a.a();
        if (hp1.c.containsKey(this) && !fp1.equals(hp1.a(this))) {
            c(hp1);
        }
        hp1.c.put(this, new WeakReference(fp1));
        if (!(e(hp1) != null)) {
            this.b.add(hp1);
        }
    }

    public final void b(Fp1 fp1) {
        Iterator it = new ArrayList(this.b).iterator();
        while (it.hasNext()) {
            Hp1 hp1 = (Hp1) it.next();
            if (fp1.equals(hp1.a(this))) {
                d(hp1);
            }
        }
    }

    public final void c(Hp1 hp1) {
        Iterator it = new ArrayList(this.b).iterator();
        while (it.hasNext()) {
            Hp1 hp12 = (Hp1) it.next();
            if (hp1.equals(hp12)) {
                d(hp12);
            }
        }
    }

    public final void d(Hp1 hp1) {
        Fp1 fp1;
        hp1.f8184a.a();
        WeakReference weakReference = (WeakReference) hp1.c.remove(this);
        if (!(weakReference == null || (fp1 = (Fp1) weakReference.get()) == null)) {
            hp1.b.post(new Gp1(hp1, fp1));
        }
        this.b.remove(hp1);
    }

    public final Fp1 e(Hp1 hp1) {
        for (Hp1 hp12 : this.b) {
            if (hp1.equals(hp12)) {
                return hp1.a(this);
            }
        }
        return null;
    }
}
