package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

/* renamed from: Jf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0565Jf0 extends OE {
    public boolean M0 = false;
    public Dialog N0;
    public C0629Kg0 O0;

    public C0565Jf0() {
        i1(true);
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void E0() {
        super.E0();
        Dialog dialog = this.N0;
        if (dialog != null && !this.M0) {
            ((DialogC0504If0) dialog).g(false);
        }
    }

    @Override // defpackage.OE
    public Dialog g1(Bundle bundle) {
        if (this.M0) {
            DialogC5460wg0 wg0 = new DialogC5460wg0(x());
            this.N0 = wg0;
            wg0.h(this.O0);
        } else {
            this.N0 = l1(x(), bundle);
        }
        return this.N0;
    }

    public DialogC0504If0 l1(Context context, Bundle bundle) {
        return new DialogC0504If0(context);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void onConfigurationChanged(Configuration configuration) {
        this.i0 = true;
        Dialog dialog = this.N0;
        if (dialog == null) {
            return;
        }
        if (this.M0) {
            ((DialogC5460wg0) dialog).k();
        } else {
            ((DialogC0504If0) dialog).v();
        }
    }
}
