package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.1cz  reason: invalid class name and case insensitive filesystem */
public final class C07771cz {
    public final List<C07761cy<?>> A00 = new ArrayList();

    @Nullable
    public final synchronized <Z> AnonymousClass1dV<Z> A00(@NonNull Class<Z> cls) {
        AbstractC06701at r0;
        int i = 0;
        List<C07761cy<?>> list = this.A00;
        int size = list.size();
        while (true) {
            if (i >= size) {
                r0 = (AnonymousClass1dV<Z>) null;
                break;
            }
            C07761cy<?> r1 = list.get(i);
            if (r1.A01.isAssignableFrom(cls)) {
                r0 = (AnonymousClass1dV<T>) r1.A00;
                break;
            }
            i++;
        }
        return (AnonymousClass1dV<Z>) r0;
    }
}
