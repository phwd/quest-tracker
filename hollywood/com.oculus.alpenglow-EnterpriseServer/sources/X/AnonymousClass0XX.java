package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLongArray;

/* renamed from: X.0XX  reason: invalid class name */
public class AnonymousClass0XX extends AnonymousClass0Bd<AtomicLongArray> {
    public final /* synthetic */ AnonymousClass0Bd A00;

    public AnonymousClass0XX(AnonymousClass0Bd r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final AtomicLongArray A02(AnonymousClass0Fo r7) throws IOException {
        ArrayList arrayList = new ArrayList();
        r7.A0H();
        while (r7.A0N()) {
            arrayList.add(Long.valueOf(((Number) this.A00.A02(r7)).longValue()));
        }
        r7.A0J();
        int size = arrayList.size();
        AtomicLongArray atomicLongArray = new AtomicLongArray(size);
        for (int i = 0; i < size; i++) {
            atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
        }
        return atomicLongArray;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r6, AtomicLongArray atomicLongArray) throws IOException {
        AtomicLongArray atomicLongArray2 = atomicLongArray;
        r6.A06();
        int length = atomicLongArray2.length();
        for (int i = 0; i < length; i++) {
            this.A00.A03(r6, Long.valueOf(atomicLongArray2.get(i)));
        }
        r6.A08();
    }
}
