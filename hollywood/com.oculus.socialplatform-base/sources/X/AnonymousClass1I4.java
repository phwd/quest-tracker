package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1I4  reason: invalid class name */
public final class AnonymousClass1I4 implements AnonymousClass1EW {
    public final Set<AnonymousClass1EW> A00 = new HashSet();

    @Override // X.AnonymousClass1EW
    public final boolean AAM(@Nullable Map<String, String> map) {
        boolean z = true;
        for (AnonymousClass1EW r0 : this.A00) {
            z &= r0.AAM(map);
        }
        return z;
    }
}
