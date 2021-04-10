package X;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0eW  reason: invalid class name and case insensitive filesystem */
public final class C04250eW implements AnonymousClass08q {
    public char A00;
    public char A01;
    public int A02 = 16;
    public int A03 = 4096;
    public int A04 = 4096;
    public int A05 = 0;
    public Intent A06;
    public ColorStateList A07 = null;
    public PorterDuff.Mode A08 = null;
    public MenuItem.OnMenuItemClickListener A09;
    public View A0A;
    public C04280eZ A0B;
    public SubMenuC01890Mu A0C;
    public AnonymousClass0AE A0D;
    public boolean A0E = false;
    public boolean A0F = false;
    public boolean A0G = false;
    public boolean A0H = false;
    public int A0I = 0;
    public Drawable A0J;
    public MenuItem.OnActionExpandListener A0K;
    public CharSequence A0L;
    public CharSequence A0M;
    public CharSequence A0N;
    public CharSequence A0O;
    public final int A0P;
    public final int A0Q;
    public final int A0R;
    public final int A0S;

    public final boolean A00() {
        AnonymousClass0AE r0;
        if ((this.A05 & 8) == 0) {
            return false;
        }
        View view = this.A0A;
        if (view == null && (r0 = this.A0D) != null) {
            view = r0.A01(this);
            this.A0A = view;
        }
        if (view != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass08q
    public final AnonymousClass0AE A4b() {
        return this.A0D;
    }

    @Override // X.AnonymousClass08q
    public final AnonymousClass08q A7p(CharSequence charSequence) {
        this.A0L = charSequence;
        this.A0B.A0G(false);
        return this;
    }

    @Override // X.AnonymousClass08q
    public final AnonymousClass08q A8A(AnonymousClass0AE r3) {
        AnonymousClass0AE r1 = this.A0D;
        if (r1 != null) {
            r1.A01 = null;
            r1.A00 = null;
        }
        this.A0A = null;
        this.A0D = r3;
        this.A0B.A0G(true);
        AnonymousClass0AE r12 = this.A0D;
        if (r12 != null) {
            r12.A03(new C04260eX(this));
        }
        return this;
    }

    @Override // X.AnonymousClass08q
    public final AnonymousClass08q A8C(CharSequence charSequence) {
        this.A0O = charSequence;
        this.A0B.A0G(false);
        return this;
    }

    @Override // X.AnonymousClass08q
    public final boolean collapseActionView() {
        if ((this.A05 & 8) != 0) {
            if (this.A0A == null) {
                return true;
            }
            MenuItem.OnActionExpandListener onActionExpandListener = this.A0K;
            if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
                return this.A0B.A0M(this);
            }
        }
        return false;
    }

    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // X.AnonymousClass08q
    public final View getActionView() {
        View view = this.A0A;
        if (view != null) {
            return view;
        }
        AnonymousClass0AE r0 = this.A0D;
        if (r0 == null) {
            return null;
        }
        View A012 = r0.A01(this);
        this.A0A = A012;
        return A012;
    }

    @Override // X.AnonymousClass08q
    public final int getAlphabeticModifiers() {
        return this.A03;
    }

    public final char getAlphabeticShortcut() {
        return this.A00;
    }

    @Override // X.AnonymousClass08q
    public final CharSequence getContentDescription() {
        return this.A0L;
    }

    public final int getGroupId() {
        return this.A0S;
    }

    public final Drawable getIcon() {
        Drawable drawable = this.A0J;
        if (drawable == null) {
            int i = this.A0I;
            if (i == 0) {
                return null;
            }
            drawable = AnonymousClass17E.A00(this.A0B.A0M, i);
            this.A0I = 0;
            this.A0J = drawable;
        }
        if (drawable != null && this.A0H && (this.A0E || this.A0F)) {
            drawable = drawable.mutate();
            if (this.A0E) {
                drawable.setTintList(this.A07);
            }
            if (this.A0F) {
                drawable.setTintMode(this.A08);
            }
            this.A0H = false;
        }
        return drawable;
    }

    @Override // X.AnonymousClass08q
    public final ColorStateList getIconTintList() {
        return this.A07;
    }

    @Override // X.AnonymousClass08q
    public final PorterDuff.Mode getIconTintMode() {
        return this.A08;
    }

    public final Intent getIntent() {
        return this.A06;
    }

    @ViewDebug.CapturedViewProperty
    public final int getItemId() {
        return this.A0P;
    }

    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // X.AnonymousClass08q
    public final int getNumericModifiers() {
        return this.A04;
    }

    public final char getNumericShortcut() {
        return this.A01;
    }

    public final int getOrder() {
        return this.A0R;
    }

    public final SubMenu getSubMenu() {
        return this.A0C;
    }

    @ViewDebug.CapturedViewProperty
    public final CharSequence getTitle() {
        return this.A0M;
    }

    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.A0N;
        if (charSequence == null) {
            return this.A0M;
        }
        return charSequence;
    }

    @Override // X.AnonymousClass08q
    public final CharSequence getTooltipText() {
        return this.A0O;
    }

    public final boolean hasSubMenu() {
        if (this.A0C != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass08q
    public final boolean isActionViewExpanded() {
        return this.A0G;
    }

    public final boolean isCheckable() {
        if ((this.A02 & 1) != 1) {
            return false;
        }
        return true;
    }

    public final boolean isChecked() {
        if ((this.A02 & 2) == 2) {
            return true;
        }
        return false;
    }

    public final boolean isEnabled() {
        if ((this.A02 & 16) != 0) {
            return true;
        }
        return false;
    }

    public final boolean isVisible() {
        AnonymousClass0AE r0 = this.A0D;
        if (r0 == null || !r0.A07()) {
            if ((this.A02 & 8) == 0) {
                return true;
            }
            return false;
        } else if ((this.A02 & 8) != 0 || !this.A0D.A05()) {
            return false;
        } else {
            return true;
        }
    }

    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public final MenuItem setCheckable(boolean z) {
        int i = this.A02;
        int i2 = (z ? 1 : 0) | (i & -2);
        this.A02 = i2;
        if (i != i2) {
            this.A0B.A0G(false);
        }
        return this;
    }

    public final MenuItem setChecked(boolean z) {
        int i = this.A02;
        if ((i & 4) != 0) {
            C04280eZ r9 = this.A0B;
            int groupId = getGroupId();
            int size = r9.A07.size();
            r9.A09();
            for (int i2 = 0; i2 < size; i2++) {
                C04250eW r5 = r9.A07.get(i2);
                if (r5.getGroupId() == groupId && (r5.A02 & 4) != 0 && r5.isCheckable()) {
                    boolean z2 = false;
                    if (r5 == this) {
                        z2 = true;
                    }
                    int i3 = r5.A02;
                    int i4 = i3 & -3;
                    int i5 = 0;
                    if (z2) {
                        i5 = 2;
                    }
                    int i6 = i5 | i4;
                    r5.A02 = i6;
                    if (i3 != i6) {
                        r5.A0B.A0G(false);
                    }
                }
            }
            r9.A08();
            return this;
        }
        int i7 = i & -3;
        int i8 = 0;
        if (z) {
            i8 = 2;
        }
        int i9 = i8 | i7;
        this.A02 = i9;
        if (i != i9) {
            this.A0B.A0G(false);
        }
        return this;
    }

    public final MenuItem setEnabled(boolean z) {
        int i;
        if (z) {
            i = this.A02 | 16;
        } else {
            i = this.A02 & -17;
        }
        this.A02 = i;
        this.A0B.A0G(false);
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setIconTintList(@Nullable ColorStateList colorStateList) {
        this.A07 = colorStateList;
        this.A0E = true;
        this.A0H = true;
        this.A0B.A0G(false);
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.A08 = mode;
        this.A0F = true;
        this.A0H = true;
        this.A0B.A0G(false);
        return this;
    }

    @Override // X.AnonymousClass08q
    public final void setShowAsAction(int i) {
        int i2 = i & 3;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            this.A05 = i;
            C04280eZ r1 = this.A0B;
            r1.A0A = true;
            r1.A0G(true);
            return;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.A0N = charSequence;
        this.A0B.A0G(false);
        return this;
    }

    public final MenuItem setVisible(boolean z) {
        int i = this.A02;
        int i2 = i & -9;
        int i3 = 8;
        if (z) {
            i3 = 0;
        }
        int i4 = i3 | i2;
        this.A02 = i4;
        if (i != i4) {
            C04280eZ r1 = this.A0B;
            r1.A0B = true;
            r1.A0G(true);
        }
        return this;
    }

    public final String toString() {
        CharSequence charSequence = this.A0M;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public C04250eW(C04280eZ r3, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.A0B = r3;
        this.A0P = i2;
        this.A0S = i;
        this.A0R = i3;
        this.A0Q = i4;
        this.A0M = charSequence;
        this.A05 = i5;
    }

    @Override // X.AnonymousClass08q
    public final boolean expandActionView() {
        MenuItem.OnActionExpandListener onActionExpandListener;
        if (!A00() || ((onActionExpandListener = this.A0K) != null && !onActionExpandListener.onMenuItemActionExpand(this))) {
            return false;
        }
        return this.A0B.A0N(this);
    }

    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        A7p(charSequence);
        return this;
    }

    public final MenuItem setIntent(Intent intent) {
        this.A06 = intent;
        return this;
    }

    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.A0K = onActionExpandListener;
        return this;
    }

    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.A09 = onMenuItemClickListener;
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        A8C(charSequence);
        return this;
    }

    @Override // X.AnonymousClass08q, android.view.MenuItem
    public final MenuItem setActionView(int i) {
        int i2;
        Context context = this.A0B.A0M;
        View inflate = LayoutInflater.from(context).inflate(i, (ViewGroup) new LinearLayout(context), false);
        this.A0A = inflate;
        this.A0D = null;
        if (inflate != null && inflate.getId() == -1 && (i2 = this.A0P) > 0) {
            inflate.setId(i2);
        }
        C04280eZ r1 = this.A0B;
        r1.A0A = true;
        r1.A0G(true);
        return this;
    }

    @Override // X.AnonymousClass08q, android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setActionView(View view) {
        int i;
        this.A0A = view;
        this.A0D = null;
        if (view != null && view.getId() == -1 && (i = this.A0P) > 0) {
            view.setId(i);
        }
        C04280eZ r1 = this.A0B;
        r1.A0A = true;
        r1.A0G(true);
        return this;
    }

    public final MenuItem setAlphabeticShortcut(char c) {
        if (this.A00 != c) {
            this.A00 = Character.toLowerCase(c);
            this.A0B.A0G(false);
        }
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setAlphabeticShortcut(char c, int i) {
        if (this.A00 == c && this.A03 == i) {
            return this;
        }
        this.A00 = Character.toLowerCase(c);
        this.A03 = KeyEvent.normalizeMetaState(i);
        this.A0B.A0G(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i) {
        this.A0J = null;
        this.A0I = i;
        this.A0H = true;
        this.A0B.A0G(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.A0I = 0;
        this.A0J = drawable;
        this.A0H = true;
        this.A0B.A0G(false);
        return this;
    }

    public final MenuItem setNumericShortcut(char c) {
        if (this.A01 != c) {
            this.A01 = c;
            this.A0B.A0G(false);
        }
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setNumericShortcut(char c, int i) {
        if (this.A01 == c && this.A04 == i) {
            return this;
        }
        this.A01 = c;
        this.A04 = KeyEvent.normalizeMetaState(i);
        this.A0B.A0G(false);
        return this;
    }

    public final MenuItem setShortcut(char c, char c2) {
        this.A01 = c;
        this.A00 = Character.toLowerCase(c2);
        this.A0B.A0G(false);
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.A01 = c;
        this.A04 = KeyEvent.normalizeMetaState(i);
        this.A00 = Character.toLowerCase(c2);
        this.A03 = KeyEvent.normalizeMetaState(i2);
        this.A0B.A0G(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i) {
        setTitle(this.A0B.A0M.getString(i));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.A0M = charSequence;
        this.A0B.A0G(false);
        SubMenuC01890Mu r0 = this.A0C;
        if (r0 != null) {
            r0.setHeaderTitle(charSequence);
        }
        return this;
    }
}
