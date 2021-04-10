package X;

import android.database.DataSetObserver;

/* renamed from: X.1tZ  reason: invalid class name */
public class AnonymousClass1tZ extends DataSetObserver {
    public final /* synthetic */ C11691sf A00;

    public AnonymousClass1tZ(C11691sf r1) {
        this.A00 = r1;
    }

    public final void onChanged() {
        C11691sf r1 = this.A00;
        if (r1.A6B()) {
            r1.AAO();
        }
    }

    public final void onInvalidated() {
        this.A00.dismiss();
    }
}
