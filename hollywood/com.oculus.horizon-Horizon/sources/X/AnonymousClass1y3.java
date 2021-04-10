package X;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nullable;

@TargetApi(18)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1y3  reason: invalid class name */
public final class AnonymousClass1y3 {
    @Nullable
    public CountDownLatch A00;
    public final AnonymousClass1yB A01;
    @Nullable
    public final AnonymousClass1yQ A02;
    @Nullable
    public final AnonymousClass1yQ A03;
    @Nullable
    public final AbstractC11091xe A04;
    public final String A05;
    public volatile boolean A06;
    public volatile boolean A07;

    public static synchronized void A00(AnonymousClass1y3 r4) throws IOException {
        AnonymousClass1yQ r3;
        MediaFormat A41;
        MediaFormat A412;
        synchronized (r4) {
            if (!r4.A06 && !r4.A07 && ((r3 = r4.A02) == null || r3.A41() != null)) {
                AnonymousClass1yQ r2 = r4.A03;
                if (r2 == null || r2.A41() != null) {
                    AnonymousClass1yB r1 = r4.A01;
                    r1.A1o(r4.A05);
                    if (!(r3 == null || (A412 = r3.A41()) == null)) {
                        r1.A8b(A412);
                    }
                    if (!(r2 == null || (A41 = r2.A41()) == null)) {
                        r1.A8t(A41);
                    }
                    r1.A8h(0);
                    r1.start();
                    r4.A06 = true;
                }
            }
        }
    }

    public AnonymousClass1y3(String str, AnonymousClass1yB r4, @Nullable AnonymousClass1yQ r5, @Nullable AnonymousClass1yQ r6, AbstractC11091xe r7) {
        this.A01 = r4;
        this.A05 = str;
        this.A02 = r5;
        this.A03 = r6;
        if (!(r5 == null || r6 == null)) {
            this.A00 = new CountDownLatch(2);
        }
        this.A04 = r7;
    }
}
