package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0n5  reason: invalid class name */
public final class AnonymousClass0n5 extends RuntimeException {
    public AnonymousClass0n5() {
    }

    public AnonymousClass0n5(String str) {
        super("A local injection was attempted before the constructor completed or before injectMe was called.");
    }
}
