package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.omnibox.status.StatusView;

/* renamed from: G21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G21 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        int i;
        UH0 uh0 = (UH0) obj;
        StatusView statusView = (StatusView) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = A21.f7651a;
        if (qh0.equals(kh0)) {
            statusView.O = uh0.h(qh0);
            return;
        }
        QH0 qh02 = A21.b;
        int i2 = 0;
        if (qh02.equals(kh0)) {
            boolean h = uh0.h(qh02);
            View view = statusView.F;
            if (view != null || h) {
                if (view == null) {
                    statusView.F = ((ViewStub) statusView.findViewById(R.id.location_bar_incognito_badge_stub)).inflate();
                    statusView.G = statusView.getResources().getDimensionPixelSize(R.dimen.f20440_resource_name_obfuscated_RES_2131165663);
                    statusView.H = statusView.getResources().getDimensionPixelSize(R.dimen.f20450_resource_name_obfuscated_RES_2131165664);
                    statusView.d();
                }
                View view2 = statusView.F;
                if (!h) {
                    i2 = 8;
                }
                view2.setVisibility(i2);
                statusView.b();
                return;
            }
            return;
        }
        SH0 sh0 = A21.c;
        if (sh0.equals(kh0)) {
            statusView.M.setBackgroundColor(statusView.getResources().getColor(uh0.f(sh0)));
            return;
        }
        QH0 qh03 = A21.d;
        if (qh03.equals(kh0)) {
            boolean h2 = uh0.h(qh03);
            if (statusView.K != null) {
                AbstractC4422qa0 qa0 = statusView.b0;
                if (qa0 != null) {
                    F21 f21 = statusView.V;
                    boolean a2 = qa0.a();
                    Objects.requireNonNull(f21);
                    if (!AbstractC5762yQ0.g(a2)) {
                        return;
                    }
                }
                ImageView imageView = statusView.K;
                if (!h2) {
                    i2 = 8;
                }
                imageView.setVisibility(i2);
                statusView.b();
                return;
            }
            return;
        }
        TH0 th0 = A21.e;
        if (th0.equals(kh0)) {
            View.OnClickListener onClickListener = (View.OnClickListener) uh0.g(th0);
            statusView.K.setOnClickListener(onClickListener);
            statusView.L.setOnClickListener(onClickListener);
            return;
        }
        SH0 sh02 = A21.f;
        if (sh02.equals(kh0)) {
            statusView.R = uh0.f(sh02);
            return;
        }
        RH0 rh0 = A21.g;
        if (rh0.equals(kh0)) {
            float e = uh0.e(rh0);
            ImageView imageView2 = statusView.K;
            if (imageView2 != null) {
                imageView2.setAlpha(e);
                return;
            }
            return;
        }
        SH0 sh03 = A21.h;
        Drawable drawable = null;
        String str = null;
        if (sh03.equals(kh0)) {
            int f = uh0.f(sh03);
            if (f != 0) {
                str = statusView.getResources().getString(f);
                i = 1;
            } else {
                i = 2;
            }
            statusView.K.setContentDescription(str);
            statusView.K.setImportantForAccessibility(i);
            return;
        }
        TH0 th02 = A21.i;
        if (th02.equals(kh0)) {
            C5868z21 z21 = (C5868z21) uh0.g(th02);
            if (z21 == null) {
                statusView.c(null);
                return;
            }
            Context context = statusView.getContext();
            Resources resources = statusView.getResources();
            if (z21.c != null) {
                drawable = new BitmapDrawable(resources, z21.c);
                int i3 = z21.b;
                if (i3 != 0) {
                    ThreadLocal threadLocal = AbstractC5544x8.f11592a;
                    drawable.setTintList(context.getColorStateList(i3));
                }
            } else {
                Integer num = z21.f11717a;
                if (num != null) {
                    drawable = z21.b == 0 ? AbstractC5544x8.a(context, num.intValue()) : AbstractC2417ep1.f(context, num.intValue(), z21.b);
                }
            }
            statusView.c(drawable);
            return;
        }
        SH0 sh04 = A21.j;
        if (sh04.equals(kh0)) {
            statusView.L.setTextColor(statusView.getResources().getColor(uh0.f(sh04)));
            return;
        }
        SH0 sh05 = A21.k;
        if (sh05.equals(kh0)) {
            statusView.L.setText(uh0.f(sh05));
            return;
        }
        QH0 qh04 = A21.l;
        if (qh04.equals(kh0)) {
            if (!uh0.h(qh04)) {
                i2 = 8;
            }
            statusView.L.setVisibility(i2);
            statusView.M.setVisibility(i2);
            statusView.N.setVisibility(i2);
            return;
        }
        SH0 sh06 = A21.m;
        if (sh06.equals(kh0)) {
            statusView.L.setMaxWidth(uh0.f(sh06));
        }
    }
}
