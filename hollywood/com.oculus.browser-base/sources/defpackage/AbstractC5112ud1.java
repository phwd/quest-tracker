package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.display.DisplayAndroidManager;

/* renamed from: ud1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5112ud1 {
    public static Rect a(Context context) {
        Rect rect = new Rect();
        Point point = new Point();
        DisplayAndroidManager.a(context).getSize(point);
        Resources resources = context.getResources();
        try {
            point.y -= resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
        } catch (Resources.NotFoundException unused) {
        }
        rect.set(0, resources.getDimensionPixelSize(R.dimen.f17860_resource_name_obfuscated_RES_2131165405), point.x, point.y);
        return rect;
    }

    public static Activity b(Tab tab) {
        WindowAndroid I;
        WebContents l = tab != null ? tab.l() : null;
        if (l == null || l.g() || (I = l.I()) == null) {
            return null;
        }
        return (Activity) I.s0().get();
    }
}
