package X;

import android.net.Uri;
import android.os.SystemClock;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Request;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1kP  reason: invalid class name and case insensitive filesystem */
public final class C09931kP implements AnonymousClass1j8<AnonymousClass0PZ> {
    public final AnonymousClass0VT A00;
    public final AnonymousClass0JW A01;
    public final AbstractC10031kh A02;

    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext) {
        AnonymousClass1lA r5;
        String num;
        String num2;
        producerContext.A05.A7e(producerContext, "NetworkFetchProducer");
        AbstractC10031kh r3 = this.A02;
        boolean z = r3 instanceof C09941kQ;
        if (!z) {
            r5 = new AnonymousClass1lI(consumer, producerContext);
        } else {
            r5 = new AnonymousClass1lJ(consumer, producerContext);
        }
        C09951kS r4 = new C09951kS(this, r5);
        if (!z) {
            AnonymousClass1kR r32 = (AnonymousClass1kR) r3;
            AnonymousClass1lI r52 = (AnonymousClass1lI) r5;
            r52.A02 = SystemClock.elapsedRealtime();
            C10161kv r6 = r52.A04;
            AnonymousClass1kA r1 = r6.A07;
            Uri uri = r1.A03;
            try {
                Request.Builder builder = new Request.Builder();
                builder.url(uri.toString());
                builder.get();
                CacheControl cacheControl = r32.A01;
                if (cacheControl != null) {
                    builder.cacheControl(cacheControl);
                }
                AnonymousClass0PH r10 = r1.A04;
                if (r10 != null) {
                    int i = r10.A00;
                    if (i == Integer.MAX_VALUE) {
                        num = "";
                    } else {
                        num = Integer.toString(i);
                    }
                    int i2 = r10.A01;
                    if (i2 == Integer.MAX_VALUE) {
                        num2 = "";
                    } else {
                        num2 = Integer.toString(i2);
                    }
                    builder.addHeader("Range", String.format(null, "bytes=%s-%s", num, num2));
                }
                Call newCall = r32.A02.newCall(builder.build());
                r6.A04(new AnonymousClass1l5(r32, newCall));
                newCall.enqueue(new C09961kT(r32, r52, r4));
            } catch (Exception e) {
                r4.A02(e);
            }
        } else {
            C09941kQ r33 = (C09941kQ) r3;
            AnonymousClass1lJ r53 = (AnonymousClass1lJ) r5;
            r53.A02 = r33.A01.now();
            r53.A04.A04(new AnonymousClass1lN(r33, r33.A02.submit(new AnonymousClass1kZ(r33, r53, r4)), r4));
        }
    }

    public C09931kP(AnonymousClass0JW r1, AnonymousClass0VT r2, AbstractC10031kh r3) {
        this.A01 = r1;
        this.A00 = r2;
        this.A02 = r3;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0JY;ILX/0PH;Lcom/facebook/imagepipeline/producers/Consumer<LX/0PZ;>;Lcom/facebook/imagepipeline/producers/ProducerContext;)V */
    public static void A00(AnonymousClass0JY r3, int i, @Nullable AnonymousClass0PH r5, AbstractC10011kf r6) {
        AnonymousClass0PZ r1;
        Throwable th;
        AbstractC00820Ju A012 = AbstractC00820Ju.A01(r3.A01(), AbstractC00820Ju.A04);
        try {
            r1 = new AnonymousClass0PZ(A012);
            try {
                r1.A08 = r5;
                AnonymousClass0PZ.A05(r1);
                r6.A07(r1, i);
                AnonymousClass0PZ.A04(r1);
                AbstractC00820Ju.A03(A012);
            } catch (Throwable th2) {
                th = th2;
                AnonymousClass0PZ.A04(r1);
                AbstractC00820Ju.A03(A012);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            r1 = null;
            AnonymousClass0PZ.A04(r1);
            AbstractC00820Ju.A03(A012);
            throw th;
        }
    }
}
