package X;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import java.util.Collections;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.1qt  reason: invalid class name and case insensitive filesystem */
public final class C10051qt {
    public static C10051qt A0I;
    @Nullable
    public AnonymousClass1oR A00;
    @Nullable
    public AnonymousClass1oR A01;
    public CountingMemoryCache<AnonymousClass1kC, AnonymousClass1q1> A02;
    public CountingMemoryCache<AnonymousClass1kC, PooledByteBuffer> A03;
    @Nullable
    public C09801pc A04;
    @Nullable
    public AnonymousClass1pf A05;
    @Nullable
    public C09821pe A06;
    @Nullable
    public AnonymousClass1tN A07;
    @Nullable
    public AbstractC10231rm A08;
    @Nullable
    public AnonymousClass1lX A09;
    @Nullable
    public AbstractC10521tg A0A;
    @Nullable
    public AnonymousClass1oQ A0B;
    @Nullable
    public AnonymousClass1oQ A0C;
    @Nullable
    public C10261rp<AnonymousClass1kC, AnonymousClass1q1> A0D;
    @Nullable
    public C10261rp<AnonymousClass1kC, PooledByteBuffer> A0E;
    public final C09761ow A0F;
    public final C10061qu A0G;
    public final AnonymousClass1uO A0H;

    private final AbstractC10521tg A00() {
        AbstractC10521tg r0 = this.A0A;
        if (r0 != null) {
            return r0;
        }
        C10071qv r1 = this.A0G.A0G;
        if (this.A08 == null) {
            this.A08 = A06(r1);
        }
        C10441sq r02 = new C10441sq(r1.A02(), this.A0F);
        this.A0A = r02;
        return r02;
    }

    private AnonymousClass1oQ A01() {
        AnonymousClass1oQ r1 = this.A0C;
        if (r1 != null) {
            return r1;
        }
        AnonymousClass1oR r2 = this.A01;
        if (r2 == null) {
            C10061qu r0 = this.A0G;
            r2 = r0.A0C.A00(r0.A02);
            this.A01 = r2;
        }
        C10061qu r12 = this.A0G;
        C10071qv r02 = r12.A0G;
        AnonymousClass1pV A032 = r02.A03();
        C09891pv A012 = r02.A01();
        Executor executor = r12.A0B.A02;
        AnonymousClass1oQ r13 = new AnonymousClass1oQ(r2, A032, A012, executor, executor, r12.A0A);
        this.A0C = r13;
        return r13;
    }

    private final AnonymousClass1oQ A02() {
        AnonymousClass1oQ r1 = this.A0B;
        if (r1 != null) {
            return r1;
        }
        AnonymousClass1oR r2 = this.A00;
        if (r2 == null) {
            C10061qu r0 = this.A0G;
            r2 = r0.A0C.A00(r0.A01);
            this.A00 = r2;
        }
        C10061qu r12 = this.A0G;
        C10071qv r02 = r12.A0G;
        AnonymousClass1pV A032 = r02.A03();
        C09891pv A012 = r02.A01();
        Executor executor = r12.A0B.A02;
        AnonymousClass1oQ r13 = new AnonymousClass1oQ(r2, A032, A012, executor, executor, r12.A0A);
        this.A0B = r13;
        return r13;
    }

    private final C10261rp<AnonymousClass1kC, AnonymousClass1q1> A03() {
        C10261rp<AnonymousClass1kC, AnonymousClass1q1> r0 = this.A0D;
        if (r0 != null) {
            return r0;
        }
        C10271rq r2 = this.A02;
        if (r2 == null) {
            C10061qu r02 = this.A0G;
            AnonymousClass1rV r22 = r02.A07;
            AnonymousClass0KW<C10411sn> r3 = r02.A03;
            r2 = new C10271rq(new AnonymousClass1sX(r22), r02.A09, r3);
            this.A02 = r2;
        }
        C10261rp<AnonymousClass1kC, AnonymousClass1q1> r03 = new C10261rp<>(r2, new AnonymousClass1tH(this.A0G.A0A));
        this.A0D = r03;
        return r03;
    }

    private final C10261rp<AnonymousClass1kC, PooledByteBuffer> A04() {
        C10261rp<AnonymousClass1kC, PooledByteBuffer> r0 = this.A0E;
        if (r0 != null) {
            return r0;
        }
        C10061qu r4 = this.A0G;
        C10271rq r2 = this.A03;
        if (r2 == null) {
            r2 = new C10271rq(new AnonymousClass1sg(), new AnonymousClass1tW(), r4.A04);
            this.A03 = r2;
        }
        C10261rp<AnonymousClass1kC, PooledByteBuffer> r02 = new C10261rp<>(r2, new AnonymousClass1tI(r4.A0A));
        this.A0E = r02;
        return r02;
    }

    public static C09801pc A05(C10051qt r27) {
        C09821pe r7 = r27.A06;
        if (r7 == null) {
            C10061qu r1 = r27.A0G;
            Context context = r1.A00;
            ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
            AnonymousClass1pf r6 = r27.A05;
            if (r6 == null) {
                C10071qv r5 = r1.A0G;
                AnonymousClass0Km r8 = r5.A00;
                if (r8 == null) {
                    AnonymousClass1r6 r2 = r5.A05;
                    r8 = new C10141rL(r2.A00, r2.A04, r2.A07);
                    r5.A00 = r8;
                }
                AnonymousClass1tN r9 = r27.A07;
                if (r9 == null) {
                    A07(r27);
                    AbstractC10231rm r22 = r27.A08;
                    if (r22 == null) {
                        r22 = A06(r5);
                        r27.A08 = r22;
                    }
                    r9 = new AnonymousClass1qs(r22);
                    r27.A07 = r9;
                }
                AnonymousClass1tj r10 = r1.A0F;
                boolean z = r1.A0L;
                AnonymousClass1o9 r12 = r1.A0B;
                AnonymousClass1pV A032 = r5.A03();
                r5.A01();
                r6 = new AnonymousClass1pf(context, r8, r9, r10, z, r12, A032, r27.A03(), r27.A04(), r27.A02(), r27.A01(), r1.A08, r27.A00(), r27.A0F);
                r27.A05 = r6;
            }
            AnonymousClass1k9 r52 = r1.A0H;
            boolean z2 = r1.A0L;
            AnonymousClass1uO r3 = r27.A0H;
            boolean z3 = r1.A0K;
            AnonymousClass1lX r13 = r27.A09;
            if (r13 == null) {
                r13 = new AnonymousClass1lV();
                r27.A09 = r13;
            }
            r7 = new C09821pe(contentResolver, r6, r52, z2, r3, z3, r13);
            r27.A06 = r7;
        }
        C10061qu r23 = r27.A0G;
        return new C09801pc(r7, Collections.unmodifiableSet(r23.A0J), Collections.unmodifiableSet(r23.A0I), r23.A05, r27.A03(), r27.A04(), r27.A02(), r27.A01(), r23.A08, r27.A0H, r23.A0D.A00, r23);
    }

    public static AbstractC10231rm A06(C10071qv r5) {
        int i = Build.VERSION.SDK_INT;
        int i2 = r5.A05.A02.A00;
        AnonymousClass0Ox A022 = r5.A02();
        if (i >= 26) {
            return new AnonymousClass1rz(A022, i2, new AnonymousClass0KB(i2));
        }
        return new AnonymousClass1sI(A022, i2, new AnonymousClass0KB(i2));
    }

    public C10051qt(C10061qu r3) {
        AnonymousClass1zo.A00();
        this.A0G = r3;
        this.A0H = new AnonymousClass1hV(r3.A0B.A03);
        this.A0F = new C09761ow(r3.A0E);
        AnonymousClass1zo.A00();
    }

    @Nullable
    public static void A07(C10051qt r11) {
        AbstractC10521tg A002 = r11.A00();
        C10061qu r0 = r11.A0G;
        AnonymousClass1o9 r8 = r0.A0B;
        C10271rq r7 = r11.A02;
        if (r7 == null) {
            AnonymousClass1rV r3 = r0.A07;
            AnonymousClass0KW<C10411sn> r2 = r0.A03;
            r7 = new C10271rq(new AnonymousClass1sX(r3), r0.A09, r2);
            r11.A02 = r7;
        }
        try {
            Class.forName("com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl").getConstructor(AbstractC10521tg.class, AnonymousClass1o9.class, C10271rq.class, Boolean.TYPE).newInstance(A002, r8, r7, false);
        } catch (Throwable unused) {
        }
    }
}
