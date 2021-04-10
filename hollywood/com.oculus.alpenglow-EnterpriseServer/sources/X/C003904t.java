package X;

import android.database.DataSetObserver;

/* renamed from: X.04t  reason: invalid class name and case insensitive filesystem */
public class C003904t extends DataSetObserver {
    public final /* synthetic */ C04080dy A00;

    public C003904t(C04080dy r1) {
        this.A00 = r1;
    }

    public final void onChanged() {
        C04080dy r1 = this.A00;
        if (r1.A5a()) {
            r1.A8P();
        }
    }

    public final void onInvalidated() {
        this.A00.dismiss();
    }
}
