package X;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0d6  reason: invalid class name */
public class AnonymousClass0d6 extends AnonymousClass13Y<AtomicInteger> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r3, AtomicInteger atomicInteger) throws IOException {
        r3.A0A((long) atomicInteger.get());
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final AtomicInteger A02(AnonymousClass14I r3) throws IOException {
        try {
            return new AtomicInteger(r3.A0D());
        } catch (NumberFormatException e) {
            throw new AnonymousClass0eR(e);
        }
    }
}
