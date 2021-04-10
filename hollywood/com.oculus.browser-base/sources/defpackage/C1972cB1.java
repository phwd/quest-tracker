package defpackage;

import java.lang.ref.WeakReference;

/* renamed from: cB1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1972cB1 extends AbstractC4193pB1 {

    /* renamed from: a  reason: collision with root package name */
    public WeakReference f9590a;

    public C1972cB1(VA1 va1) {
        this.f9590a = new WeakReference(va1);
    }

    @Override // defpackage.AbstractC4193pB1
    public final void a() {
        VA1 va1 = (VA1) this.f9590a.get();
        if (va1 != null) {
            VA1.m(va1);
        }
    }
}
