package X;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.InputStream;

/* renamed from: X.1bI  reason: invalid class name and case insensitive filesystem */
public final class C06901bI implements AbstractC07011bT<Uri, InputStream> {
    public final Context A00;

    /* Return type fixed from 'X.1bb' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    @Override // X.AbstractC07011bT
    @Nullable
    public final C07091bb<InputStream> A1r(@NonNull Uri uri, int i, int i2, @NonNull AnonymousClass1cO r10) {
        Number number;
        Uri uri2 = uri;
        if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE || i > 512 || i2 > 384 || (number = (Number) r10.A00(AnonymousClass1dO.A04)) == null || number.longValue() != -1) {
            return null;
        }
        AnonymousClass1S3 r5 = new AnonymousClass1S3(uri2);
        Context context = this.A00;
        return new C07091bb(r5, new C06531aV(uri2, new C06541aW(ComponentCallbacks2C07631cl.A01(context).A01.A00(), new C06581aa(context.getContentResolver()), ComponentCallbacks2C07631cl.A01(context).A03, context.getContentResolver())));
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull Uri uri) {
        Uri uri2 = uri;
        if (!AnonymousClass1bG.A00(uri2) || !uri2.getPathSegments().contains("video")) {
            return false;
        }
        return true;
    }

    public C06901bI(Context context) {
        this.A00 = context.getApplicationContext();
    }
}
