package X;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0W5  reason: invalid class name */
public class AnonymousClass0W5 extends AnonymousClass0Bd<AtomicInteger> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r3, AtomicInteger atomicInteger) throws IOException {
        r3.A0B((long) atomicInteger.get());
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final AtomicInteger A02(AnonymousClass0Fo r3) throws IOException {
        try {
            return new AtomicInteger(r3.A0A());
        } catch (NumberFormatException e) {
            throw new AnonymousClass0XQ(e);
        }
    }
}
