package defpackage;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.suggestions.tile.SuggestionsTileView;
import org.chromium.chrome.browser.suggestions.tile.TopSitesTileView;
import org.chromium.url.GURL;

/* renamed from: Kl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0642Kl0 implements AbstractC1303Vh1 {
    public final ChromeActivity F;
    public final ViewGroup G;
    public final Q31 H;
    public C1425Xh1 I;

    /* renamed from: J  reason: collision with root package name */
    public C4105oi1 f8385J;
    public O31 K;

    public C0642Kl0(ChromeActivity chromeActivity, ViewGroup viewGroup, UH0 uh0, Q31 q31) {
        this.F = chromeActivity;
        this.G = viewGroup;
        ZH0.a(uh0, viewGroup, new C0398Gl0());
        this.H = q31;
    }

    @Override // defpackage.AbstractC1303Vh1
    public void a(C0815Nh1 nh1) {
    }

    @Override // defpackage.AbstractC1303Vh1
    public void b() {
    }

    @Override // defpackage.AbstractC1303Vh1
    public void c(C0815Nh1 nh1) {
    }

    @Override // defpackage.AbstractC1303Vh1
    public void d() {
        if (this.I.g.size() >= 1) {
            C4105oi1 oi1 = this.f8385J;
            List<C0815Nh1> list = (List) this.I.g.get(1);
            ViewGroup viewGroup = this.G;
            Objects.requireNonNull(oi1);
            HashMap hashMap = new HashMap();
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                SuggestionsTileView suggestionsTileView = (SuggestionsTileView) viewGroup.getChildAt(i);
                hashMap.put(suggestionsTileView.f10769J, suggestionsTileView);
            }
            viewGroup.removeAllViews();
            for (C0815Nh1 nh1 : list) {
                SuggestionsTileView suggestionsTileView2 = (SuggestionsTileView) hashMap.get(nh1.f8567a);
                if (suggestionsTileView2 == null) {
                    if (nh1.f8567a.e == 7) {
                        suggestionsTileView2 = (TopSitesTileView) LayoutInflater.from(viewGroup.getContext()).inflate(oi1.i, viewGroup, false);
                        nh1.e = Fs1.a(oi1.f10570a, R.drawable.f29530_resource_name_obfuscated_RES_2131230993, oi1.d);
                        nh1.c = 3;
                        N.Mz5zXINc(Profile.b(), oi1.e, new C3763mi1(new C0459Hl0(this, nh1)));
                    } else {
                        suggestionsTileView2 = (SuggestionsTileView) LayoutInflater.from(viewGroup.getContext()).inflate(oi1.h, viewGroup, false);
                    }
                    Objects.requireNonNull(suggestionsTileView2);
                    SX0 sx0 = nh1.f8567a;
                    String str = sx0.f8898a;
                    GURL gurl = sx0.b;
                    if (TextUtils.isEmpty(str) && gurl != null && !GURL.k(gurl)) {
                        String d = gurl.d();
                        String str2 = "";
                        if (d == null) {
                            d = str2;
                        }
                        String f = gurl.f();
                        if (f != null && !f.equals("/")) {
                            str2 = f;
                        }
                        str = AbstractC2531fV.f(d, str2);
                    }
                    boolean a2 = nh1.a();
                    Drawable drawable = nh1.e;
                    suggestionsTileView2.F.setVisibility(a2 ? 0 : 8);
                    suggestionsTileView2.I.setImageDrawable(drawable);
                    suggestionsTileView2.G.setLines(1);
                    suggestionsTileView2.G.setText(str);
                    suggestionsTileView2.f10769J = nh1.f8567a;
                    suggestionsTileView2.a(nh1);
                    SX0 sx02 = nh1.f8567a;
                    if (sx02.e != 7) {
                        C0459Hl0 hl0 = new C0459Hl0(this, nh1);
                        if (sx02.c.isEmpty()) {
                            oi1.b.a(sx02.b, oi1.f, hl0);
                        } else {
                            C3934ni1 ni1 = new C3934ni1(oi1, sx02, hl0);
                            Executor executor = AbstractC2032cb.f9616a;
                            ni1.f();
                            ((ExecutorC1463Ya) executor).execute(ni1.e);
                        }
                    }
                    View$OnClickListenerC0581Jl0 jl0 = new View$OnClickListenerC0581Jl0(nh1, this.H);
                    int i2 = nh1.f8567a.e;
                    suggestionsTileView2.setOnClickListener(jl0);
                    suggestionsTileView2.setOnCreateContextMenuListener(jl0);
                    if (nh1.f8567a.e == 7) {
                        Profile b = Profile.b();
                        Context context = suggestionsTileView2.getContext();
                        if (context instanceof ChromeActivity) {
                            ChromeActivity chromeActivity = (ChromeActivity) context;
                            if (suggestionsTileView2.isAttachedToWindow()) {
                                CM.a(suggestionsTileView2, b, chromeActivity);
                            } else {
                                suggestionsTileView2.addOnAttachStateChangeListener(new AM(suggestionsTileView2, b, chromeActivity));
                            }
                        }
                    }
                }
                viewGroup.addView(suggestionsTileView2);
            }
        }
    }
}
