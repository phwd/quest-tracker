package X;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* renamed from: X.1dB  reason: invalid class name */
public final class AnonymousClass1dB implements AnonymousClass1dN<Uri, Drawable> {
    public final Context A00;

    /* Return type fixed from 'X.1fR' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AnonymousClass1dN
    @Nullable
    public final /* bridge */ /* synthetic */ AnonymousClass1fR<Drawable> A2V(@NonNull Uri uri, int i, int i2, @NonNull AnonymousClass1cO r5) throws IOException {
        return A00(uri);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.1cO] */
    @Override // X.AnonymousClass1dN
    public final boolean A5O(@NonNull Uri uri, @NonNull AnonymousClass1cO r4) throws IOException {
        return uri.getScheme().equals("android.resource");
    }

    public AnonymousClass1dB(Context context) {
        this.A00 = context.getApplicationContext();
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/net/Uri;IILX/1cO;)LX/1fR<Landroid/graphics/drawable/Drawable;>; */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass1fR A00(@androidx.annotation.NonNull android.net.Uri r7) {
        /*
        // Method dump skipped, instructions count: 205
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1dB.A00(android.net.Uri):X.1fR");
    }
}
