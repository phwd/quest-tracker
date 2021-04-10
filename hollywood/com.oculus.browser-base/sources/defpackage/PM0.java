package defpackage;

import android.app.Activity;
import android.view.View;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: PM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class PM0 implements View.OnClickListener {
    public final SM0 F;
    public final C4765sb0 G;
    public final Activity H;
    public final Callback I;

    public PM0(SM0 sm0, C4765sb0 sb0, Activity activity, Callback callback) {
        this.F = sm0;
        this.G = sb0;
        this.H = activity;
        this.I = callback;
    }

    public void onClick(View view) {
        SM0 sm0 = this.F;
        C4765sb0 sb0 = this.G;
        Activity activity = this.H;
        Callback callback = this.I;
        Objects.requireNonNull(sm0);
        sm0.a(sb0.b.f(ZM0.f), activity, callback);
    }
}
