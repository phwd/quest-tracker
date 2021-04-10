package X;

import com.facebook.inject.ApplicationScoped;
import java.util.ArrayDeque;
import javax.annotation.Nullable;

/* renamed from: X.0Qj  reason: invalid class name */
public final class AnonymousClass0Qj {
    public static final ArrayDeque<AnonymousClass0Qj> A04 = new ArrayDeque<>(32);
    @Nullable
    public AnonymousClass0mK A00;
    @Nullable
    public AnonymousClass0RH A01;
    public C01130Rh A02;
    @Nullable
    public Byte A03;

    @Nullable
    public static AnonymousClass0Qj A00(@Nullable Object obj, @Nullable AnonymousClass0lg r4) {
        AnonymousClass0Qj pollFirst;
        if (obj != null || r4 == null) {
            return null;
        }
        ArrayDeque<AnonymousClass0Qj> arrayDeque = A04;
        synchronized (arrayDeque) {
            pollFirst = arrayDeque.pollFirst();
        }
        if (pollFirst == null) {
            pollFirst = new AnonymousClass0Qj();
        }
        C01130Rh r2 = C01130Rh.A01.get();
        pollFirst.A02 = r2;
        byte b = r2.A00;
        r2.A00 = (byte) (1 | b);
        pollFirst.A03 = Byte.valueOf(b);
        AnonymousClass0mK r0 = (AnonymousClass0mK) r4.getScope(ApplicationScoped.class);
        pollFirst.A00 = r0;
        pollFirst.A01 = r0.A01();
        return pollFirst;
    }

    public final void A01() {
        AnonymousClass0RH r0 = this.A01;
        if (r0 != null) {
            AnonymousClass0mK.A00(r0);
            this.A01 = null;
        }
        this.A00 = null;
        Byte b = this.A03;
        if (b != null) {
            this.A02.A00 = b.byteValue();
            this.A03 = null;
        }
        this.A02 = null;
        ArrayDeque<AnonymousClass0Qj> arrayDeque = A04;
        synchronized (arrayDeque) {
            arrayDeque.addFirst(this);
        }
    }
}
