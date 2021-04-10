package X;

import android.content.res.Resources;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1jN  reason: invalid class name */
public final class AnonymousClass1jN implements AnonymousClass0PY {
    public final Resources A00;

    public AnonymousClass1jN(Resources resources) {
        this.A00 = resources;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (r2 == -1) goto L_0x001c;
     */
    @Override // X.AnonymousClass0PY
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.drawable.Drawable A2Q(X.AnonymousClass0VM r5) {
        /*
            r4 = this;
            X.C01060Pq.A00()     // Catch:{ all -> 0x003b }
            boolean r0 = r5 instanceof X.C002305g     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0036
            X.05g r5 = (X.C002305g) r5     // Catch:{ all -> 0x003b }
            android.content.res.Resources r1 = r4.A00     // Catch:{ all -> 0x003b }
            android.graphics.Bitmap r0 = r5.A04()     // Catch:{ all -> 0x003b }
            android.graphics.drawable.BitmapDrawable r3 = new android.graphics.drawable.BitmapDrawable     // Catch:{ all -> 0x003b }
            r3.<init>(r1, r0)     // Catch:{ all -> 0x003b }
            int r2 = r5.A02     // Catch:{ all -> 0x003b }
            if (r2 == 0) goto L_0x001c
            r1 = -1
            r0 = 1
            if (r2 != r1) goto L_0x001d
        L_0x001c:
            r0 = 0
        L_0x001d:
            if (r0 != 0) goto L_0x002e
            int r1 = r5.A01     // Catch:{ all -> 0x003b }
            r0 = 1
            if (r1 == r0) goto L_0x0027
            if (r1 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r0 = 0
        L_0x0028:
            if (r0 != 0) goto L_0x002e
            X.C01060Pq.A00()
            return r3
        L_0x002e:
            int r1 = r5.A01
            X.1jU r0 = new X.1jU
            r0.<init>(r3, r2, r1)
            goto L_0x0037
        L_0x0036:
            r0 = 0
        L_0x0037:
            X.C01060Pq.A00()
            return r0
        L_0x003b:
            r0 = move-exception
            X.C01060Pq.A00()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1jN.A2Q(X.0VM):android.graphics.drawable.Drawable");
    }
}
