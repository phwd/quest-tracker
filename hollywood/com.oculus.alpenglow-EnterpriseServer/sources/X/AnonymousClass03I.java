package X;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* renamed from: X.03I  reason: invalid class name */
public class AnonymousClass03I {
    public AnonymousClass0AE A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    public int A06;
    public Menu A07;
    public CharSequence A08;
    public boolean A09;
    public boolean A0A;
    public boolean A0B;
    public char A0C;
    public char A0D;
    public int A0E;
    public int A0F;
    public int A0G;
    public int A0H;
    public int A0I;
    public int A0J;
    public ColorStateList A0K = null;
    public PorterDuff.Mode A0L = null;
    public CharSequence A0M;
    public CharSequence A0N;
    public CharSequence A0O;
    public String A0P;
    public String A0Q;
    public boolean A0R;
    public boolean A0S;
    public boolean A0T;
    public final /* synthetic */ AnonymousClass03J A0U;

    private <T> T A00(String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, this.A0U.A00.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return (T) constructor.newInstance(objArr);
        } catch (Exception e) {
            Log.w("SupportMenuInflater", AnonymousClass006.A05("Cannot instantiate class: ", str), e);
            return null;
        }
    }

    public AnonymousClass03I(AnonymousClass03J r2, Menu menu) {
        this.A0U = r2;
        this.A07 = menu;
        this.A03 = 0;
        this.A01 = 0;
        this.A04 = 0;
        this.A02 = 0;
        this.A0A = true;
        this.A09 = true;
    }

    public static void A01(AnonymousClass03I r6, MenuItem menuItem) {
        MenuItem enabled = menuItem.setChecked(r6.A0R).setVisible(r6.A0T).setEnabled(r6.A0S);
        boolean z = false;
        boolean z2 = false;
        if (r6.A0G >= 1) {
            z2 = true;
        }
        enabled.setCheckable(z2).setTitleCondensed(r6.A0N).setIcon(r6.A0H);
        int i = r6.A0J;
        if (i >= 0) {
            menuItem.setShowAsAction(i);
        }
        if (r6.A0Q != null) {
            AnonymousClass03J r2 = r6.A0U;
            if (!r2.A00.isRestricted()) {
                Object obj = r2.A01;
                if (obj == null) {
                    obj = r2.A00;
                    if (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
                        obj = ((ContextWrapper) obj).getBaseContext();
                        if (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
                            obj = ((ContextWrapper) obj).getBaseContext();
                            if (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
                                obj = AnonymousClass03J.A00(r2, ((ContextWrapper) obj).getBaseContext());
                            }
                        }
                    }
                    r2.A01 = obj;
                }
                menuItem.setOnMenuItemClickListener(new AnonymousClass03H(obj, r6.A0Q));
            } else {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
        }
        if (r6.A0G >= 2) {
            if (menuItem instanceof C04250eW) {
                C04250eW r22 = (C04250eW) menuItem;
                r22.A02 = 4 | (r22.A02 & -5);
            } else if (menuItem instanceof MenuItemC04230eT) {
                MenuItemC04230eT r4 = (MenuItemC04230eT) menuItem;
                try {
                    Method method = r4.A00;
                    if (method == null) {
                        method = r4.A01.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                        r4.A00 = method;
                    }
                    method.invoke(r4.A01, true);
                } catch (Exception e) {
                    Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e);
                }
            }
        }
        String str = r6.A0P;
        if (str != null) {
            menuItem.setActionView((View) r6.A00(str, AnonymousClass03J.A05, r6.A0U.A03));
            z = true;
        }
        int i2 = r6.A0E;
        if (i2 > 0) {
            if (!z) {
                menuItem.setActionView(i2);
            } else {
                Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            }
        }
        AnonymousClass0AE r1 = r6.A00;
        if (r1 != null) {
            if (menuItem instanceof AnonymousClass08q) {
                ((AnonymousClass08q) menuItem).A8A(r1);
            } else {
                Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
            }
        }
        CharSequence charSequence = r6.A0M;
        boolean z3 = menuItem instanceof AnonymousClass08q;
        if (z3) {
            ((AnonymousClass08q) menuItem).A7p(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setContentDescription(charSequence);
        }
        CharSequence charSequence2 = r6.A0O;
        if (z3) {
            ((AnonymousClass08q) menuItem).A8C(charSequence2);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setTooltipText(charSequence2);
        }
        char c = r6.A0C;
        int i3 = r6.A0F;
        if (z3) {
            ((AnonymousClass08q) menuItem).setAlphabeticShortcut(c, i3);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setAlphabeticShortcut(c, i3);
        }
        char c2 = r6.A0D;
        int i4 = r6.A0I;
        if (z3) {
            ((AnonymousClass08q) menuItem).setNumericShortcut(c2, i4);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setNumericShortcut(c2, i4);
        }
        PorterDuff.Mode mode = r6.A0L;
        if (mode != null) {
            if (z3) {
                ((AnonymousClass08q) menuItem).setIconTintMode(mode);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setIconTintMode(mode);
            }
        }
        ColorStateList colorStateList = r6.A0K;
        if (colorStateList == null) {
            return;
        }
        if (z3) {
            ((AnonymousClass08q) menuItem).setIconTintList(colorStateList);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setIconTintList(colorStateList);
        }
    }

    public final void A02(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.A0U.A00.obtainStyledAttributes(attributeSet, AnonymousClass18N.A0F);
        this.A03 = obtainStyledAttributes.getResourceId(1, 0);
        this.A01 = obtainStyledAttributes.getInt(3, 0);
        this.A04 = obtainStyledAttributes.getInt(4, 0);
        this.A02 = obtainStyledAttributes.getInt(5, 0);
        this.A0A = obtainStyledAttributes.getBoolean(2, true);
        this.A09 = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v27, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0118  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03(android.util.AttributeSet r10) {
        /*
        // Method dump skipped, instructions count: 309
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass03I.A03(android.util.AttributeSet):void");
    }
}
