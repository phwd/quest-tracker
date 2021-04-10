package X;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.File;

/* renamed from: X.1bE  reason: invalid class name and case insensitive filesystem */
public final class C06871bE implements AbstractC07011bT<Uri, File> {
    public final Context A00;

    /* Return type fixed from 'X.1bb' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    public final C07091bb<File> A1r(@NonNull Uri uri, int i, int i2, @NonNull AnonymousClass1cO r7) {
        Uri uri2 = uri;
        return new C07091bb(new AnonymousClass1S3(uri2), new AnonymousClass1YB(this.A00, uri2));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull Uri uri) {
        return AnonymousClass1bG.A00(uri);
    }

    public C06871bE(Context context) {
        this.A00 = context;
    }
}
