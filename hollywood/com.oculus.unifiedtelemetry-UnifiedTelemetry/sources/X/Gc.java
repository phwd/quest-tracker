package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Gc extends Exception {
    public Gc(String str) {
        super(str);
    }
}
