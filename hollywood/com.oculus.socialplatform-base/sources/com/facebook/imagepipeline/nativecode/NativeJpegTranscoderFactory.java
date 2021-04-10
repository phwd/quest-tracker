package com.facebook.imagepipeline.nativecode;

import X.AbstractC01070Pt;
import X.AbstractC01080Pu;
import X.AnonymousClass0Oi;
import X.AnonymousClass0Oj;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class NativeJpegTranscoderFactory implements AbstractC01080Pu {
    public final int A00;
    public final boolean A01;
    public final boolean A02;

    @Override // X.AbstractC01080Pu
    @DoNotStrip
    @Nullable
    public AbstractC01070Pt createImageTranscoder(AnonymousClass0Oj r5, boolean z) {
        if (r5 != AnonymousClass0Oi.A05) {
            return null;
        }
        return new NativeJpegTranscoder(z, this.A00, this.A02, this.A01);
    }

    @DoNotStrip
    public NativeJpegTranscoderFactory(int i, boolean z, boolean z2) {
        this.A00 = i;
        this.A02 = z;
        this.A01 = z2;
    }
}
