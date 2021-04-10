package X;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0Mu  reason: invalid class name and case insensitive filesystem */
public final class SubMenuC01890Mu extends C04280eZ implements SubMenu {
    public C04280eZ A00;
    public C04250eW A01;

    public final SubMenu setHeaderView(View view) {
        C04280eZ.A02(this, 0, null, 0, null, view);
        return this;
    }

    @Override // X.C04280eZ
    public final C04280eZ A04() {
        return this.A00.A04();
    }

    @Override // X.C04280eZ
    public final String A05() {
        int itemId;
        C04250eW r0 = this.A01;
        if (r0 == null || (itemId = r0.getItemId()) == 0) {
            return null;
        }
        return AnonymousClass006.A06(super.A05(), ":", itemId);
    }

    @Override // X.C04280eZ
    public final void A0C(AnonymousClass03V r2) {
        this.A00.A0C(r2);
    }

    @Override // X.C04280eZ
    public final boolean A0H() {
        return this.A00.A0H();
    }

    @Override // X.C04280eZ
    public final boolean A0I() {
        return this.A00.A0I();
    }

    @Override // X.C04280eZ
    public final boolean A0J() {
        return this.A00.A0J();
    }

    @Override // X.C04280eZ
    public final boolean A0M(C04250eW r2) {
        return this.A00.A0M(r2);
    }

    @Override // X.C04280eZ
    public final boolean A0N(C04250eW r2) {
        return this.A00.A0N(r2);
    }

    public final MenuItem getItem() {
        return this.A01;
    }

    @Override // X.C04280eZ
    public final void setGroupDividerEnabled(boolean z) {
        this.A00.setGroupDividerEnabled(z);
    }

    @Override // X.C04280eZ
    public final void setQwertyMode(boolean z) {
        this.A00.setQwertyMode(z);
    }

    public SubMenuC01890Mu(Context context, C04280eZ r2, C04250eW r3) {
        super(context);
        this.A00 = r2;
        this.A01 = r3;
    }

    @Override // X.C04280eZ
    public final boolean A0L(@NonNull C04280eZ r3, @NonNull MenuItem menuItem) {
        if (super.A0L(r3, menuItem) || this.A00.A0L(r3, menuItem)) {
            return true;
        }
        return false;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i) {
        C04280eZ.A02(this, 0, null, i, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        C04280eZ.A02(this, 0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i) {
        C04280eZ.A02(this, i, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        C04280eZ.A02(this, 0, charSequence, 0, null, null);
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
