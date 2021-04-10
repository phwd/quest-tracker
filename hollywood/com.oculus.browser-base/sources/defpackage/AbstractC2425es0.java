package defpackage;

import android.content.SharedPreferences;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.AppHooks;

/* renamed from: es0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2425es0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2767gs0 f9886a;

    public AbstractC2425es0(AbstractC2767gs0 gs0) {
        this.f9886a = gs0;
    }

    public static SharedPreferences e() {
        return ContextUtils.getApplicationContext().getSharedPreferences("com.google.android.apps.chrome.omaha", 0);
    }

    public final void d() {
        Objects.requireNonNull(this.f9886a);
        AppHooks.get().d();
    }
}
