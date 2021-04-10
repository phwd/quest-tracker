package X;

import android.database.Cursor;
import android.widget.Filter;

/* renamed from: X.08c  reason: invalid class name */
public final class AnonymousClass08c extends Filter {
    public AnonymousClass08b A00;

    public final CharSequence convertResultToString(Object obj) {
        return this.A00.A2I((Cursor) obj);
    }

    public final Filter.FilterResults performFiltering(CharSequence charSequence) {
        Cursor A9S = this.A00.A9S(charSequence);
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (A9S != null) {
            filterResults.count = A9S.getCount();
        } else {
            filterResults.count = 0;
            A9S = null;
        }
        filterResults.values = A9S;
        return filterResults;
    }

    public final void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        AnonymousClass08b r2 = this.A00;
        Cursor A3f = r2.A3f();
        Object obj = filterResults.values;
        if (obj != null && obj != A3f) {
            r2.A22((Cursor) obj);
        }
    }

    public AnonymousClass08c(AnonymousClass08b r1) {
        this.A00 = r1;
    }
}
