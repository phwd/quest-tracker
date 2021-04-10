package X;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class U8 extends AbstractC0131Ob<AtomicLong> {
    public final /* synthetic */ AbstractC0131Ob A00;

    public U8(AbstractC0131Ob ob) {
        this.A00 = ob;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final AtomicLong A02(lk lkVar) throws IOException {
        return new AtomicLong(((Number) this.A00.A02(lkVar)).longValue());
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, AtomicLong atomicLong) throws IOException {
        this.A00.A03(mmVar, Long.valueOf(atomicLong.get()));
    }
}
