package X;

import com.facebook.analytics2.logger.PrivacyControlledUploader;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Iterator;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Hk  reason: invalid class name and case insensitive filesystem */
public final class C00920Hk {
    public final Iterator<AbstractC06950qN> A00;
    public final PrivacyControlledUploader A01;
    public final AnonymousClass0q6 A02;

    /* JADX WARN: Incorrect args count in method signature: (Lcom/facebook/analytics2/uploader/Uploader;Lcom/facebook/analytics2/uploader/UploadJob$Priority;Ljava/util/Iterator<LX/0qN;>;Lcom/facebook/analytics2/logger/UploadProcessorCallback;Lcom/facebook/flexiblesampling/SamplingPolicyConfig;)V */
    public C00920Hk(PrivacyControlledUploader privacyControlledUploader, Iterator it, AnonymousClass0q6 r3) {
        this.A01 = privacyControlledUploader;
        this.A00 = it;
        this.A02 = r3;
    }
}
