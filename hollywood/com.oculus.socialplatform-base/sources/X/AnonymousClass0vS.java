package X;

import android.widget.ListView;
import androidx.annotation.NonNull;

/* renamed from: X.0vS  reason: invalid class name */
public final class AnonymousClass0vS extends AnonymousClass08J {
    public final ListView A00;

    @Override // X.AnonymousClass08J
    public final boolean A05(int i) {
        return false;
    }

    @Override // X.AnonymousClass08J
    public final void A03(int i, int i2) {
        this.A00.scrollListBy(i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037 A[RETURN] */
    @Override // X.AnonymousClass08J
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A06(int r8) {
        /*
            r7 = this;
            android.widget.ListView r6 = r7.A00
            int r5 = r6.getCount()
            r4 = 0
            if (r5 == 0) goto L_0x0027
            int r3 = r6.getChildCount()
            int r1 = r6.getFirstVisiblePosition()
            int r0 = r1 + r3
            r2 = 1
            if (r8 <= 0) goto L_0x0028
            if (r0 < r5) goto L_0x0037
            int r3 = r3 - r2
            android.view.View r0 = r6.getChildAt(r3)
            int r1 = r0.getBottom()
            int r0 = r6.getHeight()
            if (r1 > r0) goto L_0x0037
        L_0x0027:
            return r4
        L_0x0028:
            if (r8 >= 0) goto L_0x0027
            if (r1 > 0) goto L_0x0037
            android.view.View r0 = r6.getChildAt(r4)
            int r0 = r0.getTop()
            if (r0 < 0) goto L_0x0037
            return r4
        L_0x0037:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0vS.A06(int):boolean");
    }

    public AnonymousClass0vS(@NonNull ListView listView) {
        super(listView);
        this.A00 = listView;
    }
}
