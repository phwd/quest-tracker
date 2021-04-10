package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0iW  reason: invalid class name and case insensitive filesystem */
public final class C04610iW extends RuntimeException {
    public C04610iW() {
    }

    public C04610iW(String str) {
        super("A local injection was attempted before the constructor completed or before injectMe was called.");
    }
}
