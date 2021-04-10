package defpackage;

import J.N;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.view.ViewConfiguration;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.ui.gfx.ViewConfigurationHelper;

/* renamed from: du1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ComponentCallbacksC2261du1 implements ComponentCallbacks {
    public final /* synthetic */ ViewConfigurationHelper F;

    public ComponentCallbacksC2261du1(ViewConfigurationHelper viewConfigurationHelper) {
        this.F = viewConfigurationHelper;
    }

    public void onConfigurationChanged(Configuration configuration) {
        ViewConfigurationHelper viewConfigurationHelper = this.F;
        Objects.requireNonNull(viewConfigurationHelper);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(ContextUtils.getApplicationContext());
        if (viewConfigurationHelper.f11025a != viewConfiguration) {
            viewConfigurationHelper.f11025a = viewConfiguration;
            viewConfigurationHelper.b = ContextUtils.getApplicationContext().getResources().getDisplayMetrics().density;
            N.MtrEpb2R(viewConfigurationHelper, viewConfigurationHelper.getMaximumFlingVelocity(), viewConfigurationHelper.getMinimumFlingVelocity(), viewConfigurationHelper.getTouchSlop(), viewConfigurationHelper.getDoubleTapSlop(), viewConfigurationHelper.getMinScalingSpan());
        }
    }

    public void onLowMemory() {
    }
}
