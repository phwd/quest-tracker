package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* renamed from: pk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract /* synthetic */ class AbstractC4280pk implements AbstractC4621rk {
    public static AbstractC4621rk a() {
        Object obj = ThreadUtils.f10596a;
        if (BrowserStartupControllerImpl.f10909a == null) {
            BrowserStartupControllerImpl.f10909a = new BrowserStartupControllerImpl();
        }
        return BrowserStartupControllerImpl.f10909a;
    }
}
