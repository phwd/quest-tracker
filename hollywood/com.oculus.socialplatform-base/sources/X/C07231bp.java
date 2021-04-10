package X;

import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.InputStream;

/* renamed from: X.1bp  reason: invalid class name and case insensitive filesystem */
public class C07231bp implements AbstractC07401c9<String, InputStream> {
    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<String, InputStream> A1o(@NonNull C07381c7 r3) {
        return new C07201bm(r3.A00(Uri.class, InputStream.class));
    }
}
