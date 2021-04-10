package defpackage;

import android.view.View;
import org.chromium.base.Callback;

/* renamed from: oM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC4051oM implements View.OnClickListener {
    public final UH0 F;
    public final C3522lG G;

    public View$OnClickListenerC4051oM(UH0 uh0, C3522lG lGVar) {
        this.F = uh0;
        this.G = lGVar;
    }

    public void onClick(View view) {
        UH0 uh0 = this.F;
        ((Callback) uh0.g(M1.c)).onResult(this.G);
    }
}
