package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.oculus.browser.R;

/* renamed from: Hk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0457Hk0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        Integer num;
        UH0 uh0 = (UH0) obj;
        C1371Wk0 wk0 = (C1371Wk0) obj2;
        KH0 kh0 = (KH0) obj3;
        OH0 oh0 = AbstractC1310Vk0.f9101a;
        if (oh0 == kh0) {
            wk0.g.setImageDrawable((Drawable) uh0.g(oh0));
            return;
        }
        OH0 oh02 = AbstractC1310Vk0.d;
        if (oh02 == kh0) {
            wk0.o.setText((CharSequence) uh0.g(oh02));
            return;
        }
        OH0 oh03 = AbstractC1310Vk0.c;
        if (oh03 == kh0) {
            CharSequence charSequence = (CharSequence) uh0.g(oh03);
            wk0.r.setText(charSequence);
            wk0.l.setText(charSequence);
            wk0.j.setText(charSequence);
            return;
        }
        OH0 oh04 = AbstractC1310Vk0.b;
        if (oh04 == kh0) {
            CharSequence charSequence2 = (CharSequence) uh0.g(oh04);
            wk0.q.setText(charSequence2);
            wk0.k.setText(charSequence2);
            return;
        }
        TH0 th0 = AbstractC1310Vk0.n;
        if (th0 == kh0) {
            wk0.i.setText((CharSequence) uh0.g(th0));
            return;
        }
        RH0 rh0 = AbstractC1310Vk0.j;
        float f = 0.0f;
        int i = 8;
        if (rh0 == kh0) {
            float e = uh0.e(rh0);
            if (e != 0.0f) {
                wk0.o.setAlpha(e);
                wk0.n.setVisibility(8);
                wk0.p.setVisibility(8);
                wk0.q.setVisibility(8);
                wk0.r.setVisibility(8);
                wk0.e.setVisibility(8);
                wk0.w.setVisibility(8);
                wk0.h.setVisibility(8);
                return;
            }
            return;
        }
        QH0 qh0 = AbstractC1310Vk0.g;
        if (qh0 == kh0) {
            boolean h = uh0.h(qh0);
            wk0.d.setVisibility(h ? 0 : 8);
            int i2 = h ? 8 : 0;
            wk0.f.setVisibility(i2);
            wk0.m.setVisibility(i2);
            float e2 = uh0.e(rh0);
            wk0.e.setVisibility((!h || e2 != 0.0f) ? 8 : 0);
            if (!h && e2 <= 0.0f) {
                i = 0;
            }
            wk0.h.setVisibility(i);
            return;
        }
        QH0 qh02 = AbstractC1310Vk0.h;
        if (qh02 != kh0) {
            TH0 th02 = AbstractC1310Vk0.o;
            if (th02 == kh0) {
                CharSequence charSequence3 = (CharSequence) uh0.g(th02);
                if (charSequence3 != null) {
                    wk0.m.setText(charSequence3);
                    wk0.n.setText(charSequence3);
                    wk0.p.setText(charSequence3);
                    return;
                }
                return;
            }
            TH0 th03 = AbstractC1310Vk0.m;
            if (th03 != kh0) {
                TH0 th04 = AbstractC1310Vk0.k;
                if (th04 == kh0) {
                    Integer num2 = (Integer) uh0.g(th04);
                    if (num2 != null) {
                        wk0.f.setImageResource(num2.intValue());
                        wk0.h.setImageResource(num2.intValue());
                        return;
                    }
                    return;
                }
                TH0 th05 = AbstractC1310Vk0.l;
                if (th05 == kh0) {
                    Integer num3 = (Integer) uh0.g(th05);
                    if (num3 != null) {
                        ImageView imageView = wk0.h;
                        Context context = wk0.c;
                        int intValue = num3.intValue();
                        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
                        imageView.setImageTintList(context.getColorStateList(intValue));
                        wk0.f.setImageTintList(wk0.c.getColorStateList(num3.intValue()));
                        return;
                    }
                    return;
                }
                QH0 qh03 = AbstractC1310Vk0.f;
                if (qh03 == kh0) {
                    boolean h2 = uh0.h(qh03);
                    wk0.t.setVisibility(h2 ? 0 : 8);
                    float e3 = uh0.e(rh0);
                    if (h2 && e3 == 0.0f) {
                        i = 0;
                    }
                    wk0.w.setVisibility(i);
                    return;
                }
                QH0 qh04 = AbstractC1310Vk0.i;
                if (qh04 == kh0) {
                    boolean h3 = uh0.h(qh04);
                    if (h3) {
                        i = 0;
                    }
                    wk0.j.setVisibility(i);
                    wk0.s.setVisibility(i);
                    wk0.i.setVisibility(i);
                    wk0.k.setVisibility(i);
                    wk0.l.setVisibility(i);
                    wk0.u.setVisibility(i);
                    wk0.v.setVisibility(i);
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) wk0.f.getLayoutParams();
                    marginLayoutParams.setMargins(marginLayoutParams.leftMargin, wk0.c.getResources().getDimensionPixelSize(h3 ? R.dimen.f23860_resource_name_obfuscated_RES_2131166005 : R.dimen.f23870_resource_name_obfuscated_RES_2131166006), marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
                    wk0.t.setLayoutParams(marginLayoutParams);
                    wk0.f.setLayoutParams(marginLayoutParams);
                    return;
                }
                QH0 qh05 = AbstractC1310Vk0.e;
                if (qh05 == kh0) {
                    wk0.x = uh0.h(qh05);
                }
            } else if (((CharSequence) uh0.g(th02)) == null && (num = (Integer) uh0.g(th03)) != null) {
                wk0.m.setText(num.intValue());
                wk0.n.setText(num.intValue());
                wk0.p.setText(num.intValue());
            }
        } else if (uh0.e(rh0) <= 0.0f) {
            boolean h4 = uh0.h(qh02);
            float f2 = h4 ? 0.0f : 1.0f;
            wk0.p.setAlpha(f2);
            wk0.q.setAlpha(f2);
            wk0.r.setAlpha(f2);
            wk0.e.setAlpha(f2);
            if (h4) {
                f = 1.0f;
            }
            wk0.n.setAlpha(f);
        }
    }
}
