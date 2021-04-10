package X;

import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1oR  reason: invalid class name */
public final class AnonymousClass1oR {
    public static final long A0E = TimeUnit.HOURS.toMillis(2);
    public static final long A0F = TimeUnit.MINUTES.toMillis(30);
    public long A00;
    public long A01 = 41943040;
    public final long A02 = 41943040;
    public final long A03 = 10485760;
    public final C09621of A04;
    public final AnonymousClass1oV A05;
    public final AnonymousClass1oY A06;
    public final AnonymousClass0L6 A07;
    public final AnonymousClass0LA A08;
    public final Object A09 = new Object();
    @GuardedBy("mLock")
    @VisibleForTesting
    public final Set<String> A0A;
    public final CountDownLatch A0B;
    public final C09611oe A0C;
    public final C09651oi A0D;

    @GuardedBy("mLock")
    public static boolean A00(AnonymousClass1oR r17) {
        boolean z;
        long j;
        AnonymousClass0LA r7 = r17.A08;
        long now = r7.now();
        AnonymousClass1oY r9 = r17.A06;
        synchronized (r9) {
            z = r9.A02;
        }
        if (z) {
            long j2 = r17.A00;
            if (j2 != -1 && now - j2 <= A0F) {
                return false;
            }
        }
        long now2 = r7.now();
        long j3 = A0E + now2;
        try {
            long j4 = 0;
            int i = 0;
            for (AnonymousClass1oE r2 : r17.A05.A3N()) {
                i++;
                long j5 = r2.A00;
                if (j5 < 0) {
                    j5 = r2.A02.A00.length();
                    r2.A00 = j5;
                }
                j4 += j5;
                if (r2.A00() > j3) {
                    if (r2.A00 < 0) {
                        r2.A00 = r2.A02.A00.length();
                    }
                    r2.A00();
                }
            }
            synchronized (r9) {
                j = r9.A00;
            }
            long j6 = (long) i;
            if (!(j == j6 && r9.A00() == j4)) {
                synchronized (r9) {
                    r9.A00 = j6;
                    r9.A01 = j4;
                    r9.A02 = true;
                }
            }
            r17.A00 = now2;
            return true;
        } catch (IOException e) {
            e.getMessage();
            return false;
        }
    }

    public AnonymousClass1oR(AnonymousClass1oV r3, C09621of r4, C09651oi r5, C09611oe r6) {
        AnonymousClass0L6 r0;
        synchronized (AnonymousClass0L6.class) {
            r0 = AnonymousClass0L6.A07;
            if (r0 == null) {
                r0 = new AnonymousClass0L6();
                AnonymousClass0L6.A07 = r0;
            }
        }
        this.A07 = r0;
        this.A05 = r3;
        this.A04 = r4;
        this.A00 = -1;
        this.A0D = r5;
        this.A0C = r6;
        this.A06 = new AnonymousClass1oY();
        this.A08 = AnonymousClass0pW.A00;
        this.A0A = new HashSet();
        this.A0B = new CountDownLatch(0);
    }
}
