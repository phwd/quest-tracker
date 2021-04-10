package X;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* renamed from: X.1sL  reason: invalid class name */
public class AnonymousClass1sL {
    public char A00;
    public char A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    public int A06;
    public int A07;
    public int A08;
    public int A09;
    public int A0A;
    public int A0B;
    public int A0C;
    public int A0D;
    public ColorStateList A0E = null;
    public PorterDuff.Mode A0F = null;
    public Menu A0G;
    public AbstractC002906y A0H;
    public CharSequence A0I;
    public CharSequence A0J;
    public CharSequence A0K;
    public CharSequence A0L;
    public String A0M;
    public String A0N;
    public boolean A0O;
    public boolean A0P;
    public boolean A0Q;
    public boolean A0R;
    public boolean A0S;
    public boolean A0T;
    public final /* synthetic */ C11571sK A0U;

    /* JADX WARN: Incorrect args count in method signature: <T:Ljava/lang/Object;>(Ljava/lang/String;[Ljava/lang/Class<*>;[Ljava/lang/Object;)TT; */
    public static Object A00(AnonymousClass1sL r2, String str, Class[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, r2.A0U.A00.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception e) {
            Log.w("SupportMenuInflater", AnonymousClass006.A07("Cannot instantiate class: ", str), e);
            return null;
        }
    }

    public AnonymousClass1sL(C11571sK r2, Menu menu) {
        this.A0U = r2;
        this.A0G = menu;
        this.A04 = 0;
        this.A02 = 0;
        this.A05 = 0;
        this.A03 = 0;
        this.A0P = true;
        this.A0O = true;
    }

    public static void A01(AnonymousClass1sL r6, MenuItem menuItem) {
        MenuItem enabled = menuItem.setChecked(r6.A0R).setVisible(r6.A0T).setEnabled(r6.A0S);
        boolean z = false;
        boolean z2 = false;
        if (r6.A09 >= 1) {
            z2 = true;
        }
        enabled.setCheckable(z2).setTitleCondensed(r6.A0K).setIcon(r6.A0A);
        int i = r6.A0D;
        if (i >= 0) {
            menuItem.setShowAsAction(i);
        }
        if (r6.A0N != null) {
            C11571sK r2 = r6.A0U;
            if (!r2.A00.isRestricted()) {
                Object obj = r2.A01;
                if (obj == null) {
                    obj = r2.A00;
                    if (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
                        obj = ((ContextWrapper) obj).getBaseContext();
                        if (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
                            obj = ((ContextWrapper) obj).getBaseContext();
                            if (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) {
                                obj = C11571sK.A00(r2, ((ContextWrapper) obj).getBaseContext());
                            }
                        }
                    }
                    r2.A01 = obj;
                }
                menuItem.setOnMenuItemClickListener(new AnonymousClass1Ju(obj, r6.A0N));
            } else {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
        }
        if (r6.A09 >= 2) {
            if (menuItem instanceof C11601sP) {
                C11601sP r22 = (C11601sP) menuItem;
                r22.A02 = 4 | (r22.A02 & -5);
            } else if (menuItem instanceof MenuItemC11611sU) {
                MenuItemC11611sU r4 = (MenuItemC11611sU) menuItem;
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
        String str = r6.A0M;
        if (str != null) {
            menuItem.setActionView((View) A00(r6, str, C11571sK.A04, r6.A0U.A02));
            z = true;
        }
        int i2 = r6.A06;
        if (i2 > 0) {
            if (!z) {
                menuItem.setActionView(i2);
            } else {
                Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            }
        }
        AbstractC002906y r1 = r6.A0H;
        if (r1 != null) {
            if (menuItem instanceof AnonymousClass05Y) {
                ((AnonymousClass05Y) menuItem).AAD(r1);
            } else {
                Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
            }
        }
        CharSequence charSequence = r6.A0I;
        boolean z3 = menuItem instanceof AnonymousClass05Y;
        if (z3) {
            ((AnonymousClass05Y) menuItem).A9m(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setContentDescription(charSequence);
        }
        CharSequence charSequence2 = r6.A0L;
        if (z3) {
            ((AnonymousClass05Y) menuItem).AAE(charSequence2);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setTooltipText(charSequence2);
        }
        char c = r6.A00;
        int i3 = r6.A07;
        if (z3) {
            ((AnonymousClass05Y) menuItem).setAlphabeticShortcut(c, i3);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setAlphabeticShortcut(c, i3);
        }
        char c2 = r6.A01;
        int i4 = r6.A0C;
        if (z3) {
            ((AnonymousClass05Y) menuItem).setNumericShortcut(c2, i4);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setNumericShortcut(c2, i4);
        }
        PorterDuff.Mode mode = r6.A0F;
        if (mode != null) {
            if (z3) {
                ((AnonymousClass05Y) menuItem).setIconTintMode(mode);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setIconTintMode(mode);
            }
        }
        ColorStateList colorStateList = r6.A0E;
        if (colorStateList == null) {
            return;
        }
        if (z3) {
            ((AnonymousClass05Y) menuItem).setIconTintList(colorStateList);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setIconTintList(colorStateList);
        }
    }
}
