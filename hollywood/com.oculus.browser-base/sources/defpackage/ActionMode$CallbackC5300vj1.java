package defpackage;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/* renamed from: vj1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionMode$CallbackC5300vj1 implements ActionMode.Callback {

    /* renamed from: a  reason: collision with root package name */
    public G2 f11492a;
    public boolean b;

    public final void a(boolean z) {
        if (z != this.b) {
            if (z) {
                this.f11492a.c();
            } else {
                this.f11492a.b();
            }
            this.b = z;
        }
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        a(!(actionMode.getType() == 1));
        return true;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        a(false);
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return true;
    }
}
