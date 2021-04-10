package defpackage;

import android.view.View;

/* renamed from: wJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnFocusChangeListenerC5407wJ implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AJ f11538a;

    public View$OnFocusChangeListenerC5407wJ(AJ aj) {
        this.f11538a = aj;
    }

    public void onFocusChange(View view, boolean z) {
        this.f11538a.f9772a.I0.setActivated(z);
        if (!z) {
            AJ.e(this.f11538a, false);
            this.f11538a.g = false;
        }
    }
}
