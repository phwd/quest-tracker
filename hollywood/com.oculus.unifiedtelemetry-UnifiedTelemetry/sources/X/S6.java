package X;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class S6 extends AbstractC0131Ob<AtomicBoolean> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, AtomicBoolean atomicBoolean) throws IOException {
        mmVar.A0H(atomicBoolean.get());
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final AtomicBoolean A02(lk lkVar) throws IOException {
        return new AtomicBoolean(lkVar.A0S());
    }
}
