package X;

import android.graphics.Bitmap;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Nullsafe(Nullsafe.Mode.STRICT)
@Immutable
/* renamed from: X.0PI  reason: invalid class name */
public final class AnonymousClass0PI {
    public static final AnonymousClass0PI A03 = new AnonymousClass0PI(new AnonymousClass0PJ());
    public final int A00 = Integer.MAX_VALUE;
    public final int A01 = 100;
    public final Bitmap.Config A02;

    public final boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj != null && getClass() == obj.getClass()) {
                AnonymousClass0PI r5 = (AnonymousClass0PI) obj;
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
        C00720Ig A002 = C00730Ih.A00(this);
        C00720Ig.A00(A002, "minDecodeIntervalMs", String.valueOf(this.A01));
        C00720Ig.A00(A002, "maxDimensionPx", String.valueOf(this.A00));
        String valueOf = String.valueOf(false);
        C00720Ig.A00(A002, "decodePreviewFrame", valueOf);
        C00720Ig.A00(A002, "useLastFrameForPreview", valueOf);
        C00720Ig.A00(A002, "decodeAllFrames", valueOf);
        C00720Ig.A00(A002, "forceStaticImage", valueOf);
        C00720Ig.A00(A002, "bitmapConfigName", this.A02.name());
        C00720Ig.A00(A002, "customImageDecoder", null);
        C00720Ig.A00(A002, "bitmapTransformation", null);
        C00720Ig.A00(A002, "colorSpace", null);
        return AnonymousClass006.A09("ImageDecodeOptions{", A002.toString(), "}");
    }

    public AnonymousClass0PI(AnonymousClass0PJ r2) {
        this.A02 = r2.A00;
    }
}
