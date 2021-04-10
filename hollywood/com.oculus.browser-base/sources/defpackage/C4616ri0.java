package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: ri0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4616ri0 implements U31 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f11215a = {1, 4, 5, 3, 2, 0};
    public final Context b;
    public final Resources c;
    public boolean d;
    public boolean e;
    public AbstractC4275pi0 f;
    public ArrayList g;
    public ArrayList h;
    public boolean i;
    public ArrayList j;
    public ArrayList k;
    public boolean l;
    public int m = 0;
    public CharSequence n;
    public Drawable o;
    public View p;
    public boolean q = false;
    public boolean r = false;
    public boolean s = false;
    public boolean t = false;
    public ArrayList u = new ArrayList();
    public CopyOnWriteArrayList v = new CopyOnWriteArrayList();
    public C0817Ni0 w;
    public boolean x = false;
    public boolean y;

    public C4616ri0(Context context) {
        boolean z;
        boolean z2 = false;
        this.b = context;
        Resources resources = context.getResources();
        this.c = resources;
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.i = true;
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = true;
        if (resources.getConfiguration().keyboard != 1) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            Method method = AbstractC2091cu1.f9729a;
            if (Build.VERSION.SDK_INT >= 28) {
                z = viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
            } else {
                Resources resources2 = context.getResources();
                int identifier = resources2.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
                z = identifier != 0 && resources2.getBoolean(identifier);
            }
            if (z) {
                z2 = true;
            }
        }
        this.e = z2;
    }

    public MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        int i5;
        int i6 = (-65536 & i4) >> 16;
        if (i6 >= 0) {
            int[] iArr = f11215a;
            if (i6 < iArr.length) {
                int i7 = (iArr[i6] << 16) | (65535 & i4);
                C0817Ni0 ni0 = new C0817Ni0(this, i2, i3, i4, i7, charSequence, this.m);
                ArrayList arrayList = this.g;
                int size = arrayList.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        if (((C0817Ni0) arrayList.get(size)).d <= i7) {
                            i5 = size + 1;
                            break;
                        }
                    } else {
                        i5 = 0;
                        break;
                    }
                }
                arrayList.add(i5, ni0);
                p(true);
                return ni0;
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    public int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        int i6;
        PackageManager packageManager = this.b.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i7 = 0; i7 < size; i7++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i7);
            int i8 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i8 < 0 ? intent : intentArr[i8]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            C0817Ni0 ni0 = (C0817Ni0) a(i2, i3, i4, resolveInfo.loadLabel(packageManager));
            ni0.setIcon(resolveInfo.loadIcon(packageManager));
            ni0.setIntent(intent2);
            if (menuItemArr != null && (i6 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i6] = ni0;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public void b(AbstractC2057cj0 cj0, Context context) {
        this.v.add(new WeakReference(cj0));
        cj0.g(context, this);
        this.l = true;
    }

    public final void c(boolean z) {
        if (!this.t) {
            this.t = true;
            Iterator it = this.v.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                AbstractC2057cj0 cj0 = (AbstractC2057cj0) weakReference.get();
                if (cj0 == null) {
                    this.v.remove(weakReference);
                } else {
                    cj0.a(this, z);
                }
            }
            this.t = false;
        }
    }

    public void clear() {
        C0817Ni0 ni0 = this.w;
        if (ni0 != null) {
            d(ni0);
        }
        this.g.clear();
        p(true);
    }

    public void clearHeader() {
        this.o = null;
        this.n = null;
        this.p = null;
        p(false);
    }

    public void close() {
        c(true);
    }

    public boolean d(C0817Ni0 ni0) {
        boolean z = false;
        if (!this.v.isEmpty() && this.w == ni0) {
            y();
            Iterator it = this.v.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                AbstractC2057cj0 cj0 = (AbstractC2057cj0) weakReference.get();
                if (cj0 == null) {
                    this.v.remove(weakReference);
                } else {
                    z = cj0.k(this, ni0);
                    if (z) {
                        break;
                    }
                }
            }
            x();
            if (z) {
                this.w = null;
            }
        }
        return z;
    }

    public boolean e(C4616ri0 ri0, MenuItem menuItem) {
        AbstractC4275pi0 pi0 = this.f;
        return pi0 != null && pi0.a(ri0, menuItem);
    }

    public boolean f(C0817Ni0 ni0) {
        boolean z = false;
        if (this.v.isEmpty()) {
            return false;
        }
        y();
        Iterator it = this.v.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            AbstractC2057cj0 cj0 = (AbstractC2057cj0) weakReference.get();
            if (cj0 == null) {
                this.v.remove(weakReference);
            } else {
                z = cj0.c(this, ni0);
                if (z) {
                    break;
                }
            }
        }
        x();
        if (z) {
            this.w = ni0;
        }
        return z;
    }

    public MenuItem findItem(int i2) {
        MenuItem findItem;
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            C0817Ni0 ni0 = (C0817Ni0) this.g.get(i3);
            if (ni0.f8568a == i2) {
                return ni0;
            }
            if (ni0.hasSubMenu() && (findItem = ni0.o.findItem(i2)) != null) {
                return findItem;
            }
        }
        return null;
    }

    public C0817Ni0 g(int i2, KeyEvent keyEvent) {
        char c2;
        ArrayList arrayList = this.u;
        arrayList.clear();
        h(arrayList, i2, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (C0817Ni0) arrayList.get(0);
        }
        boolean n2 = n();
        for (int i3 = 0; i3 < size; i3++) {
            C0817Ni0 ni0 = (C0817Ni0) arrayList.get(i3);
            if (n2) {
                c2 = ni0.j;
            } else {
                c2 = ni0.h;
            }
            char[] cArr = keyData.meta;
            if ((c2 == cArr[0] && (metaState & 2) == 0) || ((c2 == cArr[2] && (metaState & 2) != 0) || (n2 && c2 == '\b' && i2 == 67))) {
                return ni0;
            }
        }
        return null;
    }

    public MenuItem getItem(int i2) {
        return (MenuItem) this.g.get(i2);
    }

    public void h(List list, int i2, KeyEvent keyEvent) {
        char c2;
        int i3;
        boolean n2 = n();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i2 == 67) {
            int size = this.g.size();
            for (int i4 = 0; i4 < size; i4++) {
                C0817Ni0 ni0 = (C0817Ni0) this.g.get(i4);
                if (ni0.hasSubMenu()) {
                    ni0.o.h(list, i2, keyEvent);
                }
                if (n2) {
                    c2 = ni0.j;
                } else {
                    c2 = ni0.h;
                }
                if (n2) {
                    i3 = ni0.k;
                } else {
                    i3 = ni0.i;
                }
                if (((modifiers & 69647) == (i3 & 69647)) && c2 != 0) {
                    char[] cArr = keyData.meta;
                    if ((c2 == cArr[0] || c2 == cArr[2] || (n2 && c2 == '\b' && i2 == 67)) && ni0.isEnabled()) {
                        list.add(ni0);
                    }
                }
            }
        }
    }

    public boolean hasVisibleItems() {
        if (this.y) {
            return true;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((C0817Ni0) this.g.get(i2)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public void i() {
        ArrayList l2 = l();
        if (this.l) {
            Iterator it = this.v.iterator();
            boolean z = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                AbstractC2057cj0 cj0 = (AbstractC2057cj0) weakReference.get();
                if (cj0 == null) {
                    this.v.remove(weakReference);
                } else {
                    z |= cj0.j();
                }
            }
            if (z) {
                this.j.clear();
                this.k.clear();
                int size = l2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    C0817Ni0 ni0 = (C0817Ni0) l2.get(i2);
                    if (ni0.g()) {
                        this.j.add(ni0);
                    } else {
                        this.k.add(ni0);
                    }
                }
            } else {
                this.j.clear();
                this.k.clear();
                this.k.addAll(l());
            }
            this.l = false;
        }
    }

    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return g(i2, keyEvent) != null;
    }

    public String j() {
        return "android:menu:actionviewstates";
    }

    public C4616ri0 k() {
        return this;
    }

    public ArrayList l() {
        if (!this.i) {
            return this.h;
        }
        this.h.clear();
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0817Ni0 ni0 = (C0817Ni0) this.g.get(i2);
            if (ni0.isVisible()) {
                this.h.add(ni0);
            }
        }
        this.i = false;
        this.l = true;
        return this.h;
    }

    public boolean m() {
        return this.x;
    }

    public boolean n() {
        return this.d;
    }

    public boolean o() {
        return this.e;
    }

    public void p(boolean z) {
        if (!this.q) {
            if (z) {
                this.i = true;
                this.l = true;
            }
            if (!this.v.isEmpty()) {
                y();
                Iterator it = this.v.iterator();
                while (it.hasNext()) {
                    WeakReference weakReference = (WeakReference) it.next();
                    AbstractC2057cj0 cj0 = (AbstractC2057cj0) weakReference.get();
                    if (cj0 == null) {
                        this.v.remove(weakReference);
                    } else {
                        cj0.h(z);
                    }
                }
                x();
                return;
            }
            return;
        }
        this.r = true;
        if (z) {
            this.s = true;
        }
    }

    public boolean performIdentifierAction(int i2, int i3) {
        return q(findItem(i2), i3);
    }

    public boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        C0817Ni0 g2 = g(i2, keyEvent);
        boolean r2 = g2 != null ? r(g2, null, i3) : false;
        if ((i3 & 2) != 0) {
            c(true);
        }
        return r2;
    }

    public boolean q(MenuItem menuItem, int i2) {
        return r(menuItem, null, i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean r(android.view.MenuItem r7, defpackage.AbstractC2057cj0 r8, int r9) {
        /*
        // Method dump skipped, instructions count: 211
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4616ri0.r(android.view.MenuItem, cj0, int):boolean");
    }

    public void removeGroup(int i2) {
        int size = size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                i3 = -1;
                break;
            } else if (((C0817Ni0) this.g.get(i3)).b == i2) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 >= 0) {
            int size2 = this.g.size() - i3;
            int i4 = 0;
            while (true) {
                int i5 = i4 + 1;
                if (i4 >= size2 || ((C0817Ni0) this.g.get(i3)).b != i2) {
                    p(true);
                } else {
                    s(i3, false);
                    i4 = i5;
                }
            }
            p(true);
        }
    }

    public void removeItem(int i2) {
        int size = size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                i3 = -1;
                break;
            } else if (((C0817Ni0) this.g.get(i3)).f8568a == i2) {
                break;
            } else {
                i3++;
            }
        }
        s(i3, true);
    }

    public final void s(int i2, boolean z) {
        if (i2 >= 0 && i2 < this.g.size()) {
            this.g.remove(i2);
            if (z) {
                p(true);
            }
        }
    }

    public void setGroupCheckable(int i2, boolean z, boolean z2) {
        int size = this.g.size();
        for (int i3 = 0; i3 < size; i3++) {
            C0817Ni0 ni0 = (C0817Ni0) this.g.get(i3);
            if (ni0.b == i2) {
                ni0.x = (ni0.x & -5) | (z2 ? 4 : 0);
                ni0.setCheckable(z);
            }
        }
    }

    public void setGroupDividerEnabled(boolean z) {
        this.x = z;
    }

    public void setGroupEnabled(int i2, boolean z) {
        int size = this.g.size();
        for (int i3 = 0; i3 < size; i3++) {
            C0817Ni0 ni0 = (C0817Ni0) this.g.get(i3);
            if (ni0.b == i2) {
                ni0.setEnabled(z);
            }
        }
    }

    public void setGroupVisible(int i2, boolean z) {
        int size = this.g.size();
        boolean z2 = false;
        for (int i3 = 0; i3 < size; i3++) {
            C0817Ni0 ni0 = (C0817Ni0) this.g.get(i3);
            if (ni0.b == i2 && ni0.k(z)) {
                z2 = true;
            }
        }
        if (z2) {
            p(true);
        }
    }

    public void setQwertyMode(boolean z) {
        this.d = z;
        p(false);
    }

    public int size() {
        return this.g.size();
    }

    public void t(AbstractC2057cj0 cj0) {
        Iterator it = this.v.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            AbstractC2057cj0 cj02 = (AbstractC2057cj0) weakReference.get();
            if (cj02 == null || cj02 == cj0) {
                this.v.remove(weakReference);
            }
        }
    }

    public void u(Bundle bundle) {
        MenuItem findItem;
        if (bundle != null) {
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(j());
            int size = size();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItem item = getItem(i2);
                View actionView = item.getActionView();
                if (!(actionView == null || actionView.getId() == -1)) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((SubMenuC4510r31) item.getSubMenu()).u(bundle);
                }
            }
            int i3 = bundle.getInt("android:menu:expandedactionview");
            if (i3 > 0 && (findItem = findItem(i3)) != null) {
                findItem.expandActionView();
            }
        }
    }

    public void v(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((SubMenuC4510r31) item.getSubMenu()).v(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(j(), sparseArray);
        }
    }

    public final void w(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        Resources resources = this.c;
        if (view != null) {
            this.p = view;
            this.n = null;
            this.o = null;
        } else {
            if (i2 > 0) {
                this.n = resources.getText(i2);
            } else if (charSequence != null) {
                this.n = charSequence;
            }
            if (i3 > 0) {
                Context context = this.b;
                Object obj = K2.f8337a;
                this.o = context.getDrawable(i3);
            } else if (drawable != null) {
                this.o = drawable;
            }
            this.p = null;
        }
        p(false);
    }

    public void x() {
        this.q = false;
        if (this.r) {
            this.r = false;
            p(this.s);
        }
    }

    public void y() {
        if (!this.q) {
            this.q = true;
            this.r = false;
            this.s = false;
        }
    }

    @Override // android.view.Menu
    public MenuItem add(int i2) {
        return a(0, 0, 0, this.c.getString(i2));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, this.c.getString(i2));
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        C0817Ni0 ni0 = (C0817Ni0) a(i2, i3, i4, charSequence);
        SubMenuC4510r31 r31 = new SubMenuC4510r31(this.b, this, ni0);
        ni0.o = r31;
        r31.setHeaderTitle(ni0.e);
        return r31;
    }

    @Override // android.view.Menu
    public MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.c.getString(i5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, this.c.getString(i5));
    }
}
