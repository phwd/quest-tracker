package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0wd  reason: invalid class name and case insensitive filesystem */
public final class C08140wd implements AbstractC08160wg {
    public final Set<AbstractC08160wg> A00 = new HashSet();

    @Override // X.AbstractC08160wg
    public final boolean A8L(@Nullable Map<String, String> map) {
        boolean z = true;
        for (AbstractC08160wg r0 : this.A00) {
            z &= r0.A8L(map);
        }
        return z;
    }
}
