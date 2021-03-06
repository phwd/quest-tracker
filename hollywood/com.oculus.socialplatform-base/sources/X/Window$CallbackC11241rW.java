package X;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.util.List;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1rW  reason: invalid class name and case insensitive filesystem */
public class Window$CallbackC11241rW implements Window.Callback {
    public final Window.Callback A00;

    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.A00.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.A00.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return this.A00.dispatchKeyShortcutEvent(keyEvent);
    }

    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.A00.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.A00.dispatchTouchEvent(motionEvent);
    }

    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.A00.dispatchTrackballEvent(motionEvent);
    }

    public final void onActionModeFinished(ActionMode actionMode) {
        this.A00.onActionModeFinished(actionMode);
    }

    public final void onActionModeStarted(ActionMode actionMode) {
        this.A00.onActionModeStarted(actionMode);
    }

    public final void onAttachedToWindow() {
        this.A00.onAttachedToWindow();
    }

    public final void onContentChanged() {
        if (!(this instanceof C11231rV)) {
            this.A00.onContentChanged();
        }
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        return this.A00.onCreatePanelMenu(i, menu);
    }

    public View onCreatePanelView(int i) {
        return this.A00.onCreatePanelView(i);
    }

    public final void onDetachedFromWindow() {
        this.A00.onDetachedFromWindow();
    }

    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return this.A00.onMenuItemSelected(i, menuItem);
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return this.A00.onMenuOpened(i, menu);
    }

    public void onPanelClosed(int i, Menu menu) {
        this.A00.onPanelClosed(i, menu);
    }

    @RequiresApi(26)
    public final void onPointerCaptureChanged(boolean z) {
        this.A00.onPointerCaptureChanged(z);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        return this.A00.onPreparePanel(i, view, menu);
    }

    @Override // android.view.Window.Callback
    @RequiresApi(24)
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i) {
        this.A00.onProvideKeyboardShortcuts(list, menu, i);
    }

    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.A00.onWindowAttributesChanged(layoutParams);
    }

    public final void onWindowFocusChanged(boolean z) {
        this.A00.onWindowFocusChanged(z);
    }

    public Window$CallbackC11241rW(Window.Callback callback) {
        if (callback != null) {
            this.A00 = callback;
            return;
        }
        throw new IllegalArgumentException("Window callback may not be null");
    }

    public final boolean onSearchRequested() {
        return this.A00.onSearchRequested();
    }

    @RequiresApi(23)
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        return this.A00.onSearchRequested(searchEvent);
    }

    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        if (!(this instanceof C11231rV)) {
            return this.A00.onWindowStartingActionMode(callback);
        }
        return null;
    }

    @RequiresApi(23)
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return this.A00.onWindowStartingActionMode(callback, i);
    }
}
