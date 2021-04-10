package X;

import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.HashMap;

/* renamed from: X.1qO  reason: invalid class name */
public class AnonymousClass1qO implements AnonymousClass1uN {
    public final /* synthetic */ AnonymousClass1qT A00;
    public final /* synthetic */ C09901px A01;

    public AnonymousClass1qO(AnonymousClass1qT r1, C09901px r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AnonymousClass1uN
    public final void A8P(AnonymousClass1qQ r21, int i) {
        long j;
        int i2 = i;
        AnonymousClass1qT r7 = this.A00;
        AnonymousClass1lX r2 = r7.A03;
        AnonymousClass1qQ.A05(r21);
        AnonymousClass1s3 createImageTranscoder = r2.createImageTranscoder(r21.A07, r7.A04);
        if (createImageTranscoder != null) {
            ProducerContext producerContext = r7.A01;
            AnonymousClass1qE r6 = producerContext.A05;
            r6.A6b(producerContext, "ResizeAndRotateProducer");
            C09811pd r3 = producerContext.A07;
            AbstractC10131rK r22 = r7.A05.A00.A01;
            C09991ql r15 = new C09991ql(r22, r22.A00[0]);
            AnonymousClass0KP r4 = null;
            try {
                C10371se transcode = createImageTranscoder.transcode(r21, r15, r3.A07, null, null, 85);
                int i3 = transcode.A00;
                if (i3 != 2) {
                    String identifier = createImageTranscoder.getIdentifier();
                    if (r6.A8K(producerContext, "ResizeAndRotateProducer")) {
                        StringBuilder sb = new StringBuilder();
                        AnonymousClass1qQ.A05(r21);
                        sb.append(r21.A05);
                        sb.append("x");
                        AnonymousClass1qQ.A05(r21);
                        sb.append(r21.A01);
                        String obj = sb.toString();
                        HashMap hashMap = new HashMap();
                        AnonymousClass1qQ.A05(r21);
                        hashMap.put("Image format", String.valueOf(r21.A07));
                        hashMap.put("Original size", obj);
                        hashMap.put("Requested size", "Unspecified");
                        C09971qh r14 = r7.A02;
                        synchronized (r14) {
                            j = r14.A01 - r14.A02;
                        }
                        hashMap.put("queueTime", String.valueOf(j));
                        hashMap.put("Transcoder id", identifier);
                        hashMap.put("Transcoding result", String.valueOf(transcode));
                        r4 = new AnonymousClass0KP(hashMap);
                    }
                    AnonymousClass1qa A012 = AnonymousClass1qa.A01(r15.A00(), AnonymousClass1qa.A04);
                    try {
                        AnonymousClass1qQ r1 = new AnonymousClass1qQ(A012);
                        r1.A07 = C10191ri.A05;
                        try {
                            AnonymousClass1qQ.A04(r1);
                            r6.A6Z(producerContext, "ResizeAndRotateProducer", r4);
                            if (i3 != 1) {
                                i2 = i | 16;
                            }
                            ((AnonymousClass1rX) r7).A00.A06(r1, i2);
                            r15.close();
                        } finally {
                            AnonymousClass1qQ.A03(r1);
                        }
                    } finally {
                        if (A012 != null) {
                            A012.close();
                            throw th;
                        }
                    }
                } else {
                    throw new RuntimeException("Error while transcoding the image");
                }
            } catch (Exception e) {
                r6.A6X(producerContext, "ResizeAndRotateProducer", e, null);
                if ((i2 & 1) == 1) {
                    ((AnonymousClass1rX) r7).A00.A07(e);
                }
            } catch (Throwable th) {
                r15.close();
                throw th;
            }
        } else {
            throw null;
        }
    }
}
