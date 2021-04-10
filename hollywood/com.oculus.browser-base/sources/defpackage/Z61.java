package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;

/* renamed from: Z61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Z61 implements ComponentCallbacks {
    public final /* synthetic */ C1789b71 F;

    public Z61(C1789b71 b71) {
        this.F = b71;
    }

    public void onConfigurationChanged(Configuration configuration) {
        O4 o4 = this.F.d;
        if (o4 != null && o4.c()) {
            this.F.d.K.dismiss();
        }
    }

    public void onLowMemory() {
    }
}
