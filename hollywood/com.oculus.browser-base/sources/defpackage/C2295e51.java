package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.sync.settings.SyncPromoPreference;

/* renamed from: e51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2295e51 implements AbstractC5094uW0 {

    /* renamed from: a  reason: collision with root package name */
    public final SyncPromoPreference f9830a;

    public C2295e51(SyncPromoPreference syncPromoPreference) {
        this.f9830a = syncPromoPreference;
    }

    @Override // defpackage.AbstractC5094uW0
    public void onDismiss() {
        SyncPromoPreference syncPromoPreference = this.f9830a;
        Objects.requireNonNull(syncPromoPreference);
        NU0.f8549a.m("settings_personalized_signin_promo_dismissed", true);
        syncPromoPreference.b0();
    }
}
