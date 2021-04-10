package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class JT extends RuntimeException {
    public JT() {
    }

    public JT(String str) {
        super("A local injection was attempted before the constructor completed or before injectMe was called.");
    }
}
