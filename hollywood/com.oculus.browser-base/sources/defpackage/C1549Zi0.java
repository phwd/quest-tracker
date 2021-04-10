package defpackage;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;

/* renamed from: Zi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1549Zi0 extends C1823bJ {
    public final int S;
    public final int T;
    public AbstractC0696Li0 U;
    public MenuItem V;

    public C1549Zi0(Context context, boolean z) {
        super(context, z);
        if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
            this.S = 21;
            this.T = 22;
            return;
        }
        this.S = 22;
        this.T = 21;
    }

    @Override // defpackage.C1823bJ
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int i;
        C4104oi0 oi0;
        int pointToPosition;
        int i2;
        if (this.U != null) {
            ListAdapter adapter = getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                i = headerViewListAdapter.getHeadersCount();
                oi0 = (C4104oi0) headerViewListAdapter.getWrappedAdapter();
            } else {
                i = 0;
                oi0 = (C4104oi0) adapter;
            }
            C0817Ni0 ni0 = null;
            if (motionEvent.getAction() != 10 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i2 = pointToPosition - i) >= 0 && i2 < oi0.getCount()) {
                ni0 = oi0.getItem(i2);
            }
            MenuItem menuItem = this.V;
            if (menuItem != ni0) {
                C4616ri0 ri0 = oi0.F;
                if (menuItem != null) {
                    this.U.a(ri0, menuItem);
                }
                this.V = ni0;
                if (ni0 != null) {
                    this.U.n(ri0, ni0);
                }
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
        if (listMenuItemView != null && i == this.S) {
            if (listMenuItemView.isEnabled() && listMenuItemView.F.hasSubMenu()) {
                performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
            }
            return true;
        } else if (listMenuItemView == null || i != this.T) {
            return super.onKeyDown(i, keyEvent);
        } else {
            setSelection(-1);
            ((C4104oi0) getAdapter()).F.c(false);
            return true;
        }
    }
}
