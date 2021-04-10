package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0mf  reason: invalid class name and case insensitive filesystem */
public final class C06190mf implements AbstractC01900Xy {
    public final Set<AbstractC01900Xy> A00 = new HashSet();

    @Override // X.AbstractC01900Xy
    public final boolean A8x(@Nullable Map<String, String> map) {
        boolean z = true;
        for (AbstractC01900Xy r0 : this.A00) {
            z &= r0.A8x(map);
        }
        return z;
    }
}
