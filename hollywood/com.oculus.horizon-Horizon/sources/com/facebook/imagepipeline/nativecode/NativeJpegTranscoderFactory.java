package com.facebook.imagepipeline.nativecode;

import X.AnonymousClass1lX;
import X.AnonymousClass1s3;
import X.AnonymousClass1tL;
import X.C10191ri;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class NativeJpegTranscoderFactory implements AnonymousClass1lX {
    public final int A00;
    public final boolean A01;
    public final boolean A02;

    @Override // X.AnonymousClass1lX
    @DoNotStrip
    @Nullable
    public AnonymousClass1s3 createImageTranscoder(AnonymousClass1tL r5, boolean z) {
        if (r5 != C10191ri.A05) {
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
