package X;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.ListMenuItemView;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1sa  reason: invalid class name and case insensitive filesystem */
public class C11641sa extends AnonymousClass1F5 {
    public AnonymousClass1tS A00;
    public MenuItem A01;
    public final int A02;
    public final int A03;

    @Override // X.AnonymousClass1F5
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int i;
        int pointToPosition;
        int i2;
        if (this.A00 != null) {
            ListAdapter adapter = getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                i = headerViewListAdapter.getHeadersCount();
                adapter = headerViewListAdapter.getWrappedAdapter();
            } else {
                i = 0;
            }
            C11661sc r4 = (C11661sc) adapter;
            C11601sP r3 = null;
            if (motionEvent.getAction() != 10 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i2 = pointToPosition - i) >= 0 && i2 < r4.getCount()) {
                r3 = r4.getItem(i2);
            }
            MenuItem menuItem = this.A01;
            if (menuItem != r3) {
                C11581sN r1 = r4.A00;
                if (menuItem != null) {
                    this.A00.A7D(r1, menuItem);
                }
                this.A01 = r3;
                if (r3 != null) {
                    this.A00.A7C(r1, r3);
                }
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    public C11641sa(Context context, boolean z) {
        super(context, z);
        if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
            this.A02 = 21;
            this.A03 = 22;
            return;
        }
        this.A02 = 22;
        this.A03 = 21;
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
        if (listMenuItemView != null) {
            if (i == this.A02) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            } else if (i == this.A03) {
                setSelection(-1);
                ((C11661sc) getAdapter()).A00.A0F(false);
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void setHoverListener(AnonymousClass1tS r1) {
        this.A00 = r1;
    }
}
