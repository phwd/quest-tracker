package defpackage;

import android.content.Context;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: iq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3101iq implements AbstractC2675gI0 {
    public static Context a(C2931hq hqVar) {
        Objects.requireNonNull(hqVar);
        Context applicationContext = ContextUtils.getApplicationContext();
        Objects.requireNonNull(applicationContext, "Cannot return null from a non-@Nullable @Provides method");
        return applicationContext;
    }
}
