package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;

/* renamed from: Jk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Jk1 implements ComponentCallbacks {
    public final /* synthetic */ Uk1 F;

    public Jk1(Uk1 uk1) {
        this.F = uk1;
    }

    public void onConfigurationChanged(Configuration configuration) {
        int i = configuration.orientation;
        Uk1 uk1 = this.F;
        if (i != uk1.P0) {
            uk1.P0 = i;
            G2 g2 = uk1.p0;
            if (g2 != null && g2.d && g2.c == null) {
                g2.c();
            }
        }
    }

    public void onLowMemory() {
    }
}
