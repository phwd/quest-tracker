package X;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import java.util.Collections;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.1ji  reason: invalid class name and case insensitive filesystem */
public final class C09761ji {
    public static C09761ji A0I;
    @Nullable
    public AnonymousClass1lR A00;
    @Nullable
    public AnonymousClass1lR A01;
    public CountingMemoryCache<AnonymousClass0H3, AnonymousClass0VM> A02;
    public CountingMemoryCache<AnonymousClass0H3, AnonymousClass0JV> A03;
    @Nullable
    public AnonymousClass1k6 A04;
    @Nullable
    public AnonymousClass1k5 A05;
    @Nullable
    public AnonymousClass1k7 A06;
    @Nullable
    public AnonymousClass0PW A07;
    @Nullable
    public AnonymousClass1i6 A08;
    @Nullable
    public AbstractC01080Pu A09;
    @Nullable
    public AnonymousClass0P2 A0A;
    @Nullable
    public C09901kJ A0B;
    @Nullable
    public C09901kJ A0C;
    @Nullable
    public C09811jo<AnonymousClass0H3, AnonymousClass0VM> A0D;
    @Nullable
    public C09811jo<AnonymousClass0H3, AnonymousClass0JV> A0E;
    public final AnonymousClass1Wf A0F;
    public final C09771jj A0G;
    public final AbstractC10441mf A0H;

    private final AnonymousClass0P2 A00() {
        AnonymousClass0P2 r0 = this.A0A;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1iP r1 = this.A0G.A0G;
        if (this.A08 == null) {
            this.A08 = A06(r1);
        }
        AnonymousClass1js r02 = new AnonymousClass1js(r1.A03(), this.A0F);
        this.A0A = r02;
        return r02;
    }

    private C09901kJ A01() {
        C09901kJ r1 = this.A0C;
        if (r1 != null) {
            return r1;
        }
        AnonymousClass1lR r2 = this.A01;
        if (r2 == null) {
            C09771jj r0 = this.A0G;
            r2 = r0.A0C.A00(r0.A02);
            this.A01 = r2;
        }
        C09771jj r12 = this.A0G;
        AnonymousClass1iP r02 = r12.A0G;
        AnonymousClass0JW A012 = r02.A01();
        AnonymousClass0JZ A022 = r02.A02();
        Executor executor = r12.A0B.A02;
        C09901kJ r13 = new C09901kJ(r2, A012, A022, executor, executor, r12.A0A);
        this.A0C = r13;
        return r13;
    }

    private final C09901kJ A02() {
        C09901kJ r1 = this.A0B;
        if (r1 != null) {
            return r1;
        }
        AnonymousClass1lR r2 = this.A00;
        if (r2 == null) {
            C09771jj r0 = this.A0G;
            r2 = r0.A0C.A00(r0.A01);
            this.A00 = r2;
        }
        C09771jj r12 = this.A0G;
        AnonymousClass1iP r02 = r12.A0G;
        AnonymousClass0JW A012 = r02.A01();
        AnonymousClass0JZ A022 = r02.A02();
        Executor executor = r12.A0B.A02;
        C09901kJ r13 = new C09901kJ(r2, A012, A022, executor, executor, r12.A0A);
        this.A0B = r13;
        return r13;
    }

    private final C09811jo<AnonymousClass0H3, AnonymousClass0VM> A03() {
        C09811jo<AnonymousClass0H3, AnonymousClass0VM> r0 = this.A0D;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass0I1 r2 = this.A02;
        if (r2 == null) {
            C09771jj r02 = this.A0G;
            AnonymousClass0mj r22 = r02.A07;
            AbstractC00750Ik<AnonymousClass0PF> r3 = r02.A03;
            r2 = new AnonymousClass0I1(new C03480mk(r22), r02.A09, r3);
            this.A02 = r2;
        }
        C09811jo<AnonymousClass0H3, AnonymousClass0VM> r03 = new C09811jo<>(r2, new C09851jw(this.A0G.A0A));
        this.A0D = r03;
        return r03;
    }

    private final C09811jo<AnonymousClass0H3, AnonymousClass0JV> A04() {
        C09811jo<AnonymousClass0H3, AnonymousClass0JV> r0 = this.A0E;
        if (r0 != null) {
            return r0;
        }
        C09771jj r4 = this.A0G;
        AnonymousClass0I1 r2 = this.A03;
        if (r2 == null) {
            r2 = new AnonymousClass0I1(new C09821jt(), new C09871jz(), r4.A04);
            this.A03 = r2;
        }
        C09811jo<AnonymousClass0H3, AnonymousClass0JV> r02 = new C09811jo<>(r2, new C09841jv(r4.A0A));
        this.A0E = r02;
        return r02;
    }

    public static AnonymousClass1k6 A05(C09761ji r29) {
        C09771jj r0 = r29.A0G;
        AnonymousClass1jq r2 = r0.A0D;
        AnonymousClass1k7 r9 = r29.A06;
        if (r9 == null) {
            Context context = r0.A00;
            ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
            AnonymousClass1k5 r8 = r29.A05;
            if (r8 == null) {
                AnonymousClass1iP r6 = r0.A0G;
                AnonymousClass0VT r10 = r6.A00;
                if (r10 == null) {
                    AnonymousClass1iQ r3 = r6.A05;
                    r10 = new C09451hz(r3.A00, r3.A04, r3.A07);
                    r6.A00 = r10;
                }
                AnonymousClass0PW r11 = r29.A07;
                if (r11 == null) {
                    A08(r29);
                    AnonymousClass1i6 r32 = r29.A08;
                    if (r32 == null) {
                        r32 = A06(r6);
                        r29.A08 = r32;
                    }
                    r11 = new C09691jW(r32);
                    r29.A07 = r11;
                }
                AnonymousClass1k2 r12 = r0.A0F;
                boolean z = r0.A0L;
                C03440md r14 = r0.A0B;
                AnonymousClass0JW A012 = r6.A01();
                r6.A02();
                r8 = new AnonymousClass1k5(context, r10, r11, r12, z, r14, A012, r29.A03(), r29.A04(), r29.A02(), r29.A01(), r0.A08, r29.A00(), r29.A0F);
                r29.A05 = r8;
            }
            AbstractC10031kh r7 = r0.A0H;
            boolean z2 = r0.A0L;
            AbstractC10441mf r5 = r29.A0H;
            boolean z3 = r0.A0K;
            AbstractC01080Pu r33 = r29.A09;
            if (r33 == null) {
                r33 = new AnonymousClass1HM();
                r29.A09 = r33;
            }
            r9 = new AnonymousClass1k7(contentResolver, r8, r7, z2, r5, z3, r33);
            r29.A06 = r9;
        }
        return new AnonymousClass1k6(r9, Collections.unmodifiableSet(r0.A0J), Collections.unmodifiableSet(r0.A0I), r0.A05, r29.A03(), r29.A04(), r29.A02(), r29.A01(), r0.A08, r29.A0H, r2.A00, r0);
    }

    public static AnonymousClass1i6 A06(AnonymousClass1iP r5) {
        int i = Build.VERSION.SDK_INT;
        int i2 = r5.A05.A02.A00;
        AnonymousClass0VL A032 = r5.A03();
        if (i >= 26) {
            return new AnonymousClass1i7(A032, i2, new AnonymousClass0WB(i2));
        }
        return new AnonymousClass1i8(A032, i2, new AnonymousClass0WB(i2));
    }

    public static synchronized void A07(C09771jj r4) {
        synchronized (C09761ji.class) {
            if (A0I != null) {
                AnonymousClass0J6 r1 = AnonymousClass0J5.A00;
                if (r1.A64(5)) {
                    r1.AB0(C09761ji.class.getSimpleName(), "ImagePipelineFactory has already been initialized! `ImagePipelineFactory.initialize(...)` should only be called once to avoid unexpected behavior.");
                }
            }
            A0I = new C09761ji(r4);
        }
    }

    public C09761ji(C09771jj r3) {
        C01060Pq.A00();
        this.A0G = r3;
        this.A0H = new C09861jx(r3.A0B.A03);
        this.A0F = new AnonymousClass1Wf(r3.A0E);
        C01060Pq.A00();
    }

    @Nullable
    public static void A08(C09761ji r11) {
        AnonymousClass0P2 A002 = r11.A00();
        C09771jj r0 = r11.A0G;
        C03440md r9 = r0.A0B;
        AnonymousClass0I1 r8 = r11.A02;
        if (r8 == null) {
            AnonymousClass0mj r3 = r0.A07;
            AbstractC00750Ik<AnonymousClass0PF> r2 = r0.A03;
            r8 = new AnonymousClass0I1(new C03480mk(r3), r0.A09, r2);
            r11.A02 = r8;
        }
        try {
            Class.forName("com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl").getConstructor(AnonymousClass0P2.class, C03440md.class, AnonymousClass0I1.class, Boolean.TYPE, AnonymousClass0IK.class).newInstance(A002, r9, r8, false, null);
        } catch (Throwable unused) {
        }
    }
}
