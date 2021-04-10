package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;

/* renamed from: vy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5344vy0 extends AbstractC0896Or {

    /* renamed from: a  reason: collision with root package name */
    public final PasswordSettings f11508a;

    public C5344vy0(PasswordSettings passwordSettings) {
        this.f11508a = passwordSettings;
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        return this.f11508a.r1();
    }
}
