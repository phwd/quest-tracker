package X;

import com.facebook.inject.ApplicationScoped;
import java.util.ArrayDeque;
import javax.annotation.Nullable;

public final class OJ {
    public static final ArrayDeque<OJ> A04 = new ArrayDeque<>(32);
    @Nullable
    public Sh A00;
    @Nullable
    public Op A01;
    public PE A02;
    @Nullable
    public Byte A03;

    @Nullable
    public static OJ A00(@Nullable Object obj, @Nullable SZ sz) {
        OJ pollFirst;
        if (obj != null || sz == null) {
            return null;
        }
        ArrayDeque<OJ> arrayDeque = A04;
        synchronized (arrayDeque) {
            pollFirst = arrayDeque.pollFirst();
        }
        if (pollFirst == null) {
            pollFirst = new OJ();
        }
        PE pe = PE.A01.get();
        pollFirst.A02 = pe;
        byte b = pe.A00;
        pe.A00 = (byte) (1 | b);
        pollFirst.A03 = Byte.valueOf(b);
        Sh sh = (Sh) sz.getScope(ApplicationScoped.class);
        pollFirst.A00 = sh;
        pollFirst.A01 = sh.A01();
        return pollFirst;
    }

    public final void A01() {
        Op op = this.A01;
        if (op != null) {
            Sh.A00(op);
            this.A01 = null;
        }
        this.A00 = null;
        Byte b = this.A03;
        if (b != null) {
            this.A02.A00 = b.byteValue();
            this.A03 = null;
        }
        this.A02 = null;
        ArrayDeque<OJ> arrayDeque = A04;
        synchronized (arrayDeque) {
            arrayDeque.addFirst(this);
        }
    }
}
