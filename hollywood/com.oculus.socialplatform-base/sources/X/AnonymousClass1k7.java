package X;

import android.content.ContentResolver;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1k7  reason: invalid class name */
public final class AnonymousClass1k7 {
    @Nullable
    @VisibleForTesting
    public AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A00;
    @VisibleForTesting
    public Map<AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>>, AnonymousClass1j8<Void>> A01 = new HashMap();
    @Nullable
    public AnonymousClass1j8<AnonymousClass0PZ> A02;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A03;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A04;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A05;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A06;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A07;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A08;
    @Nullable
    @VisibleForTesting
    public AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A09;
    @VisibleForTesting
    public Map<AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>>, AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>>> A0A = new HashMap();
    @VisibleForTesting
    public Map<AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>>, AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>>> A0B = new HashMap();
    public final ContentResolver A0C;
    public final AnonymousClass1k5 A0D;
    public final AbstractC10031kh A0E;
    public final AbstractC01080Pu A0F;
    public final boolean A0G;
    public final AbstractC10441mf A0H;
    public final boolean A0I;

    /* JADX WARN: Incorrect args count in method signature: ()LX/1j8<LX/0Ju<LX/0VM;>;>; */
    public static synchronized AnonymousClass1j8 A00(AnonymousClass1k7 r3) {
        AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> r0;
        synchronized (r3) {
            r0 = r3.A00;
            if (r0 == null) {
                AnonymousClass1k5 r1 = r3.A0D;
                r0 = r3.A05(new AnonymousClass1j1(r1.A0F.A02, r1.A00));
                r3.A00 = r0;
            }
        }
        return r0;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1j8<LX/0PZ;>;)LX/1j8<LX/0Ju<LX/0VM;>;>; */
    public static AnonymousClass1j8 A02(AnonymousClass1k7 r4, AnonymousClass1j8 r5) {
        AnonymousClass1k5 r1 = r4.A0D;
        return A04(r4, r5, new AnonymousClass1j5[]{new AnonymousClass1j0(r1.A0F.A02, r1.A05, r1.A00)});
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1j8<LX/0PZ;>;)LX/1j8<LX/0PZ;>; */
    public static AnonymousClass1j8 A03(AnonymousClass1k7 r4, AnonymousClass1j8 r5) {
        if (r4.A0I) {
            C01060Pq.A00();
            AnonymousClass1k5 r0 = r4.A0D;
            C09901kJ r3 = r0.A09;
            C09901kJ r2 = r0.A0A;
            AnonymousClass1l8 r1 = r0.A0B;
            r5 = new AnonymousClass1kH(r3, r2, r1, new C09991kd(r3, r2, r1, r5));
            C01060Pq.A00();
        }
        AnonymousClass1k5 r12 = r4.A0D;
        AbstractC03450mg<AnonymousClass0H3, AnonymousClass0JV> r02 = r12.A0D;
        AnonymousClass1l8 r22 = r12.A0B;
        return new C10071kl(r22, new AnonymousClass1kE(r02, r22, r5));
    }

    private AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A05(AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> r6) {
        AnonymousClass1k5 r0 = this.A0D;
        AbstractC03450mg<AnonymousClass0H3, AnonymousClass0VM> r4 = r0.A0C;
        AnonymousClass1l8 r3 = r0.A0B;
        return new C10021kg(r4, r3, new AnonymousClass1l2(new C10041ki(r3, new C09881kD(r4, r3, r6)), this.A0H));
    }

    public AnonymousClass1k7(ContentResolver contentResolver, AnonymousClass1k5 r3, AbstractC10031kh r4, boolean z, AbstractC10441mf r6, boolean z2, AbstractC01080Pu r8) {
        this.A0C = contentResolver;
        this.A0D = r3;
        this.A0E = r4;
        this.A0G = z;
        this.A0H = r6;
        this.A0I = z2;
        this.A0F = r8;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1j8<LX/0PZ;>;)LX/1j8<LX/0Ju<LX/0VM;>;>; */
    public static AnonymousClass1j8 A01(AnonymousClass1k7 r11, AnonymousClass1j8 r12) {
        C01060Pq.A00();
        AnonymousClass1k5 r1 = r11.A0D;
        AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> A052 = r11.A05(new AnonymousClass1je(r1.A04, r1.A0F.A01, r1.A0G, r1.A0H, r1.A0I, r12, r1.A03, r1.A0E, C00760Il.A00));
        C01060Pq.A00();
        return A052;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1j8<LX/0PZ;>;[LX/1j5<LX/0PZ;>;)LX/1j8<LX/0Ju<LX/0VM;>;>; */
    public static AnonymousClass1j8 A04(AnonymousClass1k7 r6, AnonymousClass1j8 r7, AnonymousClass1j5[] r8) {
        C10391lx r0 = new C10391lx(A03(r6, r7));
        AnonymousClass1k5 r5 = r6.A0D;
        AbstractC01080Pu r4 = r6.A0F;
        return A01(r6, new AnonymousClass1jJ(r5.A00(new AnonymousClass1j7(r8), true, r4), new C10051kj(r5.A0F.A03, r5.A00(r0, true, r4))));
    }
}
