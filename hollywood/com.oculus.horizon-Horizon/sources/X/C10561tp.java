package X;

import com.facebook.common.util.TriState;
import com.facebook.quicklog.EventLevel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.1tp  reason: invalid class name and case insensitive filesystem */
public final class C10561tp {
    public RunnableC10551tn A00;
    public final AnonymousClass0LF A01;
    public final AnonymousClass1tt A02;
    public final AnonymousClass1u6 A03;
    @GuardedBy("mLock")
    public final ArrayList<String>[] A04;
    public final AnonymousClass1u6 A05;
    public final AnonymousClass1hU A06;
    @GuardedBy("mLock")
    public final AnonymousClass0VE<Collection<? extends Object>> A07;
    public final AnonymousClass0VF A08;
    public final AnonymousClass0VH A09;
    public final Object A0A = new Object();
    public volatile Boolean A0B;

    public C10561tp(AnonymousClass1hU r3, @Nullable AnonymousClass0LF r4, @Nullable AnonymousClass0VF r5, AnonymousClass0VH r6) {
        this.A06 = r3;
        this.A01 = r4;
        this.A03 = new AnonymousClass1u6(r4);
        this.A05 = new AnonymousClass1u6(r4);
        this.A04 = new ArrayList[16358];
        TriState.valueOf((Boolean) null).asBooleanObject();
        this.A08 = r5;
        this.A09 = r6;
        this.A02 = new AnonymousClass1tt(r6, this.A05);
        this.A07 = r6.A5b();
    }

    /* JADX WARN: Incorrect args count in method signature: (IIIJLjava/util/concurrent/TimeUnit;ZLjava/lang/String;LX/0T7;ILX/0VE<*>;ILX/1ty;Lcom/facebook/quicklog/HealthPerfLog;)V */
    public final void A00(int i, int i2, @EventLevel int i3, long j, TimeUnit timeUnit, String str, AnonymousClass0T7 r28, @Nullable int i4) {
        long j2;
        int i5;
        int i6 = i ^ (i2 * 179426549);
        AnonymousClass1u6 r4 = this.A03;
        r4.A00();
        try {
            AnonymousClass1tt r8 = this.A02;
            RunnableC10551tn A012 = r8.A01(i6);
            boolean z = false;
            if (A012 == null || !AnonymousClass1tt.A00(A012)) {
                j2 = 0;
                i5 = 0;
            } else {
                j2 = timeUnit.toNanos(j) - A012.A09;
                long j3 = A012.A08;
                i5 = A012.A06;
                if (j3 == 0 || i4 == 0) {
                    A012.A00(j2, TimeUnit.NANOSECONDS, i3, str, r28);
                    timeUnit.toMillis(j);
                    timeUnit.toNanos(j);
                } else {
                    z = true;
                }
            }
            if (z) {
                r4.A00();
                RunnableC10551tn A013 = r8.A01(i6);
                if (A013 != null && A013.A06 == i5 && AnonymousClass1tt.A00(A013)) {
                    A013.A00(j2, TimeUnit.NANOSECONDS, i3, str, r28);
                    timeUnit.toMillis(j);
                    timeUnit.toNanos(j);
                }
                r4.unlock();
            }
        } finally {
            r4.unlock();
        }
    }
}
