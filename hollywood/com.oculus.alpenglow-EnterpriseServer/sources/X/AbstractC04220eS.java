package X;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.0eS  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC04220eS implements AbstractC000603b, AbstractC000903e, AdapterView.OnItemClickListener {
    public Rect A00;

    public static int A01(ListAdapter listAdapter, Context context, int i) {
        FrameLayout frameLayout = null;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        View view = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = listAdapter.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (frameLayout == null) {
                frameLayout = new FrameLayout(context);
            }
            view = listAdapter.getView(i4, view, frameLayout);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            int measuredWidth = view.getMeasuredWidth();
            if (measuredWidth >= i) {
                return i;
            }
            if (measuredWidth > i2) {
                i2 = measuredWidth;
            }
        }
        return i2;
    }

    public abstract void A02(int i);

    public abstract void A03(int i);

    public abstract void A04(int i);

    public abstract void A05(View view);

    public abstract void A06(PopupWindow.OnDismissListener onDismissListener);

    public abstract void A07(C04280eZ v);

    public abstract void A08(boolean z);

    public abstract void A09(boolean z);

    public boolean A0A() {
        return true;
    }

    @Override // X.AbstractC000603b
    public final boolean A1k(C04280eZ r2, C04250eW r3) {
        return false;
    }

    @Override // X.AbstractC000603b
    public final boolean A2V(C04280eZ r2, C04250eW r3) {
        return false;
    }

    @Override // X.AbstractC000603b
    public final void A5E(@NonNull Context context, @Nullable C04280eZ r2) {
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        ListAdapter listAdapter2 = listAdapter;
        if (listAdapter instanceof HeaderViewListAdapter) {
            listAdapter2 = ((HeaderViewListAdapter) listAdapter2).getWrappedAdapter();
        }
        C04280eZ r3 = ((AnonymousClass03U) listAdapter2).A00;
        MenuItem menuItem = (MenuItem) listAdapter.getItem(i);
        int i2 = 4;
        if (A0A()) {
            i2 = 0;
        }
        r3.A0K(menuItem, this, i2);
    }
}
