package defpackage;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.os.Handler;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.components.payments.PaymentApp;

/* renamed from: Uk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC1249Uk0 implements AbstractC0576Jj, View.OnClickListener {
    public final PaymentApp F;
    public final UH0 G;
    public final C6005zs H;
    public final C0045As I;

    /* renamed from: J  reason: collision with root package name */
    public final Runnable f9045J;
    public final FingerprintManager K;
    public final CancellationSignal L;
    public final Handler M = new Handler();
    public final boolean N;
    public C5835ys O;
    public Runnable P;
    public boolean Q;
    public boolean R;

    public View$OnClickListenerC1249Uk0(Context context, PaymentApp paymentApp, UH0 uh0, C5835ys ysVar, C6005zs zsVar, C0045As as, Runnable runnable) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        this.L = cancellationSignal;
        this.F = paymentApp;
        this.G = uh0;
        this.O = ysVar;
        this.H = zsVar;
        this.I = as;
        this.f9045J = runnable;
        boolean z = false;
        if (ContextUtils.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService("fingerprint");
            this.K = fingerprintManager;
            if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
                z = true;
            }
            this.N = z;
            if (z) {
                fingerprintManager.authenticate(null, cancellationSignal, 0, new C1188Tk0(this), null);
                uh0.m(AbstractC1310Vk0.k, Integer.valueOf((int) R.drawable.f30270_resource_name_obfuscated_RES_2131231067));
                uh0.m(AbstractC1310Vk0.l, Integer.valueOf((int) R.color.f14520_resource_name_obfuscated_RES_2131100142));
            }
        } else {
            this.K = null;
            this.N = false;
        }
        uh0.j(AbstractC1310Vk0.g, !this.N);
        uh0.m(AbstractC1310Vk0.m, Integer.valueOf(this.N ? R.string.f58290_resource_name_obfuscated_RES_2131953146 : R.string.f58280_resource_name_obfuscated_RES_2131953145));
    }

    public static void a(View$OnClickListenerC1249Uk0 uk0, CharSequence charSequence, Integer num) {
        uk0.M.removeCallbacksAndMessages(null);
        uk0.c(num, charSequence, Integer.valueOf((int) R.drawable.f30060_resource_name_obfuscated_RES_2131231046), Integer.valueOf((int) R.color.f14540_resource_name_obfuscated_RES_2131100144));
        uk0.M.postDelayed(new RunnableC1005Qk0(uk0), 2000);
    }

    public void b(C3679mB0 mb0) {
        if (this.R) {
            this.P = new RunnableC0701Lk0(this, mb0);
            return;
        }
        this.M.removeCallbacksAndMessages(null);
        this.L.cancel();
        c(Integer.valueOf((int) R.string.f58210_resource_name_obfuscated_RES_2131953138), null, Integer.valueOf((int) R.drawable.f29930_resource_name_obfuscated_RES_2131231033), Integer.valueOf((int) R.color.f14530_resource_name_obfuscated_RES_2131100143));
        this.M.postDelayed(new RunnableC0761Mk0(this, mb0), 500);
    }

    public final void c(Integer num, CharSequence charSequence, Integer num2, Integer num3) {
        this.G.j(AbstractC1310Vk0.g, false);
        this.G.m(AbstractC1310Vk0.o, charSequence);
        this.G.m(AbstractC1310Vk0.m, num);
        this.G.j(AbstractC1310Vk0.h, true);
        this.G.m(AbstractC1310Vk0.k, num2);
        this.G.m(AbstractC1310Vk0.l, num3);
        this.G.j(AbstractC1310Vk0.f, false);
        if (!this.N) {
            this.G.j(AbstractC1310Vk0.i, false);
        }
    }

    public void d(AbstractC0579Jk0 jk0, CharSequence charSequence, Integer num) {
        if (this.R) {
            this.P = new RunnableC0822Nk0(this, jk0, charSequence, num);
            return;
        }
        this.M.removeCallbacksAndMessages(null);
        this.L.cancel();
        c(num, charSequence, Integer.valueOf((int) R.drawable.f30060_resource_name_obfuscated_RES_2131231046), Integer.valueOf((int) R.color.f14540_resource_name_obfuscated_RES_2131100144));
        this.M.postDelayed(new RunnableC0883Ok0(this, jk0), 2000);
    }

    public final void e() {
        this.M.removeCallbacksAndMessages(null);
        this.L.cancel();
        this.G.j(AbstractC1310Vk0.g, false);
        this.G.m(AbstractC1310Vk0.o, null);
        this.G.m(AbstractC1310Vk0.m, Integer.valueOf((int) R.string.f58790_resource_name_obfuscated_RES_2131953196));
        if (this.N) {
            this.G.m(AbstractC1310Vk0.k, Integer.valueOf((int) R.drawable.f30270_resource_name_obfuscated_RES_2131231067));
            this.G.m(AbstractC1310Vk0.l, Integer.valueOf((int) R.color.f14530_resource_name_obfuscated_RES_2131100143));
        } else {
            this.G.j(AbstractC1310Vk0.f, true);
            this.G.j(AbstractC1310Vk0.i, false);
        }
        this.R = true;
        this.M.postDelayed(new RunnableC0944Pk0(this), 1000);
        C6005zs zsVar = this.H;
        PaymentApp paymentApp = this.F;
        C0289Es es = zsVar.f11775a;
        Objects.requireNonNull(es);
        paymentApp.k();
        es.f(null, null, paymentApp);
    }

    @Override // defpackage.AbstractC0576Jj
    public void f(float f, float f2) {
        UH0 uh0 = this.G;
        RH0 rh0 = AbstractC1310Vk0.j;
        float e = uh0.e(rh0);
        float f3 = 1.0f;
        if (e != 1.0f && this.Q) {
            float f4 = f * 2.0f;
            if (e < f4) {
                UH0 uh02 = this.G;
                if (f4 <= 1.0f) {
                    f3 = f4;
                }
                uh02.k(rh0, f3);
            }
        }
    }

    @Override // defpackage.AbstractC0576Jj
    public void g(int i) {
        this.Q = true;
        this.G.j(AbstractC1310Vk0.e, false);
    }

    @Override // defpackage.AbstractC0576Jj
    public void h(AbstractC4277pj pjVar) {
    }

    @Override // defpackage.AbstractC0576Jj
    public void i(int i) {
        if (i == 0) {
            this.f9045J.run();
            this.I.f7701a.g(0, "User closed the Payment Request UI.");
        } else if (i == 3) {
            this.G.k(AbstractC1310Vk0.j, 1.0f);
        }
    }

    @Override // defpackage.AbstractC0576Jj
    public void j() {
        this.M.post(new RunnableC1066Rk0(this));
    }

    @Override // defpackage.AbstractC0576Jj
    public void k(int i) {
    }

    public void onClick(View view) {
        if (this.G.h(AbstractC1310Vk0.g)) {
            e();
        }
    }
}
