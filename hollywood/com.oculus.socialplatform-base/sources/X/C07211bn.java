package X;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import androidx.annotation.NonNull;

/* renamed from: X.1bn  reason: invalid class name and case insensitive filesystem */
public final class C07211bn implements AbstractC07401c9<String, AssetFileDescriptor> {
    @Override // X.AbstractC07401c9
    public final AbstractC07011bT<String, AssetFileDescriptor> A1o(@NonNull C07381c7 r3) {
        return new C07201bm(r3.A00(Uri.class, AssetFileDescriptor.class));
    }
}
