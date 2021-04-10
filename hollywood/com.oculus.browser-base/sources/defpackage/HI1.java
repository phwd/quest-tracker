package defpackage;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* renamed from: HI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HI1 extends WeakReference {

    /* renamed from: a  reason: collision with root package name */
    public final int f8151a;

    public HI1(Throwable th, ReferenceQueue referenceQueue) {
        super(th, referenceQueue);
        Objects.requireNonNull(th, "The referent cannot be null");
        this.f8151a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == HI1.class) {
            if (this == obj) {
                return true;
            }
            HI1 hi1 = (HI1) obj;
            return this.f8151a == hi1.f8151a && get() == hi1.get();
        }
    }

    public final int hashCode() {
        return this.f8151a;
    }
}
