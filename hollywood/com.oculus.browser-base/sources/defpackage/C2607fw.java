package defpackage;

import android.view.View;
import android.view.Window;

/* renamed from: fw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2607fw implements AbstractC3841n80 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractActivityC3119iw f9966a;

    public C2607fw(AbstractActivityC3119iw iwVar) {
        this.f9966a = iwVar;
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        if (j80 == EnumC3157j80.ON_STOP) {
            Window window = this.f9966a.getWindow();
            View peekDecorView = window != null ? window.peekDecorView() : null;
            if (peekDecorView != null) {
                peekDecorView.cancelPendingInputEvents();
            }
        }
    }
}
