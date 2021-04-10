package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* renamed from: X.0V9  reason: invalid class name */
public class AnonymousClass0V9 extends AnonymousClass0yl<AtomicIntegerArray> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final AtomicIntegerArray A02(C09120zR r6) throws IOException {
        ArrayList arrayList = new ArrayList();
        r6.A0L();
        while (r6.A0R()) {
            try {
                arrayList.add(Integer.valueOf(r6.A0D()));
            } catch (NumberFormatException e) {
                throw new C03080c5(e);
            }
        }
        r6.A0N();
        int size = arrayList.size();
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
        for (int i = 0; i < size; i++) {
            atomicIntegerArray.set(i, ((Number) arrayList.get(i)).intValue());
        }
        return atomicIntegerArray;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r5, AtomicIntegerArray atomicIntegerArray) throws IOException {
        AtomicIntegerArray atomicIntegerArray2 = atomicIntegerArray;
        r5.A05();
        int length = atomicIntegerArray2.length();
        for (int i = 0; i < length; i++) {
            r5.A0A((long) atomicIntegerArray2.get(i));
        }
        r5.A07();
    }
}
