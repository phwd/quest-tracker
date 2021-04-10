package X;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class S8 extends AbstractC0131Ob<AtomicInteger> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, AtomicInteger atomicInteger) throws IOException {
        mmVar.A0C((long) atomicInteger.get());
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final AtomicInteger A02(lk lkVar) throws IOException {
        try {
            return new AtomicInteger(lkVar.A0D());
        } catch (NumberFormatException e) {
            throw new U0(e);
        }
    }
}
