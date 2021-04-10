package X;

import com.facebook.inject.ApplicationScoped;
import java.util.ArrayDeque;
import javax.annotation.Nullable;

public final class Pj {
    public static final ArrayDeque<Pj> A04 = new ArrayDeque<>(32);
    @Nullable
    public Y2 A00;
    @Nullable
    public QF A01;
    public Qe A02;
    @Nullable
    public Byte A03;

    @Nullable
    public static Pj A00(@Nullable Object obj, @Nullable AbstractC0247Xu xu) {
        Pj pollFirst;
        if (obj != null || xu == null) {
            return null;
        }
        ArrayDeque<Pj> arrayDeque = A04;
        synchronized (arrayDeque) {
            pollFirst = arrayDeque.pollFirst();
        }
        if (pollFirst == null) {
            pollFirst = new Pj();
        }
        Qe qe = Qe.A01.get();
        pollFirst.A02 = qe;
        byte b = qe.A00;
        qe.A00 = (byte) (1 | b);
        pollFirst.A03 = Byte.valueOf(b);
        Y2 y2 = (Y2) xu.getScope(ApplicationScoped.class);
        pollFirst.A00 = y2;
        pollFirst.A01 = y2.A01();
        return pollFirst;
    }

    public final void A01() {
        QF qf = this.A01;
        if (qf != null) {
            Y2.A00(qf);
            this.A01 = null;
        }
        this.A00 = null;
        Byte b = this.A03;
        if (b != null) {
            this.A02.A00 = b.byteValue();
            this.A03 = null;
        }
        this.A02 = null;
        ArrayDeque<Pj> arrayDeque = A04;
        synchronized (arrayDeque) {
            arrayDeque.addFirst(this);
        }
    }
}
