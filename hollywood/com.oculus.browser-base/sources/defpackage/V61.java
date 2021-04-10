package defpackage;

import android.view.View;
import org.chromium.base.Callback;

/* renamed from: V61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class V61 implements View.OnClickListener {
    public final Callback F;

    public V61(Callback callback) {
        this.F = callback;
    }

    public void onClick(View view) {
        O4 o4 = new C1789b71(view.getContext(), view, this.F).d;
        if (o4 != null) {
            o4.d();
        }
    }
}
