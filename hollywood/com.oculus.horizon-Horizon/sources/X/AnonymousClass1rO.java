package X;

import android.graphics.Bitmap;

/* renamed from: X.1rO  reason: invalid class name */
public class AnonymousClass1rO implements AnonymousClass1ou<Bitmap> {
    public final /* synthetic */ AnonymousClass1rU A00;

    public AnonymousClass1rO(AnonymousClass1rU r1) {
        this.A00 = r1;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000e */
    @Override // X.AnonymousClass1ou
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A86(android.graphics.Bitmap r13) {
        /*
            r12 = this;
            android.graphics.Bitmap r13 = (android.graphics.Bitmap) r13
            X.1rU r7 = r12.A00     // Catch:{ all -> 0x0055 }
            monitor-enter(r7)     // Catch:{ all -> 0x0055 }
            if (r13 != 0) goto L_0x0009
            r10 = 0
            goto L_0x0012
        L_0x0009:
            int r10 = r13.getAllocationByteCount()     // Catch:{ NullPointerException -> 0x000e }
            goto L_0x0012
        L_0x000e:
            int r10 = r13.getByteCount()     // Catch:{ all -> 0x0052 }
        L_0x0012:
            int r0 = r7.A00     // Catch:{ all -> 0x0052 }
            r11 = 0
            r9 = 1
            r1 = 0
            if (r0 <= 0) goto L_0x001a
            r1 = 1
        L_0x001a:
            java.lang.String r0 = "No bitmaps registered."
            X.AnonymousClass0KU.A04(r1, r0)     // Catch:{ all -> 0x0052 }
            long r3 = (long) r10     // Catch:{ all -> 0x0052 }
            long r1 = r7.A01     // Catch:{ all -> 0x0052 }
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            r8 = 0
            if (r0 > 0) goto L_0x0028
            r8 = 1
        L_0x0028:
            java.lang.String r6 = "Bitmap size bigger than the total registered size: %d, %d"
            r0 = 2
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch:{ all -> 0x0052 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x0052 }
            r5[r11] = r0     // Catch:{ all -> 0x0052 }
            java.lang.Long r0 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0052 }
            r5[r9] = r0     // Catch:{ all -> 0x0052 }
            if (r8 == 0) goto L_0x0048
            long r1 = r1 - r3
            r7.A01 = r1     // Catch:{ all -> 0x0052 }
            int r0 = r7.A00     // Catch:{ all -> 0x0052 }
            int r0 = r0 - r9
            r7.A00 = r0     // Catch:{ all -> 0x0052 }
            monitor-exit(r7)
            r13.recycle()
            return
        L_0x0048:
            java.lang.String r1 = X.AnonymousClass0KU.A00(r6, r5)
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r1)
            throw r0
        L_0x0052:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        L_0x0055:
            r0 = move-exception
            r13.recycle()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1rO.A86(java.lang.Object):void");
    }
}
