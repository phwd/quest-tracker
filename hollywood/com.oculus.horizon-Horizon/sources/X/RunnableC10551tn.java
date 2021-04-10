package X;

import com.facebook.common.util.TriState;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* renamed from: X.1tn  reason: invalid class name and case insensitive filesystem */
public final class RunnableC10551tn implements Runnable, AnonymousClass1Yh {
    public static final String __redex_internal_original_name = "com.facebook.quicklog.QuickEventImpl";
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    public int A06;
    public long A07;
    public long A08;
    public long A09;
    public long A0A;
    public TriState A0B;
    public AnonymousClass1n9 A0C;
    @Nullable
    public AnonymousClass0T0 A0D;
    public short A0E;
    public boolean A0F;
    public boolean A0G;
    public boolean A0H;
    @Nullable
    public AnonymousClass1tq A0I;
    public final AnonymousClass1tr A0J = new AnonymousClass1tr();
    public final ArrayList<String> A0K = new ArrayList<>();

    /* JADX WARN: Incorrect args count in method signature: (JLjava/util/concurrent/TimeUnit;ILjava/lang/String;LX/0T7;LX/0VE<*>;)V */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A00(long r7, java.util.concurrent.TimeUnit r9, @com.facebook.quicklog.EventLevel int r10, java.lang.String r11, @javax.annotation.Nullable X.AnonymousClass0T7 r12) {
        /*
        // Method dump skipped, instructions count: 160
        */
        throw new UnsupportedOperationException("Method not decompiled: X.RunnableC10551tn.A00(long, java.util.concurrent.TimeUnit, int, java.lang.String, X.0T7):void");
    }

    @Override // X.AnonymousClass1Yh
    public final int A3L() {
        return (int) TimeUnit.NANOSECONDS.toMillis(this.A07);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0118  */
    @Override // X.AnonymousClass1Yh
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.String> A3P() {
        /*
        // Method dump skipped, instructions count: 370
        */
        throw new UnsupportedOperationException("Method not decompiled: X.RunnableC10551tn.A3P():java.util.List");
    }

    @Override // X.AnonymousClass1Yh
    public final boolean A3S() {
        return this.A0B.asBoolean(false);
    }

    @Override // X.AnonymousClass1Yh
    public final AnonymousClass1tq A3r() {
        AnonymousClass1tq r0 = this.A0I;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1tq r02 = new AnonymousClass1tq();
        this.A0I = r02;
        return r02;
    }

    @Override // X.AnonymousClass1Yh
    public final long A3u() {
        return TimeUnit.NANOSECONDS.toMillis(this.A09);
    }

    @Override // X.AnonymousClass1Yh
    public final boolean A4u() {
        if ((this.A00 & 1) <= 0) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1Yh
    public final boolean A52() {
        TriState triState = this.A0B;
        if (triState == null || !triState.isSet()) {
            return false;
        }
        return true;
    }

    public final void run() {
        this.A0C.A5I(this);
    }

    @Override // X.AnonymousClass1Yh
    public final short A2v() {
        return this.A0E;
    }

    @Override // X.AnonymousClass1Yh
    @Nullable
    public final AnonymousClass0T0 A4A() {
        return this.A0D;
    }

    @Override // X.AnonymousClass1Yh
    public final List<String> A4X() {
        return this.A0K;
    }

    @Override // X.AnonymousClass1Yh
    public final long A4Y() {
        return this.A0A;
    }

    @Override // X.AnonymousClass1Yh
    public final int A4a() {
        return this.A06;
    }

    @Override // X.AnonymousClass1Yh
    public final int getMarkerId() {
        return this.A02;
    }
}
