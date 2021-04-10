package defpackage;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Objects;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: oz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionMode$CallbackC4155oz0 implements ActionMode.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final B2 f11041a;

    public ActionMode$CallbackC4155oz0(WebContents webContents) {
        SelectionPopupControllerImpl r = SelectionPopupControllerImpl.r(webContents);
        Objects.requireNonNull(r);
        this.f11041a = r;
        r.S = 0;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (!this.f11041a.e()) {
            return true;
        }
        return this.f11041a.f(actionMode, menuItem);
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        this.f11041a.g(actionMode, menu);
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        SelectionPopupControllerImpl selectionPopupControllerImpl = (SelectionPopupControllerImpl) this.f11041a;
        selectionPopupControllerImpl.R = null;
        if (selectionPopupControllerImpl.Y) {
            selectionPopupControllerImpl.m();
        }
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        this.f11041a.j(actionMode, menu);
        return true;
    }
}
