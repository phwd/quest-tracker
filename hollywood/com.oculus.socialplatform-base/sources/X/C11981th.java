package X;

import android.content.Context;
import android.widget.ArrayAdapter;

/* renamed from: X.1th  reason: invalid class name and case insensitive filesystem */
public class C11981th extends ArrayAdapter<CharSequence> {
    public final long getItemId(int i) {
        return (long) i;
    }

    public final boolean hasStableIds() {
        return true;
    }

    public C11981th(Context context, int i) {
        super(context, i, 16908308, (Object[]) null);
    }
}
