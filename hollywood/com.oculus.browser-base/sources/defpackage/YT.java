package defpackage;

import android.content.Intent;
import org.chromium.base.task.PostTask;

/* renamed from: YT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YT extends XY0 {
    @Override // defpackage.XY0
    public void a(Intent intent) {
        C3383kU a2 = C3383kU.a(intent.getExtras(), new C2358eU(null));
        if (a2 == null) {
            AbstractC1220Ua0.a("GCMBackgroundService", "The received bundle containing message data could not be validated.", new Object[0]);
        } else {
            PostTask.e(Zo1.f9374a, new XT(a2));
        }
    }
}
