package X;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.io.File;

@RequiresApi(29)
/* renamed from: X.1bZ  reason: invalid class name and case insensitive filesystem */
public final class C07071bZ<DataT> implements AbstractC07011bT<Uri, DataT> {
    public final Context A00;
    public final AbstractC07011bT<File, DataT> A01;
    public final AbstractC07011bT<Uri, DataT> A02;
    public final Class<DataT> A03;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb A1r(@NonNull Uri uri, int i, int i2, @NonNull AnonymousClass1cO r15) {
        Uri uri2 = uri;
        return new C07091bb(new AnonymousClass1S3(uri2), new C07061bY(this.A00, this.A01, this.A02, uri2, i, i2, r15, this.A03));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull Uri uri) {
        Uri uri2 = uri;
        if (Build.VERSION.SDK_INT < 29 || !AnonymousClass1bG.A00(uri2)) {
            return false;
        }
        return true;
    }

    public C07071bZ(Context context, AbstractC07011bT<File, DataT> r3, AbstractC07011bT<Uri, DataT> r4, Class<DataT> cls) {
        this.A00 = context.getApplicationContext();
        this.A01 = r3;
        this.A02 = r4;
        this.A03 = cls;
    }
}
