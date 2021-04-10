package X;

import android.database.DataSetObserver;

/* renamed from: X.08a  reason: invalid class name */
public class AnonymousClass08a extends DataSetObserver {
    public final /* synthetic */ AbstractC05360vQ A00;

    public AnonymousClass08a(AbstractC05360vQ r1) {
        this.A00 = r1;
    }

    public final void onChanged() {
        AbstractC05360vQ r1 = this.A00;
        r1.A06 = true;
        r1.notifyDataSetChanged();
    }

    public final void onInvalidated() {
        AbstractC05360vQ r1 = this.A00;
        r1.A06 = false;
        r1.notifyDataSetInvalidated();
    }
}
