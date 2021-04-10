package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import javax.annotation.Nullable;

@TargetApi(18)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1x5  reason: invalid class name */
public final class AnonymousClass1x5 {
    public static MediaCodec A00(String str, MediaFormat mediaFormat, @Nullable MediaCodec.Callback callback) throws Exception {
        MediaCodec mediaCodec = null;
        int i = 0;
        Exception e = null;
        while (true) {
            if (i >= 3) {
                break;
            }
            try {
                MediaCodec createEncoderByType = MediaCodec.createEncoderByType(str);
                if (callback != null) {
                    createEncoderByType.setCallback(callback);
                }
                createEncoderByType.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
                mediaCodec = createEncoderByType;
            } catch (Exception e2) {
                e = e2;
                i++;
            }
        }
        if (mediaCodec != null) {
            return mediaCodec;
        }
        if (e == null) {
            throw new IOException("Failed to create media codec encode");
        }
        throw e;
    }
}
