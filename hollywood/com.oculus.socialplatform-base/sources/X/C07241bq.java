package X;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;

/* renamed from: X.1bq  reason: invalid class name and case insensitive filesystem */
public final class C07241bq implements AbstractC07401c9<Uri, AssetFileDescriptor>, AbstractC07281bu<AssetFileDescriptor> {
    public final ContentResolver A00;

    @Override // X.AbstractC07281bu
    public final AbstractC07051bX<AssetFileDescriptor> A1m(Uri uri) {
        return new AnonymousClass1YQ(this.A00, uri);
    }

    @Override // X.AbstractC07401c9
    public final AbstractC07011bT<Uri, AssetFileDescriptor> A1o(C07381c7 r2) {
        return new C07251br(this);
    }

    public C07241bq(ContentResolver contentResolver) {
        this.A00 = contentResolver;
    }
}
