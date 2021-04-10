package X;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;

/* renamed from: X.1bl  reason: invalid class name and case insensitive filesystem */
public final class C07191bl implements AbstractC07401c9<Integer, AssetFileDescriptor> {
    public final Resources A00;

    @Override // X.AbstractC07401c9
    public final AbstractC07011bT<Integer, AssetFileDescriptor> A1o(C07381c7 r4) {
        return new C07181bk(this.A00, r4.A00(Uri.class, AssetFileDescriptor.class));
    }

    public C07191bl(Resources resources) {
        this.A00 = resources;
    }
}
