package defpackage;

import android.app.Activity;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.locale.LocaleManager;

/* renamed from: ga0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2713ga0 implements Runnable {
    public final /* synthetic */ Activity F;
    public final /* synthetic */ Callback G;
    public final /* synthetic */ LocaleManager H;

    public RunnableC2713ga0(LocaleManager localeManager, Activity activity, Callback callback) {
        this.H = localeManager;
        this.F = activity;
        this.G = callback;
    }

    public void run() {
        LocaleManager localeManager = this.H;
        Callback callback = this.G;
        Objects.requireNonNull(localeManager);
        if (AbstractC0444Hf1.a().f() || AbstractC3153j7.f()) {
            Boolean bool = Boolean.TRUE;
            if (bool != null) {
                localeManager.b = true;
            }
            if (callback != null) {
                callback.onResult(bool);
                return;
            }
            return;
        }
        Boolean bool2 = Boolean.TRUE;
        if (bool2 != null) {
            localeManager.b = true;
        }
        if (callback != null) {
            callback.onResult(bool2);
        }
    }
}
