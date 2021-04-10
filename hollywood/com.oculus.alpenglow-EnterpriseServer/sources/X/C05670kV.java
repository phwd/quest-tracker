package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0kV  reason: invalid class name and case insensitive filesystem */
public final class C05670kV extends RuntimeException {
    public C05670kV() {
    }

    public C05670kV(String str) {
        super("A local injection was attempted before the constructor completed or before injectMe was called.");
    }
}
