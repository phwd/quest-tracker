package defpackage;

import android.view.View;
import org.chromium.base.Callback;

/* renamed from: zH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC5912zH0 implements View.OnClickListener {
    public final Callback F;

    public View$OnClickListenerC5912zH0(Callback callback) {
        this.F = callback;
    }

    public void onClick(View view) {
        this.F.onResult(view);
    }
}