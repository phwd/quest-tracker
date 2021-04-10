package defpackage;

import com.oculus.browser.VrShellDelegate;
import com.oculus.browser.VrShellImpl;
import com.oculus.vrapi.SystemProps;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: tw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC4999tw1 implements Runnable {
    public final /* synthetic */ VrShellDelegate F;

    public RunnableC4999tw1(VrShellDelegate vrShellDelegate) {
        this.F = vrShellDelegate;
    }

    public void run() {
        if (this.F.L) {
            boolean hmtMounted = SystemProps.getHmtMounted();
            VrShellDelegate vrShellDelegate = this.F;
            if (vrShellDelegate.W != hmtMounted) {
                if (hmtMounted) {
                    Objects.requireNonNull(vrShellDelegate);
                    AbstractC1220Ua0.d("VrShellDelegate.Oculus", "Device mounted.", new Object[0]);
                    VrShellImpl vrShellImpl = vrShellDelegate.K;
                    if (!(vrShellImpl == null || vrShellImpl.b == null)) {
                        vrShellDelegate.p();
                    }
                } else {
                    Objects.requireNonNull(vrShellDelegate);
                    AbstractC1220Ua0.d("VrShellDelegate.Oculus", "Device unmounted.", new Object[0]);
                    VrShellImpl vrShellImpl2 = vrShellDelegate.K;
                    if (!(vrShellImpl2 == null || vrShellImpl2.b == null)) {
                        vrShellDelegate.o();
                    }
                }
                this.F.W = hmtMounted;
            }
            ThreadUtils.e(this, 500);
        }
    }
}
