package defpackage;

import android.view.View;

/* renamed from: gS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2696gS implements AbstractC3841n80 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractComponentCallbacksC3550lS f9999a;

    public C2696gS(AbstractComponentCallbacksC3550lS lSVar) {
        this.f9999a = lSVar;
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        View view;
        if (j80 == EnumC3157j80.ON_STOP && (view = this.f9999a.k0) != null) {
            view.cancelPendingInputEvents();
        }
    }
}
