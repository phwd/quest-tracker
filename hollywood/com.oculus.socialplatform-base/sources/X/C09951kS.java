package X;

import android.os.SystemClock;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/* renamed from: X.1kS  reason: invalid class name and case insensitive filesystem */
public class C09951kS {
    public final /* synthetic */ AnonymousClass1lA A00;
    public final /* synthetic */ C09931kP A01;

    public C09951kS(C09931kP r1, AnonymousClass1lA r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final void A00() {
        AnonymousClass1lA r4 = this.A00;
        ProducerContext producerContext = r4.A04;
        producerContext.A05.A7Y(producerContext, "NetworkFetchProducer", null);
        r4.A03.A04();
    }

    public final void A02(Throwable th) {
        AnonymousClass1lA r4 = this.A00;
        ProducerContext producerContext = r4.A04;
        AnonymousClass1l6 r2 = producerContext.A05;
        r2.A7a(producerContext, "NetworkFetchProducer", th, null);
        r2.A8F(producerContext, "NetworkFetchProducer", false);
        producerContext.A06("network", "default");
        r4.A03.A08(th);
    }

    /* JADX INFO: finally extract failed */
    public final void A01(InputStream inputStream, int i) throws IOException {
        AnonymousClass0JY A6U;
        HashMap hashMap;
        float f;
        C01060Pq.A00();
        C09931kP r4 = this.A01;
        AnonymousClass1lA r6 = this.A00;
        AnonymousClass0JW r0 = r4.A01;
        if (i > 0) {
            A6U = r0.A6V(i);
        } else {
            A6U = r0.A6U();
        }
        AnonymousClass0VT r12 = r4.A00;
        byte[] bArr = (byte[]) r12.get(16384);
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read < 0) {
                    break;
                } else if (read > 0) {
                    A6U.write(bArr, 0, read);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    C10161kv r8 = r6.A04;
                    if (r8.A08() && uptimeMillis - r6.A01 >= 100) {
                        r6.A01 = uptimeMillis;
                        r8.A05.A7W(r8, "NetworkFetchProducer", "intermediate_result");
                        C09931kP.A00(A6U, r6.A00, r6.A02, r6.A03);
                    }
                    int A002 = A6U.A00();
                    if (i > 0) {
                        f = ((float) A002) / ((float) i);
                    } else {
                        f = 1.0f - ((float) Math.exp(((double) (-A002)) / 50000.0d));
                    }
                    r6.A03.A06(f);
                }
            } catch (Throwable th) {
                r12.A8y(bArr);
                A6U.close();
                throw th;
            }
        }
        AbstractC10031kh r42 = r4.A02;
        boolean z = r42 instanceof C09941kQ;
        if (z) {
            ((AnonymousClass1lJ) r6).A00 = ((C09941kQ) r42).A01.now();
        } else if (r42 instanceof AnonymousClass1kR) {
            ((AnonymousClass1lI) r6).A00 = SystemClock.elapsedRealtime();
        }
        int A003 = A6U.A00();
        ProducerContext producerContext = r6.A04;
        AnonymousClass1l6 r11 = producerContext.A05;
        if (r11.A9I(producerContext, "NetworkFetchProducer")) {
            if (z) {
                AnonymousClass1lJ r82 = (AnonymousClass1lJ) r6;
                hashMap = new HashMap(4);
                hashMap.put("queue_time", Long.toString(r82.A01 - r82.A02));
                hashMap.put("fetch_time", Long.toString(r82.A00 - r82.A01));
                hashMap.put("total_time", Long.toString(r82.A00 - r82.A02));
                hashMap.put("image_size", Integer.toString(A003));
            } else if (r42 instanceof AnonymousClass1kR) {
                AnonymousClass1lI r83 = (AnonymousClass1lI) r6;
                hashMap = new HashMap(4);
                hashMap.put("queue_time", Long.toString(r83.A01 - r83.A02));
                hashMap.put("fetch_time", Long.toString(r83.A00 - r83.A01));
                hashMap.put("total_time", Long.toString(r83.A00 - r83.A02));
                hashMap.put("image_size", Integer.toString(A003));
            }
            r11.A7c(producerContext, "NetworkFetchProducer", hashMap);
            r11.A8F(producerContext, "NetworkFetchProducer", true);
            producerContext.A06("network", "default");
            C09931kP.A00(A6U, r6.A00 | 1, r6.A02, r6.A03);
            r12.A8y(bArr);
            A6U.close();
            C01060Pq.A00();
        }
        hashMap = null;
        r11.A7c(producerContext, "NetworkFetchProducer", hashMap);
        r11.A8F(producerContext, "NetworkFetchProducer", true);
        producerContext.A06("network", "default");
        C09931kP.A00(A6U, r6.A00 | 1, r6.A02, r6.A03);
        r12.A8y(bArr);
        A6U.close();
        C01060Pq.A00();
    }
}
