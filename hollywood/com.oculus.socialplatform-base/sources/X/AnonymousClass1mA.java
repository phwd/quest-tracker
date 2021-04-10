package X;

import android.os.Looper;
import android.util.Pair;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1mA  reason: invalid class name */
public class AnonymousClass1mA {
    public final void A00() {
        AbstractC10011kf r0;
        Pair pair;
        boolean remove;
        List list;
        C10161kv r3;
        List list2;
        List list3;
        AbstractC09741jf r02;
        if (!(this instanceof AnonymousClass1lC)) {
            if (this instanceof AnonymousClass1jh) {
                AnonymousClass1jh r2 = (AnonymousClass1jh) this;
                C09721jc r1 = r2.A02;
                r1.A02.A02();
                r1.A00 = true;
                r0 = r2.A01;
            } else if (!(this instanceof C10191kz)) {
                if (this instanceof AnonymousClass1j3) {
                    r02 = ((AnonymousClass1j3) this).A01;
                } else if (this instanceof C09641jB) {
                    r02 = ((C09641jB) this).A01;
                } else if (this instanceof AnonymousClass1j4) {
                    r02 = ((AnonymousClass1j4) this).A01;
                } else if (this instanceof AnonymousClass1lN) {
                    AnonymousClass1lN r22 = (AnonymousClass1lN) this;
                    if (r22.A02.cancel(false)) {
                        r22.A01.A00();
                        return;
                    }
                    return;
                } else if (this instanceof AnonymousClass1m7) {
                    ((AnonymousClass1m7) this).A01.set(true);
                    return;
                } else if (!(this instanceof C09801jn) && (this instanceof AnonymousClass1l5)) {
                    AnonymousClass1l5 r23 = (AnonymousClass1l5) this;
                    if (Looper.myLooper() != Looper.getMainLooper()) {
                        r23.A01.cancel();
                        return;
                    } else {
                        r23.A00.A00.execute(new AnonymousClass1m9(r23));
                        return;
                    }
                } else {
                    return;
                }
                r02.A02();
                return;
            } else {
                C10191kz r12 = (C10191kz) this;
                C10171kw r7 = r12.A01;
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
                        List A02 = C10171kw.A02(r7);
                        list2 = C10171kw.A03(r7);
                        list3 = C10171kw.A01(r7);
                        r3 = null;
                        list = A02;
                    }
                    list3 = null;
                }
                C10161kv.A01(list);
                C10161kv.A02(list2);
                C10161kv.A00(list3);
                if (r3 != null) {
                    r3.A03();
                }
                if (remove) {
                    r0 = (AbstractC10011kf) pair.first;
                } else {
                    return;
                }
            }
            r0.A04();
            return;
        }
        AnonymousClass1lC r03 = (AnonymousClass1lC) this;
        AbstractC09741jf r13 = r03.A00;
        r13.A02();
        r03.A01.A01.A94(r13);
    }
}
