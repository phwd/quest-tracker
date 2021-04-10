package X;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;

/* renamed from: X.1bo  reason: invalid class name and case insensitive filesystem */
public class C07221bo implements AbstractC07401c9<String, ParcelFileDescriptor> {
    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<String, ParcelFileDescriptor> A1o(@NonNull C07381c7 r3) {
        return new C07201bm(r3.A00(Uri.class, ParcelFileDescriptor.class));
    }
}
