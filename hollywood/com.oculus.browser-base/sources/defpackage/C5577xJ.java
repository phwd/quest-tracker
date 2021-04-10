package defpackage;

import android.widget.AutoCompleteTextView;

/* renamed from: xJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5577xJ implements AutoCompleteTextView.OnDismissListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AJ f11603a;

    public C5577xJ(AJ aj) {
        this.f11603a = aj;
    }

    public void onDismiss() {
        AJ aj = this.f11603a;
        aj.g = true;
        aj.i = System.currentTimeMillis();
        AJ.e(this.f11603a, false);
    }
}
