package defpackage;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.Method;

/* renamed from: Lg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionMode$CallbackC0691Lg1 implements ActionMode.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final ActionMode.Callback f8432a;
    public final TextView b;
    public Class c;
    public Method d;
    public boolean e;
    public boolean f = false;

    public ActionMode$CallbackC0691Lg1(ActionMode.Callback callback, TextView textView) {
        this.f8432a = callback;
        this.b = textView;
    }

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.f8432a.onActionItemClicked(actionMode, menuItem);
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.f8432a.onCreateActionMode(actionMode, menu);
    }

    public void onDestroyActionMode(ActionMode actionMode) {
        this.f8432a.onDestroyActionMode(actionMode);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d1  */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[ExcHandler: IllegalAccessException | NoSuchMethodException | InvocationTargetException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:16:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x009f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPrepareActionMode(android.view.ActionMode r13, android.view.Menu r14) {
        /*
        // Method dump skipped, instructions count: 304
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ActionMode$CallbackC0691Lg1.onPrepareActionMode(android.view.ActionMode, android.view.Menu):boolean");
    }
}
