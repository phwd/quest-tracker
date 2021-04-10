package X;

import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InputStream;

/* renamed from: X.1bj  reason: invalid class name and case insensitive filesystem */
public class C07171bj implements AbstractC07401c9<Integer, InputStream> {
    public final Resources A00;

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Integer, InputStream> A1o(C07381c7 r4) {
        return new C07181bk(this.A00, r4.A00(Uri.class, InputStream.class));
    }

    public C07171bj(Resources resources) {
        this.A00 = resources;
    }
}
