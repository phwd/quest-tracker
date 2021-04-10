package X;

import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1eF  reason: invalid class name and case insensitive filesystem */
public class C08231eF implements AbstractC08271eJ {
    public final /* synthetic */ InputStream A00;

    public C08231eF(InputStream inputStream) {
        this.A00 = inputStream;
    }

    @Override // X.AbstractC08271eJ
    public final ImageHeaderParser$ImageType A56(AbstractC08251eH r3) throws IOException {
        try {
            InputStream inputStream = this.A00;
            ImageHeaderParser$ImageType A57 = r3.A57(inputStream);
            inputStream.reset();
            return A57;
        } catch (Throwable th) {
            this.A00.reset();
            throw th;
        }
    }
}
