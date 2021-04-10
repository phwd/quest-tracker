package defpackage;

import J.N;
import android.content.Context;
import android.content.res.ColorStateList;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Vg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1300Vg1 {
    public static int a(Tab tab) {
        UL0 ul0;
        int i;
        if (tab.isNativePage()) {
            return ((IT) tab.Q()).c;
        }
        WebContents l = tab.l();
        if (l == null) {
            ul0 = null;
        } else {
            ul0 = l.s();
        }
        if (ul0 != null) {
            RenderWidgetHostViewImpl renderWidgetHostViewImpl = (RenderWidgetHostViewImpl) ul0;
            i = N.MRWsmoin(renderWidgetHostViewImpl.f10916a, renderWidgetHostViewImpl);
        } else {
            i = 0;
        }
        if (i != 0) {
            return i;
        }
        return AbstractC2934hr.b(tab.getContext().getResources(), false);
    }

    public static ColorStateList b(Context context, boolean z) {
        int c = c(z);
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        return context.getColorStateList(c);
    }

    public static int c(boolean z) {
        return z ? R.color.f11330_resource_name_obfuscated_RES_2131099823 : R.color.f15360_resource_name_obfuscated_RES_2131100226;
    }
}
