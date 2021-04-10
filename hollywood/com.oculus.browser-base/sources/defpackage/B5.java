package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl;

/* renamed from: B5  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B5 implements Runnable {
    public final /* synthetic */ AndroidOverlayProviderImpl F;

    public B5(AndroidOverlayProviderImpl androidOverlayProviderImpl) {
        this.F = androidOverlayProviderImpl;
    }

    public void run() {
        AndroidOverlayProviderImpl androidOverlayProviderImpl = this.F;
        int i = AndroidOverlayProviderImpl.F;
        Objects.requireNonNull(androidOverlayProviderImpl);
        Object obj = ThreadUtils.f10596a;
        androidOverlayProviderImpl.G--;
    }
}
