package X;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InputStream;

/* renamed from: X.1bK  reason: invalid class name and case insensitive filesystem */
public class C06921bK implements AbstractC07401c9<Uri, InputStream> {
    public final Context A00;

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Uri, InputStream> A1o(C07381c7 r3) {
        return new C06901bI(this.A00);
    }

    public C06921bK(Context context) {
        this.A00 = context;
    }
}
