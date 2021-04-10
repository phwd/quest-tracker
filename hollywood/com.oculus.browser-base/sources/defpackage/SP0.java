package defpackage;

import org.chromium.base.task.PostTask;

/* renamed from: SP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SP0 implements Runnable {
    public final /* synthetic */ NO F;

    public SP0(NO no, RP0 rp0) {
        this.F = no;
    }

    public void run() {
        NO no = this.F;
        if (no.b) {
            no.a();
            this.F.f8544a = null;
        } else if (no.c != 0) {
            no.b = true;
            PostTask.b(Zo1.f9374a, no.f8544a, 200);
        }
    }
}
