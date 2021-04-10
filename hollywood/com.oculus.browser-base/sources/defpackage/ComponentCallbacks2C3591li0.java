package defpackage;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* renamed from: li0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ComponentCallbacks2C3591li0 implements ComponentCallbacks2 {
    public final /* synthetic */ C3762mi0 F;

    public ComponentCallbacks2C3591li0(C3762mi0 mi0) {
        this.F = mi0;
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
        this.F.c(2);
    }

    public void onTrimMemory(int i) {
        Integer b = C3762mi0.b(i);
        if (b != null) {
            this.F.c(b.intValue());
        }
    }
}
