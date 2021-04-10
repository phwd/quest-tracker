package defpackage;

import android.app.Activity;
import android.content.Intent;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: kw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3463kw1 {
    public boolean a(Activity activity, Intent intent) {
        Objects.requireNonNull((C2609fw1) VrModuleProvider.b());
        if (ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.vr.high_performance") && b(intent)) {
            Objects.requireNonNull(VrModuleProvider.b());
            if (activity instanceof AbstractActivityC2601fu) {
                return true;
            }
        }
        return false;
    }

    public boolean b(Intent intent) {
        return intent != null && intent.hasCategory("com.google.intent.category.DAYDREAM") && (intent.getFlags() & 1048576) == 0;
    }
}
