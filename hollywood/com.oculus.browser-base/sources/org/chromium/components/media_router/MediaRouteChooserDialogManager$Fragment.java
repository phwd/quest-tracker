package org.chromium.components.media_router;

import J.N;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.AdapterView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaRouteChooserDialogManager$Fragment extends C3411kf0 {
    public static final /* synthetic */ int P0 = 0;
    public final Handler Q0;
    public final C4779sg R0;
    public AbstractC4949tg S0;
    public boolean T0;

    public MediaRouteChooserDialogManager$Fragment() {
        Handler handler = new Handler();
        this.Q0 = handler;
        this.R0 = new C4779sg();
        handler.post(new RunnableC3582lf0(this));
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void D0() {
        this.R0.b(u());
        super.D0();
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void E0() {
        super.E0();
        this.R0.a(u());
    }

    @Override // defpackage.C3411kf0
    public DialogC3240jf0 m1(Context context, Bundle bundle) {
        DialogC3924nf0 nf0 = new DialogC3924nf0(this, context, this.D0);
        nf0.setCanceledOnTouchOutside(true);
        return nf0;
    }

    public final void o1(AdapterView adapterView, int i) {
        C2392eh0 eh0 = (C2392eh0) adapterView.getItemAtPosition(i);
        if (eh0 != null && eh0.g) {
            C1363Wh0 a2 = C1363Wh0.a(eh0);
            AbstractC4949tg tgVar = this.S0;
            BrowserMediaRouterDialogController browserMediaRouterDialogController = tgVar.d;
            String str = tgVar.f11358a;
            browserMediaRouterDialogController.b = null;
            N.MUhSLnzh(browserMediaRouterDialogController.f10850a, browserMediaRouterDialogController, str, a2.f9167a);
            this.T0 = true;
            f1(false, false);
        }
    }

    @Override // defpackage.OE
    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.J0) {
            f1(true, true);
        }
        if (!this.T0) {
            this.S0.d.a();
        }
    }

    public MediaRouteChooserDialogManager$Fragment(AbstractC4949tg tgVar) {
        this.Q0 = new Handler();
        this.R0 = new C4779sg();
        this.S0 = tgVar;
    }
}
