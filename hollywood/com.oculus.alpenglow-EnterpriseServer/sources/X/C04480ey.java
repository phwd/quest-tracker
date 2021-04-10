package X;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.Window;
import androidx.annotation.RequiresApi;
import java.util.List;

/* renamed from: X.0ey  reason: invalid class name and case insensitive filesystem */
public class C04480ey extends AnonymousClass03L {
    public final /* synthetic */ LayoutInflater$Factory2C04430et A00;

    @Override // X.AnonymousClass03L
    public final void onContentChanged() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C04480ey(LayoutInflater$Factory2C04430et r1, Window.Callback callback) {
        super(callback);
        this.A00 = r1;
    }

    @Override // X.AnonymousClass03L
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.A00.A0h(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass03L
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || (menu instanceof C04280eZ)) {
            return super.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r2 != null) goto L_0x0010;
     */
    @Override // X.AnonymousClass03L
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onPreparePanel(int r4, android.view.View r5, android.view.Menu r6) {
        /*
            r3 = this;
            boolean r0 = r6 instanceof X.C04280eZ
            r2 = 0
            if (r0 == 0) goto L_0x0008
            r2 = r6
            X.0eZ r2 = (X.C04280eZ) r2
        L_0x0008:
            r1 = 0
            if (r4 != 0) goto L_0x000e
            if (r2 != 0) goto L_0x0010
            return r1
        L_0x000e:
            if (r2 == 0) goto L_0x0013
        L_0x0010:
            r0 = 1
            r2.A0C = r0
        L_0x0013:
            boolean r0 = super.onPreparePanel(r4, r5, r6)
            if (r2 == 0) goto L_0x001b
            r2.A0C = r1
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04480ey.onPreparePanel(int, android.view.View, android.view.Menu):boolean");
    }

    @Override // X.AnonymousClass03L, android.view.Window.Callback
    @RequiresApi(24)
    public final void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
        C04280eZ r0 = this.A00.A0d(0).A0A;
        if (r0 != null) {
            super.onProvideKeyboardShortcuts(list, r0, i);
        } else {
            super.onProvideKeyboardShortcuts(list, menu, i);
        }
    }

    @Override // X.AnonymousClass03L
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        C04280eZ r0;
        C04280eZ r02;
        if (super.dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        LayoutInflater$Factory2C04430et r6 = this.A00;
        int keyCode = keyEvent.getKeyCode();
        AnonymousClass02W A0J = r6.A0J();
        if (A0J != null && A0J.A0H(keyCode, keyEvent)) {
            return true;
        }
        AnonymousClass02x r2 = r6.A0H;
        if (r2 != null) {
            int keyCode2 = keyEvent.getKeyCode();
            if (!keyEvent.isSystem() && ((r2.A0D || LayoutInflater$Factory2C04430et.A0E(r6, r2, keyEvent)) && (r02 = r2.A0A) != null && r02.performShortcut(keyCode2, keyEvent, 1))) {
                AnonymousClass02x r03 = r6.A0H;
                if (r03 == null) {
                    return true;
                }
                r03.A0B = true;
                return true;
            }
        }
        if (r6.A0H != null) {
            return false;
        }
        AnonymousClass02x A0d = r6.A0d(0);
        LayoutInflater$Factory2C04430et.A0E(r6, A0d, keyEvent);
        int keyCode3 = keyEvent.getKeyCode();
        boolean z = false;
        if (!keyEvent.isSystem() && ((A0d.A0D || LayoutInflater$Factory2C04430et.A0E(r6, A0d, keyEvent)) && (r0 = A0d.A0A) != null)) {
            z = r0.performShortcut(keyCode3, keyEvent, 1);
        }
        A0d.A0D = false;
        if (z) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass03L
    public final boolean onMenuOpened(int i, Menu menu) {
        AnonymousClass02W A0J;
        super.onMenuOpened(i, menu);
        LayoutInflater$Factory2C04430et r1 = this.A00;
        if (i != 108 || (A0J = r1.A0J()) == null) {
            return true;
        }
        A0J.A0D(true);
        return true;
    }

    @Override // X.AnonymousClass03L
    public final void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
        LayoutInflater$Factory2C04430et r3 = this.A00;
        if (i == 108) {
            AnonymousClass02W A0J = r3.A0J();
            if (A0J != null) {
                A0J.A0D(false);
            }
        } else if (i == 0) {
            AnonymousClass02x A0d = r3.A0d(i);
            if (A0d.A0C) {
                r3.A0f(A0d, false);
            }
        }
    }

    @Override // X.AnonymousClass03L
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // X.AnonymousClass03L
    @RequiresApi(23)
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        LayoutInflater$Factory2C04430et r2 = this.A00;
        if (!r2.A0T || i != 0) {
            return super.onWindowStartingActionMode(callback, i);
        }
        AnonymousClass0eh r1 = new AnonymousClass0eh(r2.A0j, callback);
        AnonymousClass03D A0L = r2.A0L(r1);
        if (A0L != null) {
            return r1.A00(A0L);
        }
        return null;
    }
}
