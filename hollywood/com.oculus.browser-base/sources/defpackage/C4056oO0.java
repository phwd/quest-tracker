package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;

/* renamed from: oO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4056oO0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final SafeBrowsingSettingsFragment f10546a;

    public C4056oO0(SafeBrowsingSettingsFragment safeBrowsingSettingsFragment) {
        this.f10546a = safeBrowsingSettingsFragment;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        SafeBrowsingSettingsFragment safeBrowsingSettingsFragment = this.f10546a;
        Boolean bool = (Boolean) obj;
        Objects.requireNonNull(safeBrowsingSettingsFragment);
        if (bool.booleanValue()) {
            safeBrowsingSettingsFragment.o1(6);
        } else {
            safeBrowsingSettingsFragment.o1(7);
        }
        if (bool.booleanValue()) {
            N.MzV0f_Xz(0);
            safeBrowsingSettingsFragment.H0.a0(0);
        }
    }
}
