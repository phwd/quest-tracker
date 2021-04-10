package X;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;

/* renamed from: X.1bv  reason: invalid class name and case insensitive filesystem */
public class C07291bv implements AbstractC07401c9<Uri, ParcelFileDescriptor>, AbstractC07321by<ParcelFileDescriptor> {
    public final AssetManager A00;

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Uri, ParcelFileDescriptor> A1o(C07381c7 r3) {
        return new C07301bw(this.A00, this);
    }

    @Override // X.AbstractC07321by
    public final AbstractC07051bX<ParcelFileDescriptor> A1q(AssetManager assetManager, String str) {
        return new AnonymousClass1YO(assetManager, str);
    }

    public C07291bv(AssetManager assetManager) {
        this.A00 = assetManager;
    }
}
