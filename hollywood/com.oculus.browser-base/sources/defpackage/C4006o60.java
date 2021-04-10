package defpackage;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import org.chromium.base.task.PostTask;

/* renamed from: o60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4006o60 extends ActionMode.Callback2 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActionMode.Callback2 f10533a;

    public C4006o60(ActionMode.Callback2 callback2) {
        this.f10533a = callback2;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.f10533a.onActionItemClicked(actionMode, menuItem);
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.f10533a.onCreateActionMode(actionMode, menu);
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        PostTask.b(Zo1.f9374a, new RunnableC3835n60(this, actionMode), 0);
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return this.f10533a.onPrepareActionMode(actionMode, menu);
    }
}
