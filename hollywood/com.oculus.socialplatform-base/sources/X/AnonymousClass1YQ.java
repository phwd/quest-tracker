package X;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import androidx.annotation.NonNull;

/* renamed from: X.1YQ  reason: invalid class name */
public final class AnonymousClass1YQ extends AnonymousClass1S7<AssetFileDescriptor> {
    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<AssetFileDescriptor> A3h() {
        return AssetFileDescriptor.class;
    }

    public AnonymousClass1YQ(ContentResolver contentResolver, Uri uri) {
        super(contentResolver, uri);
    }
}
