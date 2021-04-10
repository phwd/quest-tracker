package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLongArray;

/* renamed from: X.0eY  reason: invalid class name */
public class AnonymousClass0eY extends AnonymousClass13Y<AtomicLongArray> {
    public final /* synthetic */ AnonymousClass13Y A00;

    public AnonymousClass0eY(AnonymousClass13Y r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final AtomicLongArray A02(AnonymousClass14I r7) throws IOException {
        ArrayList arrayList = new ArrayList();
        r7.A0L();
        while (r7.A0R()) {
            arrayList.add(Long.valueOf(((Number) this.A00.A02(r7)).longValue()));
        }
        r7.A0N();
        int size = arrayList.size();
        AtomicLongArray atomicLongArray = new AtomicLongArray(size);
        for (int i = 0; i < size; i++) {
            atomicLongArray.set(i, ((Number) arrayList.get(i)).longValue());
        }
        return atomicLongArray;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r6, AtomicLongArray atomicLongArray) throws IOException {
        AtomicLongArray atomicLongArray2 = atomicLongArray;
        r6.A05();
        int length = atomicLongArray2.length();
        for (int i = 0; i < length; i++) {
            this.A00.A03(r6, Long.valueOf(atomicLongArray2.get(i)));
        }
        r6.A07();
    }
}
