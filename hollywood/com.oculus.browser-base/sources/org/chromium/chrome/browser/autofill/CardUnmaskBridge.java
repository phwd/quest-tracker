package org.chromium.chrome.browser.autofill;

import android.app.Activity;
import android.os.Handler;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CardUnmaskBridge {

    /* renamed from: a  reason: collision with root package name */
    public final long f10610a;
    public final View$OnClickListenerC0521Im b;

    public CardUnmaskBridge(long j, String str, String str2, String str3, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, long j2, WindowAndroid windowAndroid) {
        this.f10610a = j;
        Activity activity = (Activity) windowAndroid.s0().get();
        if (activity == null) {
            this.b = null;
            new Handler().post(new RunnableC5817ym(this));
            return;
        }
        this.b = new View$OnClickListenerC0521Im(activity, this, str, str2, str3, i, i2, z, z2, z4, z5, j2);
    }

    public static CardUnmaskBridge create(long j, String str, String str2, String str3, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, long j2, WindowAndroid windowAndroid) {
        return new CardUnmaskBridge(j, str, str2, str3, i, i2, z, z2, z3, z4, z5, j2, windowAndroid);
    }

    public final void disableAndWaitForVerification() {
        View$OnClickListenerC0521Im im = this.b;
        if (im != null) {
            im.c(false);
            im.e(0);
            im.Y.setVisibility(0);
            im.Z.setText(R.string.f47160_resource_name_obfuscated_RES_2131952033);
            TextView textView = im.Z;
            textView.announceForAccessibility(textView.getText());
            im.a();
        }
    }

    public final void dismiss() {
        View$OnClickListenerC0521Im im = this.b;
        if (im != null) {
            im.d0.b(im.G, 4);
        }
    }

    public final void show(WindowAndroid windowAndroid) {
        View$OnClickListenerC0521Im im = this.b;
        if (im != null) {
            ChromeActivity chromeActivity = (ChromeActivity) windowAndroid.s0().get();
            Objects.requireNonNull(im);
            if (chromeActivity != null) {
                im.e0 = chromeActivity;
                C2746gl0 l0 = chromeActivity.l0();
                im.d0 = l0;
                l0.i(im.G, 0, false);
                im.g();
                im.G.j(AbstractC3258jl0.i, true);
                im.M.addTextChangedListener(im);
                im.M.post(new RunnableC0216Dm(im));
            }
        }
    }

    public final void update(String str, String str2, boolean z) {
        View$OnClickListenerC0521Im im = this.b;
        if (im != null) {
            im.G.m(AbstractC3258jl0.c, str);
            im.f8249J.setText(str2);
            im.H = z;
            if (z && (im.b0 == -1 || im.c0 == -1)) {
                C0460Hm hm = new C0460Hm(im, null);
                Executor executor = AbstractC2032cb.f9616a;
                hm.f();
                ((ExecutorC1463Ya) executor).execute(hm.e);
            }
            im.g();
        }
    }

    public final void verificationFinished(String str, boolean z) {
        View$OnClickListenerC0521Im im = this.b;
        if (im == null) {
            return;
        }
        if (str != null) {
            im.e(8);
            if (z) {
                TextView textView = im.R;
                textView.setText(str);
                textView.setVisibility(0);
                textView.announceForAccessibility(str);
                im.c(true);
                im.b();
                if (!im.H) {
                    im.Q.setVisibility(0);
                    return;
                }
                return;
            }
            im.a();
            im.L.setText(str);
            im.L.setVisibility(0);
            im.L.announceForAccessibility(str);
            return;
        }
        RunnableC0277Em em = new RunnableC0277Em(im);
        if (im.a0 > 0) {
            im.Y.setVisibility(8);
            im.I.findViewById(R.id.verification_success).setVisibility(0);
            im.Z.setText(R.string.f47170_resource_name_obfuscated_RES_2131952034);
            TextView textView2 = im.Z;
            textView2.announceForAccessibility(textView2.getText());
            new Handler().postDelayed(em, im.a0);
            return;
        }
        new Handler().post(em);
    }
}
