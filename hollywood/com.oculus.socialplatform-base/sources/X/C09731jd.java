package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1jd  reason: invalid class name and case insensitive filesystem */
public class C09731jd extends AbstractC09701ja {
    public int A00;
    public final AnonymousClass1jr A01;
    public final AnonymousClass1k2 A02;
    public final /* synthetic */ AnonymousClass1je A03;

    /* JADX WARN: Incorrect types in method signature: (Lcom/facebook/imagepipeline/producers/Consumer<LX/0Ju<LX/0VM;>;>;Lcom/facebook/imagepipeline/producers/ProducerContext;LX/1jr;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;ZI)V */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C09731jd(AnonymousClass1je r2, AbstractC10011kf r3, C10161kv r4, AnonymousClass1jr r5, AnonymousClass1k2 r6, int i) {
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
        C00740Ii.A01(Boolean.valueOf(z));
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

    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00ed, code lost:
        if (r6.A05 == 6) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00f1, code lost:
        if (r6.A01 == r7) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x00f3, code lost:
        r10 = true;
     */
    @Override // X.AbstractC09701ja
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A09(X.AnonymousClass0PZ r15, int r16) {
        /*
        // Method dump skipped, instructions count: 353
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09731jd.A09(X.0PZ, int):boolean");
    }
}
