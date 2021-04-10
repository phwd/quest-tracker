package defpackage;

import org.chromium.base.ContextUtils;
import org.chromium.base.task.PostTask;

/* renamed from: uG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5059uG0 implements Runnable {
    public void run() {
        PostTask.b(C3070if1.b, new RunnableC4207pG0(ContextUtils.getApplicationContext().createDeviceProtectedStorageContext()), 0);
    }
}
