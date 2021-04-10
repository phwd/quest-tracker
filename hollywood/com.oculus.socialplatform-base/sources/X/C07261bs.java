package X;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;

/* renamed from: X.1bs  reason: invalid class name and case insensitive filesystem */
public class C07261bs implements AbstractC07401c9<Uri, ParcelFileDescriptor>, AbstractC07281bu<ParcelFileDescriptor> {
    public final ContentResolver A00;

    @Override // X.AbstractC07281bu
    public final AbstractC07051bX<ParcelFileDescriptor> A1m(Uri uri) {
        return new AnonymousClass1YP(this.A00, uri);
    }

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Uri, ParcelFileDescriptor> A1o(C07381c7 r2) {
        return new C07251br(this);
    }

    public C07261bs(ContentResolver contentResolver) {
        this.A00 = contentResolver;
    }
}
