package defpackage;

import java.lang.ref.Reference;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/* renamed from: MI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class MI1 extends AbstractC5066uI1 {

    /* renamed from: a  reason: collision with root package name */
    public final BI1 f8470a = new BI1();

    @Override // defpackage.AbstractC5066uI1
    public final void a(Throwable th, Throwable th2) {
        if (th2 != th) {
            Objects.requireNonNull(th2, "The suppressed exception cannot be null.");
            BI1 bi1 = this.f8470a;
            for (Reference poll = bi1.b.poll(); poll != null; poll = bi1.b.poll()) {
                bi1.f7729a.remove(poll);
            }
            List list = (List) bi1.f7729a.get(new HI1(th, null));
            if (list == null) {
                list = new Vector(2);
                List list2 = (List) bi1.f7729a.putIfAbsent(new HI1(th, bi1.b), list);
                if (list2 != null) {
                    list = list2;
                }
            }
            list.add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }
}
