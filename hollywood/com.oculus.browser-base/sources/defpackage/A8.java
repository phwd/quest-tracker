package defpackage;

import android.view.View;
import androidx.appcompat.widget.AppCompatSpinner;

/* renamed from: A8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A8 extends AbstractView$OnTouchListenerC2013cS {
    public final /* synthetic */ H8 O;
    public final /* synthetic */ AppCompatSpinner P;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public A8(AppCompatSpinner appCompatSpinner, View view, H8 h8) {
        super(view);
        this.P = appCompatSpinner;
        this.O = h8;
    }

    @Override // defpackage.AbstractView$OnTouchListenerC2013cS
    public AbstractC3386kV0 b() {
        return this.O;
    }

    @Override // defpackage.AbstractView$OnTouchListenerC2013cS
    public boolean c() {
        if (this.P.L.b()) {
            return true;
        }
        this.P.b();
        return true;
    }
}
