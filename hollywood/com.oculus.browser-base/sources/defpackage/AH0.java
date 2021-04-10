package defpackage;

import android.view.View;
import org.chromium.base.Callback;

/* renamed from: AH0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class AH0 implements View.OnClickListener {
    public final Callback F;

    public AH0(Callback callback) {
        this.F = callback;
    }

    public void onClick(View view) {
        this.F.onResult(view);
    }
}
