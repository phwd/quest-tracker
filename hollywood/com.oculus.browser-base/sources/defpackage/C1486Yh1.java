package defpackage;

import android.content.Context;
import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.suggestions.mostvisited.MostVisitedSitesBridge;

/* renamed from: Yh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1486Yh1 implements AbstractC1181Th1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0824Nl0 f9290a;

    public C1486Yh1(Context context, Profile profile, View$OnClickListenerC5098uY0 uy0) {
        Objects.requireNonNull(F31.a());
        this.f9290a = new MostVisitedSitesBridge(profile);
    }
}
