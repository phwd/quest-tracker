package X;

import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.HashMap;

/* renamed from: X.1jb  reason: invalid class name and case insensitive filesystem */
public class C09711jb implements AnonymousClass1me {
    public final /* synthetic */ C09721jc A00;
    public final /* synthetic */ C09751jg A01;

    public C09711jb(C09721jc r1, C09751jg r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AnonymousClass1me
    public final void A9R(AnonymousClass0PZ r21, int i) {
        long j;
        int i2 = i;
        C09721jc r7 = this.A00;
        AbstractC01080Pu r2 = r7.A03;
        AnonymousClass0PZ.A06(r21);
        AbstractC01070Pt createImageTranscoder = r2.createImageTranscoder(r21.A07, r7.A04);
        if (createImageTranscoder != null) {
            ProducerContext producerContext = r7.A01;
            AnonymousClass1l6 r6 = producerContext.A05;
            r6.A7e(producerContext, "ResizeAndRotateProducer");
            AnonymousClass1kA r1 = producerContext.A07;
            AnonymousClass0JY A6U = r7.A05.A00.A6U();
            C00690Id r4 = null;
            try {
                AnonymousClass0Ps transcode = createImageTranscoder.transcode(r21, A6U, r1.A07, null, null, 85);
                int i3 = transcode.A00;
                if (i3 != 2) {
                    String identifier = createImageTranscoder.getIdentifier();
                    if (r6.A9I(producerContext, "ResizeAndRotateProducer")) {
                        StringBuilder sb = new StringBuilder();
                        AnonymousClass0PZ.A06(r21);
                        sb.append(r21.A05);
                        sb.append("x");
                        AnonymousClass0PZ.A06(r21);
                        sb.append(r21.A01);
                        String obj = sb.toString();
                        HashMap hashMap = new HashMap();
                        AnonymousClass0PZ.A06(r21);
                        hashMap.put("Image format", String.valueOf(r21.A07));
                        hashMap.put("Original size", obj);
                        hashMap.put("Requested size", "Unspecified");
                        C10081km r14 = r7.A02;
                        synchronized (r14) {
                            j = r14.A01 - r14.A02;
                        }
                        hashMap.put("queueTime", String.valueOf(j));
                        hashMap.put("Transcoder id", identifier);
                        hashMap.put("Transcoding result", String.valueOf(transcode));
                        r4 = new C00690Id(hashMap);
                    }
                    AbstractC00820Ju A012 = AbstractC00820Ju.A01(A6U.A01(), AbstractC00820Ju.A04);
                    try {
                        AnonymousClass0PZ r12 = new AnonymousClass0PZ(A012);
                        r12.A07 = AnonymousClass0Oi.A05;
                        try {
                            AnonymousClass0PZ.A05(r12);
                            r6.A7c(producerContext, "ResizeAndRotateProducer", r4);
                            if (i3 != 1) {
                                i2 = i | 16;
                            }
                            ((AbstractC09791jm) r7).A00.A07(r12, i2);
                            AnonymousClass0PZ.A04(r12);
                            A6U.close();
                        } catch (Throwable th) {
                            AnonymousClass0PZ.A04(r12);
                            throw th;
                        }
                    } finally {
                        AbstractC00820Ju.A03(A012);
                    }
                } else {
                    throw new RuntimeException("Error while transcoding the image");
                }
            } catch (Exception e) {
                r6.A7a(producerContext, "ResizeAndRotateProducer", e, null);
                if ((i2 & 1) == 1) {
                    ((AbstractC09791jm) r7).A00.A08(e);
                }
            } catch (Throwable th2) {
                A6U.close();
                throw th2;
            }
        } else {
            throw null;
        }
    }
}
