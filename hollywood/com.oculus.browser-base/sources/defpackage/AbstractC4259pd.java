package defpackage;

import J.N;
import org.chromium.content_public.browser.WebContents;

/* renamed from: pd  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4259pd {
    public static void a(int i) {
        AbstractC3364kK0.g("Android.AutofillAssistant.FeatureModuleInstallation", i, 4);
    }

    public static void b(WebContents webContents, int i) {
        if (webContents != null && !webContents.g()) {
            N.M5aNQ$DO(webContents, "AutofillAssistant.LiteScriptStarted", "LiteScriptStarted", i);
        }
    }
}
