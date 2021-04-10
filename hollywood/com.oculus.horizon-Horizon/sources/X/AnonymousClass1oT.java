package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1oT  reason: invalid class name */
public final class AnonymousClass1oT {
    @GuardedBy("this")
    public Map<AnonymousClass1kC, AnonymousClass1qQ> A00 = new HashMap();

    @Nullable
    public final synchronized AnonymousClass1qQ A00(AnonymousClass1kC r7) {
        AnonymousClass1qQ r0;
        if (r7 != null) {
            AnonymousClass1qQ r4 = this.A00.get(r7);
            if (r4 != null) {
                synchronized (r4) {
                    try {
                        if (!AnonymousClass1qQ.A06(r4)) {
                            this.A00.remove(r7);
                            C01080Kb.A02(AnonymousClass1oT.class, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(r4)), r7.A4c(), Integer.valueOf(System.identityHashCode(r7)));
                            r0 = null;
                        } else {
                            r0 = AnonymousClass1qQ.A02(r4);
                        }
                    } finally {
                    }
                }
            } else {
                r0 = r4;
            }
        } else {
            throw null;
        }
        return r0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
        if (r2 != null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
        if (r3 != null) goto L_0x0054;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void A01(X.AnonymousClass1kC r7, X.AnonymousClass1qQ r8) {
        /*
        // Method dump skipped, instructions count: 107
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1oT.A01(X.1kC, X.1qQ):void");
    }
}
