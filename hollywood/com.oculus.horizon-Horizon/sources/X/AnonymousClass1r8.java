package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.STRICT)
@Immutable
/* renamed from: X.1r8  reason: invalid class name */
public final class AnonymousClass1r8 {
    public static final AnonymousClass1r8 A03 = new AnonymousClass1r8(new AnonymousClass1sF());
    public final int A00 = Integer.MAX_VALUE;
    public final int A01 = 100;
    public final Bitmap.Config A02;

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj != null && getClass() == obj.getClass()) {
                AnonymousClass1r8 r5 = (AnonymousClass1r8) obj;
                if (!(this.A01 == r5.A01 && this.A00 == r5.A00 && this.A02 == r5.A02)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((((((((((((((((this.A01 * 31) + this.A00) * 31) + 0) * 31) + 0) * 31) + 0) * 31) + 0) * 31) + this.A02.ordinal()) * 31) + 0) * 31) + 0) * 31) + 0;
    }

    public final String toString() {
        AnonymousClass0KS A002 = AnonymousClass0KT.A00(this);
        AnonymousClass0KS.A00(A002, "minDecodeIntervalMs", String.valueOf(this.A01));
        AnonymousClass0KS.A00(A002, "maxDimensionPx", String.valueOf(this.A00));
        String valueOf = String.valueOf(false);
        AnonymousClass0KS.A00(A002, "decodePreviewFrame", valueOf);
        AnonymousClass0KS.A00(A002, "useLastFrameForPreview", valueOf);
        AnonymousClass0KS.A00(A002, "decodeAllFrames", valueOf);
        AnonymousClass0KS.A00(A002, "forceStaticImage", valueOf);
        AnonymousClass0KS.A00(A002, "bitmapConfigName", this.A02.name());
        AnonymousClass0KS.A00(A002, "customImageDecoder", null);
        AnonymousClass0KS.A00(A002, "bitmapTransformation", null);
        AnonymousClass0KS.A00(A002, "colorSpace", null);
        return AnonymousClass006.A07("ImageDecodeOptions{", A002.toString(), "}");
    }

    public AnonymousClass1r8(AnonymousClass1sF r2) {
        this.A02 = r2.A00;
    }
}
