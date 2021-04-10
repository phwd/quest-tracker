package org.chromium.ui.gfx;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewConfigurationHelper {

    /* renamed from: a  reason: collision with root package name */
    public ViewConfiguration f11025a;
    public float b;

    public ViewConfigurationHelper() {
        P21 Y = P21.Y();
        try {
            this.f11025a = ViewConfiguration.get(ContextUtils.getApplicationContext());
            Y.close();
            this.b = ContextUtils.getApplicationContext().getResources().getDisplayMetrics().density;
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static ViewConfigurationHelper createWithListener() {
        ViewConfigurationHelper viewConfigurationHelper = new ViewConfigurationHelper();
        ContextUtils.getApplicationContext().registerComponentCallbacks(new ComponentCallbacksC2261du1(viewConfigurationHelper));
        return viewConfigurationHelper;
    }

    public static int getDoubleTapTimeout() {
        return ViewConfiguration.getDoubleTapTimeout();
    }

    public static int getLongPressTimeout() {
        return ViewConfiguration.getLongPressTimeout();
    }

    public static int getTapTimeout() {
        return ViewConfiguration.getTapTimeout();
    }

    public final float a(int i) {
        return ((float) i) / this.b;
    }

    public final float getDoubleTapSlop() {
        return a(this.f11025a.getScaledDoubleTapSlop());
    }

    public final float getMaximumFlingVelocity() {
        return a(this.f11025a.getScaledMaximumFlingVelocity());
    }

    public final float getMinScalingSpan() {
        int i;
        Resources resources = ContextUtils.getApplicationContext().getResources();
        try {
            i = resources.getDimensionPixelSize(R.dimen.f17630_resource_name_obfuscated_RES_2131165382);
        } catch (Resources.NotFoundException unused) {
            i = (int) TypedValue.applyDimension(5, 12.0f, resources.getDisplayMetrics());
        }
        return a(i);
    }

    public final float getMinimumFlingVelocity() {
        return a(this.f11025a.getScaledMinimumFlingVelocity());
    }

    public final float getTouchSlop() {
        return a(this.f11025a.getScaledTouchSlop());
    }
}
