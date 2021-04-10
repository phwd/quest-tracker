package X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1rg  reason: invalid class name */
public final class AnonymousClass1rg extends AbstractC11401rv implements Menu {
    public final AnonymousClass05X A00;

    public final int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2;
        if (menuItemArr != null) {
            menuItemArr2 = new MenuItem[menuItemArr.length];
        } else {
            menuItemArr2 = null;
        }
        int addIntentOptions = this.A00.addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr[i5] = A00(menuItemArr2[i5]);
            }
        }
        return addIntentOptions;
    }

    public final void clear() {
        C000502v<AnonymousClass05Y, MenuItem> r0 = super.A00;
        if (r0 != null) {
            r0.clear();
        }
        this.A00.clear();
    }

    public final void close() {
        this.A00.close();
    }

    public final MenuItem findItem(int i) {
        return A00(this.A00.findItem(i));
    }

    public final MenuItem getItem(int i) {
        return A00(this.A00.getItem(i));
    }

    public final boolean hasVisibleItems() {
        return this.A00.hasVisibleItems();
    }

    public final boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return this.A00.isShortcutKey(i, keyEvent);
    }

    public final boolean performIdentifierAction(int i, int i2) {
        return this.A00.performIdentifierAction(i, i2);
    }

    public final boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return this.A00.performShortcut(i, keyEvent, i2);
    }

    public final void removeGroup(int i) {
        if (super.A00 != null) {
            int i2 = 0;
            while (true) {
                C000502v<AnonymousClass05Y, MenuItem> r1 = super.A00;
                if (i2 >= r1.size()) {
                    break;
                }
                if (((AnonymousClass05Y) r1.A02[i2 << 1]).getGroupId() == i) {
                    super.A00.A06(i2);
                    i2--;
                }
                i2++;
            }
        }
        this.A00.removeGroup(i);
    }

    public final void removeItem(int i) {
        if (super.A00 != null) {
            int i2 = 0;
            while (true) {
                C000502v<AnonymousClass05Y, MenuItem> r1 = super.A00;
                if (i2 >= r1.size()) {
                    break;
                } else if (((AnonymousClass05Y) r1.A02[i2 << 1]).getItemId() == i) {
                    super.A00.A06(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
        this.A00.removeItem(i);
    }

    public final void setGroupCheckable(int i, boolean z, boolean z2) {
        this.A00.setGroupCheckable(i, z, z2);
    }

    public final void setGroupEnabled(int i, boolean z) {
        this.A00.setGroupEnabled(i, z);
    }

    public final void setGroupVisible(int i, boolean z) {
        this.A00.setGroupVisible(i, z);
    }

    public final void setQwertyMode(boolean z) {
        this.A00.setQwertyMode(z);
    }

    public final int size() {
        return this.A00.size();
    }

    public AnonymousClass1rg(Context context, AnonymousClass05X r4) {
        super(context);
        if (r4 != null) {
            this.A00 = r4;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    @Override // android.view.Menu
    public final MenuItem add(int i) {
        return A00(this.A00.add(i));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i, int i2, int i3, int i4) {
        return A00(this.A00.add(i, i2, i3, i4));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return A00(this.A00.add(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return A00(this.A00.add(charSequence));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i) {
        return this.A00.addSubMenu(i);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return this.A00.addSubMenu(i, i2, i3, i4);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return this.A00.addSubMenu(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return this.A00.addSubMenu(charSequence);
    }
}
