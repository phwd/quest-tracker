package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLongArray;

public class U7 extends AbstractC0131Ob<AtomicLongArray> {
    public final /* synthetic */ AbstractC0131Ob A00;

    public U7(AbstractC0131Ob ob) {
        this.A00 = ob;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final AtomicLongArray A02(lk lkVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        lkVar.A0L();
        while (lkVar.A0R()) {
            arrayList.add(Long.valueOf(((Number) this.A00.A02(lkVar)).longValue()));
        }
        lkVar.A0N();
        int size = arrayList.size();
        AtomicLongArray atomicLongArray = new AtomicLongArray(size);
        for (int i = 0; i < size; i++) {
            atomicLongArray.set(i, ((Number) arrayList.get(i)).longValue());
        }
        return atomicLongArray;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, AtomicLongArray atomicLongArray) throws IOException {
        AtomicLongArray atomicLongArray2 = atomicLongArray;
        mmVar.A07();
        int length = atomicLongArray2.length();
        for (int i = 0; i < length; i++) {
            this.A00.A03(mmVar, Long.valueOf(atomicLongArray2.get(i)));
        }
        mmVar.A09();
    }
}
