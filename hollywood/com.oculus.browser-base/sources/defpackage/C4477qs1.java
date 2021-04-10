package defpackage;

import J.N;
import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: qs1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4477qs1 extends AbstractC5079uP {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f11170a;

    public C4477qs1(Profile profile) {
        this.f11170a = profile.g();
    }

    @Override // defpackage.AbstractC5249vP, defpackage.AbstractC5079uP
    public Map c() {
        if (this.f11170a) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("Chrome Variations", N.Mkq2qJev());
        return hashMap;
    }
}
