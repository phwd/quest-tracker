package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ReturnsOwnership;
import java.io.IOException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lK  reason: invalid class name */
public final class AnonymousClass1lK implements AnonymousClass0H1 {
    public static int A03;
    @Nullable
    public static AnonymousClass1lK A04;
    public static final Object A05 = new Object();
    @Nullable
    public AnonymousClass0H3 A00;
    @Nullable
    public IOException A01;
    @Nullable
    public AnonymousClass1lK A02;

    @ReturnsOwnership
    public static AnonymousClass1lK A00() {
        synchronized (A05) {
            AnonymousClass1lK r1 = A04;
            if (r1 == null) {
                return new AnonymousClass1lK();
            }
            A04 = r1.A02;
            r1.A02 = null;
            A03--;
            return r1;
        }
    }

    public final void A01() {
        synchronized (A05) {
            int i = A03;
            if (i < 5) {
                this.A00 = null;
                this.A01 = null;
                A03 = i + 1;
                AnonymousClass1lK r0 = A04;
                if (r0 != null) {
                    this.A02 = r0;
                }
                A04 = this;
            }
        }
    }
}
