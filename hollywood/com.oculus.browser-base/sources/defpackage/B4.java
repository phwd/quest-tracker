package defpackage;

import android.view.View;
import org.chromium.base.Callback;

/* renamed from: B4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class B4 implements View.OnClickListener {
    public final UH0 F;
    public final C2824hB G;

    public B4(UH0 uh0, C2824hB hBVar) {
        this.F = uh0;
        this.G = hBVar;
    }

    public void onClick(View view) {
        UH0 uh0 = this.F;
        ((Callback) uh0.g(AbstractC4682s4.b)).onResult(this.G);
    }
}
