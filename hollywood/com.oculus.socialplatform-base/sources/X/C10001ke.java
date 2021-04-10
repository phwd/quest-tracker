package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1ke  reason: invalid class name and case insensitive filesystem */
public final class C10001ke implements AnonymousClass1j6 {
    public final List<AnonymousClass1j6> A00;

    @Override // X.AnonymousClass1j6
    public final synchronized void A79(String str, int i, boolean z, @Nullable String str2) {
        List<AnonymousClass1j6> list = this.A00;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            AnonymousClass1j6 r0 = list.get(i2);
            if (r0 != null) {
                try {
                    r0.A79(str, i, z, str2);
                } catch (Exception e) {
                    AnonymousClass0J5.A04("ForwardingImageOriginListener", "InternalListener exception in onImageLoaded", e);
                }
            }
        }
    }

    public C10001ke(AnonymousClass1j6... r3) {
        ArrayList arrayList = new ArrayList(r3.length);
        this.A00 = arrayList;
        Collections.addAll(arrayList, r3);
    }
}
