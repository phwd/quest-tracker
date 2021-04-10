package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import org.chromium.components.messages.MessageBannerView;

/* renamed from: jj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3252jj0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        MessageBannerView messageBannerView = (MessageBannerView) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC4619rj0.f11217a;
        if (kh0 == th0) {
            messageBannerView.L.setVisibility(0);
            messageBannerView.L.setText((String) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC4619rj0.q;
        if (kh0 == th02) {
            messageBannerView.L.setOnClickListener((View.OnClickListener) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC4619rj0.d;
        if (kh0 == th03) {
            messageBannerView.f10853J.setText((String) uh0.g(th03));
            return;
        }
        TH0 th04 = AbstractC4619rj0.e;
        if (kh0 == th04) {
            messageBannerView.K.setVisibility(0);
            messageBannerView.K.setText((String) uh0.g(th04));
            return;
        }
        TH0 th05 = AbstractC4619rj0.f;
        if (kh0 == th05) {
            messageBannerView.I.setImageDrawable((Drawable) uh0.g(th05));
            return;
        }
        SH0 sh0 = AbstractC4619rj0.g;
        if (kh0 == sh0) {
            messageBannerView.I.setImageDrawable(AbstractC5544x8.a(messageBannerView.getContext(), uh0.f(sh0)));
            return;
        }
        TH0 th06 = AbstractC4619rj0.h;
        if (kh0 == th06) {
            messageBannerView.M.setImageDrawable((Drawable) uh0.g(th06));
            messageBannerView.M.setVisibility(0);
            messageBannerView.N.setVisibility(0);
            return;
        }
        SH0 sh02 = AbstractC4619rj0.i;
        if (kh0 == sh02) {
            messageBannerView.M.setImageDrawable(AbstractC5544x8.a(messageBannerView.getContext(), uh0.f(sh02)));
            messageBannerView.M.setVisibility(0);
            messageBannerView.N.setVisibility(0);
            return;
        }
        TH0 th07 = AbstractC4619rj0.j;
        if (kh0 == th07) {
            messageBannerView.O = (String) uh0.g(th07);
            return;
        }
        TH0 th08 = AbstractC4619rj0.k;
        if (kh0 == th08) {
            messageBannerView.M.setContentDescription((String) uh0.g(th08));
            return;
        }
        TH0 th09 = AbstractC4619rj0.c;
        if (kh0 == th09) {
            messageBannerView.P = (Runnable) uh0.g(th09);
            return;
        }
        TH0 th010 = AbstractC4619rj0.p;
        if (kh0 == th010) {
            Runnable runnable = (Runnable) uh0.g(th010);
            if (runnable == null) {
                messageBannerView.setOnTouchListener(null);
            } else {
                messageBannerView.setOnTouchListener(new View$OnTouchListenerC5469wj0(runnable));
            }
        } else {
            RH0 rh0 = AbstractC4619rj0.o;
            if (kh0 == rh0) {
                messageBannerView.setAlpha(uh0.e(rh0));
                return;
            }
            RH0 rh02 = AbstractC4619rj0.m;
            if (kh0 == rh02) {
                messageBannerView.setTranslationX(uh0.e(rh02));
                return;
            }
            RH0 rh03 = AbstractC4619rj0.n;
            if (kh0 == rh03) {
                messageBannerView.setTranslationY(uh0.e(rh03));
            }
        }
    }
}
