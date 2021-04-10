package X;

import android.net.Uri;
import androidx.annotation.NonNull;

/* renamed from: X.1bm  reason: invalid class name and case insensitive filesystem */
public final class C07201bm<Data> implements AbstractC07011bT<String, Data> {
    public final AbstractC07011bT<Uri, Data> A00;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, int, int, X.1cO] */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0019, code lost:
        if (r2.getScheme() == null) goto L_0x001b;
     */
    @Override // X.AbstractC07011bT
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C07091bb A1r(@androidx.annotation.NonNull java.lang.String r4, int r5, int r6, @androidx.annotation.NonNull X.AnonymousClass1cO r7) {
        /*
            r3 = this;
            java.lang.String r4 = (java.lang.String) r4
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 != 0) goto L_0x0033
            r0 = 0
            char r1 = r4.charAt(r0)
            r0 = 47
            if (r1 == r0) goto L_0x001b
            android.net.Uri r2 = android.net.Uri.parse(r4)
            java.lang.String r0 = r2.getScheme()
            if (r0 != 0) goto L_0x0026
        L_0x001b:
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            android.net.Uri r2 = android.net.Uri.fromFile(r0)
            if (r2 == 0) goto L_0x0033
        L_0x0026:
            X.1bT<android.net.Uri, Data> r1 = r3.A00
            boolean r0 = r1.A5N(r2)
            if (r0 == 0) goto L_0x0033
            X.1bb r0 = r1.A1r(r2, r5, r6, r7)
            return r0
        L_0x0033:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C07201bm.A1r(java.lang.Object, int, int, X.1cO):X.1bb");
    }

    public C07201bm(AbstractC07011bT<Uri, Data> r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AbstractC07011bT
    public final boolean A5N(@NonNull String str) {
        return true;
    }
}
