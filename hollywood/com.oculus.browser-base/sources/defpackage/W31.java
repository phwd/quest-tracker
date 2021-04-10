package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Constructor;

/* renamed from: W31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class W31 {
    public H2 A;
    public CharSequence B;
    public CharSequence C;
    public ColorStateList D = null;
    public PorterDuff.Mode E = null;
    public final /* synthetic */ X31 F;

    /* renamed from: a  reason: collision with root package name */
    public Menu f9126a;
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public int j;
    public CharSequence k;
    public CharSequence l;
    public int m;
    public char n;
    public int o;
    public char p;
    public int q;
    public int r;
    public boolean s;
    public boolean t;
    public boolean u;
    public int v;
    public int w;
    public String x;
    public String y;
    public String z;

    public W31(X31 x31, Menu menu) {
        this.F = x31;
        this.f9126a = menu;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = true;
        this.g = true;
    }

    public SubMenu a() {
        this.h = true;
        SubMenu addSubMenu = this.f9126a.addSubMenu(this.b, this.i, this.j, this.k);
        c(addSubMenu.getItem());
        return addSubMenu;
    }

    public final Object b(String str, Class[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, this.F.e.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception e2) {
            Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
            return null;
        }
    }

    public final void c(MenuItem menuItem) {
        boolean z2 = false;
        menuItem.setChecked(this.s).setVisible(this.t).setEnabled(this.u).setCheckable(this.r >= 1).setTitleCondensed(this.l).setIcon(this.m);
        int i2 = this.v;
        if (i2 >= 0) {
            menuItem.setShowAsAction(i2);
        }
        if (this.z != null) {
            if (!this.F.e.isRestricted()) {
                X31 x31 = this.F;
                if (x31.f == null) {
                    x31.f = x31.a(x31.e);
                }
                menuItem.setOnMenuItemClickListener(new V31(x31.f, this.z));
            } else {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
        }
        if (this.r >= 2) {
            if (menuItem instanceof C0817Ni0) {
                C0817Ni0 ni0 = (C0817Ni0) menuItem;
                ni0.x = (ni0.x & -5) | 4;
            } else if (menuItem instanceof MenuItemC1183Ti0) {
                MenuItemC1183Ti0 ti0 = (MenuItemC1183Ti0) menuItem;
                try {
                    if (ti0.d == null) {
                        ti0.d = ti0.c.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                    }
                    ti0.d.invoke(ti0.c, Boolean.TRUE);
                } catch (Exception e2) {
                    Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e2);
                }
            }
        }
        String str = this.x;
        if (str != null) {
            menuItem.setActionView((View) b(str, X31.f9191a, this.F.c));
            z2 = true;
        }
        int i3 = this.w;
        if (i3 > 0) {
            if (!z2) {
                menuItem.setActionView(i3);
            } else {
                Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            }
        }
        H2 h2 = this.A;
        if (h2 != null) {
            if (menuItem instanceof Y31) {
                ((Y31) menuItem).b(h2);
            } else {
                Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
            }
        }
        CharSequence charSequence = this.B;
        boolean z3 = menuItem instanceof Y31;
        if (z3) {
            ((Y31) menuItem).setContentDescription(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.C;
        if (z3) {
            ((Y31) menuItem).setTooltipText(charSequence2);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setTooltipText(charSequence2);
        }
        char c2 = this.n;
        int i4 = this.o;
        if (z3) {
            ((Y31) menuItem).setAlphabeticShortcut(c2, i4);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setAlphabeticShortcut(c2, i4);
        }
        char c3 = this.p;
        int i5 = this.q;
        if (z3) {
            ((Y31) menuItem).setNumericShortcut(c3, i5);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setNumericShortcut(c3, i5);
        }
        PorterDuff.Mode mode = this.E;
        if (mode != null) {
            if (z3) {
                ((Y31) menuItem).setIconTintMode(mode);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setIconTintMode(mode);
            }
        }
        ColorStateList colorStateList = this.D;
        if (colorStateList == null) {
            return;
        }
        if (z3) {
            ((Y31) menuItem).setIconTintList(colorStateList);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setIconTintList(colorStateList);
        }
    }
}
