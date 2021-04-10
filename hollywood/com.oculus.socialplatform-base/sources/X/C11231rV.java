package X;

import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.Window;
import androidx.annotation.RequiresApi;
import java.util.List;

/* renamed from: X.1rV  reason: invalid class name and case insensitive filesystem */
public class C11231rV extends Window$CallbackC11241rW {
    public final /* synthetic */ AnonymousClass1rJ A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C11231rV(AnonymousClass1rJ r1, Window.Callback callback) {
        super(callback);
        this.A00 = r1;
    }

    @Override // X.Window$CallbackC11241rW
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.A00.A0R(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    @Override // X.Window$CallbackC11241rW
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || (menu instanceof C11581sN)) {
            return super.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r2 != null) goto L_0x0010;
     */
    @Override // X.Window$CallbackC11241rW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onPreparePanel(int r4, android.view.View r5, android.view.Menu r6) {
        /*
            r3 = this;
            boolean r0 = r6 instanceof X.C11581sN
            r2 = 0
            if (r0 == 0) goto L_0x0008
            r2 = r6
            X.1sN r2 = (X.C11581sN) r2
        L_0x0008:
            r1 = 0
            if (r4 != 0) goto L_0x000e
            if (r2 != 0) goto L_0x0010
            return r1
        L_0x000e:
            if (r2 == 0) goto L_0x0013
        L_0x0010:
            r0 = 1
            r2.A0D = r0
        L_0x0013:
            boolean r0 = super.onPreparePanel(r4, r5, r6)
            if (r2 == 0) goto L_0x001b
            r2.A0D = r1
        L_0x001b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11231rV.onPreparePanel(int, android.view.View, android.view.Menu):boolean");
    }

    @Override // X.Window$CallbackC11241rW, android.view.Window.Callback
    @RequiresApi(24)
    public final void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
        C11581sN r0 = this.A00.A0N(0).A0A;
        if (r0 != null) {
            super.onProvideKeyboardShortcuts(list, r0, i);
        } else {
            super.onProvideKeyboardShortcuts(list, menu, i);
        }
    }

    @Override // X.Window$CallbackC11241rW
    @RequiresApi(23)
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        AnonymousClass1rJ r2 = this.A00;
        if (!r2.A0V || i != 0) {
            return super.onWindowStartingActionMode(callback, i);
        }
        AnonymousClass1ra r1 = new AnonymousClass1ra(r2.A0j, callback);
        AbstractC11301rk A0D = r2.A0D(r1);
        if (A0D != null) {
            return r1.A00(A0D);
        }
        return null;
    }

    @Override // X.Window$CallbackC11241rW
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        C11581sN r0;
        C11581sN r02;
        Menu A002;
        boolean performShortcut;
        if (super.dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        AnonymousClass1rJ r5 = this.A00;
        int keyCode = keyEvent.getKeyCode();
        AnonymousClass1rJ.A07(r5);
        AbstractC11251rX r3 = r5.A0B;
        if (r3 != null) {
            if (r3 instanceof C11201rK) {
                AnonymousClass1rN r03 = ((C11201rK) r3).A04;
                if (!(r03 == null || (A002 = r03.A00()) == null)) {
                    boolean z = true;
                    if (KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() == 1) {
                        z = false;
                    }
                    A002.setQwertyMode(z);
                    performShortcut = A002.performShortcut(keyCode, keyEvent, 0);
                }
            } else if (r3 instanceof AnonymousClass1rR) {
                AnonymousClass1rR r32 = (AnonymousClass1rR) r3;
                if (!r32.A05) {
                    r32.A02.AA0(new C11371rs(r32), new C11351rq(r32));
                    r32.A05 = true;
                }
                Menu A4O = r32.A02.A4O();
                if (A4O != null) {
                    boolean z2 = true;
                    if (KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() == 1) {
                        z2 = false;
                    }
                    A4O.setQwertyMode(z2);
                    performShortcut = A4O.performShortcut(keyCode, keyEvent, 0);
                }
            }
            if (performShortcut) {
                return true;
            }
        }
        AnonymousClass1rh r2 = r5.A0F;
        if (r2 != null) {
            int keyCode2 = keyEvent.getKeyCode();
            if (!keyEvent.isSystem() && ((r2.A0D || AnonymousClass1rJ.A0A(r5, r2, keyEvent)) && (r02 = r2.A0A) != null && r02.performShortcut(keyCode2, keyEvent, 1))) {
                AnonymousClass1rh r04 = r5.A0F;
                if (r04 == null) {
                    return true;
                }
                r04.A0B = true;
                return true;
            }
        }
        if (r5.A0F != null) {
            return false;
        }
        AnonymousClass1rh A0N = r5.A0N(0);
        AnonymousClass1rJ.A0A(r5, A0N, keyEvent);
        int keyCode3 = keyEvent.getKeyCode();
        boolean z3 = false;
        if (!keyEvent.isSystem() && ((A0N.A0D || AnonymousClass1rJ.A0A(r5, A0N, keyEvent)) && (r0 = A0N.A0A) != null)) {
            z3 = r0.performShortcut(keyCode3, keyEvent, 1);
        }
        A0N.A0D = false;
        if (z3) {
            return true;
        }
        return false;
    }

    @Override // X.Window$CallbackC11241rW
    public final boolean onMenuOpened(int i, Menu menu) {
        super.onMenuOpened(i, menu);
        AnonymousClass1rJ r1 = this.A00;
        if (i != 108) {
            return true;
        }
        AnonymousClass1rJ.A07(r1);
        AbstractC11251rX r12 = r1.A0B;
        if (r12 == null) {
            return true;
        }
        r12.A04(true);
        return true;
    }

    @Override // X.Window$CallbackC11241rW
    public final void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
        AnonymousClass1rJ r3 = this.A00;
        if (i == 108) {
            AnonymousClass1rJ.A07(r3);
            AbstractC11251rX r0 = r3.A0B;
            if (r0 != null) {
                r0.A04(false);
            }
        } else if (i == 0) {
            AnonymousClass1rh A0N = r3.A0N(i);
            if (A0N.A0C) {
                r3.A0P(A0N, false);
            }
        }
    }
}
