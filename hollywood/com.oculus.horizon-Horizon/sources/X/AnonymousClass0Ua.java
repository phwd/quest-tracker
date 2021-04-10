package X;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0Ua  reason: invalid class name */
public class AnonymousClass0Ua extends AnonymousClass0yl<AtomicInteger> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r3, AtomicInteger atomicInteger) throws IOException {
        r3.A0A((long) atomicInteger.get());
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final AtomicInteger A02(C09120zR r3) throws IOException {
        try {
            return new AtomicInteger(r3.A0D());
        } catch (NumberFormatException e) {
            throw new C03080c5(e);
        }
    }
}
