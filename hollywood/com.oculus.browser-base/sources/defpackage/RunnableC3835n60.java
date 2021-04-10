package defpackage;

import android.view.ActionMode;

/* renamed from: n60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC3835n60 implements Runnable {
    public final /* synthetic */ ActionMode F;
    public final /* synthetic */ C4006o60 G;

    public RunnableC3835n60(C4006o60 o60, ActionMode actionMode) {
        this.G = o60;
        this.F = actionMode;
    }

    public void run() {
        this.G.f10533a.onDestroyActionMode(this.F);
    }
}
