package X;

import android.content.ContentResolver;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.IOException;

/* renamed from: X.1S7  reason: invalid class name */
public abstract class AnonymousClass1S7<T> implements AbstractC07051bX<T> {
    public T A00;
    public final ContentResolver A01;
    public final Uri A02;

    @Override // X.AbstractC07051bX
    public final void cancel() {
    }

    @Override // X.AbstractC07051bX
    public final void A26() {
        T t = this.A00;
        if (t != null) {
            try {
                if (this instanceof AnonymousClass1YC) {
                    t.close();
                } else if (!(this instanceof AnonymousClass1YP)) {
                    t.close();
                } else {
                    t.close();
                }
            } catch (IOException unused) {
            }
        }
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004f A[Catch:{ FileNotFoundException -> 0x0091 }] */
    @Override // X.AbstractC07051bX
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A6H(@androidx.annotation.NonNull X.AnonymousClass1cY r5, @androidx.annotation.NonNull X.AnonymousClass1Ry<? super T> r6) {
        /*
        // Method dump skipped, instructions count: 150
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1S7.A6H(X.1cY, X.1Ry):void");
    }

    public AnonymousClass1S7(ContentResolver contentResolver, Uri uri) {
        this.A01 = contentResolver;
        this.A02 = uri;
    }
}
