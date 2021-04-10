package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* renamed from: X.1eC  reason: invalid class name and case insensitive filesystem */
public final class C08201eC {
    public static int A00(@NonNull List<AbstractC08251eH> list, @Nullable InputStream inputStream, @NonNull ArrayPool arrayPool) throws IOException {
        if (inputStream != null) {
            if (!inputStream.markSupported()) {
                inputStream = new C06741ax(inputStream, arrayPool);
            }
            inputStream.mark(5242880);
            C08241eG r4 = new C08241eG(inputStream, arrayPool);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int A4Z = r4.A4Z(list.get(i));
                if (A4Z != -1) {
                    return A4Z;
                }
            }
        }
        return -1;
    }

    @NonNull
    public static ImageHeaderParser$ImageType A01(@NonNull List<AbstractC08251eH> list, AbstractC08271eJ r5) throws IOException {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ImageHeaderParser$ImageType A56 = r5.A56(list.get(i));
            if (A56 != ImageHeaderParser$ImageType.UNKNOWN) {
                return A56;
            }
        }
        return ImageHeaderParser$ImageType.UNKNOWN;
    }
}
