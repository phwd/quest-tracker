package X;

import android.content.res.Resources;
import android.net.Uri;
import androidx.annotation.NonNull;

/* renamed from: X.1bh  reason: invalid class name and case insensitive filesystem */
public class C07151bh implements AbstractC07401c9<Integer, Uri> {
    public final Resources A00;

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Integer, Uri> A1o(C07381c7 r4) {
        return new C07181bk(this.A00, C07131bf.A00);
    }

    public C07151bh(Resources resources) {
        this.A00 = resources;
    }
}
