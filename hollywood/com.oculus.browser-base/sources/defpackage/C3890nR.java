package defpackage;

import android.graphics.Rect;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

/* renamed from: nR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3890nR extends ActionMode.Callback2 {

    /* renamed from: a  reason: collision with root package name */
    public final B2 f10490a;
    public final ActionMode.Callback b;

    public C3890nR(B2 b2, ActionMode.Callback callback) {
        this.f10490a = b2;
        this.b = callback;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.b.onActionItemClicked(actionMode, menuItem);
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        if (actionMode.getType() != 1) {
            return false;
        }
        return this.b.onCreateActionMode(actionMode, menu);
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        this.b.onDestroyActionMode(actionMode);
    }

    public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
        rect.set(((SelectionPopupControllerImpl) this.f10490a).u());
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return this.b.onPrepareActionMode(actionMode, menu);
    }
}
