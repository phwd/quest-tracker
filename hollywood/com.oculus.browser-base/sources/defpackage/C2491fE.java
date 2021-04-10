package defpackage;

import android.view.View;
import android.view.ViewStub;
import org.chromium.base.Callback;

/* renamed from: fE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2491fE implements AbstractC2435ev1 {

    /* renamed from: a  reason: collision with root package name */
    public final ViewStub f9907a;
    public C5232vH0 b = new C5232vH0();

    public C2491fE(ViewStub viewStub) {
        this.f9907a = viewStub;
        viewStub.setOnInflateListener(new ViewStub$OnInflateListenerC2320eE(this));
    }

    @Override // defpackage.AbstractC2435ev1
    public void a(Callback callback) {
        if (this.b.d()) {
            callback.onResult((View) this.b.b);
        } else {
            this.b.g(callback);
        }
    }

    @Override // defpackage.AbstractC2435ev1
    public void b() {
        this.f9907a.inflate();
    }
}
