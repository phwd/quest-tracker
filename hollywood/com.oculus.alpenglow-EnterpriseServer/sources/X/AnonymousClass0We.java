package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* renamed from: X.0We  reason: invalid class name */
public class AnonymousClass0We extends AnonymousClass0Bd<AtomicIntegerArray> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final AtomicIntegerArray A02(AnonymousClass0Fo r6) throws IOException {
        ArrayList arrayList = new ArrayList();
        r6.A0H();
        while (r6.A0N()) {
            try {
                arrayList.add(Integer.valueOf(r6.A0A()));
            } catch (NumberFormatException e) {
                throw new AnonymousClass0XQ(e);
            }
        }
        r6.A0J();
        int size = arrayList.size();
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
        for (int i = 0; i < size; i++) {
            atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
        }
        return atomicIntegerArray;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r5, AtomicIntegerArray atomicIntegerArray) throws IOException {
        AtomicIntegerArray atomicIntegerArray2 = atomicIntegerArray;
        r5.A06();
        int length = atomicIntegerArray2.length();
        for (int i = 0; i < length; i++) {
            r5.A0B((long) atomicIntegerArray2.get(i));
        }
        r5.A08();
    }
}
