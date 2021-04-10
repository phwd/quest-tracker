package defpackage;

import org.chromium.chrome.browser.sync.settings.AccountManagementFragment;

/* renamed from: O0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O0 extends AbstractC2705gW0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OE f8590a;
    public final /* synthetic */ AccountManagementFragment b;

    public O0(AccountManagementFragment accountManagementFragment, OE oe) {
        this.b = accountManagementFragment;
        this.f8590a = oe;
    }

    @Override // defpackage.AbstractC2705gW0
    public void a() {
        this.f8590a.k1(this.b.W, "clear_data_progress");
    }

    @Override // defpackage.AbstractC2705gW0
    public void b() {
        if (this.f8590a.U()) {
            this.f8590a.e1();
        }
    }
}
