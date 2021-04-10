package X;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.RestrictTo;

/* renamed from: X.0vQ  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05360vQ extends BaseAdapter implements AnonymousClass08b, Filterable {
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public int A00;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public Context A01;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public Cursor A02 = null;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public DataSetObserver A03;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public AnonymousClass08Z A04;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public boolean A05 = true;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public boolean A06 = false;
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public AnonymousClass08c A07;

    public AbstractC05360vQ(Context context) {
        this.A01 = context;
        this.A00 = -1;
        this.A04 = new AnonymousClass08Z(this);
        this.A03 = new AnonymousClass08a(this);
    }

    public abstract View A03(Context context, Cursor cursor, ViewGroup viewGroup);

    public abstract void A04(View view, Context context, Cursor cursor);

    public boolean hasStableIds() {
        return true;
    }

    @Override // X.AnonymousClass08b
    public void A22(Cursor cursor) {
        Cursor cursor2 = this.A02;
        if (cursor != cursor2) {
            if (cursor2 != null) {
                AnonymousClass08Z r0 = this.A04;
                if (r0 != null) {
                    cursor2.unregisterContentObserver(r0);
                }
                DataSetObserver dataSetObserver = this.A03;
                if (dataSetObserver != null) {
                    cursor2.unregisterDataSetObserver(dataSetObserver);
                }
            }
            this.A02 = cursor;
            if (cursor != null) {
                AnonymousClass08Z r02 = this.A04;
                if (r02 != null) {
                    cursor.registerContentObserver(r02);
                }
                DataSetObserver dataSetObserver2 = this.A03;
                if (dataSetObserver2 != null) {
                    cursor.registerDataSetObserver(dataSetObserver2);
                }
                this.A00 = cursor.getColumnIndexOrThrow("_id");
                this.A06 = true;
                notifyDataSetChanged();
            } else {
                this.A00 = -1;
                this.A06 = false;
                notifyDataSetInvalidated();
            }
            if (cursor2 != null) {
                cursor2.close();
            }
        }
    }

    @Override // X.AnonymousClass08b
    public CharSequence A2I(Cursor cursor) {
        if (cursor == null) {
            return "";
        }
        return cursor.toString();
    }

    public final int getCount() {
        Cursor cursor;
        if (!this.A06 || (cursor = this.A02) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.A06) {
            return null;
        }
        this.A02.moveToPosition(i);
        if (view == null) {
            view = A02(this.A01, this.A02, viewGroup);
        }
        A04(view, this.A01, this.A02);
        return view;
    }

    public final Filter getFilter() {
        AnonymousClass08c r0 = this.A07;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass08c r02 = new AnonymousClass08c(this);
        this.A07 = r02;
        return r02;
    }

    public final Object getItem(int i) {
        Cursor cursor;
        if (!this.A06 || (cursor = this.A02) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.A02;
    }

    public final long getItemId(int i) {
        Cursor cursor;
        if (!this.A06 || (cursor = this.A02) == null || !cursor.moveToPosition(i)) {
            return 0;
        }
        return this.A02.getLong(this.A00);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.A06) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.A02.moveToPosition(i)) {
            if (view == null) {
                view = A03(this.A01, this.A02, viewGroup);
            }
            A04(view, this.A01, this.A02);
            return view;
        } else {
            throw new IllegalStateException(AnonymousClass006.A03("couldn't move cursor to position ", i));
        }
    }

    public View A02(Context context, Cursor cursor, ViewGroup viewGroup) {
        return A03(context, cursor, viewGroup);
    }

    @Override // X.AnonymousClass08b
    public final Cursor A3f() {
        return this.A02;
    }

    @Override // X.AnonymousClass08b
    public Cursor A9S(CharSequence charSequence) {
        return this.A02;
    }
}
