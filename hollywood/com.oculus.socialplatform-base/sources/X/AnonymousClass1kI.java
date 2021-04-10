package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1kI  reason: invalid class name */
public final class AnonymousClass1kI {
    @GuardedBy("this")
    public Map<AnonymousClass0H3, AnonymousClass0PZ> A00 = new HashMap();

    @Nullable
    public final synchronized AnonymousClass0PZ A00(AnonymousClass0H3 r7) {
        AnonymousClass0PZ r0;
        if (r7 != null) {
            AnonymousClass0PZ r5 = this.A00.get(r7);
            if (r5 != null) {
                synchronized (r5) {
                    if (!AnonymousClass0PZ.A08(r5)) {
                        this.A00.remove(r7);
                        AnonymousClass0J5.A02(AnonymousClass1kI.class, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(r5)), r7.A5D(), Integer.valueOf(System.identityHashCode(r7)));
                        r0 = null;
                    } else {
                        r0 = AnonymousClass0PZ.A03(r5);
                    }
                }
            } else {
                r0 = r5;
            }
        } else {
            throw null;
        }
        return r0;
    }

    public final synchronized void A01(AnonymousClass0H3 r7, AnonymousClass0PZ r8) {
        if (r7 == null) {
            throw null;
        } else if (r8 != null) {
            C00740Ii.A01(Boolean.valueOf(AnonymousClass0PZ.A08(r8)));
            AnonymousClass0PZ r4 = this.A00.get(r7);
            if (r4 != null) {
                AbstractC00820Ju A002 = AbstractC00820Ju.A00(r4.A0B);
                AbstractC00820Ju A003 = AbstractC00820Ju.A00(r8.A0B);
                if (!(A002 == null || A003 == null)) {
                    try {
                        if (A002.A06() == A003.A06()) {
                            this.A00.remove(r7);
                            this.A00.size();
                        }
                    } finally {
                        AbstractC00820Ju.A03(A003);
                        AbstractC00820Ju.A03(A002);
                        AnonymousClass0PZ.A04(r4);
                    }
                }
                AbstractC00820Ju.A03(A003);
                AbstractC00820Ju.A03(A002);
                AnonymousClass0PZ.A04(r4);
            }
        } else {
            throw null;
        }
    }
}
