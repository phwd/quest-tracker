package X;

import android.os.SystemClock;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.squareup.okhttp.internal.framed.Http2;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import retrofit.Endpoints;

/* renamed from: X.1pX  reason: invalid class name */
public class AnonymousClass1pX {
    public final /* synthetic */ AnonymousClass1k1 A00;
    public final /* synthetic */ AnonymousClass1pY A01;

    public AnonymousClass1pX(AnonymousClass1pY r1, AnonymousClass1k1 r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void A00() {
        AnonymousClass1k1 r4 = this.A00;
        ProducerContext producerContext = r4.A04;
        producerContext.A05.A6V(producerContext, "NetworkFetchProducer", null);
        r4.A03.A03();
    }

    public final void A02(Throwable th) {
        AnonymousClass1k1 r4 = this.A00;
        ProducerContext producerContext = r4.A04;
        AnonymousClass1qE r2 = producerContext.A05;
        r2.A6X(producerContext, "NetworkFetchProducer", th, null);
        r2.A77(producerContext, "NetworkFetchProducer", false);
        producerContext.A06("network", Endpoints.DEFAULT_NAME);
        r4.A03.A07(th);
    }

    /* JADX INFO: finally extract failed */
    public final void A01(InputStream inputStream, int i) throws IOException {
        C09991ql r5;
        HashMap hashMap;
        float f;
        AnonymousClass1zo.A00();
        AnonymousClass1pY r4 = this.A01;
        AnonymousClass1k1 r6 = this.A00;
        AbstractC10131rK r2 = r4.A01.A01;
        if (i > 0) {
            r5 = new C09991ql(r2, i);
        } else {
            r5 = new C09991ql(r2, r2.A00[0]);
        }
        AnonymousClass0Km r12 = r4.A00;
        byte[] bArr = (byte[]) r12.get(Http2.INITIAL_MAX_FRAME_SIZE);
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read < 0) {
                    break;
                } else if (read > 0) {
                    r5.write(bArr, 0, read);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    AnonymousClass1qU r9 = r6.A04;
                    if (r9.A08() && uptimeMillis - r6.A01 >= 100) {
                        r6.A01 = uptimeMillis;
                        r9.A05.A6T(r9, "NetworkFetchProducer", "intermediate_result");
                        AnonymousClass1pY.A00(r5, r6.A00, r6.A02, r6.A03);
                    }
                    int i2 = r5.A00;
                    if (i > 0) {
                        f = ((float) i2) / ((float) i);
                    } else {
                        f = 1.0f - ((float) Math.exp(((double) (-i2)) / 50000.0d));
                    }
                    r6.A03.A05(f);
                }
            } catch (Throwable th) {
                r12.A86(bArr);
                r5.close();
                throw th;
            }
        }
        AnonymousClass1k9 r3 = r4.A02;
        boolean z = r3 instanceof AnonymousClass1jw;
        if (z) {
            ((AnonymousClass1k0) r6).A00 = ((AnonymousClass1jw) r3).A01.now();
        } else if (r3 instanceof C09481jv) {
            ((AnonymousClass1k2) r6).A00 = SystemClock.elapsedRealtime();
        }
        int i3 = r5.A00;
        ProducerContext producerContext = r6.A04;
        AnonymousClass1qE r11 = producerContext.A05;
        if (r11.A8K(producerContext, "NetworkFetchProducer")) {
            if (z) {
                AnonymousClass1k0 r8 = (AnonymousClass1k0) r6;
                hashMap = new HashMap(4);
                hashMap.put("queue_time", Long.toString(r8.A01 - r8.A02));
                hashMap.put("fetch_time", Long.toString(r8.A00 - r8.A01));
                hashMap.put("total_time", Long.toString(r8.A00 - r8.A02));
                hashMap.put("image_size", Integer.toString(i3));
            } else if (r3 instanceof C09481jv) {
                AnonymousClass1k2 r82 = (AnonymousClass1k2) r6;
                hashMap = new HashMap(4);
                hashMap.put("queue_time", Long.toString(r82.A01 - r82.A02));
                hashMap.put("fetch_time", Long.toString(r82.A00 - r82.A01));
                hashMap.put("total_time", Long.toString(r82.A00 - r82.A02));
                hashMap.put("image_size", Integer.toString(i3));
            }
            r11.A6Z(producerContext, "NetworkFetchProducer", hashMap);
            r11.A77(producerContext, "NetworkFetchProducer", true);
            producerContext.A06("network", Endpoints.DEFAULT_NAME);
            AnonymousClass1pY.A00(r5, r6.A00 | 1, r6.A02, r6.A03);
            r12.A86(bArr);
            r5.close();
            AnonymousClass1zo.A00();
        }
        hashMap = null;
        r11.A6Z(producerContext, "NetworkFetchProducer", hashMap);
        r11.A77(producerContext, "NetworkFetchProducer", true);
        producerContext.A06("network", Endpoints.DEFAULT_NAME);
        AnonymousClass1pY.A00(r5, r6.A00 | 1, r6.A02, r6.A03);
        r12.A86(bArr);
        r5.close();
        AnonymousClass1zo.A00();
    }
}
