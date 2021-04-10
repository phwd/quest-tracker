package X;

import android.database.DataSetObserver;

/* renamed from: X.0Bs  reason: invalid class name */
public class AnonymousClass0Bs extends DataSetObserver {
    public final /* synthetic */ AbstractC03680cx A00;

    public AnonymousClass0Bs(AbstractC03680cx r1) {
        this.A00 = r1;
    }

    public final void onChanged() {
        AbstractC03680cx r1 = this.A00;
        r1.A06 = true;
        r1.notifyDataSetChanged();
    }

    public final void onInvalidated() {
        AbstractC03680cx r1 = this.A00;
        r1.A06 = false;
        r1.notifyDataSetInvalidated();
    }
}
