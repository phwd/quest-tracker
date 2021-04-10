package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* renamed from: fj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MenuC2569fj0 extends AbstractC5119ug implements Menu {
    public final U31 c;

    public MenuC2569fj0(Context context, U31 u31) {
        super(context);
        if (u31 != null) {
            this.c = u31;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return c(((C4616ri0) this.c).add(charSequence));
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int addIntentOptions = ((C4616ri0) this.c).addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr[i5] = c(menuItemArr2[i5]);
            }
        }
        return addIntentOptions;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return ((C4616ri0) this.c).addSubMenu(charSequence);
    }

    public void clear() {
        BW0 bw0 = this.b;
        if (bw0 != null) {
            bw0.clear();
        }
        ((C4616ri0) this.c).clear();
    }

    public void close() {
        ((C4616ri0) this.c).close();
    }

    public MenuItem findItem(int i) {
        return c(((C4616ri0) this.c).findItem(i));
    }

    public MenuItem getItem(int i) {
        return c((MenuItem) ((C4616ri0) this.c).g.get(i));
    }

    public boolean hasVisibleItems() {
        return ((C4616ri0) this.c).hasVisibleItems();
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return ((C4616ri0) this.c).isShortcutKey(i, keyEvent);
    }

    public boolean performIdentifierAction(int i, int i2) {
        return ((C4616ri0) this.c).performIdentifierAction(i, i2);
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return ((C4616ri0) this.c).performShortcut(i, keyEvent, i2);
    }

    public void removeGroup(int i) {
        if (this.b != null) {
            int i2 = 0;
            while (true) {
                BW0 bw0 = this.b;
                if (i2 >= bw0.L) {
                    break;
                }
                if (((Y31) bw0.h(i2)).getGroupId() == i) {
                    this.b.i(i2);
                    i2--;
                }
                i2++;
            }
        }
        ((C4616ri0) this.c).removeGroup(i);
    }

    public void removeItem(int i) {
        if (this.b != null) {
            int i2 = 0;
            while (true) {
                BW0 bw0 = this.b;
                if (i2 >= bw0.L) {
                    break;
                } else if (((Y31) bw0.h(i2)).getItemId() == i) {
                    this.b.i(i2);
                    break;
                } else {
                    i2++;
                }
            }
        }
        ((C4616ri0) this.c).removeItem(i);
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        ((C4616ri0) this.c).setGroupCheckable(i, z, z2);
    }

    public void setGroupEnabled(int i, boolean z) {
        ((C4616ri0) this.c).setGroupEnabled(i, z);
    }

    public void setGroupVisible(int i, boolean z) {
        ((C4616ri0) this.c).setGroupVisible(i, z);
    }

    public void setQwertyMode(boolean z) {
        this.c.setQwertyMode(z);
    }

    public int size() {
        return ((C4616ri0) this.c).size();
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return c(((C4616ri0) this.c).add(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return ((C4616ri0) this.c).addSubMenu(i);
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return c(((C4616ri0) this.c).add(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return ((C4616ri0) this.c).addSubMenu(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return c(((C4616ri0) this.c).add(i, i2, i3, i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return ((C4616ri0) this.c).addSubMenu(i, i2, i3, i4);
    }
}
