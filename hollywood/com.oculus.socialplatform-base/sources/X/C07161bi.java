package X;

import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import androidx.annotation.NonNull;

/* renamed from: X.1bi  reason: invalid class name and case insensitive filesystem */
public class C07161bi implements AbstractC07401c9<Integer, ParcelFileDescriptor> {
    public final Resources A00;

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Integer, ParcelFileDescriptor> A1o(C07381c7 r4) {
        return new C07181bk(this.A00, r4.A00(Uri.class, ParcelFileDescriptor.class));
    }

    public C07161bi(Resources resources) {
        this.A00 = resources;
    }
}
