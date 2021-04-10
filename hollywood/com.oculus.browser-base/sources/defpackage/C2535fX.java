package defpackage;

import android.app.Activity;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.feedback.ScreenshotTask;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: fX  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2535fX {

    /* renamed from: a  reason: collision with root package name */
    public static C2535fX f9926a;

    public static C2535fX a() {
        Object obj = ThreadUtils.f10596a;
        if (f9926a == null) {
            Objects.requireNonNull(AppHooks.get());
            f9926a = new C2535fX();
        }
        return f9926a;
    }

    public void b(Activity activity, String str, Profile profile, String str2) {
        AbstractC3535lK0.a("MobileHelpAndFeedback");
        new RunnableC0165Cr(activity, null, null, new ScreenshotTask(activity), new C0104Br(profile, str2, str), new C2364eX(this, activity, str));
    }
}
