package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

/* renamed from: kf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3411kf0 extends OE {
    public boolean M0 = false;
    public Dialog N0;
    public C0629Kg0 O0;

    public C3411kf0() {
        i1(true);
    }

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        if (this.M0) {
            DialogC1877bg0 bg0 = new DialogC1877bg0(x());
            this.N0 = bg0;
            l1();
            bg0.d(this.O0);
        } else {
            DialogC3240jf0 m1 = m1(x(), bundle);
            this.N0 = m1;
            l1();
            m1.d(this.O0);
        }
        return this.N0;
    }

    public final void l1() {
        if (this.O0 == null) {
            Bundle bundle = this.K;
            if (bundle != null) {
                this.O0 = C0629Kg0.b(bundle.getBundle("selector"));
            }
            if (this.O0 == null) {
                this.O0 = C0629Kg0.f8380a;
            }
        }
    }

    public DialogC3240jf0 m1(Context context, Bundle bundle) {
        return new DialogC3240jf0(context, 0);
    }

    public void n1(C0629Kg0 kg0) {
        if (kg0 != null) {
            l1();
            if (!this.O0.equals(kg0)) {
                this.O0 = kg0;
                Bundle bundle = this.K;
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putBundle("selector", kg0.b);
                U0(bundle);
                Dialog dialog = this.N0;
                if (dialog == null) {
                    return;
                }
                if (this.M0) {
                    ((DialogC1877bg0) dialog).d(kg0);
                } else {
                    ((DialogC3240jf0) dialog).d(kg0);
                }
            }
        } else {
            throw new IllegalArgumentException("selector must not be null");
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void onConfigurationChanged(Configuration configuration) {
        this.i0 = true;
        Dialog dialog = this.N0;
        if (dialog != null) {
            if (this.M0) {
                ((DialogC1877bg0) dialog).e();
                return;
            }
            DialogC3240jf0 jf0 = (DialogC3240jf0) dialog;
            jf0.getWindow().setLayout(AbstractC0991Qf0.a(jf0.getContext()), -2);
        }
    }
}
