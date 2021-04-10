package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ow extends RuntimeException {
    public ow() {
    }

    public ow(String str) {
        super("A local injection was attempted before the constructor completed or before injectMe was called.");
    }
}
