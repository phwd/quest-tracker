package X;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.hi  reason: case insensitive filesystem */
public final /* synthetic */ class C0794hi implements AbstractC0463a6 {
    public final /* synthetic */ String A00;
    public final /* synthetic */ AtomicInteger A01;

    public /* synthetic */ C0794hi(String str, AtomicInteger atomicInteger) {
        this.A00 = str;
        this.A01 = atomicInteger;
    }

    @Override // X.AbstractC0463a6
    public final Object get() {
        return AnonymousClass08.A00(this.A00, this.A01.getAndIncrement());
    }
}
