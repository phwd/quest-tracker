package defpackage;

import android.graphics.Canvas;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: t40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4853t40 {

    /* renamed from: a  reason: collision with root package name */
    public static final Interpolator f11320a = new animation.InterpolatorC4512r40();
    public static final Interpolator b = new animation.InterpolatorC4683s40();
    public int c = -1;

    public static int d(int i, int i2) {
        int i3;
        int i4 = i & 789516;
        if (i4 == 0) {
            return i;
        }
        int i5 = i & (~i4);
        if (i2 == 0) {
            i3 = i4 << 2;
        } else {
            int i6 = i4 << 1;
            i5 |= -789517 & i6;
            i3 = (i6 & 789516) << 2;
        }
        return i5 | i3;
    }

    public static int j(int i, int i2) {
        return (i << 16) | (i2 << 8) | ((i2 | i) << 0);
    }

    public abstract boolean a(RecyclerView recyclerView, XK0 xk0, XK0 xk02);

    public void b(RecyclerView recyclerView, XK0 xk0) {
        View view = xk0.G;
        Object tag = view.getTag(R.id.item_touch_helper_previous_elevation);
        if (tag instanceof Float) {
            float floatValue = ((Float) tag).floatValue();
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            view.setElevation(floatValue);
        }
        view.setTag(R.id.item_touch_helper_previous_elevation, null);
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    public int c(int i, int i2) {
        int i3;
        int i4 = i & 3158064;
        if (i4 == 0) {
            return i;
        }
        int i5 = i & (~i4);
        if (i2 == 0) {
            i3 = i4 >> 2;
        } else {
            int i6 = i4 >> 1;
            i5 |= -3158065 & i6;
            i3 = (i6 & 3158064) >> 2;
        }
        return i5 | i3;
    }

    public final int e(RecyclerView recyclerView, XK0 xk0) {
        int f = f(recyclerView, xk0);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        return c(f, recyclerView.getLayoutDirection());
    }

    public abstract int f(RecyclerView recyclerView, XK0 xk0);

    public float g(XK0 xk0) {
        return 0.5f;
    }

    public int h(RecyclerView recyclerView, int i, int i2, long j) {
        if (this.c == -1) {
            this.c = recyclerView.getResources().getDimensionPixelSize(R.dimen.f20090_resource_name_obfuscated_RES_2131165628);
        }
        int i3 = this.c;
        float f = 1.0f;
        int interpolation = (int) (((animation.InterpolatorC4683s40) b).getInterpolation(Math.min(1.0f, (((float) Math.abs(i2)) * 1.0f) / ((float) i))) * ((float) (((int) Math.signum((float) i2)) * i3)));
        if (j <= 2000) {
            f = ((float) j) / 2000.0f;
        }
        int interpolation2 = (int) (((animation.InterpolatorC4512r40) f11320a).getInterpolation(f) * ((float) interpolation));
        if (interpolation2 != 0) {
            return interpolation2;
        }
        if (i2 > 0) {
            return 1;
        }
        return -1;
    }

    public boolean i() {
        return true;
    }

    public void k(Canvas canvas, RecyclerView recyclerView, XK0 xk0, float f, float f2, int i, boolean z) {
        View view = xk0.G;
        if (z && view.getTag(R.id.item_touch_helper_previous_elevation) == null) {
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            Float valueOf = Float.valueOf(view.getElevation());
            int childCount = recyclerView.getChildCount();
            float f3 = 0.0f;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = recyclerView.getChildAt(i2);
                if (childAt != view) {
                    AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                    float elevation = childAt.getElevation();
                    if (elevation > f3) {
                        f3 = elevation;
                    }
                }
            }
            view.setElevation(f3 + 1.0f);
            view.setTag(R.id.item_touch_helper_previous_elevation, valueOf);
        }
        view.setTranslationX(f);
        view.setTranslationY(f2);
    }

    public abstract boolean l(RecyclerView recyclerView, XK0 xk0, XK0 xk02);

    public abstract void m(XK0 xk0, int i);

    public abstract void n(XK0 xk0, int i);
}
