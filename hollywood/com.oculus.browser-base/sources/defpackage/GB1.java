package defpackage;

import android.app.Activity;
import android.content.Intent;

/* renamed from: GB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class GB1 extends XE {
    public final /* synthetic */ Intent F;
    public final /* synthetic */ Activity G;
    public final /* synthetic */ int H;

    public GB1(Intent intent, Activity activity, int i) {
        this.F = intent;
        this.G = activity;
        this.H = i;
    }

    @Override // defpackage.XE
    public final void a() {
        Intent intent = this.F;
        if (intent != null) {
            this.G.startActivityForResult(intent, this.H);
        }
    }
}
