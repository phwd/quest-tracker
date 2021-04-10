package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

/* renamed from: Wi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1366Wi0 implements AbstractC3386kV0, AbstractC2057cj0, AdapterView.OnItemClickListener {
    public Rect F;

    public static int m(ListAdapter listAdapter, ViewGroup viewGroup, Context context, int i) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        C4104oi0 oi0 = (C4104oi0) listAdapter;
        int count = oi0.getCount();
        int i2 = 0;
        int i3 = 0;
        FrameLayout frameLayout = null;
        View view = null;
        for (int i4 = 0; i4 < count; i4++) {
            int itemViewType = oi0.getItemViewType(i4);
            if (itemViewType != i3) {
                view = null;
                i3 = itemViewType;
            }
            if (frameLayout == null) {
                frameLayout = new FrameLayout(context);
            }
            view = oi0.getView(i4, view, frameLayout);
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

    public static boolean u(C4616ri0 ri0) {
        int size = ri0.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = ri0.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean c(C4616ri0 ri0, C0817Ni0 ni0) {
        return false;
    }

    @Override // defpackage.AbstractC2057cj0
    public void g(Context context, C4616ri0 ri0) {
    }

    @Override // defpackage.AbstractC2057cj0
    public boolean k(C4616ri0 ri0, C0817Ni0 ni0) {
        return false;
    }

    public abstract void l(C4616ri0 ri0);

    public abstract void n(View view);

    public abstract void o(boolean z);

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        C4104oi0 oi0;
        ListAdapter listAdapter = (ListAdapter) adapterView.getAdapter();
        if (listAdapter instanceof HeaderViewListAdapter) {
            oi0 = (C4104oi0) ((HeaderViewListAdapter) listAdapter).getWrappedAdapter();
        } else {
            oi0 = (C4104oi0) listAdapter;
        }
        oi0.F.r((MenuItem) listAdapter.getItem(i), this, (this instanceof View$OnKeyListenerC0886Om) ^ true ? 0 : 4);
    }

    public abstract void p(int i);

    public abstract void q(int i);

    public abstract void r(PopupWindow.OnDismissListener onDismissListener);

    public abstract void s(boolean z);

    public abstract void t(int i);
}
