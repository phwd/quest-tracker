package X;

import android.net.Uri;
import android.os.SystemClock;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.tigon.iface.TigonRequest;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1pY  reason: invalid class name */
public final class AnonymousClass1pY implements AnonymousClass1pP<AnonymousClass1qQ> {
    public final AnonymousClass0Km A00;
    public final AnonymousClass1pV A01;
    public final AnonymousClass1k9 A02;

    /* JADX WARN: Incorrect args count in method signature: (LX/1rW;ILX/1jz;Lcom/facebook/imagepipeline/producers/Consumer<LX/1qQ;>;Lcom/facebook/imagepipeline/producers/ProducerContext;)V */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(X.AnonymousClass1rW r3, int r4, @javax.annotation.Nullable X.AnonymousClass1jz r5, X.AnonymousClass1qD r6) {
        /*
            X.1ql r3 = (X.C09991ql) r3
            X.1qp r1 = r3.A00()
            X.1ou<java.io.Closeable> r0 = X.AnonymousClass1qa.A04
            X.1qa r3 = X.AnonymousClass1qa.A01(r1, r0)
            r2 = 0
            X.1qQ r1 = new X.1qQ     // Catch:{ all -> 0x0023 }
            r1.<init>(r3)     // Catch:{ all -> 0x0023 }
            r1.A08 = r5     // Catch:{ all -> 0x0026 }
            X.AnonymousClass1qQ.A04(r1)     // Catch:{ all -> 0x0026 }
            r6.A06(r1, r4)     // Catch:{ all -> 0x0026 }
            X.AnonymousClass1qQ.A03(r1)
            if (r3 == 0) goto L_0x0022
            r3.close()
        L_0x0022:
            return
        L_0x0023:
            r0 = move-exception
            r1 = r2
            goto L_0x0027
        L_0x0026:
            r0 = move-exception
        L_0x0027:
            X.AnonymousClass1qQ.A03(r1)
            if (r3 == 0) goto L_0x002f
            r3.close()
        L_0x002f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1pY.A00(X.1rW, int, X.1jz, X.1qD):void");
    }

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        AnonymousClass1k1 r5;
        String num;
        String num2;
        producerContext.A05.A6b(producerContext, "NetworkFetchProducer");
        AnonymousClass1k9 r3 = this.A02;
        boolean z = r3 instanceof AnonymousClass1jw;
        if (!z) {
            r5 = new AnonymousClass1k2(consumer, producerContext);
        } else {
            r5 = new AnonymousClass1k0(consumer, producerContext);
        }
        AnonymousClass1pX r4 = new AnonymousClass1pX(this, r5);
        if (!z) {
            C09481jv r32 = (C09481jv) r3;
            AnonymousClass1k2 r52 = (AnonymousClass1k2) r5;
            r52.A02 = SystemClock.elapsedRealtime();
            AnonymousClass1qU r2 = r52.A04;
            C09811pd r7 = r2.A07;
            Uri uri = r7.A03;
            try {
                C08340wO r8 = new C08340wO();
                r8.A01(uri.toString());
                r8.A03(TigonRequest.GET, null);
                C08580wo r0 = r32.A01;
                if (r0 != null) {
                    String obj = r0.toString();
                    if (obj.isEmpty()) {
                        r8.A03.A01("Cache-Control");
                    } else {
                        r8.A02("Cache-Control", obj);
                    }
                }
                AnonymousClass1jz r11 = r7.A04;
                if (r11 != null) {
                    Object[] objArr = new Object[2];
                    int i = r11.A00;
                    if (i == Integer.MAX_VALUE) {
                        num = "";
                    } else {
                        num = Integer.toString(i);
                    }
                    objArr[0] = num;
                    int i2 = r11.A01;
                    if (i2 == Integer.MAX_VALUE) {
                        num2 = "";
                    } else {
                        num2 = Integer.toString(i2);
                    }
                    objArr[1] = num2;
                    String format = String.format(null, "bytes=%s-%s", objArr);
                    C08420wY r02 = r8.A03;
                    C08420wY.A00("Range", format);
                    r02.A02("Range", format);
                }
                AnonymousClass0Mz A002 = r32.A02.A00(r8.A00());
                r2.A04(new AnonymousClass1jy(r32, A002));
                A002.A03(new AnonymousClass1ju(r32, r52, r4));
            } catch (Exception e) {
                r4.A02(e);
            }
        } else {
            AnonymousClass1jw r33 = (AnonymousClass1jw) r3;
            AnonymousClass1k0 r53 = (AnonymousClass1k0) r5;
            r53.A02 = r33.A01.now();
            r53.A04.A04(new AnonymousClass1k3(r33, r33.A02.submit(new AnonymousClass1jx(r33, r53, r4)), r4));
        }
    }

    public AnonymousClass1pY(AnonymousClass1pV r1, AnonymousClass0Km r2, AnonymousClass1k9 r3) {
        this.A01 = r1;
        this.A00 = r2;
        this.A02 = r3;
    }
}
