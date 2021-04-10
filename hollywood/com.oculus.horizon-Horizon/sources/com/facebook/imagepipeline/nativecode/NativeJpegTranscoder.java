package com.facebook.imagepipeline.nativecode;

import X.AnonymousClass0KO;
import X.AnonymousClass1nS;
import X.AnonymousClass1p9;
import X.AnonymousClass1pN;
import X.AnonymousClass1qQ;
import X.AnonymousClass1qg;
import X.AnonymousClass1s3;
import X.AnonymousClass1tL;
import X.C10191ri;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class NativeJpegTranscoder implements AnonymousClass1s3 {
    public int mMaxBitmapSize;
    public boolean mResizingEnabled;
    public boolean mUseDownsamplingRatio;

    @DoNotStrip
    public static native void nativeTranscodeJpeg(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    @DoNotStrip
    public static native void nativeTranscodeJpegWithExifOrientation(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    @Override // X.AnonymousClass1s3
    public String getIdentifier() {
        return "NativeJpegTranscoder";
    }

    @Override // X.AnonymousClass1s3
    public boolean canResize(AnonymousClass1qQ r3, @Nullable AnonymousClass1pN r4, @Nullable AnonymousClass1p9 r5) {
        if (r4 == null) {
            r4 = AnonymousClass1pN.A02;
        }
        if (!this.mResizingEnabled || r5 == null) {
            return false;
        }
        AnonymousClass1qg.A01(r4, r3);
        AnonymousClass0KO<Integer> r1 = AnonymousClass1qg.A00;
        AnonymousClass1qQ.A05(r3);
        if (r1.contains(Integer.valueOf(r3.A00))) {
            AnonymousClass1qg.A00(r4, r3);
        }
        AnonymousClass1qQ.A05(r3);
        AnonymousClass1qQ.A05(r3);
        throw null;
    }

    @Override // X.AnonymousClass1s3
    public boolean canTranscode(AnonymousClass1tL r3) {
        if (r3 == C10191ri.A05) {
            return true;
        }
        return false;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0106, code lost:
        if ((r6 % 90) != 0) goto L_0x0108;
     */
    @Override // X.AnonymousClass1s3
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public X.C10371se transcode(X.AnonymousClass1qQ r10, java.io.OutputStream r11, @javax.annotation.Nullable X.AnonymousClass1pN r12, @javax.annotation.Nullable X.AnonymousClass1p9 r13, @javax.annotation.Nullable X.AnonymousClass1tL r14, @javax.annotation.Nullable java.lang.Integer r15) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 332
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.nativecode.NativeJpegTranscoder.transcode(X.1qQ, java.io.OutputStream, X.1pN, X.1p9, X.1tL, java.lang.Integer):X.1se");
    }

    public NativeJpegTranscoder(boolean z, int i, boolean z2, boolean z3) {
        this.mResizingEnabled = z;
        this.mMaxBitmapSize = i;
        this.mUseDownsamplingRatio = z2;
        if (z3) {
            AnonymousClass1nS.A00();
        }
    }
}
