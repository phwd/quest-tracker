package defpackage;

import android.view.View;

/* renamed from: io0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC3096io0 implements View.OnClickListener {
    public final AbstractC3226ja1 F;
    public final AbstractC0124Ca1 G;

    public View$OnClickListenerC3096io0(AbstractC3226ja1 ja1, AbstractC0124Ca1 ca1) {
        this.F = ja1;
        this.G = ca1;
    }

    public void onClick(View view) {
        this.F.S(((AbstractC0246Ea1) this.G).r()).e();
        AbstractC3535lK0.a("MobileNewTabOpened.NewTabTile");
    }
}
