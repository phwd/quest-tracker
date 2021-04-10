package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

/* renamed from: X.0s5  reason: invalid class name */
public class AnonymousClass0s5 implements AnonymousClass09Z {
    public final int A00 = 1;
    public final int A01;
    public final /* synthetic */ AbstractC003209a A02;

    public AnonymousClass0s5(@Nullable AbstractC003209a r2, int i) {
        this.A02 = r2;
        this.A01 = i;
    }

    @Override // X.AnonymousClass09Z
    public final boolean A2s(@NonNull ArrayList<AnonymousClass0sD> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        return this.A02.A0u(arrayList, arrayList2, this.A01, this.A00);
    }
}
