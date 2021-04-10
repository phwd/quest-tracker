package X;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0eg  reason: invalid class name */
public final class AnonymousClass0eg implements AnonymousClass08q {
    public char A00;
    public char A01;
    public int A02 = 16;
    public int A03 = 4096;
    public int A04 = 4096;
    public Context A05;
    public Intent A06;
    public ColorStateList A07 = null;
    public PorterDuff.Mode A08 = null;
    public Drawable A09;
    public MenuItem.OnMenuItemClickListener A0A;
    public CharSequence A0B;
    public CharSequence A0C;
    public CharSequence A0D;
    public CharSequence A0E;
    public boolean A0F = false;
    public boolean A0G = false;
    public final int A0H;

    @Override // X.AnonymousClass08q
    public final AnonymousClass0AE A4b() {
        return null;
    }

    @Override // X.AnonymousClass08q
    public final boolean collapseActionView() {
        return false;
    }

    @Override // X.AnonymousClass08q
    public final boolean expandActionView() {
        return false;
    }

    @Override // X.AnonymousClass08q
    public final View getActionView() {
        return null;
    }

    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public final SubMenu getSubMenu() {
        return null;
    }

    public final boolean hasSubMenu() {
        return false;
    }

    @Override // X.AnonymousClass08q
    public final boolean isActionViewExpanded() {
        return false;
    }

    @Override // X.AnonymousClass08q
    public final void setShowAsAction(int i) {
    }

    private void A00() {
        Drawable drawable = this.A09;
        if (drawable == null) {
            return;
        }
        if (this.A0F || this.A0G) {
            this.A09 = drawable;
            Drawable mutate = drawable.mutate();
            this.A09 = mutate;
            if (this.A0F) {
                mutate.setTintList(this.A07);
            }
            if (this.A0G) {
                this.A09.setTintMode(this.A08);
            }
        }
    }

    @Override // X.AnonymousClass08q
    public final AnonymousClass08q A8A(AnonymousClass0AE r2) {
        throw new UnsupportedOperationException();
    }

    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
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
        return this.A0B;
    }

    public final int getGroupId() {
        return 0;
    }

    public final Drawable getIcon() {
        return this.A09;
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

    public final int getItemId() {
        return this.A0H;
    }

    @Override // X.AnonymousClass08q
    public final int getNumericModifiers() {
        return this.A04;
    }

    public final char getNumericShortcut() {
        return this.A01;
    }

    public final int getOrder() {
        return 0;
    }

    public final CharSequence getTitle() {
        return this.A0C;
    }

    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.A0D;
        if (charSequence == null) {
            return this.A0C;
        }
        return charSequence;
    }

    @Override // X.AnonymousClass08q
    public final CharSequence getTooltipText() {
        return this.A0E;
    }

    public final boolean isCheckable() {
        if ((this.A02 & 1) == 0) {
            return false;
        }
        return true;
    }

    public final boolean isChecked() {
        if ((this.A02 & 2) != 0) {
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
        if ((this.A02 & 8) == 0) {
            return true;
        }
        return false;
    }

    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setCheckable(boolean z) {
        this.A02 = (z ? 1 : 0) | (this.A02 & -2);
        return this;
    }

    public final MenuItem setChecked(boolean z) {
        int i = this.A02 & -3;
        int i2 = 0;
        if (z) {
            i2 = 2;
        }
        this.A02 = i2 | i;
        return this;
    }

    public final MenuItem setEnabled(boolean z) {
        int i = this.A02 & -17;
        int i2 = 0;
        if (z) {
            i2 = 16;
        }
        this.A02 = i2 | i;
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setIconTintList(@Nullable ColorStateList colorStateList) {
        this.A07 = colorStateList;
        this.A0F = true;
        A00();
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.A08 = mode;
        this.A0G = true;
        A00();
        return this;
    }

    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setVisible(boolean z) {
        int i = 8;
        int i2 = this.A02 & 8;
        if (z) {
            i = 0;
        }
        this.A02 = i2 | i;
        return this;
    }

    public AnonymousClass0eg(Context context, CharSequence charSequence) {
        this.A05 = context;
        this.A0H = 16908332;
        this.A0C = charSequence;
    }

    @Override // X.AnonymousClass08q
    public final AnonymousClass08q A7p(CharSequence charSequence) {
        this.A0B = charSequence;
        return this;
    }

    @Override // X.AnonymousClass08q
    public final AnonymousClass08q A8C(CharSequence charSequence) {
        this.A0E = charSequence;
        return this;
    }

    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        A7p(charSequence);
        return this;
    }

    public final MenuItem setIntent(Intent intent) {
        this.A06 = intent;
        return this;
    }

    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.A0A = onMenuItemClickListener;
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setShowAsActionFlags(int i) {
        return this;
    }

    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.A0D = charSequence;
        return this;
    }

    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        A8C(charSequence);
        return this;
    }

    @Override // X.AnonymousClass08q, android.view.MenuItem
    public final MenuItem setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AnonymousClass08q, android.view.MenuItem
    public final MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public final MenuItem setAlphabeticShortcut(char c) {
        this.A00 = Character.toLowerCase(c);
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setAlphabeticShortcut(char c, int i) {
        this.A00 = Character.toLowerCase(c);
        this.A03 = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i) {
        this.A09 = this.A05.getDrawable(i);
        A00();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.A09 = drawable;
        A00();
        return this;
    }

    public final MenuItem setNumericShortcut(char c) {
        this.A01 = c;
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setNumericShortcut(char c, int i) {
        this.A01 = c;
        this.A04 = KeyEvent.normalizeMetaState(i);
        return this;
    }

    public final MenuItem setShortcut(char c, char c2) {
        this.A01 = c;
        this.A00 = Character.toLowerCase(c2);
        return this;
    }

    @Override // X.AnonymousClass08q
    public final MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.A01 = c;
        this.A04 = KeyEvent.normalizeMetaState(i);
        this.A00 = Character.toLowerCase(c2);
        this.A03 = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i) {
        this.A0C = this.A05.getResources().getString(i);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.A0C = charSequence;
        return this;
    }
}
