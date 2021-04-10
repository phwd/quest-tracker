package defpackage;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: V91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class V91 {
    public static void a(UH0 uh0, ViewGroup viewGroup, KH0 kh0) {
        TH0 th0 = AbstractC5106ub1.g;
        if (th0 == kh0) {
            ((TextView) viewGroup.findViewById(R.id.title)).setText((String) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC5106ub1.d;
        if (th02 == kh0) {
            ImageView imageView = (ImageView) viewGroup.findViewById(R.id.start_icon);
            imageView.setBackgroundResource(R.drawable.f33510_resource_name_obfuscated_RES_2131231391);
            imageView.setImageDrawable((Drawable) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC5106ub1.c;
        InsetDrawable insetDrawable = null;
        if (th03 != kh0) {
            QH0 qh0 = AbstractC5106ub1.h;
            if (qh0 == kh0) {
                int f = uh0.f(AbstractC5106ub1.o);
                Resources resources = viewGroup.getResources();
                InsetDrawable insetDrawable2 = new InsetDrawable(resources.getDrawable(f, viewGroup.getContext().getTheme()), (int) resources.getDimension(R.dimen.f25660_resource_name_obfuscated_RES_2131166185));
                if (uh0.h(qh0)) {
                    insetDrawable = insetDrawable2;
                }
                viewGroup.setForeground(insetDrawable);
                return;
            }
            TH0 th04 = AbstractC5106ub1.b;
            if (th04 != kh0) {
                TH0 th05 = AbstractC5106ub1.s;
                if (th05 == kh0) {
                    ((TextView) viewGroup.findViewById(R.id.description)).setText((String) uh0.g(th05));
                }
            } else if (uh0.g(th04) == null) {
                viewGroup.setOnClickListener(null);
            } else {
                viewGroup.setOnClickListener(new S91(uh0));
            }
        } else if (uh0.g(th03) == null) {
            viewGroup.findViewById(R.id.end_button).setOnClickListener(null);
        } else {
            viewGroup.findViewById(R.id.end_button).setOnClickListener(new R91(uh0));
        }
    }
}
