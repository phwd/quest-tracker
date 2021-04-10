package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1qX  reason: invalid class name */
public class AnonymousClass1qX extends AnonymousClass1qN {
    public int A00;
    public final C10421so A01;
    public final AnonymousClass1tj A02;
    public final /* synthetic */ AnonymousClass1q3 A03;

    /* JADX WARN: Incorrect types in method signature: (Lcom/facebook/imagepipeline/producers/Consumer<LX/1qa<LX/1q1;>;>;Lcom/facebook/imagepipeline/producers/ProducerContext;LX/1so;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;ZI)V */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1qX(AnonymousClass1q3 r2, AnonymousClass1qD r3, AnonymousClass1qU r4, C10421so r5, AnonymousClass1tj r6, int i) {
        super(r2, r3, r4, i);
        this.A03 = r2;
        this.A01 = r5;
        if (r6 != null) {
            this.A02 = r6;
            this.A00 = 0;
            return;
        }
        throw null;
    }

    public static void A02(InputStream inputStream, long j) throws IOException {
        boolean z = false;
        if (j >= 0) {
            z = true;
        }
        AnonymousClass0KU.A01(Boolean.valueOf(z));
        while (j > 0) {
            long skip = inputStream.skip(j);
            if (skip <= 0) {
                if (inputStream.read() != -1) {
                    skip = 1;
                } else {
                    return;
                }
            }
            j -= skip;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00ef, code lost:
        if (r6.A05 == 6) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00f3, code lost:
        if (r6.A01 == r5) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x00f5, code lost:
        r10 = true;
     */
    @Override // X.AnonymousClass1qN
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A08(X.AnonymousClass1qQ r15, int r16) {
        /*
        // Method dump skipped, instructions count: 355
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1qX.A08(X.1qQ, int):boolean");
    }
}
