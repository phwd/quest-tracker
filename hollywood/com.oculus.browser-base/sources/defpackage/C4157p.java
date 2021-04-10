package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* renamed from: p  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4157p extends AbstractC3302k {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f11042a;
    public final AtomicReferenceFieldUpdater b;
    public final AtomicReferenceFieldUpdater c;
    public final AtomicReferenceFieldUpdater d;
    public final AtomicReferenceFieldUpdater e;

    public C4157p(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        super(null);
        this.f11042a = atomicReferenceFieldUpdater;
        this.b = atomicReferenceFieldUpdater2;
        this.c = atomicReferenceFieldUpdater3;
        this.d = atomicReferenceFieldUpdater4;
        this.e = atomicReferenceFieldUpdater5;
    }

    @Override // defpackage.AbstractC3302k
    public boolean a(AbstractC4669s sVar, C3986o oVar, C3986o oVar2) {
        return this.d.compareAndSet(sVar, oVar, oVar2);
    }

    @Override // defpackage.AbstractC3302k
    public boolean b(AbstractC4669s sVar, Object obj, Object obj2) {
        return this.e.compareAndSet(sVar, obj, obj2);
    }

    @Override // defpackage.AbstractC3302k
    public boolean c(AbstractC4669s sVar, r rVar, r rVar2) {
        return this.c.compareAndSet(sVar, rVar, rVar2);
    }

    @Override // defpackage.AbstractC3302k
    public void d(r rVar, r rVar2) {
        this.b.lazySet(rVar, rVar2);
    }

    @Override // defpackage.AbstractC3302k
    public void e(r rVar, Thread thread) {
        this.f11042a.lazySet(rVar, thread);
    }
}
