package X;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

/* renamed from: X.1tf  reason: invalid class name and case insensitive filesystem */
public class C11961tf implements AbstractC12021tm {
    public final /* synthetic */ Toolbar A00;

    public C11961tf(Toolbar toolbar) {
        this.A00 = toolbar;
    }

    @Override // X.AbstractC12021tm
    public final boolean onMenuItemClick(MenuItem menuItem) {
        AbstractC12031tn r0 = this.A00.A0C;
        if (r0 != null) {
            return r0.onMenuItemClick(menuItem);
        }
        return false;
    }
}
