package X;

import android.database.Cursor;
import android.widget.Filter;

/* renamed from: X.0Bu  reason: invalid class name and case insensitive filesystem */
public final class C00910Bu extends Filter {
    public AbstractC00900Bt A00;

    public final CharSequence convertResultToString(Object obj) {
        return this.A00.A1r((Cursor) obj);
    }

    public final Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor A7W = this.A00.A7W(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (A7W != null) {
            filterResults.count = A7W.getCount();
        } else {
            filterResults.count = 0;
            A7W = null;
        }
        filterResults.values = A7W;
        return filterResults;
    }

    public final void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        AbstractC00900Bt r2 = this.A00;
        Cursor A3I = r2.A3I();
        Object obj = filterResults.values;
        if (obj != null && obj != A3I) {
            r2.A1g((Cursor) obj);
        }
    }

    public C00910Bu(AbstractC00900Bt r1) {
        this.A00 = r1;
    }
}
