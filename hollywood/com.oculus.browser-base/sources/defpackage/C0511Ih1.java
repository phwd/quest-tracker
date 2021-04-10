package defpackage;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: Ih1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0511Ih1 {

    /* renamed from: a  reason: collision with root package name */
    public final ConcurrentHashMap f8242a = new ConcurrentHashMap(16, 0.75f, 10);
    public final ReferenceQueue b = new ReferenceQueue();

    public List a(Throwable th, boolean z) {
        while (true) {
            Reference poll = this.b.poll();
            if (poll == null) {
                break;
            }
            this.f8242a.remove(poll);
        }
        List list = (List) this.f8242a.get(new C0450Hh1(th, null));
        if (!z || list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List list2 = (List) this.f8242a.putIfAbsent(new C0450Hh1(th, this.b), vector);
        return list2 == null ? vector : list2;
    }
}
