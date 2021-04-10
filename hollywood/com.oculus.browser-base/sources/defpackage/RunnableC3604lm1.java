package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.firstrun.TosAndUmaFirstRunFragmentWithEnterpriseSupport;

/* renamed from: lm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3604lm1 implements Runnable {
    public final TosAndUmaFirstRunFragmentWithEnterpriseSupport F;

    public RunnableC3604lm1(TosAndUmaFirstRunFragmentWithEnterpriseSupport tosAndUmaFirstRunFragmentWithEnterpriseSupport) {
        this.F = tosAndUmaFirstRunFragmentWithEnterpriseSupport;
    }

    public void run() {
        TosAndUmaFirstRunFragmentWithEnterpriseSupport tosAndUmaFirstRunFragmentWithEnterpriseSupport = this.F;
        Objects.requireNonNull(tosAndUmaFirstRunFragmentWithEnterpriseSupport);
        TQ.a(tosAndUmaFirstRunFragmentWithEnterpriseSupport).s();
    }
}
