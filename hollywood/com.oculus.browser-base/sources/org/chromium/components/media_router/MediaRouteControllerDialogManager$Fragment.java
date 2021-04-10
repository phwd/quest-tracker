package org.chromium.components.media_router;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaRouteControllerDialogManager$Fragment extends C0565Jf0 {
    public final Handler P0;
    public final C4779sg Q0;
    public AbstractC4949tg R0;
    public AbstractC0750Mg0 S0;

    public MediaRouteControllerDialogManager$Fragment() {
        Handler handler = new Handler();
        this.P0 = handler;
        this.Q0 = new C4779sg();
        handler.post(new RunnableC0687Lf0(this));
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void D0() {
        this.Q0.b(u());
        super.D0();
    }

    @Override // defpackage.C0565Jf0, defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void E0() {
        super.E0();
        this.Q0.a(u());
    }

    @Override // defpackage.C0565Jf0
    public DialogC0504If0 l1(Context context, Bundle bundle) {
        DialogC0504If0 if0 = new DialogC0504If0(context);
        if0.setCanceledOnTouchOutside(true);
        return if0;
    }

    @Override // defpackage.OE
    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.J0) {
            f1(true, true);
        }
        AbstractC4949tg tgVar = this.R0;
        if (tgVar != null) {
            tgVar.d.a();
            this.R0.c.j(this.S0);
            this.R0.e = null;
        }
    }

    public MediaRouteControllerDialogManager$Fragment(AbstractC4949tg tgVar, AbstractC0750Mg0 mg0) {
        this.P0 = new Handler();
        this.Q0 = new C4779sg();
        this.R0 = tgVar;
        this.S0 = mg0;
    }
}
