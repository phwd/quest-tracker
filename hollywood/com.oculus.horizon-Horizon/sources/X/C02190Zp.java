package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Zp  reason: invalid class name and case insensitive filesystem */
public final class C02190Zp {
    public final List<Integer> A00;

    public C02190Zp(List<Integer> list) {
        this.A00 = Collections.unmodifiableList(new ArrayList(list));
    }
}
