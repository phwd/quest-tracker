package defpackage;

import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;

/* renamed from: Tb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1162Tb0 extends AbstractC2705gW0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OE f8967a;
    public final /* synthetic */ ManageSyncSettings b;

    public C1162Tb0(ManageSyncSettings manageSyncSettings, OE oe) {
        this.b = manageSyncSettings;
        this.f8967a = oe;
    }

    @Override // defpackage.AbstractC2705gW0
    public void a() {
        this.f8967a.k1(this.b.w(), "clear_data_progress");
    }

    @Override // defpackage.AbstractC2705gW0
    public void b() {
        if (this.f8967a.U()) {
            this.f8967a.e1();
        }
    }
}
