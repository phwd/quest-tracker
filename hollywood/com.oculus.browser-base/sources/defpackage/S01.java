package defpackage;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.toolbar.NewTabButton;
import org.chromium.chrome.browser.toolbar.top.StartSurfaceToolbarView;

/* renamed from: S01  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class S01 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        int i;
        UH0 uh0 = (UH0) obj;
        StartSurfaceToolbarView startSurfaceToolbarView = (StartSurfaceToolbarView) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = Z01.k;
        if (kh0 == qh0) {
            uh0.h(qh0);
            startSurfaceToolbarView.b();
            return;
        }
        QH0 qh02 = Z01.n;
        if (kh0 == qh02) {
            boolean h = uh0.h(qh02);
            startSurfaceToolbarView.F.setClickable(h);
            startSurfaceToolbarView.G.setClickable(h);
            return;
        }
        TH0 th0 = Z01.q;
        int i2 = 8;
        boolean z = false;
        if (kh0 == th0) {
            boolean booleanValue = ((Boolean) uh0.g(th0)).booleanValue();
            View view = startSurfaceToolbarView.G;
            if (booleanValue) {
                i2 = 0;
            }
            view.setVisibility(i2);
            return;
        }
        QH0 qh03 = Z01.p;
        if (kh0 == qh03) {
            uh0.h(qh03);
            startSurfaceToolbarView.c();
            return;
        }
        TH0 th02 = Z01.c;
        if (kh0 == th02) {
            startSurfaceToolbarView.I.setOnClickListener((View.OnClickListener) uh0.g(th02));
            return;
        }
        SH0 sh0 = Z01.e;
        if (kh0 == sh0) {
            startSurfaceToolbarView.I.setContentDescription(startSurfaceToolbarView.getContext().getResources().getString(uh0.f(sh0)));
            return;
        }
        TH0 th03 = Z01.d;
        if (kh0 == th03) {
            startSurfaceToolbarView.I.setImageDrawable((Drawable) uh0.g(th03));
            return;
        }
        QH0 qh04 = Z01.f;
        if (kh0 == qh04) {
            boolean h2 = uh0.h(qh04);
            ImageButton imageButton = startSurfaceToolbarView.I;
            if (h2) {
                i2 = 0;
            }
            imageButton.setVisibility(i2);
            return;
        }
        TH0 th04 = Z01.f9312a;
        if (kh0 == th04) {
            D00 d00 = (D00) uh0.g(th04);
            NewTabButton newTabButton = startSurfaceToolbarView.F;
            newTabButton.K = d00;
            d00.f7854a.b(newTabButton);
            newTabButton.c(d00.b());
            return;
        }
        QH0 qh05 = Z01.h;
        boolean z2 = true;
        if (kh0 == qh05) {
            boolean h3 = uh0.h(qh05);
            startSurfaceToolbarView.O = h3;
            if (!h3 || !startSurfaceToolbarView.N) {
                z2 = false;
            }
            startSurfaceToolbarView.d(z2, false);
            return;
        }
        QH0 qh06 = Z01.j;
        if (kh0 == qh06) {
            startSurfaceToolbarView.e(uh0.h(qh06));
            return;
        }
        QH0 qh07 = Z01.g;
        if (kh0 == qh07) {
            boolean h4 = uh0.h(qh07);
            startSurfaceToolbarView.N = h4;
            if (startSurfaceToolbarView.O && h4) {
                z = true;
            }
            startSurfaceToolbarView.d(z, true);
            return;
        }
        QH0 qh08 = Z01.i;
        if (kh0 == qh08) {
            boolean h5 = uh0.h(qh08);
            View view2 = startSurfaceToolbarView.H;
            if (h5) {
                i2 = 0;
            }
            view2.setVisibility(i2);
            return;
        }
        QH0 qh09 = Z01.l;
        if (kh0 == qh09) {
            boolean h6 = uh0.h(qh09);
            int dimensionPixelOffset = startSurfaceToolbarView.getContext().getResources().getDimensionPixelOffset(R.dimen.f25230_resource_name_obfuscated_RES_2131166142);
            if (h6) {
                i = dimensionPixelOffset;
            } else {
                i = startSurfaceToolbarView.getContext().getResources().getDimensionPixelOffset(R.dimen.f25240_resource_name_obfuscated_RES_2131166143);
            }
            startSurfaceToolbarView.I.setPadding(dimensionPixelOffset, 0, i, 0);
            startSurfaceToolbarView.F.setPadding(dimensionPixelOffset, 0, i, 0);
            return;
        }
        TH0 th05 = Z01.b;
        if (kh0 == th05) {
            startSurfaceToolbarView.F.setOnClickListener((View.OnClickListener) uh0.g(th05));
            return;
        }
        QH0 qh010 = Z01.r;
        if (kh0 == qh010) {
            boolean h7 = uh0.h(qh010);
            Objects.requireNonNull(startSurfaceToolbarView);
            if (h7) {
                ((RelativeLayout.LayoutParams) startSurfaceToolbarView.F.getLayoutParams()).removeRule(16);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) startSurfaceToolbarView.G.getLayoutParams();
                layoutParams.removeRule(20);
                layoutParams.addRule(14);
                return;
            }
            return;
        }
        QH0 qh011 = Z01.o;
        if (kh0 == qh011) {
            boolean h8 = uh0.h(qh011);
            NewTabButton newTabButton2 = startSurfaceToolbarView.F;
            if (newTabButton2 != null) {
                if (h8) {
                    AbstractC3628lu1.c(newTabButton2);
                } else {
                    AbstractC3628lu1.b(newTabButton2);
                }
            }
        } else {
            QH0 qh012 = Z01.m;
            if (kh0 == qh012) {
                boolean h9 = uh0.h(qh012);
                NewTabButton newTabButton3 = startSurfaceToolbarView.F;
                if (h9) {
                    i2 = 0;
                }
                newTabButton3.setVisibility(i2);
                if (h9 && Build.VERSION.SDK_INT < 28) {
                    ViewParent parent = startSurfaceToolbarView.F.getParent();
                    NewTabButton newTabButton4 = startSurfaceToolbarView.F;
                    parent.requestChildFocus(newTabButton4, newTabButton4);
                    return;
                }
                return;
            }
            RH0 rh0 = Z01.s;
            if (kh0 == rh0) {
                startSurfaceToolbarView.setTranslationY(uh0.e(rh0));
            }
        }
    }
}
