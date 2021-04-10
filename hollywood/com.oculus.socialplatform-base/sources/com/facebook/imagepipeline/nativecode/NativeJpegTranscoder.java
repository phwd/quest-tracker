package com.facebook.imagepipeline.nativecode;

import X.AbstractC01070Pt;
import X.AnonymousClass0Oi;
import X.AnonymousClass0Oj;
import X.AnonymousClass0PL;
import X.AnonymousClass0PO;
import X.AnonymousClass0PZ;
import X.AnonymousClass1ih;
import X.C01090Pv;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class NativeJpegTranscoder implements AbstractC01070Pt {
    public int mMaxBitmapSize;
    public boolean mResizingEnabled;
    public boolean mUseDownsamplingRatio;

    @DoNotStrip
    public static native void nativeTranscodeJpeg(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    @DoNotStrip
    public static native void nativeTranscodeJpegWithExifOrientation(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    @Override // X.AbstractC01070Pt
    public String getIdentifier() {
        return "NativeJpegTranscoder";
    }

    @Override // X.AbstractC01070Pt
    public boolean canResize(AnonymousClass0PZ r2, @Nullable AnonymousClass0PO r3, @Nullable AnonymousClass0PL r4) {
        if (r3 == null) {
            r3 = AnonymousClass0PO.A02;
        }
        C01090Pv.A00(r3, r4, r2, this.mResizingEnabled);
        return false;
    }

    @Override // X.AbstractC01070Pt
    public boolean canTranscode(AnonymousClass0Oj r3) {
        if (r3 == AnonymousClass0Oi.A05) {
            return true;
        }
        return false;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00eb, code lost:
        if ((r6 % 90) != 0) goto L_0x00ed;
     */
    @Override // X.AbstractC01070Pt
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public X.AnonymousClass0Ps transcode(X.AnonymousClass0PZ r10, java.io.OutputStream r11, @javax.annotation.Nullable X.AnonymousClass0PO r12, @javax.annotation.Nullable X.AnonymousClass0PL r13, @javax.annotation.Nullable X.AnonymousClass0Oj r14, @javax.annotation.Nullable java.lang.Integer r15) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 306
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.nativecode.NativeJpegTranscoder.transcode(X.0PZ, java.io.OutputStream, X.0PO, X.0PL, X.0Oj, java.lang.Integer):X.0Ps");
    }

    public NativeJpegTranscoder(boolean z, int i, boolean z2, boolean z3) {
        this.mResizingEnabled = z;
        this.mMaxBitmapSize = i;
        this.mUseDownsamplingRatio = z2;
        if (z3) {
            AnonymousClass1ih.A00();
        }
    }
}
