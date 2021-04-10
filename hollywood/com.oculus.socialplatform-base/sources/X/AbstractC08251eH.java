package X;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: X.1eH  reason: invalid class name and case insensitive filesystem */
public interface AbstractC08251eH {
    int A4a(@NonNull InputStream inputStream, @NonNull AnonymousClass1hX v) throws IOException;

    @NonNull
    ImageHeaderParser$ImageType A57(@NonNull InputStream inputStream) throws IOException;

    @NonNull
    ImageHeaderParser$ImageType A58(@NonNull ByteBuffer byteBuffer) throws IOException;
}
