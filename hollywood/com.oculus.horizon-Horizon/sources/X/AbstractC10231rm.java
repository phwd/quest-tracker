package X;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import javax.annotation.Nullable;

/* renamed from: X.1rm  reason: invalid class name and case insensitive filesystem */
public interface AbstractC10231rm {
    AnonymousClass1qa<Bitmap> decodeFromEncodedImageWithColorSpace(AnonymousClass1qQ v, Bitmap.Config config, @Nullable Rect rect, @Nullable ColorSpace colorSpace);

    AnonymousClass1qa<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(AnonymousClass1qQ v, Bitmap.Config config, @Nullable Rect rect, int i, @Nullable ColorSpace colorSpace);
}
