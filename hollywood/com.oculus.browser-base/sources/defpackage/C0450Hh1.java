package defpackage;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* renamed from: Hh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0450Hh1 extends WeakReference {

    /* renamed from: a  reason: collision with root package name */
    public final int f8174a;

    public C0450Hh1(Throwable th, ReferenceQueue referenceQueue) {
        super(th, referenceQueue);
        Objects.requireNonNull(th, "The referent cannot be null");
        this.f8174a = System.identityHashCode(th);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != C0450Hh1.class) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        C0450Hh1 hh1 = (C0450Hh1) obj;
        return this.f8174a == hh1.f8174a && get() == hh1.get();
    }

    public int hashCode() {
        return this.f8174a;
    }
}
