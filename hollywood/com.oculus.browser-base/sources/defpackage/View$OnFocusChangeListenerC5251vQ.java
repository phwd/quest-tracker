package defpackage;

import android.view.View;

/* renamed from: vQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnFocusChangeListenerC5251vQ implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BQ f11478a;

    public View$OnFocusChangeListenerC5251vQ(BQ bq) {
        this.f11478a = bq;
    }

    public void onFocusChange(View view, boolean z) {
        BQ bq = this.f11478a;
        bq.e0 = false;
        if (!z) {
            if (bq.G.getText().length() > 0) {
                this.f11478a.W = true;
            }
            this.f11478a.R.u0().d(this.f11478a.G);
        }
    }
}
