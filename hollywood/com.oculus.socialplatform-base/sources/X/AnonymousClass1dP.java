package X;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.IOException;

/* renamed from: X.1dP  reason: invalid class name */
public final class AnonymousClass1dP implements AnonymousClass1dN<GifDecoder, Bitmap> {
    public final AbstractC07941di A00;

    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final AnonymousClass1fR<Bitmap> A2V(@NonNull GifDecoder gifDecoder, int i, int i2, @NonNull AnonymousClass1cO r7) throws IOException {
        Bitmap A01 = ((AnonymousClass1gD) gifDecoder).A01();
        AbstractC07941di r1 = this.A00;
        if (A01 == null) {
            return null;
        }
        return new C08121e1(A01, r1);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull GifDecoder gifDecoder, @NonNull AnonymousClass1cO r3) throws IOException {
        return true;
    }

    public AnonymousClass1dP(AbstractC07941di r1) {
        this.A00 = r1;
    }
}
