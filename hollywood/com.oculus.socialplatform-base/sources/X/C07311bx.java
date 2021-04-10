package X;

import android.content.res.AssetManager;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InputStream;

/* renamed from: X.1bx  reason: invalid class name and case insensitive filesystem */
public class C07311bx implements AbstractC07401c9<Uri, InputStream>, AbstractC07321by<InputStream> {
    public final AssetManager A00;

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Uri, InputStream> A1o(C07381c7 r3) {
        return new C07301bw(this.A00, this);
    }

    @Override // X.AbstractC07321by
    public final AbstractC07051bX<InputStream> A1q(AssetManager assetManager, String str) {
        return new AnonymousClass1S5(assetManager, str);
    }

    public C07311bx(AssetManager assetManager) {
        this.A00 = assetManager;
    }
}
