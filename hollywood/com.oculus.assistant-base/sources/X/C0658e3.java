package X;

import java.util.List;

/* renamed from: X.e3  reason: case insensitive filesystem */
public final class C0658e3 implements eq {
    public final AnonymousClass6Z A00;
    public final List A01;
    public volatile boolean A02;

    public C0658e3(List list, AnonymousClass6Z r4) {
        if (!list.isEmpty()) {
            this.A01 = list;
            this.A00 = r4;
            return;
        }
        throw new IllegalArgumentException("payloads cannot be empty");
    }
}
