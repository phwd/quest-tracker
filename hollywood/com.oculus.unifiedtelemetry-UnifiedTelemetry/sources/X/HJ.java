package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Iterator;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class HJ {
    public final Iterator<Z9> A00;
    public final HK A01;
    public final AbstractC0090Hb A02;
    public final Integer A03;

    public final void A00() {
        Iterator<Z9> it = this.A00;
        if (it.hasNext()) {
            Z9 next = it.next();
            this.A02.A5a(new C0089Ha(next), new YW(next, this.A01));
            return;
        }
        throw new IllegalStateException("No more batches to upload");
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/Hb;Lcom/facebook/analytics2/uploader/UploadJob$Priority;Ljava/util/Iterator<LX/Z9;>;LX/HK;Lcom/facebook/flexiblesampling/SamplingPolicyConfig;)V */
    public HJ(AbstractC0090Hb hb, Integer num, Iterator it, HK hk) {
        this.A02 = hb;
        this.A03 = num;
        this.A00 = it;
        this.A01 = hk;
        if (it == null) {
            throw new IllegalArgumentException("mBatchPayloadIterator is null");
        }
    }
}
