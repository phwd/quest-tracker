package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Map;

/* renamed from: DP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DP0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        ViewGroup viewGroup;
        UH0 uh0 = (UH0) obj;
        NP0 np0 = (NP0) obj2;
        KH0 kh0 = (KH0) obj3;
        if (MP0.f8474a != kh0 && MP0.b != kh0) {
            OH0 oh0 = MP0.c;
            int i = 0;
            if (oh0 == kh0) {
                View view = (View) uh0.g(oh0);
                Map map = AbstractC2417ep1.f9884a;
                ViewGroup viewGroup2 = (ViewGroup) np0.getParent();
                if (viewGroup2 != null) {
                    viewGroup2.removeView(np0);
                }
                if (view != null) {
                    boolean h = uh0.h(MP0.d);
                    while (true) {
                        ViewParent parent = view.getParent();
                        viewGroup = np0.F;
                        if (parent == viewGroup) {
                            break;
                        }
                        view = (View) view.getParent();
                    }
                    if (h) {
                        AbstractC2417ep1.g(viewGroup, np0, view);
                    } else {
                        AbstractC2417ep1.h(viewGroup, np0, view, false);
                    }
                    ((ViewGroup.MarginLayoutParams) np0.getLayoutParams()).topMargin = uh0.f(MP0.f8474a);
                }
            } else if (MP0.d != kh0) {
                OH0 oh02 = MP0.f;
                if (oh02 == kh0) {
                    if (uh0.g(oh02) != null) {
                        np0.setOnClickListener(new OP0(uh0));
                    }
                } else if (MP0.e != kh0) {
                    RH0 rh0 = MP0.g;
                    if (rh0 == kh0) {
                        float e = uh0.e(rh0);
                        np0.setAlpha(e);
                        if (e <= 0.0f) {
                            i = 8;
                        }
                        if (np0.getVisibility() != i) {
                            np0.setVisibility(i);
                            return;
                        }
                        return;
                    }
                    SH0 sh0 = MP0.h;
                    if (sh0 != kh0) {
                        TH0 th0 = MP0.i;
                        if (th0 == kh0) {
                            if (uh0.g(th0) != null) {
                                np0.setBackgroundDrawable((Drawable) uh0.g(th0));
                            }
                        } else if (MP0.j != kh0) {
                            QH0 qh0 = MP0.k;
                        }
                    } else if (uh0.g(MP0.i) == null) {
                        np0.setBackgroundColor(uh0.f(sh0));
                    }
                }
            }
        }
    }
}
