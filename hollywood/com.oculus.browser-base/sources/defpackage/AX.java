package defpackage;

import android.gesture.GesturePoint;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.gesturenav.NavigationBubble;

/* renamed from: AX  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class AX implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        boolean z;
        UH0 uh0 = (UH0) obj;
        MX mx = (MX) obj2;
        KH0 kh0 = (KH0) obj3;
        RH0 rh0 = AbstractC5261vV.d;
        boolean z2 = true;
        if (rh0 == kh0) {
            float e = uh0.e(rh0);
            C4241pV0 pv0 = mx.I;
            if (pv0 != null) {
                float f = e - pv0.P;
                if (pv0.isEnabled() && pv0.Q) {
                    float f2 = pv0.I / 3.0f;
                    pv0.P += Math.max(-f2, Math.min(f2, f));
                    float a2 = pv0.a();
                    float f3 = pv0.I;
                    float f4 = a2 - f3;
                    if (a2 > pv0.K) {
                        pv0.K = a2;
                    }
                    double max = (double) (Math.max(0.0f, Math.min(f4, f3 * 2.0f) / f3) / 4.0f);
                    float pow = ((float) (max - Math.pow(max, 2.0d))) * 2.0f;
                    if (pv0.R.getVisibility() != 0) {
                        pv0.R.setVisibility(0);
                    }
                    float min = Math.min(1.0f, Math.abs(a2 / pv0.I));
                    boolean g = pv0.g();
                    if (g != pv0.c0) {
                        pv0.R.b(g);
                        if (g) {
                            pv0.performHapticFeedback(3);
                        }
                    }
                    pv0.c0 = g;
                    int i = pv0.b0;
                    if (i != 0) {
                        if (g) {
                            pv0.R.c(i);
                            pv0.S = pv0.R.getMeasuredWidth();
                        } else {
                            pv0.b();
                        }
                    }
                    int i2 = (int) ((f3 * min) + (pow * f3 * 2.0f));
                    int i3 = pv0.U;
                    if (pv0.a0) {
                        i2 = -i2;
                    }
                    pv0.e((i3 + i2) - pv0.O);
                }
                AbstractC1497Ym0 ym0 = (AbstractC1497Ym0) mx.K.get();
                C4241pV0 pv02 = mx.I;
                ym0.b(e - pv02.P, pv02.a(), mx.I.g());
                mx.I.R.a(!ym0.isHidden(), true);
                if (ym0.e()) {
                    C4241pV0 pv03 = mx.I;
                    pv03.N = false;
                    pv03.f(pv03.d0);
                    return;
                }
                return;
            }
            return;
        }
        RH0 rh02 = AbstractC5261vV.e;
        if (rh02 == kh0) {
            mx.b().b(uh0.e(rh02));
            return;
        }
        SH0 sh0 = AbstractC5261vV.f11481a;
        if (sh0 == kh0) {
            switch (uh0.f(sh0)) {
                case 1:
                    boolean h = uh0.h(AbstractC5261vV.b);
                    int f5 = uh0.f(AbstractC5261vV.f);
                    if (mx.I == null) {
                        C4241pV0 pv04 = new C4241pV0(mx.getContext());
                        mx.I = pv04;
                        pv04.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                        C4241pV0 pv05 = mx.I;
                        pv05.L = new IX(mx, pv05);
                        pv05.M = new JX(mx, pv05);
                    }
                    mx.I.setEnabled(true);
                    C4241pV0 pv06 = mx.I;
                    pv06.a0 = h;
                    NavigationBubble navigationBubble = pv06.R;
                    int i4 = h ? R.drawable.f29570_resource_name_obfuscated_RES_2131230997 : R.drawable.f29540_resource_name_obfuscated_RES_2131230994;
                    navigationBubble.M.setVisibility(0);
                    navigationBubble.M.setImageResource(i4);
                    navigationBubble.b(false);
                    C4241pV0 pv07 = mx.I;
                    pv07.b0 = f5;
                    Runnable runnable = mx.M;
                    if (runnable != null) {
                        pv07.removeCallbacks(runnable);
                        mx.M = null;
                    }
                    C4241pV0 pv08 = mx.I;
                    if (((pv08 == null || pv08.getParent() == null) ? 1 : null) != null) {
                        mx.addView(mx.I);
                    }
                    C4241pV0 pv09 = mx.I;
                    if (pv09.isEnabled() && !pv09.N && pv09.L != null) {
                        pv09.P = 0.0f;
                        pv09.K = 0.0f;
                        pv09.Q = true;
                        pv09.c0 = false;
                        int width = pv09.a0 ? ((View) pv09.getParent()).getWidth() : -pv09.S;
                        pv09.U = width;
                        pv09.O = width;
                        pv09.R.a(false, false);
                    }
                    AbstractC1497Ym0 ym02 = (AbstractC1497Ym0) mx.K.get();
                    if (f5 == 0) {
                        z2 = false;
                    }
                    ym02.c(h, z2);
                    return;
                case 2:
                    GesturePoint gesturePoint = (GesturePoint) uh0.g(AbstractC5261vV.g);
                    mx.b().d(gesturePoint.x, gesturePoint.y);
                    return;
                case 3:
                    boolean h2 = uh0.h(AbstractC5261vV.c);
                    if (mx.I != null) {
                        mx.a();
                        AbstractC1497Ym0 ym03 = (AbstractC1497Ym0) mx.K.get();
                        C4241pV0 pv010 = mx.I;
                        Object[] objArr = (!h2 || !ym03.isHidden()) ? null : 1;
                        if (pv010.Q) {
                            pv010.Q = false;
                            Object[] objArr2 = pv010.K >= ((float) (pv010.S / 3)) ? 1 : null;
                            if (objArr2 != null) {
                                AbstractC3364kK0.g("GestureNavigation.Activated", pv010.a0 ? 1 : 0, 2);
                            }
                            if (!pv010.isEnabled() || !pv010.g()) {
                                pv010.N = false;
                                pv010.T = pv010.O;
                                pv010.e0.reset();
                                pv010.e0.setDuration(500);
                                pv010.e0.setInterpolator(pv010.H);
                                pv010.R.clearAnimation();
                                pv010.R.startAnimation(pv010.e0);
                                if (objArr2 != null) {
                                    AbstractC3364kK0.g("GestureNavigation.Cancelled", pv010.a0 ? 1 : 0, 2);
                                }
                            } else if (objArr != null) {
                                pv010.d(true);
                                AbstractC3364kK0.g("GestureNavigation.Completed", pv010.a0 ? 1 : 0, 2);
                                long currentTimeMillis = System.currentTimeMillis();
                                long j = C4241pV0.F;
                                if (j > 0 && currentTimeMillis - j < 3000 && (z = pv010.a0) != C4241pV0.G) {
                                    AbstractC3364kK0.g("GestureNavigation.Reversed", z ? 1 : 0, 2);
                                }
                                C4241pV0.F = currentTimeMillis;
                                C4241pV0.G = pv010.a0;
                            } else {
                                pv010.N = false;
                                pv010.f(pv010.d0);
                            }
                        }
                        ym03.a();
                        return;
                    }
                    return;
                case 4:
                    mx.b().f();
                    return;
                case 5:
                    if (mx.I != null) {
                        mx.a();
                        mx.I.c();
                        return;
                    }
                    return;
                case 6:
                    mx.b().g();
                    return;
                default:
                    return;
            }
        }
    }
}
