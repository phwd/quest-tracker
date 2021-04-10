package defpackage;

import org.chromium.base.task.PostTask;

/* renamed from: Y1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Y1 extends PK {
    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public void B(Exception exc) {
        C4072oW0.f10556a.b.m("prefs_sync_accounts_changed", true);
    }

    @Override // defpackage.AbstractC3083ik, defpackage.PK
    public void u() {
        PostTask.c(Zo1.f9374a, new X1());
    }
}
