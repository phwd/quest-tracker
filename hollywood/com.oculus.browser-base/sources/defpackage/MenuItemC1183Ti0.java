package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Method;

/* renamed from: Ti0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MenuItemC1183Ti0 extends AbstractC5119ug implements MenuItem {
    public final Y31 c;
    public Method d;

    public MenuItemC1183Ti0(Context context, Y31 y31) {
        super(context);
        if (y31 != null) {
            this.c = y31;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    public boolean collapseActionView() {
        return this.c.collapseActionView();
    }

    public boolean expandActionView() {
        return this.c.expandActionView();
    }

    public ActionProvider getActionProvider() {
        H2 a2 = this.c.a();
        if (a2 instanceof AbstractC0878Oi0) {
            return ((AbstractC0878Oi0) a2).c;
        }
        return null;
    }

    public View getActionView() {
        View actionView = this.c.getActionView();
        return actionView instanceof C1000Qi0 ? (View) ((C1000Qi0) actionView).F : actionView;
    }

    public int getAlphabeticModifiers() {
        return this.c.getAlphabeticModifiers();
    }

    public char getAlphabeticShortcut() {
        return this.c.getAlphabeticShortcut();
    }

    public CharSequence getContentDescription() {
        return this.c.getContentDescription();
    }

    public int getGroupId() {
        return this.c.getGroupId();
    }

    public Drawable getIcon() {
        return this.c.getIcon();
    }

    public ColorStateList getIconTintList() {
        return this.c.getIconTintList();
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.c.getIconTintMode();
    }

    public Intent getIntent() {
        return this.c.getIntent();
    }

    public int getItemId() {
        return this.c.getItemId();
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.c.getMenuInfo();
    }

    public int getNumericModifiers() {
        return this.c.getNumericModifiers();
    }

    public char getNumericShortcut() {
        return this.c.getNumericShortcut();
    }

    public int getOrder() {
        return this.c.getOrder();
    }

    public SubMenu getSubMenu() {
        return this.c.getSubMenu();
    }

    public CharSequence getTitle() {
        return this.c.getTitle();
    }

    public CharSequence getTitleCondensed() {
        return this.c.getTitleCondensed();
    }

    public CharSequence getTooltipText() {
        return this.c.getTooltipText();
    }

    public boolean hasSubMenu() {
        return this.c.hasSubMenu();
    }

    public boolean isActionViewExpanded() {
        return this.c.isActionViewExpanded();
    }

    public boolean isCheckable() {
        return this.c.isCheckable();
    }

    public boolean isChecked() {
        return this.c.isChecked();
    }

    public boolean isEnabled() {
        return this.c.isEnabled();
    }

    public boolean isVisible() {
        return this.c.isVisible();
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        ActionProvider$VisibilityListenerC0939Pi0 pi0 = new ActionProvider$VisibilityListenerC0939Pi0(this, this.f11428a, actionProvider);
        Y31 y31 = this.c;
        if (actionProvider == null) {
            pi0 = null;
        }
        y31.b(pi0);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new C1000Qi0(view);
        }
        this.c.setActionView(view);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c2) {
        this.c.setAlphabeticShortcut(c2);
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        this.c.setCheckable(z);
        return this;
    }

    public MenuItem setChecked(boolean z) {
        this.c.setChecked(z);
        return this;
    }

    public MenuItem setContentDescription(CharSequence charSequence) {
        this.c.setContentDescription(charSequence);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.c.setEnabled(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.c.setIcon(drawable);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.c.setIconTintList(colorStateList);
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.c.setIconTintMode(mode);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.c.setIntent(intent);
        return this;
    }

    public MenuItem setNumericShortcut(char c2) {
        this.c.setNumericShortcut(c2);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.c.setOnActionExpandListener(onActionExpandListener != null ? new MenuItem$OnActionExpandListenerC1061Ri0(this, onActionExpandListener) : null);
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.c.setOnMenuItemClickListener(onMenuItemClickListener != null ? new MenuItem$OnMenuItemClickListenerC1122Si0(this, onMenuItemClickListener) : null);
        return this;
    }

    public MenuItem setShortcut(char c2, char c3) {
        this.c.setShortcut(c2, c3);
        return this;
    }

    public void setShowAsAction(int i) {
        this.c.setShowAsAction(i);
    }

    public MenuItem setShowAsActionFlags(int i) {
        this.c.setShowAsActionFlags(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.c.setTitle(charSequence);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.c.setTitleCondensed(charSequence);
        return this;
    }

    public MenuItem setTooltipText(CharSequence charSequence) {
        this.c.setTooltipText(charSequence);
        return this;
    }

    public MenuItem setVisible(boolean z) {
        return this.c.setVisible(z);
    }

    public MenuItem setAlphabeticShortcut(char c2, int i) {
        this.c.setAlphabeticShortcut(c2, i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        this.c.setIcon(i);
        return this;
    }

    public MenuItem setNumericShortcut(char c2, int i) {
        this.c.setNumericShortcut(c2, i);
        return this;
    }

    public MenuItem setShortcut(char c2, char c3, int i, int i2) {
        this.c.setShortcut(c2, c3, i, i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        this.c.setTitle(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i) {
        this.c.setActionView(i);
        View actionView = this.c.getActionView();
        if (actionView instanceof CollapsibleActionView) {
            this.c.setActionView(new C1000Qi0(actionView));
        }
        return this;
    }
}
