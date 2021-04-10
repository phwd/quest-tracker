package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0eZ  reason: invalid class name and case insensitive filesystem */
public class C04280eZ implements AbstractMenuC007608p {
    public static final int[] A0O = {1, 4, 5, 3, 2, 0};
    public int A00 = 0;
    public Drawable A01;
    public View A02;
    public AnonymousClass03V A03;
    public C04250eW A04;
    public CharSequence A05;
    public ArrayList<C04250eW> A06;
    public ArrayList<C04250eW> A07;
    public ArrayList<C04250eW> A08;
    public CopyOnWriteArrayList<WeakReference<AbstractC000603b>> A09 = new CopyOnWriteArrayList<>();
    public boolean A0A;
    public boolean A0B;
    public boolean A0C;
    public boolean A0D;
    public ArrayList<C04250eW> A0E = new ArrayList<>();
    public ArrayList<C04250eW> A0F;
    public boolean A0G = false;
    public boolean A0H = false;
    public boolean A0I = false;
    public boolean A0J = false;
    public boolean A0K;
    public boolean A0L = false;
    public final Context A0M;
    public final Resources A0N;

    public C04280eZ A04() {
        return this;
    }

    public String A05() {
        return "android:menu:actionviewstates";
    }

    public final void A08() {
        this.A0J = false;
        if (this.A0I) {
            this.A0I = false;
            A0G(this.A0L);
        }
    }

    public final void clearHeader() {
        this.A01 = null;
        this.A05 = null;
        this.A02 = null;
        A0G(false);
    }

    public final void close() {
        A0F(true);
    }

    public final void removeGroup(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<C04250eW> arrayList = this.A07;
            if (arrayList.get(i2).getGroupId() == i) {
                if (i2 >= 0) {
                    int size2 = arrayList.size() - i2;
                    int i3 = 0;
                    while (true) {
                        int i4 = i3 + 1;
                        if (i3 >= size2 || arrayList.get(i2).getGroupId() != i) {
                            A0G(true);
                        } else {
                            if (i2 < arrayList.size()) {
                                arrayList.remove(i2);
                            }
                            i3 = i4;
                        }
                    }
                    A0G(true);
                    return;
                } else {
                    return;
                }
            }
        }
    }

    private final MenuItem A00(int i, int i2, int i3, CharSequence charSequence) {
        int i4;
        int i5 = (-65536 & i3) >> 16;
        if (i5 >= 0) {
            int[] iArr = A0O;
            if (i5 < iArr.length) {
                int i6 = (i3 & 65535) | (iArr[i5] << 16);
                C04250eW r3 = new C04250eW(this, i, i2, i3, i6, charSequence, this.A00);
                ArrayList<C04250eW> arrayList = this.A07;
                int size = arrayList.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        if (arrayList.get(size).A0Q <= i6) {
                            i4 = size + 1;
                            break;
                        }
                    } else {
                        i4 = 0;
                        break;
                    }
                }
                arrayList.add(i4, r3);
                A0G(true);
                return r3;
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private final C04250eW A01(int i, KeyEvent keyEvent) {
        char numericShortcut;
        ArrayList<C04250eW> arrayList = this.A0E;
        arrayList.clear();
        A03(arrayList, i, keyEvent);
        if (!arrayList.isEmpty()) {
            int metaState = keyEvent.getMetaState();
            KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
            keyEvent.getKeyData(keyData);
            int size = arrayList.size();
            if (size == 1) {
                return arrayList.get(0);
            }
            boolean A0I2 = A0I();
            for (int i2 = 0; i2 < size; i2++) {
                C04250eW r2 = arrayList.get(i2);
                if (A0I2) {
                    numericShortcut = r2.getAlphabeticShortcut();
                } else {
                    numericShortcut = r2.getNumericShortcut();
                }
                char[] cArr = keyData.meta;
                if ((numericShortcut == cArr[0] && (metaState & 2) == 0) || ((numericShortcut == cArr[2] && (metaState & 2) != 0) || (A0I2 && numericShortcut == '\b' && i == 67))) {
                    return r2;
                }
            }
        }
        return null;
    }

    public static void A02(C04280eZ r2, int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resources = r2.A0N;
        if (view != null) {
            r2.A02 = view;
            r2.A05 = null;
            r2.A01 = null;
        } else {
            if (i > 0) {
                r2.A05 = resources.getText(i);
            } else if (charSequence != null) {
                r2.A05 = charSequence;
            }
            if (i2 > 0) {
                r2.A01 = r2.A0M.getDrawable(i2);
            } else if (drawable != null) {
                r2.A01 = drawable;
            }
            r2.A02 = null;
        }
        r2.A0G(false);
    }

    @NonNull
    public final ArrayList<C04250eW> A06() {
        if (this.A0B) {
            this.A0F.clear();
            int size = this.A07.size();
            for (int i = 0; i < size; i++) {
                C04250eW r1 = this.A07.get(i);
                if (r1.isVisible()) {
                    this.A0F.add(r1);
                }
            }
            this.A0B = false;
            this.A0A = true;
        }
        return this.A0F;
    }

    public final void A09() {
        if (!this.A0J) {
            this.A0J = true;
            this.A0I = false;
            this.A0L = false;
        }
    }

    public final void A0D(AbstractC000603b r4) {
        Iterator<WeakReference<AbstractC000603b>> it = this.A09.iterator();
        while (it.hasNext()) {
            WeakReference<AbstractC000603b> next = it.next();
            AbstractC000603b r0 = next.get();
            if (r0 == null || r0 == r4) {
                this.A09.remove(next);
            }
        }
    }

    public final void A0E(AbstractC000603b r3, Context context) {
        this.A09.add(new WeakReference<>(r3));
        r3.A5E(context, this);
        this.A0A = true;
    }

    public final void A0F(boolean z) {
        if (!this.A0H) {
            this.A0H = true;
            Iterator<WeakReference<AbstractC000603b>> it = this.A09.iterator();
            while (it.hasNext()) {
                WeakReference<AbstractC000603b> next = it.next();
                AbstractC000603b r0 = next.get();
                if (r0 == null) {
                    this.A09.remove(next);
                } else {
                    r0.A5x(this, z);
                }
            }
            this.A0H = false;
        }
    }

    public final void A0G(boolean z) {
        if (!this.A0J) {
            if (z) {
                this.A0B = true;
                this.A0A = true;
            }
            if (!this.A09.isEmpty()) {
                A09();
                Iterator<WeakReference<AbstractC000603b>> it = this.A09.iterator();
                while (it.hasNext()) {
                    WeakReference<AbstractC000603b> next = it.next();
                    AbstractC000603b r0 = next.get();
                    if (r0 == null) {
                        this.A09.remove(next);
                    } else {
                        r0.A8k(z);
                    }
                }
                A08();
                return;
            }
            return;
        }
        this.A0I = true;
        if (z) {
            this.A0L = true;
        }
    }

    public boolean A0H() {
        return this.A0G;
    }

    public boolean A0I() {
        return this.A0K;
    }

    public boolean A0J() {
        return this.A0D;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        if (r6.A04() == false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        if (r7 != false) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0040, code lost:
        if ((r11 & 1) == 0) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a2, code lost:
        if (r7 == false) goto L_0x0032;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0K(android.view.MenuItem r9, X.AbstractC000603b r10, int r11) {
        /*
        // Method dump skipped, instructions count: 210
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04280eZ.A0K(android.view.MenuItem, X.03b, int):boolean");
    }

    public boolean A0L(@NonNull C04280eZ r3, @NonNull MenuItem menuItem) {
        AnonymousClass03V r0 = this.A03;
        if (r0 == null || !r0.A6D(r3, menuItem)) {
            return false;
        }
        return true;
    }

    public boolean A0M(C04250eW r5) {
        boolean z = false;
        if (!this.A09.isEmpty() && this.A04 == r5) {
            A09();
            Iterator<WeakReference<AbstractC000603b>> it = this.A09.iterator();
            while (it.hasNext()) {
                WeakReference<AbstractC000603b> next = it.next();
                AbstractC000603b r0 = next.get();
                if (r0 == null) {
                    this.A09.remove(next);
                } else {
                    z = r0.A1k(this, r5);
                    if (z) {
                        break;
                    }
                }
            }
            A08();
            if (z) {
                this.A04 = null;
            }
        }
        return z;
    }

    public boolean A0N(C04250eW r5) {
        boolean z = false;
        if (!this.A09.isEmpty()) {
            A09();
            Iterator<WeakReference<AbstractC000603b>> it = this.A09.iterator();
            while (it.hasNext()) {
                WeakReference<AbstractC000603b> next = it.next();
                AbstractC000603b r0 = next.get();
                if (r0 == null) {
                    this.A09.remove(next);
                } else {
                    z = r0.A2V(this, r5);
                    if (z) {
                        break;
                    }
                }
            }
            A08();
            if (z) {
                this.A04 = r5;
            }
        }
        return z;
    }

    public final void clear() {
        C04250eW r0 = this.A04;
        if (r0 != null) {
            A0M(r0);
        }
        this.A07.clear();
        A0G(true);
    }

    public final MenuItem getItem(int i) {
        return this.A07.get(i);
    }

    public final boolean hasVisibleItems() {
        if (!this.A0C) {
            int size = size();
            for (int i = 0; i < size; i++) {
                if (!this.A07.get(i).isVisible()) {
                }
            }
            return false;
        }
        return true;
    }

    public final void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.A07.size();
        for (int i2 = 0; i2 < size; i2++) {
            C04250eW r2 = this.A07.get(i2);
            if (r2.getGroupId() == i) {
                int i3 = r2.A02 & -5;
                int i4 = 0;
                if (z2) {
                    i4 = 4;
                }
                r2.A02 = i4 | i3;
                r2.setCheckable(z);
            }
        }
    }

    public final void setGroupEnabled(int i, boolean z) {
        int size = this.A07.size();
        for (int i2 = 0; i2 < size; i2++) {
            C04250eW r1 = this.A07.get(i2);
            if (r1.getGroupId() == i) {
                r1.setEnabled(z);
            }
        }
    }

    public final void setGroupVisible(int i, boolean z) {
        ArrayList<C04250eW> arrayList = this.A07;
        int size = arrayList.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            C04250eW r3 = arrayList.get(i2);
            if (r3.getGroupId() == i) {
                int i3 = r3.A02;
                int i4 = i3 & -9;
                int i5 = 8;
                if (z) {
                    i5 = 0;
                }
                int i6 = i5 | i4;
                r3.A02 = i6;
                if (i3 != i6) {
                    z2 = true;
                }
            }
        }
        if (z2) {
            A0G(true);
        }
    }

    public void setQwertyMode(boolean z) {
        this.A0K = z;
        A0G(false);
    }

    public final int size() {
        return this.A07.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x005a, code lost:
        if (X.AnonymousClass0Ax.A01(android.view.ViewConfiguration.get(r1), r1) != false) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C04280eZ(android.content.Context r5) {
        /*
            r4 = this;
            r4.<init>()
            r1 = 0
            r4.A00 = r1
            r4.A0J = r1
            r4.A0I = r1
            r4.A0L = r1
            r4.A0H = r1
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4.A0E = r0
            java.util.concurrent.CopyOnWriteArrayList r0 = new java.util.concurrent.CopyOnWriteArrayList
            r0.<init>()
            r4.A09 = r0
            r4.A0G = r1
            r4.A0M = r5
            android.content.res.Resources r3 = r5.getResources()
            r4.A0N = r3
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4.A07 = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4.A0F = r0
            r1 = 1
            r4.A0B = r1
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4.A06 = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4.A08 = r0
            r4.A0A = r1
            r2 = 1
            android.content.res.Configuration r0 = r3.getConfiguration()
            int r0 = r0.keyboard
            if (r0 == r1) goto L_0x005f
            android.content.Context r1 = r4.A0M
            android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r1)
            boolean r0 = X.AnonymousClass0Ax.A01(r0, r1)
            if (r0 == 0) goto L_0x005f
        L_0x005c:
            r4.A0D = r2
            return
        L_0x005f:
            r2 = 0
            goto L_0x005c
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04280eZ.<init>(android.content.Context):void");
    }

    private final void A03(List<C04250eW> list, int i, KeyEvent keyEvent) {
        char numericShortcut;
        int numericModifiers;
        boolean A0I2 = A0I();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.A07.size();
            for (int i2 = 0; i2 < size; i2++) {
                C04250eW r2 = this.A07.get(i2);
                if (r2.hasSubMenu()) {
                    ((C04280eZ) r2.getSubMenu()).A03(list, i, keyEvent);
                }
                if (A0I2) {
                    numericShortcut = r2.getAlphabeticShortcut();
                    numericModifiers = r2.getAlphabeticModifiers();
                } else {
                    numericShortcut = r2.getNumericShortcut();
                    numericModifiers = r2.getNumericModifiers();
                }
                if ((modifiers & 69647) == (numericModifiers & 69647) && numericShortcut != 0) {
                    char[] cArr = keyData.meta;
                    if ((numericShortcut == cArr[0] || numericShortcut == cArr[2] || (A0I2 && numericShortcut == '\b' && i == 67)) && r2.isEnabled()) {
                        list.add(r2);
                    }
                }
            }
        }
    }

    public final void A07() {
        ArrayList<C04250eW> A062 = A06();
        if (this.A0A) {
            Iterator<WeakReference<AbstractC000603b>> it = this.A09.iterator();
            boolean z = false;
            while (it.hasNext()) {
                WeakReference<AbstractC000603b> next = it.next();
                AbstractC000603b r0 = next.get();
                if (r0 == null) {
                    this.A09.remove(next);
                } else {
                    z |= r0.A2p();
                }
            }
            if (z) {
                ArrayList<C04250eW> arrayList = this.A06;
                arrayList.clear();
                ArrayList<C04250eW> arrayList2 = this.A08;
                arrayList2.clear();
                int size = A062.size();
                for (int i = 0; i < size; i++) {
                    C04250eW r2 = A062.get(i);
                    if ((r2.A02 & 32) == 32) {
                        arrayList.add(r2);
                    } else {
                        arrayList2.add(r2);
                    }
                }
            } else {
                this.A06.clear();
                ArrayList<C04250eW> arrayList3 = this.A08;
                arrayList3.clear();
                arrayList3.addAll(A06());
            }
            this.A0A = false;
        }
    }

    public final void A0A(Bundle bundle) {
        MenuItem findItem;
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(A05());
        int size = size();
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((C04280eZ) item.getSubMenu()).A0A(bundle);
            }
        }
        int i2 = bundle.getInt("android:menu:expandedactionview");
        if (i2 > 0 && (findItem = findItem(i2)) != null) {
            findItem.expandActionView();
        }
    }

    public final void A0B(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
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
                ((C04280eZ) item.getSubMenu()).A0B(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(A05(), sparseArray);
        }
    }

    public final MenuItem findItem(int i) {
        MenuItem findItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            C04250eW r1 = this.A07.get(i2);
            if (r1.getItemId() == i) {
                return r1;
            }
            if (r1.hasSubMenu() && (findItem = r1.getSubMenu().findItem(i)) != null) {
                return findItem;
            }
        }
        return null;
    }

    public final boolean isShortcutKey(int i, KeyEvent keyEvent) {
        if (A01(i, keyEvent) != null) {
            return true;
        }
        return false;
    }

    public final boolean performIdentifierAction(int i, int i2) {
        return A0K(findItem(i), null, i2);
    }

    public final boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        boolean z;
        C04250eW A012 = A01(i, keyEvent);
        if (A012 != null) {
            z = A0K(A012, null, i2);
        } else {
            z = false;
        }
        if ((i2 & 2) != 0) {
            A0F(true);
        }
        return z;
    }

    public final void removeItem(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList<C04250eW> arrayList = this.A07;
            if (arrayList.get(i2).getItemId() == i) {
                if (i2 >= 0 && i2 < arrayList.size()) {
                    arrayList.remove(i2);
                    A0G(true);
                    return;
                } else {
                    return;
                }
            }
        }
    }

    public void A0C(AnonymousClass03V r1) {
        this.A03 = r1;
    }

    public void setGroupDividerEnabled(boolean z) {
        this.A0G = z;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i) {
        return A00(0, 0, 0, this.A0N.getString(i));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i, int i2, int i3, int i4) {
        return A00(i, i2, i3, this.A0N.getString(i4));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return A00(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return A00(0, 0, 0, charSequence);
    }

    public final int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        Intent intent2;
        PackageManager packageManager = this.A0M.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        if (queryIntentActivityOptions != null) {
            i5 = queryIntentActivityOptions.size();
        } else {
            i5 = 0;
        }
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i6 = 0; i6 < i5; i6++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i6);
            if (resolveInfo.specificIndex < 0) {
                intent2 = intent;
            } else {
                intent2 = intentArr[resolveInfo.specificIndex];
            }
            Intent intent3 = new Intent(intent2);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent3.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            MenuItem intent4 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent3);
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = intent4;
            }
        }
        return i5;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.A0N.getString(i));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.A0N.getString(i4));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        C04250eW r2 = (C04250eW) A00(i, i2, i3, charSequence);
        SubMenuC01890Mu r1 = new SubMenuC01890Mu(this.A0M, this, r2);
        r2.A0C = r1;
        r1.setHeaderTitle(r2.getTitle());
        return r1;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }
}
