package X;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1sY  reason: invalid class name */
public abstract class AnonymousClass1sY implements AnonymousClass1t6, AnonymousClass1FA, AdapterView.OnItemClickListener {
    public Rect A00;

    public static int A00(ListAdapter listAdapter, Context context, int i) {
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

    @Override // X.AnonymousClass1t6
    public final boolean A2B(C11581sN r2, C11601sP r3) {
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final boolean A2v(C11581sN r2, C11601sP r3) {
        return false;
    }

    @Override // X.AnonymousClass1t6
    public final void A5e(@NonNull Context context, @Nullable C11581sN r2) {
    }

    public final void A02(boolean z) {
        if (!(this instanceof View$OnKeyListenerC11681se)) {
            ((View$OnKeyListenerC11671sd) this).A0A = z;
        } else {
            ((View$OnKeyListenerC11681se) this).A0E.A01 = z;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0021, code lost:
        if (0 != 0) goto L_0x0023;
     */
    @Override // android.widget.AdapterView.OnItemClickListener
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onItemClick(android.widget.AdapterView<?> r5, android.view.View r6, int r7, long r8) {
        /*
            r4 = this;
            android.widget.Adapter r2 = r5.getAdapter()
            android.widget.ListAdapter r2 = (android.widget.ListAdapter) r2
            r1 = r2
            boolean r0 = r2 instanceof android.widget.HeaderViewListAdapter
            if (r0 == 0) goto L_0x0011
            android.widget.HeaderViewListAdapter r1 = (android.widget.HeaderViewListAdapter) r1
            android.widget.ListAdapter r1 = r1.getWrappedAdapter()
        L_0x0011:
            X.1sc r1 = (X.C11661sc) r1
            X.1sN r3 = r1.A00
            java.lang.Object r2 = r2.getItem(r7)
            android.view.MenuItem r2 = (android.view.MenuItem) r2
            boolean r0 = r4 instanceof X.View$OnKeyListenerC11671sd
            if (r0 == 0) goto L_0x0023
            r0 = 0
            r1 = 4
            if (r0 == 0) goto L_0x0024
        L_0x0023:
            r1 = 0
        L_0x0024:
            r3.A0K(r2, r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1sY.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
    }
}
