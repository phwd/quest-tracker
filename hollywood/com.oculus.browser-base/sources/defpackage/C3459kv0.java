package defpackage;

import androidx.preference.Preference;
import org.chromium.components.page_info.PageInfoCookiesPreference;

/* renamed from: kv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3459kv0 implements XE0 {
    public final C4143ov0 F;

    public C3459kv0(C4143ov0 ov0) {
        this.F = ov0;
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        C4143ov0 ov0 = this.F;
        int i = PageInfoCookiesPreference.H0;
        ov0.b.onResult((Boolean) obj);
        return true;
    }
}
