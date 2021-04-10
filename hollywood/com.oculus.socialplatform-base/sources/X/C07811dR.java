package X;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* renamed from: X.1dR  reason: invalid class name and case insensitive filesystem */
public final class C07811dR implements AnonymousClass1dN<Drawable, Drawable> {
    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    @Nullable
    public final AnonymousClass1fR<Drawable> A2V(@NonNull Drawable drawable, int i, int i2, @NonNull AnonymousClass1cO r5) throws IOException {
        Drawable drawable2 = drawable;
        if (drawable2 != null) {
            return new AnonymousClass1eT(drawable2);
        }
        return null;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull Drawable drawable, @NonNull AnonymousClass1cO r3) throws IOException {
        return true;
    }
}
