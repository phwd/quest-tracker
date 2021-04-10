package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: r31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SubMenuC4510r31 extends C4616ri0 implements SubMenu {
    public C0817Ni0 A;
    public C4616ri0 z;

    public SubMenuC4510r31(Context context, C4616ri0 ri0, C0817Ni0 ni0) {
        super(context);
        this.z = ri0;
        this.A = ni0;
    }

    @Override // defpackage.C4616ri0
    public boolean d(C0817Ni0 ni0) {
        return this.z.d(ni0);
    }

    @Override // defpackage.C4616ri0
    public boolean e(C4616ri0 ri0, MenuItem menuItem) {
        return super.e(ri0, menuItem) || this.z.e(ri0, menuItem);
    }

    @Override // defpackage.C4616ri0
    public boolean f(C0817Ni0 ni0) {
        return this.z.f(ni0);
    }

    public MenuItem getItem() {
        return this.A;
    }

    @Override // defpackage.C4616ri0
    public String j() {
        C0817Ni0 ni0 = this.A;
        int i = ni0 != null ? ni0.f8568a : 0;
        if (i == 0) {
            return null;
        }
        return "android:menu:actionviewstates" + ":" + i;
    }

    @Override // defpackage.C4616ri0
    public C4616ri0 k() {
        return this.z.k();
    }

    @Override // defpackage.C4616ri0
    public boolean m() {
        return this.z.m();
    }

    @Override // defpackage.C4616ri0
    public boolean n() {
        return this.z.n();
    }

    @Override // defpackage.C4616ri0
    public boolean o() {
        return this.z.o();
    }

    @Override // defpackage.C4616ri0
    public void setGroupDividerEnabled(boolean z2) {
        this.z.setGroupDividerEnabled(z2);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        w(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        w(0, charSequence, 0, null, null);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        w(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.A.setIcon(drawable);
        return this;
    }

    @Override // defpackage.C4616ri0
    public void setQwertyMode(boolean z2) {
        this.z.setQwertyMode(z2);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        w(0, null, i, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        w(i, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        this.A.setIcon(i);
        return this;
    }
}
