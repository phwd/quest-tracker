package X;

import android.content.res.AssetManager;
import android.net.Uri;
import androidx.annotation.NonNull;

/* renamed from: X.1bw  reason: invalid class name and case insensitive filesystem */
public final class C07301bw<Data> implements AbstractC07011bT<Uri, Data> {
    public final AssetManager A00;
    public final AbstractC07321by<Data> A01;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull Uri uri) {
        Uri uri2 = uri;
        if (!"file".equals(uri2.getScheme()) || uri2.getPathSegments().isEmpty() || !"android_asset".equals(uri2.getPathSegments().get(0))) {
            return false;
        }
        return true;
    }

    public C07301bw(AssetManager assetManager, AbstractC07321by<Data> r2) {
        this.A00 = assetManager;
        this.A01 = r2;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb A1r(@NonNull Uri uri, int i, int i2, @NonNull AnonymousClass1cO r8) {
        return new C07091bb(new AnonymousClass1S3(uri), this.A01.A1q(this.A00, uri.toString().substring(22)));
    }
}
