package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.widget.ScrollView;

/* renamed from: UO  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class UO extends ScrollView {
    public final /* synthetic */ XO F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UO(XO xo, Context context) {
        super(context);
        this.F = xo;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.F.n.b();
    }
}
