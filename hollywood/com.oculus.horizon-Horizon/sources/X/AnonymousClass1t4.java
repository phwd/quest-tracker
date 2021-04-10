package X;

import android.os.Looper;
import android.util.Pair;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1t4  reason: invalid class name */
public class AnonymousClass1t4 {
    public final void A00() {
        AnonymousClass1qD r0;
        Pair pair;
        boolean remove;
        List list;
        AnonymousClass1qU r3;
        List list2;
        List list3;
        AnonymousClass1pm r02;
        if (!(this instanceof AnonymousClass1s5)) {
            if (this instanceof C10161re) {
                C10161re r2 = (C10161re) this;
                AnonymousClass1qT r1 = r2.A02;
                r1.A02.A02();
                r1.A00 = true;
                r0 = r2.A01;
            } else if (!(this instanceof AnonymousClass1rD)) {
                if (this instanceof AnonymousClass1p3) {
                    r02 = ((AnonymousClass1p3) this).A01;
                } else if (this instanceof AnonymousClass1pS) {
                    r02 = ((AnonymousClass1pS) this).A01;
                } else if (this instanceof AnonymousClass1p4) {
                    r02 = ((AnonymousClass1p4) this).A01;
                } else if (this instanceof AnonymousClass1k3) {
                    AnonymousClass1k3 r22 = (AnonymousClass1k3) this;
                    if (r22.A02.cancel(false)) {
                        r22.A01.A00();
                        return;
                    }
                    return;
                } else if (this instanceof AnonymousClass1tC) {
                    ((AnonymousClass1tC) this).A01.set(true);
                    return;
                } else if (!(this instanceof AnonymousClass1s8) && (this instanceof AnonymousClass1jy)) {
                    AnonymousClass1jy r23 = (AnonymousClass1jy) this;
                    if (Looper.myLooper() != Looper.getMainLooper()) {
                        r23.A01.A02();
                        return;
                    } else {
                        r23.A00.A00.execute(new AnonymousClass1k5(r23));
                        return;
                    }
                } else {
                    return;
                }
                r02.A00();
                return;
            } else {
                AnonymousClass1rD r12 = (AnonymousClass1rD) this;
                C09931qV r7 = r12.A01;
                synchronized (r7) {
                    CopyOnWriteArraySet<Pair<Consumer<T>, ProducerContext>> copyOnWriteArraySet = r7.A06;
                    pair = r12.A00;
                    remove = copyOnWriteArraySet.remove(pair);
                    list = null;
                    if (!remove) {
                        r3 = null;
                        list2 = null;
                    } else if (copyOnWriteArraySet.isEmpty()) {
                        r3 = r7.A02;
                        list2 = null;
                    } else {
                        List A02 = C09931qV.A02(r7);
                        list2 = C09931qV.A03(r7);
                        list3 = C09931qV.A01(r7);
                        r3 = null;
                        list = A02;
                    }
                    list3 = null;
                }
                AnonymousClass1qU.A01(list);
                AnonymousClass1qU.A02(list2);
                AnonymousClass1qU.A00(list3);
                if (r3 != null) {
                    r3.A03();
                }
                if (remove) {
                    r0 = (AnonymousClass1qD) pair.first;
                } else {
                    return;
                }
            }
            r0.A03();
            return;
        }
        AnonymousClass1s5 r03 = (AnonymousClass1s5) this;
        AnonymousClass1pm r13 = r03.A00;
        r13.A00();
        r03.A01.A01.A89(r13);
    }
}
