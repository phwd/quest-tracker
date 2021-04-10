package defpackage;

import android.content.SyncStatusObserver;
import org.chromium.base.task.PostTask;

/* renamed from: s6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SyncStatusObserverC4688s6 implements SyncStatusObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4858t6 f11251a;

    public SyncStatusObserverC4688s6(C4858t6 t6Var) {
        this.f11251a = t6Var;
    }

    public void onStatusChanged(int i) {
        if (i == 1) {
            PostTask.b(Zo1.f9374a, new RunnableC4517r6(this), 0);
        }
    }
}
