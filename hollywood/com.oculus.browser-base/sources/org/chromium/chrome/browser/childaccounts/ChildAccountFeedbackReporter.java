package org.chromium.chrome.browser.childaccounts;

import android.app.Activity;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.feedback.ScreenshotTask;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ChildAccountFeedbackReporter {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC4909tP f10630a;

    public static void reportFeedbackWithWindow(WindowAndroid windowAndroid, String str, String str2, Profile profile) {
        Activity activity = (Activity) windowAndroid.s0().get();
        Object obj = ThreadUtils.f10596a;
        if (f10630a == null) {
            AppHooks appHooks = AppHooks.get();
            Objects.requireNonNull(appHooks);
            f10630a = new V8(appHooks);
        }
        new RunnableC0165Cr(activity, null, str, new ScreenshotTask(activity), new C0104Br(profile, str2, null), new C0888On());
    }
}
