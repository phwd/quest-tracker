package X;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

/* renamed from: X.0du  reason: invalid class name and case insensitive filesystem */
public class C04060du implements AbstractC002303v {
    public final /* synthetic */ Toolbar A00;

    public C04060du(Toolbar toolbar) {
        this.A00 = toolbar;
    }

    @Override // X.AbstractC002303v
    public final boolean onMenuItemClick(MenuItem menuItem) {
        AbstractC004505b r0 = this.A00.A0C;
        if (r0 != null) {
            return r0.onMenuItemClick(menuItem);
        }
        return false;
    }
}
