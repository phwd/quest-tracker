package defpackage;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;
import java.util.Objects;

/* renamed from: b8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Window$CallbackC1790b8 implements Window.Callback {
    public final Window.Callback F;
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 G;

    public Window$CallbackC1790b8(LayoutInflater$Factory2C3156j8 j8Var, Window.Callback callback) {
        this.G = j8Var;
        if (callback != null) {
            this.F = callback;
            return;
        }
        throw new IllegalArgumentException("Window callback may not be null");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.ActionMode a(android.view.ActionMode.Callback r11) {
        /*
        // Method dump skipped, instructions count: 521
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Window$CallbackC1790b8.a(android.view.ActionMode$Callback):android.view.ActionMode");
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.F.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.G.w(keyEvent) || this.F.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
        if (r3 != false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0069, code lost:
        if (r7 != false) goto L_0x006b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchKeyShortcutEvent(android.view.KeyEvent r7) {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Window$CallbackC1790b8.dispatchKeyShortcutEvent(android.view.KeyEvent):boolean");
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.F.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.F.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.F.dispatchTrackballEvent(motionEvent);
    }

    public void onActionModeFinished(ActionMode actionMode) {
        this.F.onActionModeFinished(actionMode);
    }

    public void onActionModeStarted(ActionMode actionMode) {
        this.F.onActionModeStarted(actionMode);
    }

    public void onAttachedToWindow() {
        this.F.onAttachedToWindow();
    }

    public void onContentChanged() {
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0 || (menu instanceof C4616ri0)) {
            return this.F.onCreatePanelMenu(i, menu);
        }
        return false;
    }

    public View onCreatePanelView(int i) {
        return this.F.onCreatePanelView(i);
    }

    public void onDetachedFromWindow() {
        this.F.onDetachedFromWindow();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.F.onMenuItemSelected(i, menuItem);
    }

    public boolean onMenuOpened(int i, Menu menu) {
        this.F.onMenuOpened(i, menu);
        LayoutInflater$Factory2C3156j8 j8Var = this.G;
        Objects.requireNonNull(j8Var);
        if (i == 108) {
            j8Var.F();
            AbstractC1772b2 b2Var = j8Var.R;
            if (b2Var != null) {
                b2Var.a(true);
            }
        }
        return true;
    }

    public void onPanelClosed(int i, Menu menu) {
        this.F.onPanelClosed(i, menu);
        LayoutInflater$Factory2C3156j8 j8Var = this.G;
        Objects.requireNonNull(j8Var);
        if (i == 108) {
            j8Var.F();
            AbstractC1772b2 b2Var = j8Var.R;
            if (b2Var != null) {
                b2Var.a(false);
            }
        } else if (i == 0) {
            C2815h8 D = j8Var.D(i);
            if (D.m) {
                j8Var.u(D, false);
            }
        }
    }

    public void onPointerCaptureChanged(boolean z) {
        this.F.onPointerCaptureChanged(z);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        C4616ri0 ri0 = menu instanceof C4616ri0 ? (C4616ri0) menu : null;
        if (i == 0 && ri0 == null) {
            return false;
        }
        if (ri0 != null) {
            ri0.y = true;
        }
        boolean onPreparePanel = this.F.onPreparePanel(i, view, menu);
        if (ri0 != null) {
            ri0.y = false;
        }
        return onPreparePanel;
    }

    @Override // android.view.Window.Callback
    public void onProvideKeyboardShortcuts(List list, Menu menu, int i) {
        C4616ri0 ri0 = this.G.D(0).h;
        if (ri0 != null) {
            this.F.onProvideKeyboardShortcuts(list, ri0, i);
        } else {
            this.F.onProvideKeyboardShortcuts(list, menu, i);
        }
    }

    public boolean onSearchRequested(SearchEvent searchEvent) {
        return this.F.onSearchRequested(searchEvent);
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.F.onWindowAttributesChanged(layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        this.F.onWindowFocusChanged(z);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        if (!this.G.c0 || i != 0) {
            return this.F.onWindowStartingActionMode(callback, i);
        }
        return a(callback);
    }

    public boolean onSearchRequested() {
        return this.F.onSearchRequested();
    }
}
