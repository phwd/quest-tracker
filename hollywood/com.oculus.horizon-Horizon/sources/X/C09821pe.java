package X;

import android.content.ContentResolver;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1pe  reason: invalid class name and case insensitive filesystem */
public final class C09821pe {
    @Nullable
    @VisibleForTesting
    public AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A00;
    @VisibleForTesting
    public Map<AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>>, AnonymousClass1pP<Void>> A01 = new HashMap();
    @Nullable
    public AnonymousClass1pP<AnonymousClass1qQ> A02;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A03;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A04;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A05;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A06;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A07;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A08;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A09;
    @VisibleForTesting
    public Map<AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>>, AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>>> A0A = new HashMap();
    @VisibleForTesting
    public Map<AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>>, AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>>> A0B = new HashMap();
    public final ContentResolver A0C;
    public final AnonymousClass1pf A0D;
    public final AnonymousClass1k9 A0E;
    public final AnonymousClass1lX A0F;
    public final boolean A0G;
    public final AnonymousClass1uO A0H;
    public final boolean A0I;

    /* JADX WARN: Incorrect args count in method signature: ()LX/1pP<LX/1qa<LX/1q1;>;>; */
    public static synchronized AnonymousClass1pP A00(C09821pe r3) {
        AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> r0;
        synchronized (r3) {
            r0 = r3.A00;
            if (r0 == null) {
                AnonymousClass1pf r1 = r3.A0D;
                r0 = r3.A05(new AnonymousClass1p2(r1.A0E.A02, r1.A00));
                r3.A00 = r0;
            }
        }
        return r0;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1pP<LX/1qQ;>;)LX/1pP<LX/1qa<LX/1q1;>;>; */
    public static AnonymousClass1pP A02(C09821pe r5, AnonymousClass1pP r6) {
        AnonymousClass1pf r1 = r5.A0D;
        return A04(r5, r6, new AnonymousClass1p8[]{new C09791oz(r1.A0E.A02, r1.A0H, r1.A00)});
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1pP<LX/1qQ;>;)LX/1pP<LX/1qQ;>; */
    public static AnonymousClass1pP A03(C09821pe r4, AnonymousClass1pP r5) {
        if (r4.A0I) {
            AnonymousClass1zo.A00();
            AnonymousClass1pf r0 = r4.A0D;
            AnonymousClass1oQ r3 = r0.A08;
            AnonymousClass1oQ r2 = r0.A09;
            C10331rw r1 = r0.A0A;
            r5 = new AnonymousClass1pa(r3, r2, r1, new C09861pp(r3, r2, r1, r5));
            AnonymousClass1zo.A00();
        }
        AnonymousClass1pf r12 = r4.A0D;
        AbstractC10301rt<AnonymousClass1kC, PooledByteBuffer> r02 = r12.A0C;
        C10331rw r22 = r12.A0A;
        return new C09871ps(r22, new C09831pg(r02, r22, r5));
    }

    private AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A05(AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> r6) {
        AnonymousClass1pf r0 = this.A0D;
        AbstractC10301rt<AnonymousClass1kC, AnonymousClass1q1> r4 = r0.A0B;
        C10331rw r3 = r0.A0A;
        return new AnonymousClass1pq(r4, r3, new AnonymousClass1rG(new AnonymousClass1pr(r3, new AnonymousClass1ph(r4, r3, r6)), this.A0H));
    }

    public C09821pe(ContentResolver contentResolver, AnonymousClass1pf r3, AnonymousClass1k9 r4, boolean z, AnonymousClass1uO r6, boolean z2, AnonymousClass1lX r8) {
        this.A0C = contentResolver;
        this.A0D = r3;
        this.A0E = r4;
        this.A0G = z;
        this.A0H = r6;
        this.A0I = z2;
        this.A0F = r8;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1pP<LX/1qQ;>;)LX/1pP<LX/1qa<LX/1q1;>;>; */
    public static AnonymousClass1pP A01(C09821pe r11, AnonymousClass1pP r12) {
        AnonymousClass1zo.A00();
        AnonymousClass1pf r1 = r11.A0D;
        AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> A052 = r11.A05(new AnonymousClass1q3(r1.A04, r1.A0E.A01, r1.A0F, r1.A0G, r1.A0I, r12, r1.A03, r1.A0D, AnonymousClass0KX.A00));
        AnonymousClass1zo.A00();
        return A052;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1pP<LX/1qQ;>;[LX/1p8<LX/1qQ;>;)LX/1pP<LX/1qa<LX/1q1;>;>; */
    public static AnonymousClass1pP A04(C09821pe r6, AnonymousClass1pP r7, AnonymousClass1p8[] r8) {
        AnonymousClass1sE r0 = new AnonymousClass1sE(A03(r6, r7));
        AnonymousClass1pf r5 = r6.A0D;
        AnonymousClass1lX r4 = r6.A0F;
        return A01(r6, new C10241rn(r5.A00(new AnonymousClass1pO(r8), true, r4), new AnonymousClass1q2(r5.A0E.A03, r5.A00(r0, true, r4))));
    }
}
