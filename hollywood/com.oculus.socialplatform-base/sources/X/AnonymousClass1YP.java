package X;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;

/* renamed from: X.1YP  reason: invalid class name */
public final class AnonymousClass1YP extends AnonymousClass1S7<ParcelFileDescriptor> {
    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<ParcelFileDescriptor> A3h() {
        return ParcelFileDescriptor.class;
    }

    public AnonymousClass1YP(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }
}
