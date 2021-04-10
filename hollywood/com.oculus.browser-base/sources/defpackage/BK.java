package defpackage;

import android.view.View;

/* renamed from: BK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BK implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DK f7730a;

    public BK(DK dk) {
        this.f7730a = dk;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            this.f7730a.O = true;
            return;
        }
        DK dk = this.f7730a;
        if (dk.O) {
            dk.c(true ^ dk.G.e());
        }
    }
}
