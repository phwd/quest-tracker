package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* renamed from: X.Sw  reason: case insensitive filesystem */
public class C0161Sw extends AbstractC0131Ob<AtomicIntegerArray> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final AtomicIntegerArray A02(lk lkVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        lkVar.A0L();
        while (lkVar.A0R()) {
            try {
                arrayList.add(Integer.valueOf(lkVar.A0D()));
            } catch (NumberFormatException e) {
                throw new U0(e);
            }
        }
        lkVar.A0N();
        int size = arrayList.size();
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
        for (int i = 0; i < size; i++) {
            atomicIntegerArray.set(i, ((Number) arrayList.get(i)).intValue());
        }
        return atomicIntegerArray;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
        AtomicIntegerArray atomicIntegerArray2 = atomicIntegerArray;
        mmVar.A07();
        int length = atomicIntegerArray2.length();
        for (int i = 0; i < length; i++) {
            mmVar.A0C((long) atomicIntegerArray2.get(i));
        }
        mmVar.A09();
    }
}
