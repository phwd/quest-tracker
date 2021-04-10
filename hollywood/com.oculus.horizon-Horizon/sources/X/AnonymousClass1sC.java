package X;

import android.graphics.ColorSpace;
import android.util.Pair;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1sC  reason: invalid class name */
public final class AnonymousClass1sC {
    @Nullable
    public final ColorSpace A00;
    @Nullable
    public final Pair<Integer, Integer> A01;

    public AnonymousClass1sC(int i, int i2, @Nullable ColorSpace colorSpace) {
        Pair<Integer, Integer> pair;
        if (i == -1 || i2 == -1) {
            pair = null;
        } else {
            pair = new Pair<>(Integer.valueOf(i), Integer.valueOf(i2));
        }
        this.A01 = pair;
        this.A00 = colorSpace;
    }
}
