package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Hr  reason: invalid class name */
public final class AnonymousClass1Hr {
    public final List<Integer> A00;

    public AnonymousClass1Hr(List<Integer> list) {
        this.A00 = Collections.unmodifiableList(new ArrayList(list));
    }
}
