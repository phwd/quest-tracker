package defpackage;

import J.N;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.ViewStub;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;

/* renamed from: tr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4983tr0 implements Runnable {
    public final C6003zr0 F;

    public RunnableC4983tr0(C6003zr0 zr0) {
        this.F = zr0;
    }

    public void run() {
        C6003zr0 zr0 = this.F;
        Objects.requireNonNull(zr0);
        AbstractC3535lK0.a("OfflineIndicator.Shown");
        zr0.o = TimeUnit.MICROSECONDS.toMillis(N.MklbOJun());
        zr0.m = SystemClock.elapsedRealtime();
        int color = zr0.f11774a.getResources().getColor(R.color.f14410_resource_name_obfuscated_RES_2131100131);
        int color2 = zr0.f11774a.getResources().getColor(R.color.f11570_resource_name_obfuscated_RES_2131099847);
        Drawable drawable = zr0.f11774a.getDrawable(R.drawable.f29780_resource_name_obfuscated_RES_2131231018);
        int color3 = zr0.f11774a.getResources().getColor(R.color.f11320_resource_name_obfuscated_RES_2131099822);
        Z11 z11 = zr0.b;
        String string = zr0.f11774a.getString(R.string.f56490_resource_name_obfuscated_RES_2131952966);
        if (!z11.c) {
            z11.c = true;
            if (!z11.k) {
                ViewResourceFrameLayout viewResourceFrameLayout = (ViewResourceFrameLayout) ((ViewStub) z11.i.findViewById(R.id.status_indicator_stub)).inflate();
                int id = viewResourceFrameLayout.getId();
                z11.e = id;
                z11.b.H = id;
                z11.f = viewResourceFrameLayout.G;
                S11 s11 = new S11(z11);
                Map c = UH0.c(AbstractC4507r21.j);
                SH0 sh0 = AbstractC4507r21.c;
                JH0 jh0 = new JH0(null);
                jh0.f8282a = 8;
                HashMap hashMap = (HashMap) c;
                hashMap.put(sh0, jh0);
                QH0 qh0 = AbstractC4507r21.d;
                GH0 gh0 = new GH0(null);
                gh0.f8081a = false;
                hashMap.put(qh0, gh0);
                UH0 uh0 = new UH0(c, null);
                ZH0.a(uh0, new C4848t21(viewResourceFrameLayout, z11.b), new T11());
                View$OnLayoutChangeListenerC4337q21 q21 = z11.f9314a;
                U11 u11 = new U11(z11);
                V11 v11 = new V11(z11);
                W11 w11 = new W11(viewResourceFrameLayout);
                q21.F = uh0;
                q21.K = u11;
                q21.L = v11;
                q21.N = s11;
                q21.O = w11;
                viewResourceFrameLayout.addOnLayoutChangeListener(q21);
                z11.d = new X11(z11, viewResourceFrameLayout);
                z11.k = true;
            }
            View$OnLayoutChangeListenerC4337q21 q212 = z11.f9314a;
            q212.K.run();
            q212.V = false;
            q212.U = 0;
            RunnableC1594a21 a21 = new RunnableC1594a21(q212, string, drawable, color, color2, color3);
            int intValue = ((Integer) q212.I.get()).intValue();
            if (intValue == color) {
                a21.run();
                return;
            }
            ValueAnimator ofInt = ValueAnimator.ofInt(intValue, color);
            q212.P = ofInt;
            ofInt.setEvaluator(new ArgbEvaluator());
            q212.P.setInterpolator(G30.c);
            q212.P.setDuration(200L);
            q212.P.addUpdateListener(new C1774b21(q212));
            q212.P.addListener(new C3140j21(q212, a21));
            q212.P.start();
        }
    }
}
