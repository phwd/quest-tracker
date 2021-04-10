package X;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1aV  reason: invalid class name and case insensitive filesystem */
public final class C06531aV implements AbstractC07051bX<InputStream> {
    public InputStream A00;
    public final Uri A01;
    public final C06541aW A02;

    @Override // X.AbstractC07051bX
    public final void cancel() {
    }

    @Override // X.AbstractC07051bX
    public final void A26() {
        InputStream inputStream = this.A00;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<InputStream> A3h() {
        return InputStream.class;
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.LOCAL;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r2 == null) goto L_0x0021;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x002d  */
    @Override // X.AbstractC07051bX
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A6H(@androidx.annotation.NonNull X.AnonymousClass1cY r10, @androidx.annotation.NonNull X.AnonymousClass1Ry<? super java.io.InputStream> r11) {
        /*
        // Method dump skipped, instructions count: 170
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06531aV.A6H(X.1cY, X.1Ry):void");
    }

    @VisibleForTesting
    public C06531aV(Uri uri, C06541aW r2) {
        this.A01 = uri;
        this.A02 = r2;
    }
}
