package defpackage;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import androidx.recyclerview.widget.GridLayoutManager;

/* renamed from: j91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ComponentCallbacksC3161j91 implements ComponentCallbacks {
    public final /* synthetic */ GridLayoutManager F;
    public final /* synthetic */ I91 G;

    public ComponentCallbacksC3161j91(I91 i91, GridLayoutManager gridLayoutManager) {
        this.G = i91;
        this.F = gridLayoutManager;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.G.n(this.F, configuration.orientation);
        I91 i91 = this.G;
        if (i91.h == 0 && i91.w != 0) {
            i91.m();
        }
    }

    public void onLowMemory() {
    }
}
