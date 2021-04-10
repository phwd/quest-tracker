package X;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.File;

/* renamed from: X.1bF  reason: invalid class name and case insensitive filesystem */
public final class C06881bF implements AbstractC07401c9<Uri, File> {
    public final Context A00;

    @Override // X.AbstractC07401c9
    @NonNull
    public final AbstractC07011bT<Uri, File> A1o(C07381c7 r3) {
        return new C06871bE(this.A00);
    }

    public C06881bF(Context context) {
        this.A00 = context;
    }
}
