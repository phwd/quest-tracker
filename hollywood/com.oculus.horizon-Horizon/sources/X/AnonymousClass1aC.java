package X;

import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: X.1aC  reason: invalid class name */
public class AnonymousClass1aC extends ThreadLocal<ConcurrentLinkedQueue<C09201aE>> {
    public final /* synthetic */ AnonymousClass1aA A00;

    public AnonymousClass1aC(AnonymousClass1aA r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final ConcurrentLinkedQueue<C09201aE> initialValue() {
        return new ConcurrentLinkedQueue();
    }
}
