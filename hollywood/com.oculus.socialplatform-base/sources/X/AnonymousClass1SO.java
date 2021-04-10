package X;

import android.content.Context;
import android.content.Intent;
import javax.annotation.Nullable;

/* renamed from: X.1SO  reason: invalid class name */
public final class AnonymousClass1SO {
    @Nullable
    public static void A00(Intent intent, Context context) {
        AnonymousClass1SN r1;
        AnonymousClass1SM A00 = AnonymousClass1SM.A00();
        synchronized (A00) {
            r1 = A00.A01;
            if (r1 == null) {
                AnonymousClass0Um r3 = A00.A04;
                if (r3 == null) {
                    r3 = new AnonymousClass0Um(AnonymousClass1SM.A0D, AnonymousClass1SM.A0C, AnonymousClass1SM.A0E);
                    A00.A04 = r3;
                }
                r1 = new AnonymousClass1SN(r3, A00.A08);
                A00.A01 = r1;
            }
        }
        r1.A00(intent, context);
    }
}
