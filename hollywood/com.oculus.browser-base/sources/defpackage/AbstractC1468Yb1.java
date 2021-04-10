package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: Yb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1468Yb1 {
    public static void a(UH0 uh0, ViewGroup viewGroup, KH0 kh0) {
        ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = (ViewLookupCachingFrameLayout) viewGroup;
        if (kh0 == null) {
            for (KH0 kh02 : AbstractC5106ub1.C) {
                a(uh0, viewLookupCachingFrameLayout, kh02);
            }
            return;
        }
        QH0 qh0 = AbstractC5106ub1.h;
        if (qh0 == kh0) {
            ImageButton imageButton = (ImageButton) viewLookupCachingFrameLayout.d(R.id.tab_strip_item_button);
            viewLookupCachingFrameLayout.setForeground(uh0.h(qh0) ? viewLookupCachingFrameLayout.getResources().getDrawable(uh0.h(AbstractC5106ub1.n) ? R.drawable.f35190_resource_name_obfuscated_RES_2131231559 : R.drawable.f35180_resource_name_obfuscated_RES_2131231558, viewLookupCachingFrameLayout.getContext().getTheme()) : null);
            String str = (String) uh0.g(AbstractC5106ub1.g);
            if (uh0.h(qh0)) {
                imageButton.setOnClickListener(new View$OnClickListenerC1346Wb1(uh0));
                imageButton.setContentDescription(viewLookupCachingFrameLayout.getContext().getString(R.string.f46030_resource_name_obfuscated_RES_2131951920, str));
                imageButton.getBackground().setAlpha(0);
                return;
            }
            imageButton.setOnClickListener(new View$OnClickListenerC1407Xb1(uh0));
            imageButton.setContentDescription(viewLookupCachingFrameLayout.getContext().getString(R.string.f46110_resource_name_obfuscated_RES_2131951928, str));
            imageButton.getBackground().setAlpha(255);
            return;
        }
        TH0 th0 = AbstractC5106ub1.d;
        if (th0 == kh0) {
            Drawable drawable = (Drawable) uh0.g(th0);
            ImageButton imageButton2 = (ImageButton) viewLookupCachingFrameLayout.d(R.id.tab_strip_item_button);
            imageButton2.setBackgroundResource(R.drawable.f35200_resource_name_obfuscated_RES_2131231560);
            Context context = viewLookupCachingFrameLayout.getContext();
            int f = uh0.f(AbstractC5106ub1.p);
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            ColorStateList colorStateList = context.getColorStateList(f);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            imageButton2.setBackgroundTintList(colorStateList);
            if (!uh0.h(qh0)) {
                imageButton2.getBackground().setAlpha(255);
            } else {
                imageButton2.getBackground().setAlpha(0);
            }
            if (drawable != null) {
                imageButton2.setImageDrawable(drawable);
            }
        }
    }
}
