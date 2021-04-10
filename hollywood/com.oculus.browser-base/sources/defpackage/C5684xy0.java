package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;

/* renamed from: xy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5684xy0 extends AbstractC0896Or {

    /* renamed from: a  reason: collision with root package name */
    public final PasswordSettings f11649a;

    public C5684xy0(PasswordSettings passwordSettings) {
        this.f11649a = passwordSettings;
    }

    @Override // defpackage.AbstractC1528Zb0
    public boolean d(Preference preference) {
        return this.f11649a.o1();
    }
}
