package X;

import com.facebook.inject.ApplicationScoped;
import java.util.ArrayDeque;
import javax.annotation.Nullable;

/* renamed from: X.0Qe  reason: invalid class name */
public final class AnonymousClass0Qe {
    public static final ArrayDeque<AnonymousClass0Qe> A04 = new ArrayDeque<>(32);
    @Nullable
    public C03060bR A00;
    @Nullable
    public AnonymousClass0RA A01;
    public AnonymousClass0RZ A02;
    @Nullable
    public Byte A03;

    @Nullable
    public static AnonymousClass0Qe A00(@Nullable Object obj, @Nullable AbstractC02990bJ r4) {
        AnonymousClass0Qe pollFirst;
        if (obj != null || r4 == null) {
            return null;
        }
        ArrayDeque<AnonymousClass0Qe> arrayDeque = A04;
        synchronized (arrayDeque) {
            pollFirst = arrayDeque.pollFirst();
        }
        if (pollFirst == null) {
            pollFirst = new AnonymousClass0Qe();
        }
        AnonymousClass0RZ r2 = AnonymousClass0RZ.A01.get();
        pollFirst.A02 = r2;
        byte b = r2.A00;
        r2.A00 = (byte) (1 | b);
        pollFirst.A03 = Byte.valueOf(b);
        C03060bR r0 = (C03060bR) r4.getScope(ApplicationScoped.class);
        pollFirst.A00 = r0;
        pollFirst.A01 = r0.A01();
        return pollFirst;
    }

    public final void A01() {
        AnonymousClass0RA r0 = this.A01;
        if (r0 != null) {
            C03060bR.A00(r0);
            this.A01 = null;
        }
        this.A00 = null;
        Byte b = this.A03;
        if (b != null) {
            this.A02.A00 = b.byteValue();
            this.A03 = null;
        }
        this.A02 = null;
        ArrayDeque<AnonymousClass0Qe> arrayDeque = A04;
        synchronized (arrayDeque) {
            arrayDeque.addFirst(this);
        }
    }
}
