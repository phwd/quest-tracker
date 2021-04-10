package X;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* renamed from: X.1dC  reason: invalid class name */
public final class AnonymousClass1dC implements AnonymousClass1dN<Uri, Bitmap> {
    public final AbstractC07941di A00;
    public final AnonymousClass1dB A01;

    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    @Nullable
    public final AnonymousClass1fR<Bitmap> A2V(@NonNull Uri uri, int i, int i2, @NonNull AnonymousClass1cO r6) throws IOException {
        AnonymousClass1fR A002 = this.A01.A00(uri);
        if (A002 == null) {
            return null;
        }
        return C08321eO.A00(this.A00, (Drawable) A002.get(), i, i2);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull Uri uri, @NonNull AnonymousClass1cO r4) throws IOException {
        return "android.resource".equals(uri.getScheme());
    }

    public AnonymousClass1dC(AnonymousClass1dB r1, AbstractC07941di r2) {
        this.A01 = r1;
        this.A00 = r2;
    }
}
