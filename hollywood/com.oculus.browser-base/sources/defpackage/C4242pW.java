package defpackage;

import org.chromium.chrome.browser.sync.settings.GoogleServicesSettings;

/* renamed from: pW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4242pW extends AbstractC2705gW0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ OE f11070a;
    public final /* synthetic */ GoogleServicesSettings b;

    public C4242pW(GoogleServicesSettings googleServicesSettings, OE oe) {
        this.b = googleServicesSettings;
        this.f11070a = oe;
    }

    @Override // defpackage.AbstractC2705gW0
    public void a() {
        this.f11070a.k1(this.b.W, "clear_data_progress");
    }

    @Override // defpackage.AbstractC2705gW0
    public void b() {
        if (this.f11070a.U()) {
            this.f11070a.e1();
        }
    }
}
