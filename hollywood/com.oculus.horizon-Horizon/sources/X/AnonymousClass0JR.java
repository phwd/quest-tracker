package X;

import androidx.annotation.VisibleForTesting;
import java.util.List;

@VisibleForTesting
/* renamed from: X.0JR  reason: invalid class name */
public class AnonymousClass0JR implements AbstractC06950qN {
    public final AnonymousClass0GB A00;
    public final List<C003909l> A01;
    public volatile boolean A02;

    public AnonymousClass0JR(List<C003909l> list, AnonymousClass0GB r4) {
        if (!list.isEmpty()) {
            this.A01 = list;
            this.A00 = r4;
            return;
        }
        throw new IllegalArgumentException("payloads cannot be empty");
    }
}
