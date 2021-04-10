package X;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import java.io.IOException;

/* renamed from: X.1d9  reason: invalid class name */
public final class AnonymousClass1d9 implements AnonymousClass1dN<Bitmap, Bitmap> {
    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final AnonymousClass1fR<Bitmap> A2V(@NonNull Bitmap bitmap, int i, int i2, @NonNull AnonymousClass1cO r5) throws IOException {
        return new AnonymousClass1dA(bitmap);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull Bitmap bitmap, @NonNull AnonymousClass1cO r3) throws IOException {
        return true;
    }
}
