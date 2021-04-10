package X;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RequiresApi(27)
/* renamed from: X.1gf  reason: invalid class name and case insensitive filesystem */
public final class C09121gf implements AbstractC08251eH {
    @Override // X.AbstractC08251eH
    public final int A4a(@NonNull InputStream inputStream, @NonNull AnonymousClass1hX r5) throws IOException {
        AnonymousClass1hO r2 = new AnonymousClass1hO(inputStream);
        AnonymousClass1hP A00 = AnonymousClass1hO.A00(r2, "Orientation");
        if (A00 == null) {
            return 1;
        }
        try {
            int A03 = A00.A03(r2.A06);
            if (A03 == 0) {
                return -1;
            }
            return A03;
        } catch (NumberFormatException unused) {
            return 1;
        }
    }

    @Override // X.AbstractC08251eH
    @NonNull
    public final ImageHeaderParser$ImageType A57(@NonNull InputStream inputStream) {
        return ImageHeaderParser$ImageType.UNKNOWN;
    }

    @Override // X.AbstractC08251eH
    @NonNull
    public final ImageHeaderParser$ImageType A58(@NonNull ByteBuffer byteBuffer) {
        return ImageHeaderParser$ImageType.UNKNOWN;
    }
}
