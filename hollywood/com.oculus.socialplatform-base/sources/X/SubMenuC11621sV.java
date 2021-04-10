package X;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1sV  reason: invalid class name and case insensitive filesystem */
public final class SubMenuC11621sV extends C11581sN implements SubMenu {
    public C11581sN A00;
    public C11601sP A01;

    public final SubMenu setHeaderView(View view) {
        C11581sN.A02(this, 0, null, 0, null, view);
        return this;
    }

    @Override // X.C11581sN
    public final String A05() {
        int itemId;
        C11601sP r0 = this.A01;
        if (r0 == null || (itemId = r0.getItemId()) == 0) {
            return null;
        }
        return AnonymousClass006.A08(super.A05(), ":", itemId);
    }

    public final MenuItem getItem() {
        return this.A01;
    }

    public SubMenuC11621sV(Context context, C11581sN r2, C11601sP r3) {
        super(context);
        this.A00 = r2;
        this.A01 = r3;
    }

    @Override // X.C11581sN
    public final boolean A0L(@NonNull C11581sN r3, @NonNull MenuItem menuItem) {
        if (super.A0L(r3, menuItem) || this.A00.A0L(r3, menuItem)) {
            return true;
        }
        return false;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i) {
        C11581sN.A02(this, 0, null, i, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        C11581sN.A02(this, 0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i) {
        C11581sN.A02(this, i, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        C11581sN.A02(this, 0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i) {
        this.A01.setIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.A01.setIcon(drawable);
        return this;
    }
}
