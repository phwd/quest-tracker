package X;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import androidx.annotation.NonNull;
import java.io.IOException;

/* renamed from: X.1dO  reason: invalid class name */
public final class AnonymousClass1dO<T> implements AnonymousClass1dN<T, Bitmap> {
    public static final C07491cP<Integer> A03 = new C07491cP<>("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.FrameOption", 2, new C07501cS());
    public static final C07491cP<Long> A04 = new C07491cP<>("com.bumptech.glide.load.resource.bitmap.VideoBitmapDecode.TargetFrame", -1L, new AnonymousClass1cT());
    public static final C08001do A05 = new C08001do();
    public final AbstractC07941di A00;
    public final C08001do A01;
    public final AbstractC07921dg<T> A02;

    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull T t, @NonNull AnonymousClass1cO r3) {
        return true;
    }

    public AnonymousClass1dO(AbstractC07941di r2, AbstractC07921dg<T> r3) {
        C08001do r0 = A05;
        this.A00 = r2;
        this.A02 = r3;
        this.A01 = r0;
    }

    @TargetApi(27)
    public static Bitmap A00(MediaMetadataRetriever mediaMetadataRetriever, long j, int i, int i2, int i3, AbstractC09081gb r10) {
        try {
            int parseInt = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
            int parseInt2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
            int parseInt3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
            if (parseInt3 == 90 || parseInt3 == 270) {
                parseInt2 = parseInt;
                parseInt = parseInt2;
            }
            float A002 = r10.A00(parseInt, parseInt2, i2, i3);
            return mediaMetadataRetriever.getScaledFrameAtTime(j, i, Math.round(((float) parseInt) * A002), Math.round(A002 * ((float) parseInt2)));
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r14v0, resolved type: X.1cO */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass1dN
    public final AnonymousClass1fR<Bitmap> A2V(@NonNull T t, int i, int i2, @NonNull AnonymousClass1cO r14) throws IOException {
        Bitmap bitmap;
        long longValue = ((Number) r14.A00(A04)).longValue();
        if (longValue >= 0 || longValue == -1) {
            Number number = (Number) r14.A00(A03);
            if (number == null) {
                number = 2;
            }
            AbstractC09081gb r9 = (AbstractC09081gb) r14.A00(AbstractC09081gb.A00);
            if (r9 == null) {
                r9 = AbstractC09081gb.A05;
            }
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                this.A02.A5g(mediaMetadataRetriever, t);
                int intValue = number.intValue();
                if (Build.VERSION.SDK_INT < 27 || i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE || r9 == AbstractC09081gb.A07 || (bitmap = A00(mediaMetadataRetriever, longValue, intValue, i, i2, r9)) == null) {
                    bitmap = mediaMetadataRetriever.getFrameAtTime(longValue, intValue);
                }
                mediaMetadataRetriever.release();
                AbstractC07941di r1 = this.A00;
                if (bitmap == null) {
                    return null;
                }
                return new C08121e1(bitmap, r1);
            } catch (RuntimeException e) {
                throw new IOException(e);
            } catch (Throwable th) {
                mediaMetadataRetriever.release();
                throw th;
            }
        } else {
            throw new IllegalArgumentException(AnonymousClass006.A06("Requested frame must be non-negative, or DEFAULT_FRAME, given: ", longValue));
        }
    }
}
