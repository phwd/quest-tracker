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

/* renamed from: X.0cx  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03680cx extends BaseAdapter implements AbstractC00900Bt, Filterable {
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public int A00;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public Context A01;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public Cursor A02 = null;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public DataSetObserver A03;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public AnonymousClass0Br A04;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public boolean A05 = true;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public boolean A06 = false;
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public C00910Bu A07;

    public AbstractC03680cx(Context context) {
        this.A01 = context;
        this.A00 = -1;
        this.A04 = new AnonymousClass0Br(this);
        this.A03 = new AnonymousClass0Bs(this);
    }

    public abstract View A03(Context context, Cursor cursor, ViewGroup viewGroup);

    public abstract void A04(View view, Context context, Cursor cursor);

    public boolean hasStableIds() {
        return true;
    }

    @Override // X.AbstractC00900Bt
    public void A1g(Cursor cursor) {
        Cursor cursor2 = this.A02;
        if (cursor != cursor2) {
            if (cursor2 != null) {
                AnonymousClass0Br r0 = this.A04;
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
                AnonymousClass0Br r02 = this.A04;
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

    @Override // X.AbstractC00900Bt
    public CharSequence A1r(Cursor cursor) {
        if (cursor == null) {
            return "";
        }
        return cursor.toString();
    }

    @Override // X.AbstractC00900Bt
    public final Cursor A3I() {
        return this.A02;
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
        C00910Bu r0 = this.A07;
        if (r0 != null) {
            return r0;
        }
        C00910Bu r02 = new C00910Bu(this);
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
        String str;
        if (!this.A06) {
            str = "this should only be called when the cursor is valid";
        } else if (this.A02.moveToPosition(i)) {
            if (view == null) {
                view = A03(this.A01, this.A02, viewGroup);
            }
            A04(view, this.A01, this.A02);
            return view;
        } else {
            str = AnonymousClass006.A01("couldn't move cursor to position ", i);
        }
        throw new IllegalStateException(str);
    }

    public View A02(Context context, Cursor cursor, ViewGroup viewGroup) {
        return A03(context, cursor, viewGroup);
    }

    @Override // X.AbstractC00900Bt
    public Cursor A7W(CharSequence charSequence) {
        return this.A02;
    }
}
