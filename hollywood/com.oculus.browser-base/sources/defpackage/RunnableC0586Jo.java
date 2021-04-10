package defpackage;

import J.N;
import org.chromium.base.JavaExceptionReporter;
import org.chromium.base.PiiElider;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* renamed from: Jo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0586Jo implements Runnable {
    public final String F;

    public RunnableC0586Jo(String str) {
        this.F = str;
    }

    public void run() {
        String str = this.F;
        boolean z = ChildProcessLauncherHelperImpl.f10910a;
        int i = JavaExceptionReporter.f10588a;
        N.MmS4zlEt(PiiElider.sanitizeStacktrace(str));
    }
}
