package X;

import android.content.ContentResolver;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InputStream;

/* renamed from: X.1bt  reason: invalid class name and case insensitive filesystem */
public class C07271bt implements AbstractC07401c9<Uri, InputStream>, AbstractC07281bu<InputStream> {
    public final ContentResolver A00;

    @Override // X.AbstractC07281bu
    public final AbstractC07051bX<InputStream> A1m(Uri uri) {
        return new AnonymousClass1YC(this.A00, uri);
    }

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Uri, InputStream> A1o(C07381c7 r2) {
        return new C07251br(this);
    }

    public C07271bt(ContentResolver contentResolver) {
        this.A00 = contentResolver;
    }
}
